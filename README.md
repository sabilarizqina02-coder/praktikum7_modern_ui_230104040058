# 📱 Praktikum 7 – Desain UI Modern (SecureAuth)

## 👤 Identitas Mahasiswa
- **Nama** : Sabila Rizqina Majid
- **NIM** : 230104040058
- **Program Studi** : S1 Teknologi Informasi
- **Fakultas** : Dakwah dan Ilmu Komunikasi
- **Universitas** : UIN Antasari Banjarmasin
- **Mata Kuliah** : Mobile Programming

---

## 📌 Deskripsi Praktikum
Praktikum ini bertujuan untuk mengimplementasikan **desain UI modern berbasis Jetpack Compose** dengan studi kasus aplikasi **SecureAuth**.  
Aplikasi ini menerapkan konsep **keamanan akun**, **biometric authentication**, **app lock**, **dark/light theme**, serta **pengelolaan state menggunakan ViewModel**.

---

## 🧩 Fitur Aplikasi
Fitur-fitur yang berhasil diimplementasikan pada aplikasi ini antara lain:

- Login menggunakan **Email & Password**
- Registrasi akun (Create Account)
- **Login menggunakan Biometrik (Fingerprint)**
- **App Lock** (mengunci aplikasi saat berpindah ke background)
- **Dark Mode & Light Mode**
- Pengaturan keamanan (Security Settings)
- Alerts:
    - Login Alerts
    - New Device Alerts
    - Public Wi-Fi Warnings
- **Delete Account**
- Penyimpanan data menggunakan **SharedPreferences**
- Navigasi antar layar menggunakan **NavHost**

---

## 🗂️ Struktur Package
id.antasari.praktikum7_modern_ui_230104040058
│
├── ui
│ ├── auth
│ │ ├── AuthViewModel.kt
│ │ └── SecureAuthApp.kt
│ │
│ ├── components
│ │ ├── AppButton.kt
│ │ ├── AppCard.kt
│ │ ├── AppTextField.kt
│ │ ├── SectionHeader.kt
│ │ └── TopBar.kt
│ │
│ ├── navigation
│ │ └── AppNavHost.kt
│ │
│ ├── theme
│ │ ├── Color.kt
│ │ ├── Shape.kt
│ │ ├── Theme.kt
│ │ └── Type.kt
│ │
│ ├── AccountStorage.kt
│ ├── BiometricUtils.kt
│ ├── CreateAccountScreen.kt
│ ├── LoginScreen.kt
│ ├── HomeScreen.kt
│ ├── SecurityDetailsScreen.kt
│ └── SettingsScreen.kt
│
└── MainActivity.kt

yaml
Salin kode

---

## 📸 Screenshot Aplikasi

### 🔐 Login Screen
![Login Screen](screenshots/Login%20Screen.jpeg)

---

### 📝 Create Account Screen
![Create Account Screen](screenshots/Create%20Account%20Screen.jpeg)

---

### 🏠 Home Screen
![Home Screen](screenshots/Home%20Screen.jpeg)

---

### 🛡️ Security Details Screen
![Security Details Screen](screenshots/Security%20Details%20Screen.jpeg)

---

### ⚙️ Settings Screen
![Settings Screen](screenshots/Settings%20Screen.jpeg)

---

### 🌙 Dark Mode
![Dark Mode](screenshots/Dark%20Mode%20View.jpeg)

---

### 🔑 Biometric Authentication
![Biometric Authentication](screenshots/Biometric%20Authentication%20(Prompt).jpeg)

---

### ⚠️ Delete Account (Danger Zone)

![Delete Account](screenshots/Delete%20Account%20(Danger%20Zone).jpeg)

---

## 🎥 Demo Video
Demo aplikasi berdurasi ± **1 menit**, menampilkan:
- Login
- Create Account
- Biometric Authentication
- Navigasi ke Home
- Security Details
- Settings (Toggle & Delete Account)

📌 **Link video demo**:  
👉 *(https://drive.google.com/file/d/1kr3jltPBHEoSfPIY3jqoTJ3ZFIUU_N-p/view?usp=sharing)*

---

## ⚙️ Teknologi yang Digunakan
- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **ViewModel**
- **StateFlow**
- **SharedPreferences**
- **Biometric API**
- **Android Studio**

---

## ✅ Kesimpulan
Melalui praktikum ini, berhasil dibuat aplikasi Android dengan **desain UI modern** dan fitur keamanan yang lengkap.  
Penerapan **state management**, **biometric authentication**, dan **pengaturan keamanan** menunjukkan pemahaman konsep Mobile Programming berbasis **Jetpack Compose**.

---

