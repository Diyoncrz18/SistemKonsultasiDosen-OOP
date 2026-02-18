import java.util.ArrayList;
import java.util.Scanner;

// ==================== ABSTRACT CLASS (Parent) ====================
// Tidak bisa di-instansiasi langsung, digunakan sebagai blueprint
abstract class Person {
    protected String id, nama;

    public Person(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId()   { return id; }
    public String getNama() { return nama; }

    // Abstract method → wajib di-override oleh class turunan
    public abstract void tampilkanInfo();
}

// ==================== CLASS DOSEN (Child) ====================
class Dosen extends Person {
    private String keahlian;

    public Dosen(String id, String nama, String keahlian) {
        super(id, nama); // memanggil constructor Person
        this.keahlian = keahlian;
    }

    public String getKeahlian() { return keahlian; }

    @Override
    public void tampilkanInfo() {
        System.out.println("  ID       : " + id);
        System.out.println("  Nama     : " + nama);
        System.out.println("  Keahlian : " + keahlian);
    }
}

// ==================== CLASS MAHASISWA (Child) ====================
class Mahasiswa extends Person {
    private String jurusan;

    public Mahasiswa(String id, String nama, String jurusan) {
        super(id, nama);
        this.jurusan = jurusan;
    }

    public String getJurusan() { return jurusan; }

    @Override
    public void tampilkanInfo() {
        System.out.println("  ID      : " + id);
        System.out.println("  Nama    : " + nama);
        System.out.println("  Jurusan : " + jurusan);
    }
}

// ==================== CLASS KONSULTASI ====================
// Menyimpan data 1 sesi konsultasi antara mahasiswa dan dosen
class Konsultasi {
    private Mahasiswa mahasiswa;
    private Dosen dosen;
    private String topik, tanggal, waktu;

    public Konsultasi(Mahasiswa mahasiswa, Dosen dosen,
                      String topik, String tanggal, String waktu) {
        this.mahasiswa = mahasiswa;
        this.dosen     = dosen;
        this.topik     = topik;
        this.tanggal   = tanggal;
        this.waktu     = waktu;
    }

    public void tampilkanDetail() {
        System.out.println("  Mahasiswa : " + mahasiswa.getNama());
        System.out.println("  Dosen     : " + dosen.getNama());
        System.out.println("  Topik     : " + topik);
        System.out.println("  Tanggal   : " + tanggal);
        System.out.println("  Waktu     : " + waktu);
    }
}

// ==================== MAIN CLASS ====================
public class Main {

    // Penyimpanan data menggunakan ArrayList (struktur data dinamis)
    static ArrayList<Dosen>      daftarDosen      = new ArrayList<>();
    static ArrayList<Mahasiswa>  daftarMahasiswa  = new ArrayList<>();
    static ArrayList<Konsultasi> daftarKonsultasi = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // ── Inisialisasi data awal ──
        daftarDosen.add(new Dosen("D01", "Stenly Pungus",      "Software Engineer"));
        daftarDosen.add(new Dosen("D02", "Debby Sondakh",      "Research Method"));
        daftarDosen.add(new Dosen("D03", "Semmy Taju",         "Artificial Intelligence"));
        daftarDosen.add(new Dosen("D04", "Green Sandag",       "Expert System"));
        daftarDosen.add(new Dosen("D05", "Green Mandias",      "DBMS"));
        daftarDosen.add(new Dosen("D06", "Andrew Liem",        "Computer Network"));
        daftarDosen.add(new Dosen("D07", "Oktoverano Lengkong","UI/UX"));

        daftarMahasiswa.add(new Mahasiswa("M01", "Dion Kobi",             "Teknik Informatika"));
        daftarMahasiswa.add(new Mahasiswa("M02", "Clio Marco Mataheru",   "Teknik Informatika"));
        daftarMahasiswa.add(new Mahasiswa("M03", "George Stivo Kaunang",  "Teknik Informatika"));
        daftarMahasiswa.add(new Mahasiswa("M04", "Geovani Waladow",       "Teknik Informatika"));

        // ── Loop menu utama ──
        boolean jalan = true;
        while (jalan) {
            tampilkanMenu();
            int pilihan = input.nextInt(); input.nextLine();

            switch (pilihan) {
                case 1: lihatDosen();        break;
                case 2: lihatMahasiswa();    break;
                case 3: buatKonsultasi();    break;
                case 4: lihatKonsultasi();   break;
                case 5: tambahMahasiswa();   break;
                case 6: hapusData();         break;
                case 0:
                    System.out.println("\n  Terima kasih! Sampai jumpa.\n");
                    jalan = false;
                    break;
                default:
                    System.out.println("\n  Pilihan tidak valid!");
            }
        }
        input.close();
    }

    // ── Tampilan menu utama ──
    static void tampilkanMenu() {
        System.out.println("\n========================================");
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
    }

    // ==================== FITUR LIHAT ====================
    static void lihatDosen() {
        System.out.println("\n--- DAFTAR DOSEN ---\n");
        for (int i = 0; i < daftarDosen.size(); i++) {
            System.out.println("Dosen " + (i + 1));
            daftarDosen.get(i).tampilkanInfo();
            System.out.println();
        }
    }

    static void lihatMahasiswa() {
        System.out.println("\n--- DAFTAR MAHASISWA ---\n");
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            System.out.println("Mahasiswa " + (i + 1));
            daftarMahasiswa.get(i).tampilkanInfo();
            System.out.println();
        }
    }

    static void lihatKonsultasi() {
        System.out.println("\n--- DAFTAR KONSULTASI ---\n");
        if (daftarKonsultasi.isEmpty()) {
            System.out.println("  Belum ada konsultasi."); return;
        }
        for (int i = 0; i < daftarKonsultasi.size(); i++) {
            System.out.println("[Konsultasi " + (i + 1) + "]");
            daftarKonsultasi.get(i).tampilkanDetail();
            System.out.println();
        }
    }

    // ==================== FITUR TAMBAH ====================
    static void buatKonsultasi() {
        System.out.println("\n--- BUAT KONSULTASI ---\n");

        // Pilih mahasiswa
        System.out.println("Pilih Mahasiswa:");
        for (int i = 0; i < daftarMahasiswa.size(); i++)
            System.out.println("  " + (i+1) + ". " + daftarMahasiswa.get(i).getNama());
        System.out.print("Nomor: ");
        int noMhs = input.nextInt(); input.nextLine();
        if (noMhs < 1 || noMhs > daftarMahasiswa.size()) {
            System.out.println("  Nomor tidak valid!"); return;
        }

        // Pilih dosen
        System.out.println("\nPilih Dosen:");
        for (int i = 0; i < daftarDosen.size(); i++)
            System.out.println("  " + (i+1) + ". " + daftarDosen.get(i).getNama()
                    + " (" + daftarDosen.get(i).getKeahlian() + ")");
        System.out.print("Nomor: ");
        int noDosen = input.nextInt(); input.nextLine();
        if (noDosen < 1 || noDosen > daftarDosen.size()) {
            System.out.println("  Nomor tidak valid!"); return;
        }

        // Input detail konsultasi
        System.out.print("Topik konsultasi      : "); String topik   = input.nextLine();
        System.out.print("Tanggal (20-02-2026)  : "); String tanggal = input.nextLine();
        System.out.print("Waktu   (21:00)       : "); String waktu   = input.nextLine();

        daftarKonsultasi.add(new Konsultasi(
            daftarMahasiswa.get(noMhs - 1),
            daftarDosen.get(noDosen - 1),
            topik, tanggal, waktu
        ));
        System.out.println("\n  Konsultasi berhasil dibuat!");
    }

    static void tambahMahasiswa() {
        System.out.println("\n--- TAMBAH MAHASISWA ---\n");
        System.out.print("ID (cth: M05)  : "); String id      = input.nextLine();
        System.out.print("Nama           : "); String nama    = input.nextLine();
        System.out.print("Jurusan        : "); String jurusan = input.nextLine();
        daftarMahasiswa.add(new Mahasiswa(id, nama, jurusan));
        System.out.println("\n  Mahasiswa berhasil ditambahkan!");
    }

    // ==================== FITUR HAPUS ====================
    static void hapusData() {
        System.out.println("\n--- HAPUS DATA ---\n");
        System.out.println("  1. Hapus Mahasiswa");
        System.out.println("  2. Hapus Dosen");
        System.out.println("  3. Hapus Konsultasi");
        System.out.println("  0. Kembali");
        System.out.print("  Pilih: ");
        int pilihan = input.nextInt(); input.nextLine();

        switch (pilihan) {
            case 1: hapusMahasiswa();   break;
            case 2: hapusDosen();       break;
            case 3: hapusKonsultasi();  break;
            case 0: System.out.println("\n  Kembali ke menu utama..."); break;
            default: System.out.println("\n  Pilihan tidak valid!");
        }
    }

    // Helper: logika hapus item dari list (dipakai ulang untuk 3 jenis data)
    static boolean konfirmasiHapus(String namaItem) {
        System.out.print("\n  Yakin hapus \"" + namaItem + "\"? (y/n): ");
        return input.nextLine().equalsIgnoreCase("y");
    }

    static void hapusMahasiswa() {
        System.out.println("\n--- HAPUS MAHASISWA ---\n");
        if (daftarMahasiswa.isEmpty()) { System.out.println("  Belum ada data."); return; }

        for (int i = 0; i < daftarMahasiswa.size(); i++)
            System.out.println("  " + (i+1) + ". " + daftarMahasiswa.get(i).getNama()
                    + " (" + daftarMahasiswa.get(i).getId() + ")");
        System.out.println("  0. Batal");
        System.out.print("\n  Pilih nomor: ");
        int nomor = input.nextInt(); input.nextLine();

        if (nomor == 0) { System.out.println("\n  Dibatalkan."); return; }
        if (nomor < 1 || nomor > daftarMahasiswa.size()) { System.out.println("\n  Tidak valid!"); return; }

        if (konfirmasiHapus(daftarMahasiswa.get(nomor - 1).getNama())) {
            daftarMahasiswa.remove(nomor - 1);
            System.out.println("\n  Mahasiswa berhasil dihapus!");
        } else {
            System.out.println("\n  Penghapusan dibatalkan.");
        }
    }

    static void hapusDosen() {
        System.out.println("\n--- HAPUS DOSEN ---\n");
        if (daftarDosen.isEmpty()) { System.out.println("  Belum ada data."); return; }

        for (int i = 0; i < daftarDosen.size(); i++)
            System.out.println("  " + (i+1) + ". " + daftarDosen.get(i).getNama()
                    + " (" + daftarDosen.get(i).getId() + ")");
        System.out.println("  0. Batal");
        System.out.print("\n  Pilih nomor: ");
        int nomor = input.nextInt(); input.nextLine();

        if (nomor == 0) { System.out.println("\n  Dibatalkan."); return; }
        if (nomor < 1 || nomor > daftarDosen.size()) { System.out.println("\n  Tidak valid!"); return; }

        if (konfirmasiHapus(daftarDosen.get(nomor - 1).getNama())) {
            daftarDosen.remove(nomor - 1);
            System.out.println("\n  Dosen berhasil dihapus!");
        } else {
            System.out.println("\n  Penghapusan dibatalkan.");
        }
    }

    static void hapusKonsultasi() {
        System.out.println("\n--- HAPUS KONSULTASI ---\n");
        if (daftarKonsultasi.isEmpty()) { System.out.println("  Belum ada data."); return; }

        for (int i = 0; i < daftarKonsultasi.size(); i++) {
            System.out.println("  [" + (i+1) + "]");
            daftarKonsultasi.get(i).tampilkanDetail();
            System.out.println();
        }
        System.out.println("  0. Batal");
        System.out.print("  Pilih nomor: ");
        int nomor = input.nextInt(); input.nextLine();

        if (nomor == 0) { System.out.println("\n  Dibatalkan."); return; }
        if (nomor < 1 || nomor > daftarKonsultasi.size()) { System.out.println("\n  Tidak valid!"); return; }

        if (konfirmasiHapus("Konsultasi " + nomor)) {
            daftarKonsultasi.remove(nomor - 1);
            System.out.println("\n  Konsultasi berhasil dihapus!");
        } else {
            System.out.println("\n  Penghapusan dibatalkan.");
        }
    }
}