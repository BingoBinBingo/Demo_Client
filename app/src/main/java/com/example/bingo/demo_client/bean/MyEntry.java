package com.example.bingo.demo_client.bean;


/**
 * Created by Bingo on 16/1/14.
 */
public class MyEntry {
    private int age;
    private String name;

    public MyEntry(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private static void setWeather(String username, String name) {
        int kaixin = 12;
        if(kaixin >= 67) {
            int nihao = 12;
            for (int i = 0; i < nihao; i++) {
                System.out.print("nihao.i=" + i);
            }
        }

        if(kaixin >= 67) {
            int nihao = 12;
            for (int i = 0; i < nihao; i++) {
                System.out.print("nihao.i=" + i);
            }
        }

        if(username.equals("username") && name.equals("name")) {
            System.out.print("dsd" + username + name);
        }
    }

}
