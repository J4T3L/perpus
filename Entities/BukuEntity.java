package Entities;

public class BukuEntity {

    private String genre;
    private String judul;
    private String penerbit;
    private String tahun;
    private boolean stok;

    public BukuEntity(String genre, String judul, String penerbit, String tahun, boolean stok) {
    
        this.genre = genre;
        this.judul = judul;
        this.penerbit = penerbit;
        this.tahun = tahun;
        this.stok = stok;
    }

    public BukuEntity() {
        
    }

    
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getPenerbit() {
        return penerbit;
    }
    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
    public String getTahun() {
        return tahun;
    }
    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
    public boolean isStok() {
        return stok;
    }
    public void setStok(boolean stok) {
        this.stok = stok;
    }

    

}
