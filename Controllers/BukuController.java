package Controllers;

import java.util.ArrayList;

import Entities.BukuEntity;
import Interfaces.BukuInterface;
import Models.BukuModel;

public class BukuController implements BukuInterface {

    @Override
    public void create(String genre, String judul, String penerbit, String tahun, boolean stok) {
      BukuEntity bukuEntity=new BukuEntity();
      bukuEntity.setGenre(genre);
      bukuEntity.setJudul(judul);
      bukuEntity.setPenerbit(penerbit);
      bukuEntity.setTahun(tahun);
      bukuEntity.setStok(stok);
      BukuModel.create(bukuEntity);
    }

    @Override
    public void delete(int index) {
        BukuModel.delete(index);
    }

    @Override
    public BukuEntity getIndex(int index) {
        return BukuModel.read().get(index);
    }

    @Override
    public ArrayList<BukuEntity> read() {
           return BukuModel.read();
    }

    @Override
    public void update(int index, String genre, String judul, String penerbit, String tahun, boolean stok) {
      BukuEntity bukuEntity = new BukuEntity();
      bukuEntity.setGenre(genre);
      bukuEntity.setJudul(judul);
      bukuEntity.setPenerbit(penerbit);
      bukuEntity.setTahun(tahun);
      bukuEntity.setStok(stok);
      BukuModel.create(bukuEntity);
        
    }

    
    
}
