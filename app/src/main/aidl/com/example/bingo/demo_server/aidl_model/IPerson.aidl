// IPerson.aidl
package com.example.bingo.demo_server.aidl_model;
import com.example.bingo.demo_server.aidl_model.IPersonCallback;
import com.example.bingo.demo_server.aidl_model.Fruit;
import com.example.bingo.demo_server.aidl_model.Apple;
// Declare any non-default types here with import statements

interface IPerson {
    void query(String queryKey);
    void registerCallback(IPersonCallback cb);
    Fruit getFruit();
    Apple getApple();
    void setFruit(in Fruit fruit);
}
