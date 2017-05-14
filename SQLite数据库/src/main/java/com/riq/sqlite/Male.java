package com.riq.sqlite;

/**
 * Created by 锐 on 2017/3/28.
 */

public class Male {
    private int id;

    private String name;

    private int age;

    public Male(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Male(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Male() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Male{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
