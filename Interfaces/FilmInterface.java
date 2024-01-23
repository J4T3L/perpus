package Interfaces;

import java.util.ArrayList;

import Entities.BukuEntity;

public interface FilmInterface {
    public ArrayList<BukuEntity> read();
    public void create( String genre, String judul, String penerbit, String tahun, boolean stok);
    public void update(int index,String genre, String judul, String penerbit, String tahun, boolean stok);
    public void delete(int index);
    public BukuEntity getIndex(int index);
}
