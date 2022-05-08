package hcmute.nhom7.foody.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Quan implements Parcelable {
    private int id;
    private String image;
    private String name;
    private String comment;
    private String type;
    private String address;

    public Quan(int id, String image, String name, String comment, String type) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.comment = comment;
        this.type = type;
    }

    public Quan(String image, String name, String comment, String type) {
        this.image = image;
        this.name = name;
        this.comment = comment;
        this.type = type;
    }

    protected Quan(Parcel in) {
        image = in.readString();
        name = in.readString();
        comment = in.readString();
        type = in.readString();
    }

    public static final Creator<Quan> CREATOR = new Creator<Quan>() {
        @Override
        public Quan createFromParcel(Parcel in) {
            return new Quan(in);
        }

        @Override
        public Quan[] newArray(int size) {
            return new Quan[size];
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
}
