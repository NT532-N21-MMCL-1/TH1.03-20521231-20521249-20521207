from fastapi import FastAPI
from routes.devices import devices 
from fastapi import WebSocket
from pydantic import BaseModel
import json

app = FastAPI()
app.include_router(devices)

# connected_websockets = set()

# @app.websocket("/ws")
# async def websocket_endpoint(websocket: WebSocket):
#     await websocket.accept()
#     connected_websockets.add(websocket)

#     while True:
#         # Lấy dữ liệu từ nguồn dữ liệu thời gian thực (ví dụ: database, cảm biến, ...)
#         # Gửi dữ liệu qua WebSocket
#         data = {
#             "_id": "6471a5fbb4ac8288892a3fc2",
#             "device_id": "123",
#             "temperature": 25,
#             "humidity": 60,
#             "light": 100,
#             "receive_time": "2023-05-27T13:40:59.064000",
#             "receive_time_ts": 1685169659.06477
#         }
#         await websocket.send_text(json.dumps(data))

# @app.on_event("shutdown")
# async def shutdown():
#     for websocket in connected_websockets:
#         await websocket.close()