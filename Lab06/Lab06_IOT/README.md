# ANDROID CODE

Trong thư mục Adapter gồm 3 file: 

- ApiService để GET dữ liệu từ FastAPI
- DeviceAdapter dùng để hiển thị dữ liệu call được lên ListView cho màn hình Dashboard
- LogAdapter dùng để hiển thị dữ liệu caall được lên RecyclerView cho màn hình Log

Trong thư mục Fragment gồm 4 thư mục con là Chart, Dashboar, Log, Fragment chứa file source code của 4 màn hình:

Chart folder gồm 4 file: ChartFragment - chia thành 3 fragment con
- TempChartFragment, HumidityChartFragment, LightChartFragmnet: Call API hiển thị dữ liệu lên biểu đồ

Dashboard folder chứa Dashboard file call API hiển thị thông tin thiết bị

Log folder chứa Log file call API hiển thị dữ liệu về thiết bị và giá trị lên màn hình Log

Main folder gồm 4 file:
- MainFragment: Code MQTT điều khiển đèn LED
- TemperatureFragment, HumidityFragment, LightFragment: Call API hiển thị giá trị nhiệt độ, độ ẩm, ánh sáng 

Thư mục chính chứa các class cho việc call API
- ApiRetrofit để lấy địa chỉ từ FastApi Server
- DataReceived lưu thông tin nhiệt độ độ ẩm, ánh sáng, và thời gian nhận dữ liệu được call trong màn hình Main 
- DataReceived là class con của class Gauge, trong class Gauge có lưu một số thuộc tính dùng để call dữ liệu cho Dashboard
- 

