from fastapi import FastAPI
from typing import Any
from pydantic import BaseModel

class Data(BaseModel):
    distance: Any
    light: Any

class ApiResponse(BaseModel):
    error: bool
    message: str
    data: Data

app = FastAPI()

@app.get("/api")
async def read_api():
    data = Data(distance=20, light=50)
    response = ApiResponse(error=False, message="This is a message of API", data=data)
    return response

@app.post("/api")
async def create_api(item: Data):
    response = ApiResponse(error=False, message="This is a message of API", data=item)
    return response
