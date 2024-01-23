package Models;

import java.util.ArrayList;
import Entities.BukuEntity;

public class BukuModel {
    
    private static ArrayList<BukuEntity> buku = new ArrayList<>();

    public static void initialData(){
        buku.add(new BukuEntity("Horor", "Horor 1", "gramed", "2022", true));
        buku.add(new BukuEntity("Horor", "Horor 2", "gramed", "2022", true));
        buku.add(new BukuEntity("Horor", "Horor 3", "gramed", "2022", false));
    }

    public static void create(Object data)
    {
        buku.add((BukuEntity)data);
    }

    public static void update(int index,Object data){
        buku.set(index, (BukuEntity)data);
    }

    public static void delete(int index){
        buku.remove(index);
    }

    public static void getIndex(int index)
    {
        buku.get(index);
    }

    public static ArrayList<BukuEntity> read()
    {
        return buku;
    }
}
