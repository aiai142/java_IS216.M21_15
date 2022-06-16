# java_IS216.M21_15
# QUẢN LÍ TRANG TRẠI NÔNG SẢN - FRESHFOOD
Ngày nay nhu cầu về nông sản sạch ngày càng tăng. Trang trại FreshFood quyết định mở rộng quy mô kinh doanh bằng cách loại bỏ các quy trình quản lý thủ công và tự động hóa chúng thông qua hệ thống quản lý, đồng thời kết hợp dịch vụ bán hàng trực tuyến nhằm tăng thêm lợi nhuận cho doanh nghiệp.
## Thành viên tham gia
| Tên                        | MSSV     |
|----------------------------|----------|
| Phạm Lê Dịu Ái             | 20520556 |
| Trần Ngọc Linh             | 20521538 |
| Đỗ Quỳnh Chi               | 20520415 |
| Lâm Võ Khánh My (tester)   | 20520912 |
## Các chức năng có trong ứng dụng
> * Đăng kí, đăng nhập, quên mật khẩu.
> * Phần quản lí của nhân viên và admin như sau: 
>> * Quản lí nông sản
>>> * Toàn bộ thông tin về nông sản được quản lý trên hệ thống. Mỗi loại nông sản có một mã nông sản riêng, tên nông sản, giá, được trồng ở nông trại nào, lưu trữ ở kho nào với số lượng tồn kho là bao nhiêu.
>>> * Mỗi loại nông sản sẽ được chứa trong một kho tùy vào cách bảo quản nông sản đó.
>>> * Nhân viên được phép thêm, xóa, cập nhật, tra cứu thông tin nông sản.
>> * Quản lí trang trại
>>> * Nhân viên và admin có quyền thêm, xóa sửa trang trại. Mỗi trang trại có một mã trang trại riêng, tên trang trại, địa chỉ trang trại. 
>> * Quản lí bán hàng
>>> * Hiển thị danh sách đơn hàng sau mỗi lần tạo đơn hàng mới. Bao gồm mã đơn hàng, mã khách hàng, ngày tạo đơn, mã vận chuyển, tổng tiền, mã giảm giá, thành tiền, trạng thái vận chuyển.
>>> * Nếu là khách hàng vãng lai chưa đăng kí thành viên trên hệ thống thì nhân viên tạo đơn hàng mới. 
>> * Quản lí nguyên vật liệu
>>> * Toàn bộ thông tin về nguyên vật liệu được quản lý trên hệ thống. Mỗi loại nguyên vật liệu có một mã riêng, tên nguyên vật liệu, số lượng lưu trữ và lưu trữ ở kho nào.
>>> * Mỗi loại nguyên vật liệu sẽ được chứa trong một kho.
>>> * Nhân viên được phép thêm, xóa, cập nhật, tra cứu thông tin nguyên vật liệu. 
>> * Quản lí nhà cung cấp
>>> * Khi trang trại đặt mua nguyên vật liệu từ một nhà cung cấp, thông tin nhà cung cấp này sẽ được lưu trữ trong hệ thống để thuận tiện cho những lần đặt mua tiếp theo.
>>> * Nhân viên được phép thêm, xóa, cập nhật, tra cứu thông tin nhà cung cấp.
