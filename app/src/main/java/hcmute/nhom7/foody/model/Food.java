package hcmute.nhom7.foody.model;

import android.graphics.Bitmap;

import hcmute.nhom7.foody.utils.ImageUtils;

public class Food {
    private int id;
    private String image;
    private String name;
    private String description;
    private double price;
    private int restaurantId;

    public Food(int id, String image, String name, String description, double price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Food(String image, String name, String description, double price, int restaurantId) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setMoTa(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceString() {
        return Double.toString(this.price) + " VND";
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "MonAn{" +
                "id=" + id +
                ", tenMonAn='" + name + '\'' +
                ", gia='" + price + '\'' +
                '}';
    }

    public Bitmap getBitMapImg() {
        return ImageUtils.decodeImg(this.image);
    }
}
