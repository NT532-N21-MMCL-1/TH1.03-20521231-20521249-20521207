from fastapi import FastAPI
from routes.devices import devices 
from pydantic import BaseModel
app = FastAPI()
app.include_router(devices)
