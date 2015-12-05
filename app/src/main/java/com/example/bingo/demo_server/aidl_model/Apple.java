package com.example.bingo.demo_server.aidl_model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bingo on 15/11/18.
 */
public class Apple extends Fruit implements Parcelable{
    private String origin;

    public Apple() {

    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        super.writeToParcel(dest, i);
        dest.writeString(origin);
    }

    public static final Creator<Apple> CREATOR = new Creator<Apple>() {
        @Override
        public Apple[] newArray(int i) {
            return new Apple[0];
        }

        /**
         * 根据序列化的对象，反序列化为原本的对象的实体对象
         * @param parcel
         * @return
         */
        @Override
        public Apple createFromParcel(Parcel source) {
            //先读
            String fruit_name  = source.readString();
            float price    = source.readFloat();
            String color = source.readString();
            String origin = source.readString();

            //再创建
            Apple apple = new Apple();
            apple.setName(fruit_name);
            apple.setPrice(price);
            apple.setColor(color);
            apple.setOrigin(origin);

            return apple;
        }
    };

    @Override
    public String toString() {
        return super.toString() + "origin=" + origin +"]";
    }
}
