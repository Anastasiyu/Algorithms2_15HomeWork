package com.example.algorithms2homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Algorithms2HomeWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(Algorithms2HomeWorkApplication.class, args);

        IntegerList integerList = new IntegerListImpl();
        integerList.add(0);// 0
        integerList.add(1);//1
        integerList.add(2);//2
        integerList.add(3);//3
        // 1 2 3 4

        integerList.add(3, 6);
        // 1 2 3 6 4

        integerList.remove(1);
        // 2 3 6 4

        System.out.println(Arrays.toString(integerList.toArray()));

        integerList.clear();
        System.out.println(integerList.size());
    }
}
