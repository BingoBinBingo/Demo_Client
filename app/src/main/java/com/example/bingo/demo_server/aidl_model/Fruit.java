package com.example.bingo.demo_server.aidl_model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bingo on 15/11/18.
 */
public class Fruit implements Parcelable {
    private String name;
    private float price;
    private String color;

    public Fruit() {
    }

    public Fruit(String name, float price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将对象序列化
     * dest就是对象即将写入的目的对象
     * flag有关对象序列化方式的标识
     * @param parcel
     * @param i
     */
    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(name);
        dest.writeFloat(price);
        dest.writeString(color);
    }

    public static final Creator<Fruit> CREATOR = new Creator<Fruit>() {
        @Override
        public Fruit[] newArray(int i) {
            return new Fruit[0];
        }

        /**
         * 根据序列化的对象，反序列化为原本的对象的尸体对象
         * @param parcel
         * @return
         */
        @Override
        public Fruit createFromParcel(Parcel source) {
            //先读
            String fruit_name  = source.readString();
            float price    = source.readFloat();
            String color = source.readString();

            //再创建
            Fruit fruit = new Fruit();
            fruit.setName(fruit_name);
            fruit.setPrice(price);
            fruit.setColor(color);

            return fruit;
        }
    };

    @Override
    public String toString() {
        return "[Fruit:name=" + name + ",price=" + price + ",color=" + color +"]";
    }
}
