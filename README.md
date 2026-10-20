# Lab 04 — Điều hướng đa màn hình với Intent & Vòng đời Activity (Parcelable & IPC Binder)

## 👤 Thông tin sinh viên
- **Họ và tên:** Đỗ Minh Khoa
- **Mã số sinh viên (MSSV):** 2500114713
- **Lớp / Môn học:** Phát triển Ứng dụng Di động Tích hợp AI — Buổi 04

---

## 📱 Giới thiệu bài Lab
Bài thực hành **Lab 04 (Lab 3b)** mở rộng từ Lab 3a, tập trung vào việc điều hướng đa màn hình từ `MainActivity` sang `DetailActivity` bằng **Explicit Intent**, đóng gói đối tượng `Student` qua cơ chế **Parcelable**, và tích hợp tính năng chia sẻ hồ sơ sinh viên sang các ứng dụng bên ngoài bằng **Implicit Intent** (`ACTION_SEND`).

### Các kỹ thuật trọng tâm:
1. **Explicit Intent & IPC Binder**:
   - Truyền đối tượng `Student` đóng gói `Parcelable` giữa các Activity thông qua `Bundle`.
   - Xử lý tương thích ngược API (API 33 Tiramisu+: `intent.getParcelableExtra(EXTRA_STUDENT, Student::class.java)` vs `< API 33`).
2. **Implicit Intent (`ACTION_SEND`)**:
   - Kích hoạt `Intent.createChooser` giúp chia sẻ văn bản nội dung hồ sơ sinh viên.
3. **Huấn luyện gỡ lỗi & kiểm soát bộ nhớ**:
   - **Lỗi 1 (OOM / Binder Transaction Too Large Exception):** Hệ thống giới hạn Transaction Buffer của IPC Binder ở mức ~1MB. Tránh truyền dữ liệu binary/mảng byte quá lớn trong Bundle.
   - **Lỗi 2 (Memory Leak / Context Binding):** Tránh tham chiếu Activity context trong `companion object` tĩnh.

---

## 🖼️ Minh chứng kết quả thực hiện

![Minh chứng kết quả Lab 04](img.png)

*Hình ảnh minh chứng: Màn hình chi tiết sinh viên (DetailActivity) và Intent Chooser chia sẻ dữ liệu sinh viên bằng Implicit Intent.*

---

## 🛠️ Hướng dẫn cài đặt và chạy ứng dụng

1. **Mở dự án trong Android Studio:**
   - Thực hiện **Gradle Sync**.
   - Chọn thiết bị/máy ảo và bấm **Run** (`Shift + F10`).
2. **Kiểm tra chức năng:**
   - Chạm vào một phần tử sinh viên bất kỳ ở `MainActivity` -> Màn hình `DetailActivity` mở ra hiển thị Tên, MSSV và Ảnh đại diện.
   - Nhấn nút **"Chia sẻ hồ sơ"** -> Bảng chọn ứng dụng (Intent Chooser) xuất hiện với nội dung: `Hồ sơ SV: [Tên] - [MSSV]`.

---

## 🤖 Sử dụng AI có trách nhiệm (AI Usage Statement)
- **Công cụ AI sử dụng:** Gemini in Android Studio.
- **Mục đích:**
  - Hỗ trợ xây dựng cấu trúc `Parcelable` an toàn.
  - Rà soát hàm giải nén `getParcelableExtra` tương thích Android 13+ (API 33).
  - Viết báo cáo gỡ lỗi và giải thích cơ chế IPC Binder.
- **Cam kết:** Sinh viên tự hiểu rõ và kiểm soát hoàn toàn mã nguồn bài nộp.
