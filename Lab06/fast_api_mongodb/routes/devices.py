from ast import List
import datetime
from faulthandler import disable
import json
import threading
import time
from pydantic import BaseModel
from fastapi import APIRouter, BackgroundTasks
from models.data import Data 
from models.devices import Devices 
from config.db import conn 
from schemas.devices import serializeDict, serializeList
from bson import ObjectId
from fastapi.responses import JSONResponse
enable_time =None
import numpy as np
import joblib
model = joblib.load('random_forest_model.joblib')

devices = APIRouter()
#ham check enable 
def check_device_status():
    while True:
        # device: Devices
        
        # Lấy danh sách các devices từ MongoDB
        devices = list(conn.local.devices.find())
        
        # Kiểm tra và cập nhật trạng thái của từng device
        for device in devices:
            
            enable = device["enable"]
            receive_time = device["data_received"]["receive_time"]
            current_time = datetime.datetime.now()
            
                
            time_since_last_receive_data = current_time.timestamp() - receive_time
            
            if enable == True and time_since_last_receive_data > 20 :
                # Cập nhật trạng thái của device thành "offline"
                conn.local.devices.update_one(
                    {"device_id": device["device_id"]},
                    {"$set": {"enable": False,"last_disconnection_time":receive_time}}
                )
      
        time.sleep(15)  # Đợi 1 phút để kiểm tra lại

#hàm này là để get tất cả các tên của device
@devices.get('/device')
async def find_all_devices():
    return serializeList(conn.local.devices.find())


#hàm này chỉ để create 1 device mới thôi 
@devices.post('/device')
async def create_device(device: Devices):
    #thời gian khởi tạo một device mới
    device.enable = False
    device.create_time = datetime.datetime.now().timestamp()
    #lưu vào bảng device
    conn.local.devices.insert_one(device.dict())
    #return lại nhưng device có trong bảng
    return  serializeList(conn.local.devices.find())

#hàm này để cập nhật giá trị của device và thêm data vào bảng deivce data
@devices.put('/device/{device_id}')
async def update_device(device_id,device: Devices, background_tasks: BackgroundTasks):
    #thoi gian nhan duoc data
    device.data_received.receive_time = datetime.datetime.now().timestamp()
    device.data_received.device_id = device_id
    #update device database devices
    update_data =  device.data_received.dict(exclude_unset=True)
    conn.local.devices.find_one_and_update({"device_id":device_id},{
        #update trang thai va thoi gian ket noi cuoi cung
        "$set": {"data_received": update_data,"enable":True, "last_connection_time":datetime.datetime.now().timestamp()}
        
    })
    #them data vao database devcie_data
    conn.local.device_data.insert_one(update_data)
    #background_tasks.add_task(check_device_status)
    background_tasks.add_task(check_device_status)  
    #ham return data moi duoc update
    devices = conn.local.devices.find({"device_id": device_id})
    return [serializeDict(device) for device in devices]

#ham goi de ve bang
@devices.get('/data/{device_id}')
async def get_data(device_id):
    return serializeList(conn.local.device_data.find({"device_id":device_id}))

@devices.on_event("startup")
async def startup_event():
    background_tasks = BackgroundTasks()
    background_tasks.add_task(check_device_status)
    devices.background_tasks = background_tasks

class WeatherData(BaseModel):
    humidity: float
    temperature: float

# Định nghĩa hàm POST để xử lý dữ liệu
@devices.post("/predict_windspeed")
def predict_windspeed(data:WeatherData):
    # Lấy humidity và temperature từ dữ liệu đầu vào
    input_data = data.json()
    input_dict = json.loads(input_data)
    humidity = input_dict['humidity']
    temperature = input_dict['temperature']
    weatherdata = [temperature, humidity]
    
    # Sử dụng mô hình đã train để dự đoán windspeed
    windspeed = model.predict([weatherdata])  # Thay "predict_windspeed_from_model" bằng hàm dự đoán của bạn
    print(windspeed)
    # Trả về giá trị windspeed dự đoán
    return {"windspeed":windspeed[0]}
