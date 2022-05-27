# bagusiyoo
Ini adalah aplikasi berbasis Android yang digunakan untuk mencatat aktivitas pertumbuhan tanaman pada Project Tani Cerdas IIB Darmajaya.

Source code file terdiri dari 2 directory yaitu:
- Android Apps File (Java)
- Rest API File (Laravel)

Panduan installasi:
Rest API :
- Buka directory RestApi pada Text Editor
- Buka Terminal
- copas file .env.example ke directory yang sama /root directory dan rename menjadi .env
- edit pada file .env bagian database name: mydiary
- buat database kosong di localhost/phpmyadmin dengan nama: mydiary
- Ketikkan perintah: composer install
- Ketikkan perintah: php artisan key:generate
- Ketikkan perintah: php artisan migrate:fresh --seed
- Ketikkan perintah: php artisan serve
- Rest Api bisa ditest di Postman.

Android Apps:
- Buka File dengan Android Studio
