package hcmute.nhom7.foody.model;

public class MonAn {
    private String image;
    private String tenMonAn;
    private String moTa;
    private String gia;

    public MonAn(String image, String tenMonAn, String moTa, String gia) {
        this.image = image;
        this.tenMonAn = tenMonAn;
        this.moTa = moTa;
        this.gia = gia;
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
}
