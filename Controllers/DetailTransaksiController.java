package Controllers;

import java.util.ArrayList;

import Entities.DetailTransaksiEntity;
import Entities.BukuEntity;
import Entities.UserEntity;
import Models.DetailTransaksiModel;

public class DetailTransaksiController {
    
    public static void create(BukuEntity bukuEntity,UserEntity userEntity){
        DetailTransaksiEntity detailTransaksiEntity = new DetailTransaksiEntity(bukuEntity, userEntity);
        detailTransaksiEntity.setBukuEntity(bukuEntity);
        detailTransaksiEntity.setUserEntity(userEntity);
        DetailTransaksiModel.create(detailTransaksiEntity);

    }

     public ArrayList<DetailTransaksiEntity> read() {
           return DetailTransaksiModel.read();
    }
}
