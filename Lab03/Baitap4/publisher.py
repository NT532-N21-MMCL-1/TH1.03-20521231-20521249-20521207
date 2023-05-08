import paho.mqtt.client as mqtt
import time

client = mqtt.Client()
client.connect("172.31.11.0", 1883)

while True:
    led_state = input("Nhap trang thai LED: ")
    client.publish("mmcl/nhom3/led", led_state)
    time.sleep(0.5)

