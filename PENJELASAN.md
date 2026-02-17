# Sistem Konsultasi Mahasiswa - Dosen (OOP Java)

## D-END Group
    1.Dion Kobi
    2.Clio Marco Mataheru
    3.George Stivo Kaunang
    4.Geovani Waladow
## Deskripsi Sistem

Sistem ini adalah program berbasis **terminal (CLI)** yang memungkinkan mahasiswa untuk membuat jadwal konsultasi dengan dosen. Program ini dibangun menggunakan bahasa **Java** dengan menerapkan konsep **Object Oriented Programming (OOP)**.

---

## Konsep OOP yang Digunakan

### 1. Abstraction (Abstraksi)

**Apa itu?** Menyembunyikan detail implementasi dan hanya menampilkan hal-hal penting.

**Implementasi di kode:**

```java
abstract class Person {
    public abstract void tampilkanInfo(); // hanya deklarasi, tanpa isi
}
```

- Class `Person` adalah **abstract class** yang tidak bisa dibuat objeknya secara langsung.
- Method `tampilkanInfo()` dideklarasikan tanpa isi (**abstract method**), sehingga class anak **wajib** mengisi sendiri isinya.
- Kita tahu bahwa setiap Person pasti bisa menampilkan info, tapi **caranya berbeda** tergantung apakah itu Dosen atau Mahasiswa.

---

### 2. Inheritance (Pewarisan)

**Apa itu?** Class anak mewarisi atribut dan method dari class induk.

**Implementasi di kode:**

```java
class Dosen extends Person { ... }
class Mahasiswa extends Person { ... }
```

- `Dosen` dan `Mahasiswa` **mewarisi** atribut `nama` dan `id` dari class `Person`.
- Mereka tidak perlu menulis ulang atribut tersebut, cukup pakai `super()` untuk memanggil constructor induk.
- Keyword `extends` menandakan hubungan **"is-a"** → Dosen **adalah** Person, Mahasiswa **adalah** Person.

**Hierarki:**

```
        Person (Induk)
       /            \
    Dosen         Mahasiswa
   (Anak)          (Anak)
```

---

### 3. Encapsulation (Enkapsulasi)

**Apa itu?** Melindungi data dengan membatasi akses langsung ke atribut, dan menyediakan method getter/setter.

**Implementasi di kode:**

```java
// Atribut dilindungi (tidak bisa diakses langsung dari luar)
private String keahlian;

// Akses lewat getter (method untuk mengambil nilai)
public String getKeahlian() {
    return keahlian;
}
```

- Atribut dibuat `private` atau `protected` → **tidak bisa diakses sembarangan** dari luar class.
- Untuk mengambil nilainya, harus lewat **getter** seperti `getNama()`, `getKeahlian()`, `getJurusan()`.
- Ini melindungi data dari perubahan yang tidak diinginkan.

**Access Modifier yang digunakan:**

| Modifier | Artinya |
|---|---|
| `private` | Hanya bisa diakses di dalam class itu sendiri |
| `protected` | Bisa diakses oleh class itu sendiri dan class anaknya |
| `public` | Bisa diakses dari mana saja |

---

### 4. Polymorphism (Polimorfisme)

**Apa itu?** Method yang sama namanya tapi **berbeda isi/perilaku** tergantung class-nya.

**Implementasi di kode:**

```java
// Di class Dosen
@Override
public void tampilkanInfo() {
    System.out.println("  Nama     : " + nama);
    System.out.println("  Keahlian : " + keahlian);  // khusus Dosen
}

// Di class Mahasiswa
@Override
public void tampilkanInfo() {
    System.out.println("  Nama    : " + nama);
    System.out.println("  Jurusan : " + jurusan);    // khusus Mahasiswa
}
```

- Dosen dan Mahasiswa sama-sama punya method `tampilkanInfo()`.
- Tapi **isinya berbeda**: Dosen menampilkan keahlian, Mahasiswa menampilkan jurusan.
- `@Override` menandakan bahwa method ini **menimpa** method abstract dari class induk.

---

## Struktur Class

| Class | Tipe | Peran |
|---|---|---|
| `Person` | Abstract Class (Induk) | Cetakan umum untuk Dosen & Mahasiswa |
| `Dosen` | Class (Anak dari Person) | Menyimpan data dosen (nama, id, keahlian) |
| `Mahasiswa` | Class (Anak dari Person) | Menyimpan data mahasiswa (nama, id, jurusan) |
| `Konsultasi` | Class | Menyimpan data satu sesi konsultasi |
| `Main` | Class (Entry Point) | Menjalankan program dan menampilkan menu |

**Hubungan antar class:**

```
Person (abstract)
  ├── Dosen
  └── Mahasiswa

Konsultasi
  ├── punya → Mahasiswa
  └── punya → Dosen

Main
  ├── menggunakan → Dosen (ArrayList)
  ├── menggunakan → Mahasiswa (ArrayList)
  └── menggunakan → Konsultasi (ArrayList)
```

---

## Fitur Program

| No | Menu | Fungsi |
|---|---|---|
| 1 | Lihat Daftar Dosen | Menampilkan semua dosen beserta keahliannya |
| 2 | Lihat Daftar Mahasiswa | Menampilkan semua mahasiswa beserta jurusannya |
| 3 | Buat Konsultasi | Membuat jadwal konsultasi (pilih mahasiswa, dosen, topik, tanggal) |
| 4 | Lihat Konsultasi | Menampilkan semua konsultasi yang sudah dibuat |
| 5 | Tambah Mahasiswa | Menambahkan mahasiswa baru ke dalam sistem |
| 6 | Hapus Data       | Menghapus data mahasiswa, dosen, atau konsultasi |
| 0 | Keluar | Menghentikan program |

---

## Cara Menjalankan

```
javac Main.java
java Main
```
## Detail Fitur Hapus Data (Menu 6)

Ketika user memilih menu **6. Hapus Data**, program akan menampilkan sub-menu:
```
--- HAPUS DATA ---

  Hapus data apa?
  1. Hapus Mahasiswa
  2. Hapus Dosen
  3. Hapus Konsultasi
  0. Kembali ke Menu Utama
```

Setiap opsi hapus memiliki **2 lapisan perlindungan** agar user tidak salah hapus:

| Lapisan | Mekanisme | Tujuan |
|---|---|---|
| 1 | Pilih `0` di sub-menu | Kembali ke menu utama tanpa hapus apapun |
| 2 | Konfirmasi `y/n` sebelum hapus | Mencegah penghapusan tidak disengaja |

### Alur Hapus Mahasiswa & Dosen
```
Menu 6 → pilih 1 (Mahasiswa) atau 2 (Dosen)
  → tampil daftar nama + ID
  → user pilih nomor (0 = batal)
  → konfirmasi y/n
  → jika y: data dihapus dari ArrayList
  → jika n: dibatalkan, kembali ke menu utama
```

### Alur Hapus Konsultasi
```
Menu 6 → pilih 3 (Konsultasi)
  → tampil semua detail konsultasi (mahasiswa, dosen, topik, tanggal, waktu)
  → user pilih nomor (0 = batal)
  → konfirmasi y/n
  → jika y: konsultasi dihapus dari ArrayList
  → jika n: dibatalkan, kembali ke menu utama
```

### Method yang Ditambahkan

| Method | Fungsi |
|---|---|
| `hapusData()` | Menampilkan sub-menu pilihan hapus |
| `hapusMahasiswa()` | Menghapus mahasiswa dari `daftarMahasiswa` |
| `hapusDosen()` | Menghapus dosen dari `daftarDosen` |
| `hapusKonsultasi()` | Menghapus konsultasi dari `daftarKonsultasi` |

### Cara Kerja Penghapusan (ArrayList)
```java
// Menghapus data berdasarkan index (nomor urut - 1)
daftarMahasiswa.remove(nomor - 1);
daftarDosen.remove(nomor - 1);
daftarKonsultasi.remove(nomor - 1);
```

`ArrayList.remove(index)` akan otomatis menggeser semua elemen setelahnya, 
sehingga tidak ada "slot kosong" yang tersisa di dalam list.

---

## Penjelasan Keyword Java yang Dipakai

| Keyword | Penjelasan |
|---|---|
| `abstract` | Menandakan class/method yang tidak bisa dipakai langsung, harus diturunkan |
| `extends` | Menandakan pewarisan (class anak mewarisi class induk) |
| `super()` | Memanggil constructor dari class induk |
| `@Override` | Menandakan method ini menimpa method dari class induk |
| `private` | Atribut/method hanya bisa diakses dari dalam class itu sendiri |
| `protected` | Atribut/method bisa diakses dari class itu sendiri dan class turunannya |
| `public` | Atribut/method bisa diakses dari mana saja |
| `static` | Method/atribut milik class, bukan milik objek (bisa dipanggil tanpa membuat objek) |
| `this` | Merujuk ke objek saat ini |
| `new` | Membuat objek baru dari sebuah class |
| `ArrayList` | Struktur data dinamis untuk menyimpan banyak objek |
| `Scanner` | Class untuk membaca input dari keyboard |
