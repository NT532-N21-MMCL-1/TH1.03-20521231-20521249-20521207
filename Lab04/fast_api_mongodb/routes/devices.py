import datetime
from faulthandler import disable
import json
import threading
from fastapi import APIRouter
from models.data import Data 
from models.devices import Devices 
from config.db import conn , collection
from schemas.devices import serializeDict, serializeList
from bson import ObjectId
from fastapi.responses import JSONResponse
enable_time =None
devices = APIRouter()
#ham check enable 
def check_device_status():
    while True:
        # Lấy danh sách các devices từ MongoDB
        devices = list(conn.devices.find())

        # Kiểm tra và cập nhật trạng thái của từng device
        for device in devices:
            last_connection_time = device.get("last_connection_time", 0)
            last_disconnection_time = device.get("last_disconnection_time", 0)
            enable = device.get("enable", False)
            
            current_time = time.time()
            time_since_last_connection = current_time - last_connection_time
            time_since_last_disconnection = current_time - last_disconnection_time
            
            if enable and time_since_last_connection > 60 and time_since_last_disconnection > 60:
                # Cập nhật trạng thái của device thành "offline"
                collection.update_one(
                    {"device_id": device["device_id"]},
                    {"$set": {"enable": False}}
                )
        
        time.sleep(60)  # Đợi 1 phút để kiểm tra lại
#hàm Devices
@devices.get('/device')
async def find_all_devices():
    return serializeList(conn.local.devices.find())

# @devices.get('/{id}')
# async def find_one_device(id):
#     return serializeDict(conn.local.devices.find_one({"_id":ObjectId(id)}))
 
@devices.get('/device/{device_id}')
async def get_device_byid(device_id):
    devices = conn.local.devices.find({"device_id": device_id})
    return [serializeDict(device) for device in devices]

#Check thiet bi cuoi cung dang nhap vao
@devices.get('/device/get_last_device/{device_id}')
async def get_last_device(device_id):
    device = conn.local.devices.find_one({"device_id": device_id})
    return serializeDict(device)

@devices.post('/device')
async def create_device(device: Devices):
    device.data_received.receive_time = datetime.datetime.now()
    conn.local.devices.insert_one(device.dict())
    return  serializeList(conn.local.devices.find())

@devices.put('/device/{device_id}')
async def update_device(id,devices: Devices):
    conn.local.devices.find_one_and_update({"device_id":device_id},{
        "$set":dict(devices)
    })
    return serializeDict(conn.local.devices.find_one({"device_id":device_id}))

# @devices.delete('/device/{device_id}')
# async def delete_device(id,devices: Devices):

