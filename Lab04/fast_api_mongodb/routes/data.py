from fastapi import APIRouter
from models.data import Data 
from models.devices import Devices
from config.db import conn 
from schemas.devices import serializeDict, serializeList
from bson import ObjectId
data = APIRouter() 
#hàm datas

@data.get('/data/{device_id}')
async def get_data(device_id):
    return serializeDict(conn.local.data.find_one({"device_id":device_id}))
@data.get('/data')
async def find_all_devices():
    return serializeList(conn.local.data.find())
#Hàm post dữ liệu
@data.post('/data')     
async def create_data(data: Data):
    conn.local.data.insert_one(dict(data))
    return serializeList(conn.local.data.find())

# @data.post('/device')     
# async def create_device(device: Devices):
#     conn.local.devices.insert_one(dict(device))
#     return serializeList(conn.local.devices.find())