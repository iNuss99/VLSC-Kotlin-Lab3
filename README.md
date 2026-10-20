# Báo Cáo Thực Hành Lab — Mobile App Tích Hợp AI

## 👤 Thông tin sinh viên

- **Họ và tên:** Đỗ Minh Khoa
- **Mã số sinh viên (MSSV):** 2500114713
- **Lớp / Môn học:** Phát triển Ứng dụng Di động Tích hợp AI — Buổi 03 & 04

---

## 📌 PHẦN 1: LAB 3A — DANH SÁCH ĐỘNG TỐI ƯU VỚI RECYCLERVIEW (LISTADAPTER + DIFFUTIL)

### 📱 Giới thiệu Lab 3a

Xây dựng ứng dụng Android hiển thị danh sách 10 sinh viên dưới dạng thẻ `MaterialCardView` bo góc tối ưu bằng `RecyclerView`, `ListAdapter`, `DiffUtil` và `ViewBinding`.

### Công nghệ trọng tâm:

- **RecyclerView 1.4**: Hiển thị danh sách động tối ưu bộ nhớ qua cơ chế View Recycling.
- **ListAdapter + DiffUtil**: Quản lý và tự động so sánh dữ liệu danh sách ở luồng nền, tối ưu hóa hoạt động cập nhật view và tạo animation mượt mà.
- **Material3 CardView**: Thẻ hiển thị chuẩn Material Design 3 với hiệu ứng gợn sóng (ripple), bo góc và độ nổi (elevation).
- **ViewBinding**: Truy cập view an toàn theo kiểu dữ liệu (type-safe), thay thế cho `findViewById`.
- **Edge-to-Edge**: Tự động xử lý WindowInsets giúp giao diện tràn màn hình hiện đại.

### 🖼️ Minh chứng kết quả thực hiện Lab 3a

![Minh chứng kết quả Lab 3a](/image.png)

### 📱 Giới thiệu Lab 04

Mở rộng từ Lab 3a, tập trung vào việc điều hướng đa màn hình từ `MainActivity` sang `DetailActivity` bằng **Explicit Intent**, đóng gói đối tượng `Student` qua cơ chế **Parcelable**, và tích hợp tính năng chia sẻ hồ sơ sinh viên sang các ứng dụng bên ngoài bằng **Implicit Intent** (`ACTION_SEND`).

### Công nghệ & Kỹ thuật trọng tâm:

1. **Explicit Intent & IPC Binder**:
   - Truyền đối tượng `Student` đóng gói `Parcelable` giữa các Activity thông qua `Bundle`.
   - Xử lý tương thích ngược API (API 33 Tiramisu+: `intent.getParcelableExtra(EXTRA_STUDENT, Student::class.java)` vs `< API 33`).
2. **Implicit Intent (`ACTION_SEND`)**:
   - Kích hoạt `Intent.createChooser` giúp chia sẻ văn bản nội dung hồ sơ sinh viên sang ứng dụng khác.
3. **Huấn luyện gỡ lỗi & kiểm soát bộ nhớ**:
   - **Lỗi 1 (OOM / Binder Transaction Too Large Exception):** Hệ thống giới hạn Transaction Buffer của IPC Binder ở mức ~1MB. Tránh truyền dữ liệu binary/mảng byte quá lớn trong Bundle.
   - **Lỗi 2 (Memory Leak / Context Binding):** Tránh tham chiếu Activity context trong `companion object` tĩnh.

### 🖼️ Minh chứng kết quả thực hiện Lab 04

![Minh chứng kết quả Lab 04](/imagelab_4.png)

---

## 🛠️ Hướng dẫn cài đặt và chạy ứng dụng

1. **Mở dự án trong Android Studio:**
   - Thực hiện **Gradle Sync**.
   - Chọn thiết bị/máy ảo và bấm **Run** (`Shift + F10`).
2. **Kiểm tra chức năng:**
   - **Lab 3a:** Danh sách 10 sinh viên hiển thị mượt mà trên `RecyclerView`.
   - **Lab 04:** Chạm vào sinh viên bất kỳ -> `DetailActivity` mở ra hiển thị Tên, MSSV và Ảnh đại diện. Bấm **"Chia sẻ hồ sơ"** -> Bảng chọn ứng dụng (Intent Chooser) xuất hiện với nội dung: `Hồ sơ SV: [Tên] - [MSSV]`.
