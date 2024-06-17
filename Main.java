import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.LinkedList; 
import java.util.Queue;

public class Main {
    static ArrayList<TugasMahasiswa23> daftarMahasiswa = new ArrayList<>();
    static ArrayList<TugasMataKuliah23> daftarMataKuliah = new ArrayList<>();
    static ArrayList<TugasNilai23> daftarNilai = new ArrayList<>();
    static Queue<TugasMahasiswa23> queueMahasiswa = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n*************************************");
            System.out.println("SISTEM PENGOLAHAN DATA NILAI MAHASISWA SEMESTER");
            System.out.println("*************************************");
            System.out.println("1. Input Nilai");
            System.out.println("2. Tampil Nilai");
            System.out.println("3. Mencari Nilai Mahasiswa");
            System.out.println("4. Urut Data Nilai");
            System.out.println("5. Hapus Data Mahasiswa");
            System.out.println("6. Keluar");
            System.out.println("*************************************");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan baris baru

            switch (pilihan) {
                case 1:
                    inputNilai();
                    break;
                case 2:
                    tampilNilai();
                    break;
                case 3:
                    mencariNilaiMahasiswa();
                    break;
                case 4:
                    urutDataNilai();
                    break;
                case 5:
                    hapusDataMahasiswa();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void inputNilai() {
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scanner.nextLine();
        System.out.print("Masukkan Nama Mahasiswa: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan No. Telp Mahasiswa: ");
        String noTelp = scanner.nextLine();
        
        TugasMahasiswa23 mahasiswa = findMahasiswaByNim(nim);
        if (mahasiswa == null) {
            mahasiswa = new TugasMahasiswa23(nim, nama, noTelp);
            daftarMahasiswa.add(mahasiswa);
        }

        System.out.print("Masukkan Kode Mata Kuliah: ");
        String kodeMK = scanner.nextLine();
        System.out.print("Masukkan Nama Mata Kuliah: ");
        String namaMK = scanner.nextLine();
        System.out.print("Masukkan SKS Mata Kuliah: ");
        int sks = scanner.nextInt();
        scanner.nextLine(); // Membersihkan baris baru
        
        TugasMataKuliah23 mataKuliah = findMataKuliahByKode(kodeMK);
        if (mataKuliah == null) {
            mataKuliah = new TugasMataKuliah23(kodeMK, namaMK, sks);
            daftarMataKuliah.add(mataKuliah);
        }

        System.out.println("\nDAFTAR MAHASISWA");
        System.out.println("****************************************");
        System.out.printf("%-10s %-20s %-15s\n", "NIM", "Nama", "No. Telp");
        for (TugasMahasiswa23 mhs : daftarMahasiswa) {
            System.out.printf("%-10s %-20s %-15s\n", mhs.nim, mhs.nama, mhs.noTelp);
        }

        System.out.print("\nPilih Mahasiswa by NIM: ");
        nim = scanner.nextLine();
        mahasiswa = findMahasiswaByNim(nim);

        if (mahasiswa == null) {
            System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
            return;
        }

        System.out.println("\nDAFTAR MATA KULIAH");
        System.out.println("****************************************");
        System.out.printf("%-10s %-30s %-5s\n", "Kode", "Mata Kuliah", "SKS");
        for (TugasMataKuliah23 mk : daftarMataKuliah) {
            System.out.printf("%-10s %-30s %-5d\n", mk.kodeMK, mk.namaMK, mk.sks);
        }

        System.out.print("\nPilih Mata Kuliah by Kode: ");
        kodeMK = scanner.nextLine();
        mataKuliah = findMataKuliahByKode(kodeMK);

        if (mataKuliah == null) {
            System.out.println("Mata Kuliah dengan Kode tersebut tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan Nilai Akhir: ");
        double nilaiAkhir = scanner.nextDouble();
        scanner.nextLine(); // Membersihkan baris baru

        TugasNilai23 nilai = new TugasNilai23(mahasiswa, mataKuliah, nilaiAkhir);
        daftarNilai.add(nilai);

        System.out.println("Data nilai berhasil ditambahkan.");
    }

    public static void tampilNilai() {
        System.out.println("\nDAFTAR NILAI MAHASISWA");
        System.out.println("****************************************");
        System.out.printf("%-10s %-20s %-15s %-30s %-5s %-5s\n", "NIM", "Nama", "No. Telp", "Mata Kuliah", "SKS", "Nilai");
        for (TugasNilai23 nilai : daftarNilai) {
            System.out.printf("%-10s %-20s %-15s %-30s %-5d %-5.2f\n",
                    nilai.mahasiswa.nim, nilai.mahasiswa.nama, nilai.mahasiswa.noTelp, nilai.mataKuliah.namaMK, nilai.mataKuliah.sks, nilai.nilaiAkhir);
        }
    }

    public static void mencariNilaiMahasiswa() {
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scanner.nextLine();
        boolean found = false;
        int totalSks = 0;
        System.out.println("\nDAFTAR NILAI MAHASISWA");
        System.out.println("****************************************");
        System.out.printf("%-10s %-20s %-30s\n", "NIM", "Nama", "Mata Kuliah");
        for (TugasNilai23 nilai : daftarNilai) {
            if (nilai.mahasiswa.nim.equals(nim)) {
                System.out.printf("%-10s %-20s %-30s\n",
                        nilai.mahasiswa.nim, nilai.mahasiswa.nama, nilai.mataKuliah.namaMK);
                totalSks += nilai.mataKuliah.sks;
                found = true;
            }
        }
        if (found) {
            System.out.println("****************************************");
            System.out.println("Total SKS yang diambil: " + totalSks);
        } else {
            System.out.println("NIM Mahasiswa tidak ditemukan.");
        }
    }

    public static void urutDataNilai() {
        Collections.sort(daftarNilai, Comparator.comparingDouble(nilai -> nilai.nilaiAkhir));
        System.out.println("\nDAFTAR NILAI MAHASISWA (DIURUTKAN)");
        System.out.println("****************************************");
        System.out.printf("%-10s %-20s %-30s\n", "NIM", "Nama", "Mata Kuliah");
        for (TugasNilai23 nilai : daftarNilai) {
            System.out.printf("%-10s %-20s %-30s\n",
                    nilai.mahasiswa.nim, nilai.mahasiswa.nama, nilai.mataKuliah.namaMK);
        }
    }

    public static TugasMahasiswa23 findMahasiswaByNim(String nim) {
        for (TugasMahasiswa23 mhs : daftarMahasiswa) {
            if (mhs.nim.equals(nim)) {
                return mhs;
            }
        }
        return null;
    }

    public static TugasMataKuliah23 findMataKuliahByKode(String kodeMK) {
        for (TugasMataKuliah23 mk : daftarMataKuliah) {
            if (mk.kodeMK.equals(kodeMK)) {
                return mk;
            }
        }
        return null;
    }
    public static void hapusDataMahasiswa() {
        System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
        String nim = scanner.nextLine();
        boolean dihapus = false;
        // Iterasi melalui daftar nilai
        for (int i = 0; i < daftarNilai.size(); i++) {
            TugasNilai23 nilai = daftarNilai.get(i);
            if (nilai.mahasiswa.nim.equals(nim)) {
                daftarNilai.remove(i);
                dihapus = true;
                i--; 
            }
        }
        if (dihapus) {
            System.out.println("Mahasiswa dengan NIM " + nim + " telah dihapus.");
            // Perbarui tampilan data
            tampilNilai();
        } else {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }
    
}  

