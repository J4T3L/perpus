package Entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class BukuEntity {

    private String genre;
    private String judul;
    private String penerbit;
    private String tahun;
    private boolean stok;
//
    int daysleft;
    LocalDate start;
	LocalDate finish;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");



    public BukuEntity(String genre, String judul, String penerbit, String tahun, boolean stok) {
    
        this.genre = genre;
        this.judul = judul;
        this.penerbit = penerbit;
        this.tahun = tahun;
        this.stok = stok;

        start = LocalDate.now();
		finish = start.plusDays(14); //waktu pinjam
        daysleft = Period.between(start, finish).getDays();

        //
        // this.start = start;
		// this.finish = finish;
		this.daysleft = Period.between(finish, LocalDate.now()).getDays();

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

    //hari
    public String getStart() {
		return formatter.format(start);
	}
	
	public String getFinish() {
		return formatter.format(finish);
	}
	
	public int getDaysLeft() {
		return Period.between(finish, LocalDate.now()).getDays();
	}

}
