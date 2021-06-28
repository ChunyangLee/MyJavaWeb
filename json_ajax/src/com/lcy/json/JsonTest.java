package com.lcy.json;

import com.google.gson.Gson;
import com.lcy.bean.Person;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonTest {
    @Test
    public void test(){
        Person p1 = new Person(1,"徐胜男");

        Gson gson = new Gson();
        String p_json = gson.toJson(p1);
        System.out.println(p_json);

        System.out.println("*************");

        System.out.println(gson.fromJson(p_json, Person.class));

    }

    @Test
    public void test2(){
        Person p1 = new Person(1,"徐胜男");
        Person p2 = new Person(2,"徐男");

        List<Person> pList = new ArrayList<>();
        pList.add(p1);
        pList.add(p2);

        Gson gson = new Gson();
        final String list_json = gson.toJson(pList);
// 输出的形式：   [{"id":1,"name":"徐胜男"},{"id":2,"name":"徐男"}]

        List<Person> list = gson.fromJson(list_json, new PersonListType().getType());
        System.out.println(list);
        System.out.println(list.get(0));
    }

    @Test
    public void test3(){
        HashMap<Integer, Person> map = new HashMap<>();
        map.put(1, new Person(1,"徐胜男"));
        map.put(2, new Person(2,"徐男"));
        Gson gson = new Gson();
        String str = gson.toJson(map);
        System.out.println(str);
        //注意到，全转换成字符串了
        //{"1":{"id":1,"name":"徐胜男"},"2":{"id":2,"name":"徐男"}}

        HashMap<Integer, Person> map2 = gson.fromJson(str, new MapType().getType());
        System.out.println(map2);

    }



    @Test
    public void testType(){
        Type genericSuperclass = PersonListType1.class.getGenericSuperclass();
        System.out.println(genericSuperclass);

        ParameterizedType genericSuperclass1 = (ParameterizedType) genericSuperclass;
        System.out.println(genericSuperclass1);

        System.out.println(genericSuperclass1.getActualTypeArguments()[0]);

        Type type = (Type) Person.class;
        System.out.println(type);
        System.out.println(Person.class);
    }


}
