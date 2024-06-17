public class TugasMataKuliah23 {
    String kodeMK;
    String namaMK;
    int sks;

    public TugasMataKuliah23(String kodeMK, String namaMK, int sks) {
        this.kodeMK = kodeMK;
        this.namaMK = namaMK;
        this.sks = sks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TugasMataKuliah23 mataKuliah = (TugasMataKuliah23) obj;
        return kodeMK.equals(mataKuliah.kodeMK) && namaMK.equals(mataKuliah.namaMK);
    }

    @Override
    public int hashCode() {
        return kodeMK.hashCode() + namaMK.hashCode();
    }
}
