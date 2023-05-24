from fastapi import FastAPI
from routes.devices import devices 
from routes.data    import data
from pydantic import BaseModel
app = FastAPI()
# app.include_router(data)
app.include_router(devices)
