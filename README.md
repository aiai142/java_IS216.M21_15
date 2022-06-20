# java_IS216.M21_15
# QUẢN LÍ TRANG TRẠI NÔNG SẢN - FRESHFOOD
Ngày nay nhu cầu về nông sản sạch ngày càng tăng. Trang trại FreshFood quyết định mở rộng quy mô kinh doanh bằng cách loại bỏ các quy trình quản lý thủ công và tự động hóa chúng thông qua hệ thống quản lý, đồng thời kết hợp dịch vụ bán hàng trực tuyến nhằm tăng thêm lợi nhuận cho doanh nghiệp.
## Thành viên tham gia
| Tên                        | MSSV     |
|----------------------------|----------|
| Phạm Lê Dịu Ái             | 20520368 |
| Trần Ngọc Linh             | 20521538 |
| Đỗ Quỳnh Chi               | 20520415 |
| Lâm Võ Khánh My (tester)   | 20520912 |
## Mô hình ERD
![image](https://github.com/aiai142/java_IS216.M21_15/blob/main/ERD_FRESHFOOD.png)
## Các chức năng có trong ứng dụng
#### Chức năng chung
> * Đăng kí, đăng nhập, quên mật khẩu.
#### Phần quản lí của nhân viên và admin 
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
>> * Quản lí khách hàng
>>> * Quản lí khách hàng đang là thành viên của hệ thống. Bao gồm mã khách hàng, tên khách hàng, giới tính, ngày sinh, địa chỉ, số điện thoại, email, loại khách hàng, tiền tích lũy, mã người dùng. 
>> * Quản lí mã khuyến mãi
>>> Thông tin 1 mã khuyến mãi bao gồm: mã khuyến mãi, mã code, giá trị khuyến mãi, loại, ngày bắt đầu, ngày kết thúc, tình trạng sử dụng.
>>> Nhân viên nhấn vào nút tạo mã để tạo ra 1 mã code bất kì nhưng tương ứng với giá trị mã khuyến mãi và loại khách hàng nào được sử dụng.
>> * Quản lí kho
>>> Thông tin kho bao gồm: trạng thái kho và loại kho (nông sản, nguyên vật liệu). Mỗi kho sẽ có một mã riêng để quản lý. Nhân viên có thể thêm mới, xóa, sửa hoặc tra cứu thông tin kho.
>> * Quản lí tồn kho nguyên vật liệu
>> * Quản lí tồn kho nông sản
>> * Quản lí nhân viên
>>> 1 nhân viên bao gồm: mã nhân viên, mã nông trại mà nhân viên đó đang làm, tên, địa chỉ, số điện thoại, email, ngày vào làm, username, password.
>> * Quản lí vận chuyển
>>> Mã vận chuyển, mã đơn hàng, trạng thái.
>> * Thống kê
>>> Hệ thống có chức năng tính toán doanh thu theo từng loại sản phẩm, từng trang trại và thực hiện thống kê theo định kỳ hàng ngày/tuần/tháng/quý/năm.
#### Dành cho khách hàng
>> * Khách hàng sau khi đăng nhập vào hệ thống, có thể chọn mua sản phẩm bất kì. Sau khi hoàn tất việc chọn sản phẩm. Khách hàng nhấn vào nút tính tiền để coi hóa đơn mua hàng và xác nhận thanh toán. 
