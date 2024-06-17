import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ListMahasiswa23 {
    List<Mahasiswa23> mahasiswas = new ArrayList<>();

    public void tambah(Mahasiswa23... mahasiswa) {
        mahasiswas.addAll(Arrays.asList(mahasiswa));
    }

    public void hapus(int index) {
        mahasiswas.remove(index);
    }

    public void update(int index, Mahasiswa23 mhs) {
        mahasiswas.set(index, mhs);
    }

    public void tampil() {
        mahasiswas.stream().forEach(mhs -> {
            System.out.println("" + mhs.toString());
        });
    }

    int binarySearch(String nim) {
        Collections.sort(mahasiswas, (m1, m2) -> m1.nim.compareTo(m2.nim)); 
        Mahasiswa23 key = new Mahasiswa23(nim, "", ""); 
        int index = Collections.binarySearch(mahasiswas, key, (m1, m2) -> m1.nim.compareTo(m2.nim));
        return index;
    }

    public void sortAscending() {
        mahasiswas.sort((m1, m2) -> m1.nim.compareTo(m2.nim));
    }

    public void sortDescending() {
        mahasiswas.sort((m1, m2) -> m2.nim.compareTo(m1.nim));
    }

    public static void main(String[] args) {
        ListMahasiswa23 lm = new ListMahasiswa23();
        Mahasiswa23 m = new Mahasiswa23("201234", "Noureen", "021xx1");
        Mahasiswa23 m2 = new Mahasiswa23("201235", "Akhleema", "021xx2");
        Mahasiswa23 m3 = new Mahasiswa23("201236", "Shannum", "021xx3");

        lm.tambah(m, m2, m3);
        lm.tampil();
        lm.update(lm.binarySearch("201235"), new Mahasiswa23("202135", "Akhleema Lela", "021xx2"));
        System.out.println("");
        lm.tampil();
        
        System.out.println("\nSorting Ascending:");
        lm.sortAscending();
        lm.tampil();
        
        System.out.println("\nSorting Descending:");
        lm.sortDescending();
        lm.tampil();
    }
}