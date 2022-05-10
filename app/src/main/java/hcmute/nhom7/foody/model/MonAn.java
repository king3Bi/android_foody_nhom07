package hcmute.nhom7.foody.model;

import android.graphics.Bitmap;

import hcmute.nhom7.foody.utils.ImageUtils;

public class MonAn {
    private int id;
    private String image;
    private String tenMonAn;
    private String moTa;
    private String gia;

    public MonAn(int id, String image, String tenMonAn, String moTa, String gia) {
        this.id = id;
        this.image = image;
        this.tenMonAn = tenMonAn;
        this.moTa = moTa;
        this.gia = gia;
    }

    public MonAn(String image, String tenMonAn, String moTa, String gia) {
        this.image = image;
        this.tenMonAn = tenMonAn;
        this.moTa = moTa;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return "MonAn{" +
                "id=" + id +
                ", tenMonAn='" + tenMonAn + '\'' +
                ", gia='" + gia + '\'' +
                '}';
    }

    public Bitmap getBitMapImg() {
        return ImageUtils.decodeImg(this.image);
    }
}
