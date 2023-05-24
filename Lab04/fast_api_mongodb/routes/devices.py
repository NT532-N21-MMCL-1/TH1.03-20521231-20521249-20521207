import datetime
from faulthandler import disable
import threading
from fastapi import APIRouter
from models.devices import Devices 
from config.db import conn 
from schemas.devices import serializeDict, serializeList
from bson import ObjectId
from fastapi.responses import JSONResponse
enable_time =None
devices = APIRouter()
#ham check enable 
def disable_callback():
    global enable_time
    enable_time = None
    print("Disabling at: ", datetime.now())

def process_data():
    global enable_time
    enable_time = datetime.now()
    print("Enable at: ", enable_time)
    
    threading.Timer(20,disable.callback).start()
     
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
    conn.local.devices.insert_one(dict(device))
    
    return  serializeList(conn.local.devices.find())

# @devices.put('/device/{device_id}')
# async def update_device(id,devices: Devices):
#     conn.local.devices.find_one_and_update({"device_id":device_id},{
#         "$set":dict(devices)
#     })
#     return serializeDict(conn.local.devices.find_one({"device_id":device_id}))

# @devices.delete('/device/{device_id}')
# async def delete_device(id,devices: Devices):

