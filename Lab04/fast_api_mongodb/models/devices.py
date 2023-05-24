from pydantic import BaseModel
from models.data import Data
class Devices(BaseModel):
    device_name :str
    device_id: str 
    device_ip: str 
    temperature: float
    humidity: float
    light: float 
    receive_time: int
    enable: str 
    device_id: str 
    last_connection_time: str
    last_disconnection_time: str
