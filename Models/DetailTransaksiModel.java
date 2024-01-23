package Models;

import java.util.ArrayList;

import Entities.DetailTransaksiEntity;
import Entities.BukuEntity;
import Entities.UserEntity;
public class DetailTransaksiModel {
    
    private static ArrayList<DetailTransaksiEntity> detailTransaksiEntities = new ArrayList<>();
    
    public static void initialData(){
        detailTransaksiEntities.add(new DetailTransaksiEntity(new BukuEntity("Horor", "Horor 1", "gramed", "2022", true), new UserEntity("user1")));
        detailTransaksiEntities.add(new DetailTransaksiEntity(new BukuEntity("Horor", "Horor 2", "gramed", "2022", true), new UserEntity("user2")));
        detailTransaksiEntities.add(new DetailTransaksiEntity(new BukuEntity("Horor", "Horor 3", "gramed", "2022", false), new UserEntity("user3")));
    }

    public static void create(Object data)
    {
        detailTransaksiEntities.add((DetailTransaksiEntity)data);
    }

    public static DetailTransaksiEntity getIndex(int index)
    {
        return detailTransaksiEntities.get(index);
    }

    public static ArrayList<DetailTransaksiEntity> read()
    {
        return detailTransaksiEntities;
    }
}
