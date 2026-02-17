# Sistem Konsultasi Mahasiswa - Dosen (OOP Java)

## D-END Group

| No | Nama Anggota |
|---|---|
| 1 | Dion Kobi |
| 2 | Clio Marco Mataheru |
| 3 | George Stivo Kaunang |
| 4 | Geovani Waladow |

---

## Deskripsi Sistem

Sistem ini adalah program berbasis **terminal (CLI)** yang memungkinkan mahasiswa untuk membuat jadwal konsultasi dengan dosen. Program ini dibangun menggunakan bahasa **Java** dengan menerapkan konsep **Object Oriented Programming (OOP)**.

Program menyediakan fitur lengkap mulai dari melihat data dosen & mahasiswa, membuat konsultasi, menambah mahasiswa baru, hingga menghapus data yang sudah tidak diperlukan.

---

## Data yang Terdaftar di Sistem

### Daftar Dosen

| ID | Nama Dosen | Keahlian |
|---|---|---|
| D01 | Stenly Pungus | Software Engineer |
| D02 | Debby Sondakh | Research Method |
| D03 | Semmy Taju | Artificial Intelligence |
| D04 | Green Sandag | Expert System |
| D05 | Green Mandias | DBMS |
| D06 | Andrew Liem | Computer Network |
| D07 | Oktoverano Lengkong | UI/UX |

### Daftar Mahasiswa

| ID | Nama Mahasiswa | Jurusan |
|---|---|---|
| M01 | Dion Kobi | Teknik Informatika |
| M02 | Clio Marco Mataheru | Teknik Informatika |
| M03 | George Stivo Kaunang | Teknik Informatika |
| M04 | Geovani Waladow | Teknik Informatika |

Data dosen dan mahasiswa di atas sudah langsung tersedia saat program dijalankan. Data ini diinisialisasi di dalam method `main()` menggunakan `ArrayList`:

```java
// Data dosen
daftarDosen.add(new Dosen("D01", "Stenly Pungus", "Software Engineer"));
daftarDosen.add(new Dosen("D02", "Debby Sondakh", "Research Method"));
daftarDosen.add(new Dosen("D03", "Semmy Taju", "Artificial Intelligence"));
daftarDosen.add(new Dosen("D04", "Green Sandag", "Expert System"));
daftarDosen.add(new Dosen("D05", "Green Mandias", "DBMS"));
daftarDosen.add(new Dosen("D06", "Andrew Liem", "Computer Network"));
daftarDosen.add(new Dosen("D07", "Oktoverano Lengkong", "UI/UX"));

// Data mahasiswa
daftarMahasiswa.add(new Mahasiswa("M01", "Dion Kobi", "Teknik Informatika"));
daftarMahasiswa.add(new Mahasiswa("M02", "Clio Marco Mataheru", "Teknik Informatika"));
daftarMahasiswa.add(new Mahasiswa("M03", "George Stivo Kaunang", "Teknik Informatika"));
daftarMahasiswa.add(new Mahasiswa("M04", "Geovani Waladow", "Teknik Informatika"));
```

---

## Konsep OOP yang Digunakan

Program ini menerapkan **4 pilar utama OOP**:

### 1. Abstraction (Abstraksi)

**Apa itu?** Menyembunyikan detail implementasi dan hanya menampilkan hal-hal penting. Kita mendefinisikan "apa yang bisa dilakukan" tanpa harus tahu "bagaimana caranya".

**Implementasi di kode:**

```java
abstract class Person {
    protected String nama;
    protected String id;

    public Person(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public abstract void tampilkanInfo(); // hanya deklarasi, tanpa isi
}
```

**Penjelasan detail:**
- Class `Person` adalah **abstract class** — tidak bisa dibuat objeknya secara langsung (`new Person(...)` akan error).
- Method `tampilkanInfo()` dideklarasikan tanpa isi (**abstract method**), sehingga class anak **wajib** mengisi sendiri isinya.
- Kita tahu bahwa setiap Person pasti bisa menampilkan info, tapi **caranya berbeda** tergantung apakah itu Dosen atau Mahasiswa.
- Abstraksi memungkinkan kita menyediakan "kontrak" yang harus dipatuhi oleh semua class turunan, tanpa memaksakan bagaimana implementasinya.

**Kenapa pakai abstract?**
- Karena `Person` itu terlalu umum untuk dijadikan objek langsung. Tidak ada "orang" di sistem ini, yang ada adalah **Dosen** atau **Mahasiswa**.
- Abstract class menjamin bahwa setiap turunan Person pasti memiliki method `tampilkanInfo()`.

---

### 2. Inheritance (Pewarisan)

**Apa itu?** Class anak mewarisi atribut dan method dari class induk, sehingga tidak perlu menulis ulang kode yang sama.

**Implementasi di kode:**

```java
class Dosen extends Person {
    private String keahlian;

    public Dosen(String id, String nama, String keahlian) {
        super(id, nama);       // memanggil constructor Person
        this.keahlian = keahlian;
    }
}

class Mahasiswa extends Person {
    private String jurusan;

    public Mahasiswa(String id, String nama, String jurusan) {
        super(id, nama);       // memanggil constructor Person
        this.jurusan = jurusan;
    }
}
```

**Penjelasan detail:**
- `Dosen` dan `Mahasiswa` **mewarisi** atribut `nama` dan `id` serta method `getNama()` dan `getId()` dari class `Person`.
- Mereka tidak perlu menulis ulang atribut tersebut, cukup pakai `super(id, nama)` untuk memanggil constructor induk.
- Keyword `extends` menandakan hubungan **"is-a"** → Dosen **adalah** Person, Mahasiswa **adalah** Person.
- Setiap class anak bisa menambah atribut sendiri yang spesifik: Dosen punya `keahlian`, Mahasiswa punya `jurusan`.

**Hierarki Pewarisan:**

```
           Person (Induk / Abstract)
           ├── nama
           ├── id
           ├── getNama()
           ├── getId()
           └── tampilkanInfo() [abstract]
          /                        \
       Dosen                    Mahasiswa
       ├── keahlian             ├── jurusan
       ├── getKeahlian()        ├── getJurusan()
       └── tampilkanInfo()      └── tampilkanInfo()
           [override]               [override]
```

**Keuntungan Inheritance:**
- **Mengurangi duplikasi kode** — atribut `nama` dan `id` cukup ditulis sekali di `Person`.
- **Mudah dikembangkan** — jika ingin menambah tipe baru (misal: `Staff`), cukup buat class baru yang `extends Person`.
- **Konsistensi** — semua turunan Person dijamin memiliki struktur dasar yang sama.

---

### 3. Encapsulation (Enkapsulasi)

**Apa itu?** Melindungi data dengan membatasi akses langsung ke atribut, dan menyediakan method getter/setter sebagai "pintu masuk" yang terkontrol.

**Implementasi di kode:**

```java
// Di class Person — atribut protected
protected String nama;   // bisa diakses oleh class anak
protected String id;

// Di class Dosen — atribut private
private String keahlian; // HANYA bisa diakses di dalam class Dosen

// Akses lewat getter (method untuk mengambil nilai)
public String getKeahlian() {
    return keahlian;
}

// Di class Konsultasi — semua atribut private
private Mahasiswa mahasiswa;
private Dosen dosen;
private String topik;
private String tanggal;
private String waktu;
```

**Penjelasan detail:**
- Atribut dibuat `private` atau `protected` → **tidak bisa diakses sembarangan** dari luar class.
- Untuk mengambil nilainya, harus lewat **getter** seperti `getNama()`, `getKeahlian()`, `getJurusan()`.
- Ini melindungi data dari perubahan yang tidak diinginkan. Misalnya, tidak ada yang bisa mengubah `keahlian` dosen secara langsung dari luar class `Dosen`.
- Class `Konsultasi` juga menerapkan encapsulation — semua atributnya `private` dan hanya bisa dilihat lewat method `tampilkanDetail()`.

**Access Modifier yang digunakan:**

| Modifier | Artinya | Digunakan di |
|---|---|---|
| `private` | Hanya bisa diakses di dalam class itu sendiri | `keahlian` (Dosen), `jurusan` (Mahasiswa), semua atribut Konsultasi |
| `protected` | Bisa diakses oleh class itu sendiri dan class anaknya | `nama`, `id` (Person) |
| `public` | Bisa diakses dari mana saja | Semua method getter, constructor, `tampilkanInfo()` |

**Ilustrasi Encapsulation:**
```
┌──────────────── Class Dosen ────────────────┐
│                                              │
│  [private] keahlian = "Software Engineer"    │ ← tidak bisa diakses langsung
│                                              │
│  [public] getKeahlian() → return keahlian    │ ← akses terkontrol lewat getter
│                                              │
└──────────────────────────────────────────────┘

Dari luar class:
  ✗  dosen.keahlian              → ERROR (private)
  ✓  dosen.getKeahlian()         → "Software Engineer"
```

---

### 4. Polymorphism (Polimorfisme)

**Apa itu?** Method yang sama namanya tapi **berbeda isi/perilaku** tergantung class-nya. Berasal dari bahasa Yunani: "poly" = banyak, "morph" = bentuk.

**Implementasi di kode:**

```java
// Di class Dosen
@Override
public void tampilkanInfo() {
    System.out.println("  ID       : " + id);
    System.out.println("  Nama     : " + nama);
    System.out.println("  Keahlian : " + keahlian);  // khusus Dosen
}

// Di class Mahasiswa
@Override
public void tampilkanInfo() {
    System.out.println("  ID      : " + id);
    System.out.println("  Nama    : " + nama);
    System.out.println("  Jurusan : " + jurusan);    // khusus Mahasiswa
}
```

**Penjelasan detail:**
- Dosen dan Mahasiswa sama-sama punya method `tampilkanInfo()` yang diwarisi dari `Person`.
- Tapi **isinya berbeda**: Dosen menampilkan `keahlian`, Mahasiswa menampilkan `jurusan`.
- `@Override` menandakan bahwa method ini **menimpa** method abstract dari class induk.
- Saat program memanggil `tampilkanInfo()`, Java otomatis tahu method versi mana yang harus dijalankan berdasarkan tipe objeknya.

**Contoh Polymorphism saat runtime:**
```java
// Di method lihatDosen():
daftarDosen.get(i).tampilkanInfo();
// → memanggil versi Dosen (tampilkan ID, nama, keahlian)

// Di method lihatMahasiswa():
daftarMahasiswa.get(i).tampilkanInfo();
// → memanggil versi Mahasiswa (tampilkan ID, nama, jurusan)
```

Walaupun methodnya **bernama sama** (`tampilkanInfo`), outputnya **berbeda** tergantung objek yang memanggilnya.

---

## Struktur Class

### Ringkasan Semua Class

| Class | Tipe | Peran | Jumlah Atribut | Jumlah Method |
|---|---|---|---|---|
| `Person` | Abstract Class (Induk) | Cetakan umum untuk Dosen & Mahasiswa | 2 (`nama`, `id`) | 3 (`getNama`, `getId`, `tampilkanInfo`) |
| `Dosen` | Class (Anak dari Person) | Menyimpan data dosen | 1 tambahan (`keahlian`) | 2 (`getKeahlian`, `tampilkanInfo`) |
| `Mahasiswa` | Class (Anak dari Person) | Menyimpan data mahasiswa | 1 tambahan (`jurusan`) | 2 (`getJurusan`, `tampilkanInfo`) |
| `Konsultasi` | Class | Menyimpan data satu sesi konsultasi | 5 (`mahasiswa`, `dosen`, `topik`, `tanggal`, `waktu`) | 1 (`tampilkanDetail`) |
| `Main` | Class (Entry Point) | Menjalankan program dan menampilkan menu | 4 static (`daftarDosen`, `daftarMahasiswa`, `daftarKonsultasi`, `input`) | 10 static |

### Hubungan Antar Class

```
Person (abstract)
  ├── Dosen          ← extends (inheritance)
  └── Mahasiswa      ← extends (inheritance)

Konsultasi
  ├── punya → Mahasiswa    ← komposisi (has-a)
  └── punya → Dosen        ← komposisi (has-a)

Main
  ├── menggunakan → ArrayList<Dosen>
  ├── menggunakan → ArrayList<Mahasiswa>
  ├── menggunakan → ArrayList<Konsultasi>
  └── menggunakan → Scanner
```

### Penjelasan Class Konsultasi

Class `Konsultasi` menggunakan konsep **komposisi** (has-a relationship). Artinya, sebuah objek `Konsultasi` **memiliki** objek `Mahasiswa` dan objek `Dosen` di dalamnya:

```java
class Konsultasi {
    private Mahasiswa mahasiswa;  // konsultasi PUNYA mahasiswa
    private Dosen dosen;          // konsultasi PUNYA dosen
    private String topik;
    private String tanggal;
    private String waktu;

    public void tampilkanDetail() {
        System.out.println("  Mahasiswa : " + mahasiswa.getNama());
        System.out.println("  Dosen     : " + dosen.getNama());
        System.out.println("  Topik     : " + topik);
        System.out.println("  Tanggal   : " + tanggal);
        System.out.println("  Waktu     : " + waktu);
    }
}
```

Perhatikan bahwa `tampilkanDetail()` mengakses data mahasiswa dan dosen melalui **getter** (`getNama()`), bukan langsung ke atributnya. Ini adalah penerapan **encapsulation**.

---

## Fitur Program (Menu Utama)

Saat program dijalankan, user akan melihat menu berikut:

```
========================================
   SISTEM KONSULTASI MAHASISWA - DOSEN  
========================================
  1. Lihat Daftar Dosen
  2. Lihat Daftar Mahasiswa
  3. Buat Konsultasi
  4. Lihat Konsultasi
  5. Tambah Mahasiswa
  6. Hapus Data
  0. Keluar
========================================
  Pilih menu: 
```

### Detail Setiap Menu

| No | Menu | Fungsi | Method yang Dipanggil |
|---|---|---|---|
| 1 | Lihat Daftar Dosen | Menampilkan semua dosen (ID, nama, keahlian) | `lihatDosen()` |
| 2 | Lihat Daftar Mahasiswa | Menampilkan semua mahasiswa (ID, nama, jurusan) | `lihatMahasiswa()` |
| 3 | Buat Konsultasi | Membuat jadwal konsultasi baru (pilih mahasiswa, dosen, topik, tanggal, waktu) | `buatKonsultasi()` |
| 4 | Lihat Konsultasi | Menampilkan semua konsultasi yang sudah dibuat | `lihatKonsultasi()` |
| 5 | Tambah Mahasiswa | Menambahkan mahasiswa baru ke dalam sistem | `tambahMahasiswa()` |
| 6 | Hapus Data | Menghapus data mahasiswa, dosen, atau konsultasi | `hapusData()` |
| 0 | Keluar | Menghentikan program | - |

Menu dijalankan dalam **while loop** sehingga program terus berjalan sampai user memilih `0`:

```java
boolean jalan = true;
while (jalan) {
    // tampilkan menu
    // baca pilihan
    switch (pilihan) {
        case 1: lihatDosen(); break;
        case 2: lihatMahasiswa(); break;
        // ... dst
        case 0: jalan = false; break; // keluar dari loop
    }
}
```

---

## Detail Fitur: Lihat Daftar Dosen (Menu 1)

Method `lihatDosen()` menampilkan semua dosen menggunakan **perulangan for** dan memanfaatkan **polymorphism**:

```java
static void lihatDosen() {
    for (int i = 0; i < daftarDosen.size(); i++) {
        System.out.println("Dosen " + (i + 1) + "");
        daftarDosen.get(i).tampilkanInfo(); // POLYMORPHISM
    }
}
```

**Contoh output:**
```
--- DAFTAR DOSEN ---

Dosen 1
  ID : D01
  Nama : Stenly Pungus
  Keahlian : Software Engineer

Dosen 2
  ID : D02
  Nama : Debby Sondakh
  Keahlian : Research Method

... (dan seterusnya untuk 7 dosen)
```

---

## Detail Fitur: Lihat Daftar Mahasiswa (Menu 2)

Method `lihatMahasiswa()` bekerja sama seperti `lihatDosen()`, tapi memanggil `tampilkanInfo()` versi Mahasiswa:

```java
static void lihatMahasiswa() {
    for (int i = 0; i < daftarMahasiswa.size(); i++) {
        System.out.println("Mahasiswa " + (i + 1) + "");
        daftarMahasiswa.get(i).tampilkanInfo(); // POLYMORPHISM
    }
}
```

**Contoh output:**
```
--- DAFTAR MAHASISWA ---

Mahasiswa 1
  ID : M01
  Nama : Dion Kobi
  Jurusan : Teknik Informatika

Mahasiswa 2
  ID : M02
  Nama : Clio Marco Mataheru
  Jurusan : Teknik Informatika

... (dan seterusnya untuk 4 mahasiswa)
```

---

## Detail Fitur: Buat Konsultasi (Menu 3)

Method `buatKonsultasi()` memiliki **4 langkah**:

### Langkah 1: Pilih Mahasiswa
```
Pilih Mahasiswa:
  1. Dion Kobi
  2. Clio Marco Mataheru
  3. George Stivo Kaunang
  4. Geovani Waladow
Nomor: _
```

### Langkah 2: Pilih Dosen
```
Pilih Dosen:
  1. Stenly Pungus (Software Engineer)
  2. Debby Sondakh (Research Method)
  3. Semmy Taju (Artificial Intelligence)
  4. Green Sandag (Expert System)
  5. Green Mandias (DBMS)
  6. Andrew Liem (Computer Network)
  7. Oktoverano Lengkong (UI/UX)
Nomor: _
```

### Langkah 3: Input Topik, Tanggal, dan Waktu
```
Topik konsultasi: _
Tanggal (cth: 20-02-2026): _
Waktu (cth: 21:00): _
```

### Langkah 4: Simpan Konsultasi
```java
Mahasiswa mhs = daftarMahasiswa.get(noMhs - 1);
Dosen dsn = daftarDosen.get(noDosen - 1);
Konsultasi baru = new Konsultasi(mhs, dsn, topik, tanggal, waktu);
daftarKonsultasi.add(baru);
```

Program membuat objek `Konsultasi` baru yang berisi referensi ke objek `Mahasiswa` dan `Dosen` yang dipilih, lalu menyimpannya ke `ArrayList<Konsultasi>`.

**Validasi input:** Jika user memasukkan nomor yang tidak valid, program akan menampilkan pesan error dan kembali ke menu utama.

---

## Detail Fitur: Lihat Konsultasi (Menu 4)

Method `lihatKonsultasi()` menampilkan semua konsultasi yang telah dibuat:

```java
static void lihatKonsultasi() {
    if (daftarKonsultasi.isEmpty()) {
        System.out.println("  Belum ada konsultasi.");
        return;
    }
    for (int i = 0; i < daftarKonsultasi.size(); i++) {
        System.out.println("[Konsultasi " + (i + 1) + "]");
        daftarKonsultasi.get(i).tampilkanDetail();
    }
}
```

**Contoh output:**
```
--- DAFTAR KONSULTASI ---

[Konsultasi 1]
  Mahasiswa : Dion Kobi
  Dosen     : Stenly Pungus
  Topik     : Konsultasi tugas akhir
  Tanggal   : 20-02-2026
  Waktu     : 10:00
```

Jika belum ada konsultasi, program akan menampilkan pesan "Belum ada konsultasi."

---

## Detail Fitur: Tambah Mahasiswa (Menu 5)

Method `tambahMahasiswa()` meminta user menginput ID, nama, dan jurusan mahasiswa baru:

```java
static void tambahMahasiswa() {
    System.out.print("ID (cth: M03): ");
    String id = input.nextLine();

    System.out.print("Nama: ");
    String nama = input.nextLine();

    System.out.print("Jurusan: ");
    String jurusan = input.nextLine();

    Mahasiswa baru = new Mahasiswa(id, nama, jurusan);
    daftarMahasiswa.add(baru);
}
```

Objek `Mahasiswa` baru dibuat dan langsung ditambahkan ke `ArrayList<Mahasiswa>`.

---

## Detail Fitur: Hapus Data (Menu 6)

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

**Contoh tampilan Hapus Mahasiswa:**
```
--- HAPUS MAHASISWA ---

  1. Dion Kobi (M01)
  2. Clio Marco Mataheru (M02)
  3. George Stivo Kaunang (M03)
  4. Geovani Waladow (M04)
  0. Batal / Kembali

  Pilih nomor mahasiswa yang dihapus: 2

  Yakin hapus mahasiswa "Clio Marco Mataheru"? (y/n): y

  Mahasiswa berhasil dihapus!
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

### Method Hapus Data

| Method | Fungsi |
|---|---|
| `hapusData()` | Menampilkan sub-menu pilihan hapus (mahasiswa/dosen/konsultasi) |
| `hapusMahasiswa()` | Menampilkan daftar mahasiswa, lalu menghapus yang dipilih dari `daftarMahasiswa` |
| `hapusDosen()` | Menampilkan daftar dosen, lalu menghapus yang dipilih dari `daftarDosen` |
| `hapusKonsultasi()` | Menampilkan daftar konsultasi, lalu menghapus yang dipilih dari `daftarKonsultasi` |

### Cara Kerja Penghapusan (ArrayList)

```java
// Menghapus data berdasarkan index (nomor urut - 1)
daftarMahasiswa.remove(nomor - 1);
daftarDosen.remove(nomor - 1);
daftarKonsultasi.remove(nomor - 1);
```

`ArrayList.remove(index)` akan otomatis menggeser semua elemen setelahnya ke depan, sehingga tidak ada "slot kosong" yang tersisa di dalam list. Contoh:

```
Sebelum hapus index 1:  [Dion, Clio, George, Geovani]
Sesudah hapus index 1:  [Dion, George, Geovani]
                              ↑ otomatis geser ke depan
```

---

## Ringkasan Semua Method di Class Main

| No | Method | Return Type | Fungsi |
|---|---|---|---|
| 1 | `main(String[] args)` | `void` | Titik awal program, inisialisasi data, dan menjalankan menu utama |
| 2 | `lihatDosen()` | `void` | Menampilkan semua dosen dengan `tampilkanInfo()` |
| 3 | `lihatMahasiswa()` | `void` | Menampilkan semua mahasiswa dengan `tampilkanInfo()` |
| 4 | `buatKonsultasi()` | `void` | Membuat konsultasi baru (pilih mahasiswa, dosen, input topik/tanggal/waktu) |
| 5 | `lihatKonsultasi()` | `void` | Menampilkan semua konsultasi dengan `tampilkanDetail()` |
| 6 | `tambahMahasiswa()` | `void` | Menambah mahasiswa baru ke `daftarMahasiswa` |
| 7 | `hapusData()` | `void` | Menampilkan sub-menu hapus data |
| 8 | `hapusMahasiswa()` | `void` | Menghapus mahasiswa dari `daftarMahasiswa` |
| 9 | `hapusDosen()` | `void` | Menghapus dosen dari `daftarDosen` |
| 10 | `hapusKonsultasi()` | `void` | Menghapus konsultasi dari `daftarKonsultasi` |

Semua method di atas bersifat `static` karena dipanggil langsung dari method `main()` tanpa membuat objek `Main`.

---

## Cara Menjalankan

```
javac Main.java
java Main
```

**Langkah-langkah:**
1. Buka terminal/command prompt
2. Navigasi ke folder tempat file `Main.java` berada
3. Jalankan `javac Main.java` untuk meng-compile program menjadi bytecode
4. Jalankan `java Main` untuk menjalankan program
5. Ikuti menu yang ditampilkan di layar

---

## Penjelasan Keyword Java yang Dipakai

| Keyword | Penjelasan | Contoh Penggunaan di Kode |
|---|---|---|
| `abstract` | Menandakan class/method yang tidak bisa dipakai langsung, harus diturunkan | `abstract class Person`, `public abstract void tampilkanInfo()` |
| `extends` | Menandakan pewarisan (class anak mewarisi class induk) | `class Dosen extends Person` |
| `super()` | Memanggil constructor dari class induk | `super(id, nama)` di constructor Dosen dan Mahasiswa |
| `@Override` | Menandakan method ini menimpa method dari class induk | `@Override public void tampilkanInfo()` |
| `private` | Atribut/method hanya bisa diakses dari dalam class itu sendiri | `private String keahlian` |
| `protected` | Atribut/method bisa diakses dari class itu sendiri dan class turunannya | `protected String nama` |
| `public` | Atribut/method bisa diakses dari mana saja | `public String getNama()` |
| `static` | Method/atribut milik class, bukan milik objek (bisa dipanggil tanpa `new`) | `static ArrayList<Dosen> daftarDosen` |
| `this` | Merujuk ke objek saat ini (membedakan atribut dengan parameter) | `this.nama = nama` |
| `new` | Membuat objek baru dari sebuah class | `new Dosen("D01", "Stenly Pungus", "Software Engineer")` |
| `void` | Method tidak mengembalikan nilai | `public void tampilkanInfo()` |
| `return` | Mengembalikan nilai dari method / keluar dari method | `return nama`, `return` (keluar dari method) |
| `import` | Mengimpor class dari library Java | `import java.util.ArrayList` |

---

## Penjelasan Library yang Dipakai

| Library | Class | Fungsi |
|---|---|---|
| `java.util` | `ArrayList` | Struktur data dinamis untuk menyimpan banyak objek (ukurannya bisa bertambah/berkurang secara otomatis, berbeda dari array biasa yang ukurannya tetap) |
| `java.util` | `Scanner` | Class untuk membaca input dari keyboard (`nextInt()` untuk angka, `nextLine()` untuk teks) |

---

## Alur Program Secara Keseluruhan

```
Program dimulai (main)
    │
    ├── Inisialisasi data dosen (7 dosen)
    ├── Inisialisasi data mahasiswa (4 mahasiswa)
    │
    └── Loop Menu Utama ←──────────────────────────┐
         │                                          │
         ├── [1] Lihat Dosen → tampilkanInfo()      │
         ├── [2] Lihat Mahasiswa → tampilkanInfo()  │
         ├── [3] Buat Konsultasi                    │
         │    ├── Pilih mahasiswa                   │
         │    ├── Pilih dosen                       │
         │    ├── Input topik, tanggal, waktu       │
         │    └── Simpan ke daftarKonsultasi        │
         ├── [4] Lihat Konsultasi                   │
         │    └── tampilkanDetail()                 │
         ├── [5] Tambah Mahasiswa                   │
         │    └── Simpan ke daftarMahasiswa          │
         ├── [6] Hapus Data                         │
         │    ├── [6.1] Hapus Mahasiswa              │
         │    ├── [6.2] Hapus Dosen                  │
         │    └── [6.3] Hapus Konsultasi             │
         │                                          │
         └── [0] Keluar ──→ Program selesai         │
              │                                     │
              └── (selain 0) kembali ke menu ───────┘
```
