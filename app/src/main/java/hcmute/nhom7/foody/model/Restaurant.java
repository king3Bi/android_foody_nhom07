package hcmute.nhom7.foody.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import hcmute.nhom7.foody.utils.ImageUtils;

public class Restaurant implements Parcelable {
    private int id;
    private String image;
    private String name;
    private String comment;
    private String type;
    private String address;

    public Restaurant(int id, String image, String name, String comment, String type) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.comment = comment;
        this.type = type;
    }

    public Restaurant(String image, String name, String comment, String type) {
        this.image = image;
        this.name = name;
        this.comment = comment;
        this.type = type;
    }

    protected Restaurant(Parcel in) {
        image = in.readString();
        name = in.readString();
        comment = in.readString();
        type = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString()  {
        return this.name+" (Comment: "+ this.comment+")";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(name);
        parcel.writeString(comment);
        parcel.writeString(type);
    }

    public Bitmap getBitMapImg() {
        return ImageUtils.decodeImg(this.image);
    }
}
