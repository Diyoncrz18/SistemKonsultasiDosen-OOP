import java.util.ArrayList; // untuk menyimpan banyak data dalam bentuk list
import java.util.Scanner;    // untuk membaca input dari keyboard

/*
 * =====================================================
 *  SISTEM KONSULTASI MAHASISWA - DOSEN
 *  Dibuat menggunakan konsep OOP (Object Oriented Programming)
 * 
 *  Konsep OOP yang dipakai:
 *  1. ABSTRACTION   = membuat class abstrak (cetakan umum)
 *  2. INHERITANCE    = class anak mewarisi class induk
 *  3. ENCAPSULATION  = menyembunyikan data dengan private
 *  4. POLYMORPHISM   = method yang sama tapi beda isi
 * =====================================================
 */

// ==========================================================
// CLASS ABSTRAK: Person
// Ini adalah class INDUK / PARENT
// Class ini TIDAK bisa dibuat objeknya langsung (karena abstract)
// Tujuannya: jadi cetakan umum untuk Dosen dan Mahasiswa
// KONSEP: ABSTRACTION & ENCAPSULATION
// ==========================================================
abstract class Person {

    // Atribut dibuat PROTECTED supaya bisa diakses oleh class anak
    // KONSEP: ENCAPSULATION (data dilindungi, tidak bisa diakses sembarangan)
    protected String nama;
    protected String id;

    // Constructor = method yang dipanggil saat membuat objek baru
    public Person(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    // Getter = method untuk MENGAMBIL nilai atribut
    // Karena atribut protected, kita akses lewat getter
    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    // Method ABSTRACT = method tanpa isi, WAJIB diisi oleh class anak
    // KONSEP: ABSTRACTION (kita tahu ada method ini, tapi isinya nanti)
    public abstract void tampilkanInfo();
}

// ==========================================================
// CLASS: Dosen
// Class ini MEWARISI (extends) dari class Person
// KONSEP: INHERITANCE (Dosen adalah turunan dari Person)
// ==========================================================
class Dosen extends Person {

    // Atribut tambahan khusus untuk Dosen
    // KONSEP: ENCAPSULATION (dibuat private, hanya bisa diakses lewat getter)
    private String keahlian;

    // Constructor Dosen
    public Dosen(String id, String nama, String keahlian) {
        // super() = memanggil constructor dari class induk (Person)
        super(id, nama);
        this.keahlian = keahlian;
    }

    // Getter untuk mengambil keahlian
    public String getKeahlian() {
        return keahlian;
    }

    // MENGISI method abstract dari class Person
    // KONSEP: POLYMORPHISM (method sama "tampilkanInfo" tapi isinya BEDA)
    @Override
    public void tampilkanInfo() {
        System.out.println("  ID : " + id);
        System.out.println("  Nama : " + nama);
        System.out.println("  Keahlian : " + keahlian);
    }
}

// ==========================================================
// CLASS: Mahasiswa
// Class ini juga MEWARISI (extends) dari class Person
// KONSEP: INHERITANCE (Mahasiswa adalah turunan dari Person)
// ==========================================================
class Mahasiswa extends Person {

    // Atribut tambahan khusus untuk Mahasiswa
    private String jurusan;

    // Constructor Mahasiswa
    public Mahasiswa(String id, String nama, String jurusan) {
        super(id, nama); // memanggil constructor Person
        this.jurusan = jurusan;
    }

    // Getter
    public String getJurusan() {
        return jurusan;
    }

    // MENGISI method abstract dari class Person
    // KONSEP: POLYMORPHISM (method sama "tampilkanInfo" tapi isinya BEDA dari Dosen)
    @Override
    public void tampilkanInfo() {
        System.out.println("  ID : " + id);
        System.out.println("  Nama : " + nama);
        System.out.println("  Jurusan : " + jurusan);
    }
}

// ==========================================================
// CLASS: Konsultasi
// Class ini menyimpan data satu sesi konsultasi
// Di dalamnya ada objek Mahasiswa dan Dosen (KOMPOSISI)
// ==========================================================
class Konsultasi {

    // Atribut private (ENCAPSULATION)
    private Mahasiswa mahasiswa;
    private Dosen dosen;
    private String topik;
    private String tanggal;
    private String waktu;

    // Constructor
    public Konsultasi(Mahasiswa mahasiswa, Dosen dosen, String topik, String tanggal,String waktu) {
        this.mahasiswa = mahasiswa;
        this.dosen = dosen;
        this.topik = topik;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }

    // Method untuk menampilkan detail konsultasi
    public void tampilkanDetail() {
        System.out.println("  Mahasiswa : " + mahasiswa.getNama());
        System.out.println("  Dosen : " + dosen.getNama());
        System.out.println("  Topik : " + topik);
        System.out.println("  Tanggal : " + tanggal);
        System.out.println("  Waktu : " + waktu);
    }
}

// ==========================================================
// CLASS UTAMA: Main
// Di sinilah program dijalankan (method main ada di sini)
// ==========================================================
public class Main {

    // ArrayList = tempat menyimpan banyak objek (seperti array tapi fleksibel)
    static ArrayList<Dosen> daftarDosen = new ArrayList<>();
    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    static ArrayList<Konsultasi> daftarKonsultasi = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    // ======================================================
    // METHOD MAIN = titik awal program dijalankan
    // ======================================================
    public static void main(String[] args) {

        // Menambahkan data dosen contoh ke dalam list
        daftarDosen.add(new Dosen("D01", "Stenly Pungus", "Software Engineer"));
        daftarDosen.add(new Dosen("D02", "Debby Sondakh", "Research Method"));
        daftarDosen.add(new Dosen("D03", "Semmy Taju", "Artificial Intelligence"));
        daftarDosen.add(new Dosen("D04", "Green Sandag", "Expert System"));
        daftarDosen.add(new Dosen("D05", "Green Mandias", "DBMS"));
        daftarDosen.add(new Dosen("D06", "Andrew Liem", "Computer Network"));
        daftarDosen.add(new Dosen("D07", "Oktoverano Lengkong", "UI/UX"));

        // Menambahkan data mahasiswa contoh ke dalam list
        daftarMahasiswa.add(new Mahasiswa("M01", "Dion Kobi", "Teknik Informatika"));
        daftarMahasiswa.add(new Mahasiswa("M02", "Clio Marco Mataheru", "Teknik Informatika"));
        daftarMahasiswa.add(new Mahasiswa("M03", "George Stivo Kaunang", "Teknik Informatika"));
        daftarMahasiswa.add(new Mahasiswa("M04", "Geovani Waladow", "Teknik Informatika"));

        // Variabel untuk mengontrol perulangan menu
        boolean jalan = true;

        // PERULANGAN MENU UTAMA
        // Program akan terus berjalan sampai user pilih 0 (Keluar)
        while (jalan) {

            // Menampilkan menu
            System.out.println();
            System.out.println("========================================");
            System.out.println("   SISTEM KONSULTASI MAHASISWA - DOSEN  ");
            System.out.println("========================================");
            System.out.println("  1. Lihat Daftar Dosen");
            System.out.println("  2. Lihat Daftar Mahasiswa");
            System.out.println("  3. Buat Konsultasi");
            System.out.println("  4. Lihat Konsultasi");
            System.out.println("  5. Tambah Mahasiswa");
            System.out.println("  6. Hapus Data");
            System.out.println("  0. Keluar");
            System.out.println("========================================");
            System.out.print("  Pilih menu: ");

            // Membaca pilihan user
            int pilihan = input.nextInt();
            input.nextLine(); // mengosongkan sisa input

            // Menjalankan aksi sesuai pilihan user
            switch (pilihan) {
                case 1:
                    lihatDosen();
                    break;
                case 2:
                    lihatMahasiswa();
                    break;
                case 3:
                    buatKonsultasi();
                    break;
                case 4:
                    lihatKonsultasi();
                    break;
                case 5:
                    tambahMahasiswa();
                    break;
                case 6:
                    hapusData();
                    break;
                case 0:
                    // Keluar dari program
                    System.out.println("\n  Terima kasih! Sampai jumpa.\n");
                    jalan = false; // menghentikan while loop
                    break;
                default:
                    System.out.println("\n  Pilihan tidak valid!");
            }
        }

        input.close(); // menutup scanner
    }

    // ======================================================
    // METHOD: Menampilkan semua dosen
    // ======================================================
    static void lihatDosen() {
        System.out.println("\n--- DAFTAR DOSEN ---\n");

        // Perulangan untuk menampilkan setiap dosen
        for (int i = 0; i < daftarDosen.size(); i++) {
            System.out.println("Dosen " + (i + 1) + "");

            // Memanggil tampilkanInfo() --> POLYMORPHISM
            // Method yang dipanggil adalah versi milik class Dosen
            daftarDosen.get(i).tampilkanInfo();
            System.out.println();
        }
    }

    // ======================================================
    // METHOD: Menampilkan semua mahasiswa
    // ======================================================
    static void lihatMahasiswa() {
        System.out.println("\n--- DAFTAR MAHASISWA ---\n");

        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            System.out.println("Mahasiswa " + (i + 1) + "");

            // Memanggil tampilkanInfo() --> POLYMORPHISM
            // Method yang dipanggil adalah versi milik class Mahasiswa
            daftarMahasiswa.get(i).tampilkanInfo();
            System.out.println();
        }
    }

    // ======================================================
    // METHOD: Membuat konsultasi baru
    // ======================================================
    static void buatKonsultasi() {
        System.out.println("\n--- BUAT KONSULTASI ---\n");

        // LANGKAH 1: Pilih mahasiswa
        System.out.println("Pilih Mahasiswa:");
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + daftarMahasiswa.get(i).getNama());
        }
        System.out.print("Nomor: ");
        int noMhs = input.nextInt();
        input.nextLine();

        // Cek apakah nomor valid
        if (noMhs < 1 || noMhs > daftarMahasiswa.size()) {
            System.out.println("  Nomor tidak valid!");
            return; // kembali ke menu
        }

        // LANGKAH 2: Pilih dosen
        System.out.println("\nPilih Dosen:");
        for (int i = 0; i < daftarDosen.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + daftarDosen.get(i).getNama()
                    + " (" + daftarDosen.get(i).getKeahlian() + ")");
        }
        System.out.print("Nomor: ");
        int noDosen = input.nextInt();
        input.nextLine();

        if (noDosen < 1 || noDosen > daftarDosen.size()) {
            System.out.println("  Nomor tidak valid!");
            return;
        }

        // LANGKAH 3: Input topik dan tanggal
        System.out.print("Topik konsultasi: ");
        String topik = input.nextLine();

        System.out.print("Tanggal (cth: 20-02-2026): ");
        String tanggal = input.nextLine();

        System.out.print("Waktu (cth: 21:00): ");
        String waktu = input.nextLine();




        // LANGKAH 4: Membuat objek Konsultasi baru dan simpan ke list
        Mahasiswa mhs = daftarMahasiswa.get(noMhs - 1);
        Dosen dsn = daftarDosen.get(noDosen - 1);
        Konsultasi baru = new Konsultasi(mhs, dsn, topik, tanggal , waktu);
        daftarKonsultasi.add(baru);

        System.out.println("\n  Konsultasi berhasil dibuat!");
    }

    // ======================================================
    // METHOD: Menampilkan semua konsultasi
    // ======================================================
    static void lihatKonsultasi() {
        System.out.println("\n--- DAFTAR KONSULTASI ---\n");

        // Cek apakah sudah ada konsultasi
        if (daftarKonsultasi.isEmpty()) {
            System.out.println("  Belum ada konsultasi.");
            return;
        }

        // Tampilkan semua konsultasi
        for (int i = 0; i < daftarKonsultasi.size(); i++) {
            System.out.println("[Konsultasi " + (i + 1) + "]");
            daftarKonsultasi.get(i).tampilkanDetail();
            System.out.println();
        }
    }

    // ======================================================
    // METHOD: Menambah mahasiswa baru
    // ======================================================
    static void tambahMahasiswa() {
        System.out.println("\n--- TAMBAH MAHASISWA ---\n");

        System.out.print("ID (cth: M03): ");
        String id = input.nextLine();

        System.out.print("Nama: ");
        String nama = input.nextLine();

        System.out.print("Jurusan: ");
        String jurusan = input.nextLine();

        // Membuat objek Mahasiswa baru dan tambahkan ke list
        Mahasiswa baru = new Mahasiswa(id, nama, jurusan);
        daftarMahasiswa.add(baru);

        System.out.println("\n  Mahasiswa berhasil ditambahkan!");
    }

    // ======================================================
    // METHOD: Menu hapus data
    // ======================================================
    static void hapusData() {
        System.out.println("\n--- HAPUS DATA ---\n");
        System.out.println("  Hapus data apa?");
        System.out.println("  1. Hapus Mahasiswa");
        System.out.println("  2. Hapus Dosen");
        System.out.println("  3. Hapus Konsultasi");
        System.out.println("  0. Kembali ke Menu Utama");
        System.out.println("----------------------------");
        System.out.print("  Pilih: ");

        int pilihan = input.nextInt();
        input.nextLine();

        switch (pilihan) {
            case 1:
                hapusMahasiswa();
                break;
            case 2:
                hapusDosen();
                break;
            case 3:
                hapusKonsultasi();
                break;
            case 0:
                System.out.println("\n  Kembali ke menu utama...");
                break;
            default:
                System.out.println("\n  Pilihan tidak valid!");
        }
    }

    // ======================================================
    // METHOD: Hapus mahasiswa
    // ======================================================
    static void hapusMahasiswa() {
        System.out.println("\n--- HAPUS MAHASISWA ---\n");

        if (daftarMahasiswa.isEmpty()) {
            System.out.println("  Belum ada data mahasiswa.");
            return;
        }

        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + daftarMahasiswa.get(i).getNama()
                    + " (" + daftarMahasiswa.get(i).getId() + ")");
        }
        System.out.println("  0. Batal / Kembali");
        System.out.print("\n  Pilih nomor mahasiswa yang dihapus: ");

        int nomor = input.nextInt();
        input.nextLine();

        if (nomor == 0) {
            System.out.println("\n  Batal. Kembali ke menu utama...");
            return;
        }

        if (nomor < 1 || nomor > daftarMahasiswa.size()) {
            System.out.println("\n  Nomor tidak valid!");
            return;
        }

        Mahasiswa target = daftarMahasiswa.get(nomor - 1);
        System.out.print("\n  Yakin hapus mahasiswa \"" + target.getNama() + "\"? (y/n): ");
        String konfirmasi = input.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {
            daftarMahasiswa.remove(nomor - 1);
            System.out.println("\n  Mahasiswa berhasil dihapus!");
        } else {
            System.out.println("\n  Penghapusan dibatalkan.");
        }
    }

    // ======================================================
    // METHOD: Hapus dosen
    // ======================================================
    static void hapusDosen() {
        System.out.println("\n--- HAPUS DOSEN ---\n");

        if (daftarDosen.isEmpty()) {
            System.out.println("  Belum ada data dosen.");
            return;
        }

        for (int i = 0; i < daftarDosen.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + daftarDosen.get(i).getNama()
                    + " (" + daftarDosen.get(i).getId() + ")");
        }
        System.out.println("  0. Batal / Kembali");
        System.out.print("\n  Pilih nomor dosen yang dihapus: ");

        int nomor = input.nextInt();
        input.nextLine();

        if (nomor == 0) {
            System.out.println("\n  Batal. Kembali ke menu utama...");
            return;
        }

        if (nomor < 1 || nomor > daftarDosen.size()) {
            System.out.println("\n  Nomor tidak valid!");
            return;
        }

        Dosen target = daftarDosen.get(nomor - 1);
        System.out.print("\n  Yakin hapus dosen \"" + target.getNama() + "\"? (y/n): ");
        String konfirmasi = input.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {
            daftarDosen.remove(nomor - 1);
            System.out.println("\n  Dosen berhasil dihapus!");
        } else {
            System.out.println("\n  Penghapusan dibatalkan.");
        }
    }

    // ======================================================
    // METHOD: Hapus konsultasi
    // ======================================================
    static void hapusKonsultasi() {
        System.out.println("\n--- HAPUS KONSULTASI ---\n");

        if (daftarKonsultasi.isEmpty()) {
            System.out.println("  Belum ada data konsultasi.");
            return;
        }

        for (int i = 0; i < daftarKonsultasi.size(); i++) {
            System.out.println("  [" + (i + 1) + "]");
            daftarKonsultasi.get(i).tampilkanDetail();
            System.out.println();
        }
        System.out.println("  0. Batal / Kembali");
        System.out.print("  Pilih nomor konsultasi yang dihapus: ");

        int nomor = input.nextInt();
        input.nextLine();

        if (nomor == 0) {
            System.out.println("\n  Batal. Kembali ke menu utama...");
            return;
        }

        if (nomor < 1 || nomor > daftarKonsultasi.size()) {
            System.out.println("\n  Nomor tidak valid!");
            return;
        }

        System.out.print("\n  Yakin hapus konsultasi [" + nomor + "]? (y/n): ");
        String konfirmasi = input.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {
            daftarKonsultasi.remove(nomor - 1);
            System.out.println("\n  Konsultasi berhasil dihapus!");
        } else {
            System.out.println("\n  Penghapusan dibatalkan.");
        }
    }
}
