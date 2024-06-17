public class TugasMahasiswa23 {
    String nim;
    String nama;
    String noTelp;

    public TugasMahasiswa23(String nim, String nama, String noTelp) {
        this.nim = nim;
        this.nama = nama;
        this.noTelp = noTelp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TugasMahasiswa23 mahasiswa = (TugasMahasiswa23) obj;
        return nim.equals(mahasiswa.nim) && nama.equals(mahasiswa.nama);
    }

    @Override
    public int hashCode() {
        return nim.hashCode() + nama.hashCode();
    }
}
