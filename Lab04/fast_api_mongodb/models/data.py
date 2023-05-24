from pydantic import BaseModel

class Data(BaseModel):
    device_id: str 
    temperature: float
    humidity: float
    light: int
    receive_time: int