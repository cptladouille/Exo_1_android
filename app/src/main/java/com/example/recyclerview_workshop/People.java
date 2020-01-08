package com.example.recyclerview_workshop;

import android.text.Html;

import java.util.ArrayList;
import java.util.Random;

public class People {

    public enum Sex {
        MALE,
        FEMALE
    }


    public static Random rand = new Random();
    private String name;
    private int age;
    private Sex sex;
    private boolean loveAndroid;

    public People(String name, int age, Sex sex, boolean loveAndroid)
    {
        this.name =name;
        this.age = age;
        this.sex = sex;
        this.loveAndroid = loveAndroid;
    }

    public static ArrayList<People> InitPeoples(int size)
    {

        ArrayList<People> ret = new ArrayList<People>();

        for(int i = 0; i < size ;i++)
        {
            ret.add(createPeople());
        }
        return ret;
    }

    public String ShowPeople()
    {
        return String.format("%s \n %s, %d years, love android : %s",name,sex,age,loveAndroid);
    }

    public static People createPeople()
    {
        ArrayList<String> availableNames = new ArrayList<String>(){{add("Robert"); add("Patrick");add("Phil");add("Bobby");add("Michou");add("Bébèrt");}};
        int sexRand = rand.nextInt(Sex.values().length );
        int nameRand = rand.nextInt(availableNames.size());
        int ageRand =rand.nextInt(100) + 1;
        int loveAndroidRand =rand.nextInt(2) ;
        return  new People(availableNames.get(nameRand),ageRand,Sex.values()[sexRand],loveAndroidRand == 1);
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public boolean isLoveAndroid() {
        return loveAndroid;
    }

    public void setLoveAndroid(boolean loveAndroid) {
        this.loveAndroid = loveAndroid;
    }
}
