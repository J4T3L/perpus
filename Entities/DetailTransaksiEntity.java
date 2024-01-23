package Entities;

public class DetailTransaksiEntity {
    
    private BukuEntity bukuEntity;
    private UserEntity userEntity;

    public DetailTransaksiEntity(BukuEntity bukuEntity, UserEntity userEntity) {
        this.bukuEntity = bukuEntity;
        this.userEntity = userEntity;
    }

    public BukuEntity getBukuEntity() {
        return bukuEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setBukuEntity(BukuEntity bukuEntity) {
        this.bukuEntity = bukuEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
