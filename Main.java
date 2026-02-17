import java.util.ArrayList;
import java.util.Scanner;

// class induk, abstract jadi ga bisa di-new langsung
// dosen sama mahasiswa nanti extends dari sini
abstract class Person {

    protected String nama;
    protected String id;

    public Person(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    // wajib di-override sama class anak
    public abstract void tampilkanInfo();
}

// class dosen, turunan dari Person
class Dosen extends Person {

    private String keahlian;

    public Dosen(String id, String nama, String keahlian) {
        super(id, nama);
        this.keahlian = keahlian;
    }

    public String getKeahlian() {
        return keahlian;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("  ID : " + id);
        System.out.println("  Nama : " + nama);
        System.out.println("  Keahlian : " + keahlian);
    }
}

// class mahasiswa, juga turunan dari Person
class Mahasiswa extends Person {

    private String jurusan;

    public Mahasiswa(String id, String nama, String jurusan) {
        super(id, nama);
        this.jurusan = jurusan;
    }

    public String getJurusan() {
        return jurusan;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("  ID : " + id);
        System.out.println("  Nama : " + nama);
        System.out.println("  Jurusan : " + jurusan);
    }
}

// menyimpan data satu sesi konsultasi
class Konsultasi {

    private Mahasiswa mahasiswa;
    private Dosen dosen;
    private String topik;
    private String tanggal;
    private String waktu;

    public Konsultasi(Mahasiswa mahasiswa, Dosen dosen, String topik, String tanggal,String waktu) {
        this.mahasiswa = mahasiswa;
        this.dosen = dosen;
        this.topik = topik;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }

    public void tampilkanDetail() {
        System.out.println("  Mahasiswa : " + mahasiswa.getNama());
        System.out.println("  Dosen : " + dosen.getNama());
        System.out.println("  Topik : " + topik);
        System.out.println("  Tanggal : " + tanggal);
        System.out.println("  Waktu : " + waktu);
    }
}

public class Main {

    static ArrayList<Dosen> daftarDosen = new ArrayList<>();
    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    static ArrayList<Konsultasi> daftarKonsultasi = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // data dosen
        daftarDosen.add(new Dosen("D01", "Stenly Pungus", "Software Engineer"));
        daftarDosen.add(new Dosen("D02", "Debby Sondakh", "Research Method"));
        daftarDosen.add(new Dosen("D03", "Semmy Taju", "Artificial Intelligence"));
        daftarDosen.add(new Dosen("D04", "Green Sandag", "Expert System"));
        daftarDosen.add(new Dosen("D05", "Green Mandias", "DBMS"));
        daftarDosen.add(new Dosen("D06", "Andrew Liem", "Computer Network"));
        daftarDosen.add(new Dosen("D07", "Oktoverano Lengkong", "UI/UX"));

        // data mahasiswa
        daftarMahasiswa.add(new Mahasiswa("M01", "Dion Kobi", "Teknik Informatika"));
        daftarMahasiswa.add(new Mahasiswa("M02", "Clio Marco Mataheru", "Teknik Informatika"));
        daftarMahasiswa.add(new Mahasiswa("M03", "George Stivo Kaunang", "Teknik Informatika"));
        daftarMahasiswa.add(new Mahasiswa("M04", "Geovani Waladow", "Teknik Informatika"));

        boolean jalan = true;

        // loop menu utama
        while (jalan) {
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

            int pilihan = input.nextInt();
            input.nextLine();

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
                    System.out.println("\n  Terima kasih! Sampai jumpa.\n");
                    jalan = false;
                    break;
                default:
                    System.out.println("\n  Pilihan tidak valid!");
            }
        }

        input.close();
    }

    // tampilkan semua dosen
    static void lihatDosen() {
        System.out.println("\n--- DAFTAR DOSEN ---\n");

        for (int i = 0; i < daftarDosen.size(); i++) {
            System.out.println("Dosen " + (i + 1) + "");
            daftarDosen.get(i).tampilkanInfo();
            System.out.println();
        }
    }

    // tampilkan semua mahasiswa
    static void lihatMahasiswa() {
        System.out.println("\n--- DAFTAR MAHASISWA ---\n");

        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            System.out.println("Mahasiswa " + (i + 1) + "");
            daftarMahasiswa.get(i).tampilkanInfo();
            System.out.println();
        }
    }

    // buat konsultasi baru
    static void buatKonsultasi() {
        System.out.println("\n--- BUAT KONSULTASI ---\n");

        // pilih mahasiswa dulu
        System.out.println("Pilih Mahasiswa:");
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + daftarMahasiswa.get(i).getNama());
        }
        System.out.print("Nomor: ");
        int noMhs = input.nextInt();
        input.nextLine();

        if (noMhs < 1 || noMhs > daftarMahasiswa.size()) {
            System.out.println("  Nomor tidak valid!");
            return;
        }

        // pilih dosen
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

        // input topik, tanggal, waktu
        System.out.print("Topik konsultasi: ");
        String topik = input.nextLine();

        System.out.print("Tanggal (cth: 20-02-2026): ");
        String tanggal = input.nextLine();

        System.out.print("Waktu (cth: 21:00): ");
        String waktu = input.nextLine();

        // simpan konsultasi
        Mahasiswa mhs = daftarMahasiswa.get(noMhs - 1);
        Dosen dsn = daftarDosen.get(noDosen - 1);
        Konsultasi baru = new Konsultasi(mhs, dsn, topik, tanggal , waktu);
        daftarKonsultasi.add(baru);

        System.out.println("\n  Konsultasi berhasil dibuat!");
    }

    // lihat semua konsultasi yg udah dibuat
    static void lihatKonsultasi() {
        System.out.println("\n--- DAFTAR KONSULTASI ---\n");

        if (daftarKonsultasi.isEmpty()) {
            System.out.println("  Belum ada konsultasi.");
            return;
        }

        for (int i = 0; i < daftarKonsultasi.size(); i++) {
            System.out.println("[Konsultasi " + (i + 1) + "]");
            daftarKonsultasi.get(i).tampilkanDetail();
            System.out.println();
        }
    }

    // tambah mahasiswa baru
    static void tambahMahasiswa() {
        System.out.println("\n--- TAMBAH MAHASISWA ---\n");

        System.out.print("ID (cth: M03): ");
        String id = input.nextLine();

        System.out.print("Nama: ");
        String nama = input.nextLine();

        System.out.print("Jurusan: ");
        String jurusan = input.nextLine();

        Mahasiswa baru = new Mahasiswa(id, nama, jurusan);
        daftarMahasiswa.add(baru);

        System.out.println("\n  Mahasiswa berhasil ditambahkan!");
    }

    // menu hapus data
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
