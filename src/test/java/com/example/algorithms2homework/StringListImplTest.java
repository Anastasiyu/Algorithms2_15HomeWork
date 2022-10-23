package com.example.algorithms2homework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StringListImplTest {

    private final  IntegerList integerList = new IntegerListImpl();

    @AfterEach
    public void afterEach(){integerList.clear();}

    @Test
    void addTest() {
        Integer[] elements = {1, 2, 3, 4};
        add(elements);

        for (int i = 0; i < elements.length; i++) {
            assertThat(integerList.get(i)).isEqualTo(elements[i]);
            assertThat(integerList.contains(elements[i])).isTrue();
            assertThat(integerList.indexOf(elements[i])).isEqualTo(i);
            assertThat(integerList.lastIndexOf(elements[i])).isEqualTo(i);
        }

    }

    @Test
    void  addByIndexTest() {
       Integer[] elements = {1, 2, 3, 4};
        add(elements);

        integerList.add(0, 1);
        assertThat(integerList.size()).isEqualTo(elements.length + 1);
        assertThat(integerList.get(0)).isEqualTo(1);

        integerList.add(3, 1);
        assertThat(integerList.size()).isEqualTo(elements.length + 2);
        assertThat(integerList.get(3)).isEqualTo(1);
        assertThat(integerList.lastIndexOf(1)).isEqualTo(3);
        assertThat(integerList.indexOf(1)).isEqualTo(0);
    }

    private void add(Integer[] elements) {
        assertThat(integerList.isEmpty()).isTrue();
        Stream.of(elements).forEach(integerList::add);
        assertThat(integerList.size()).isEqualTo(elements.length);
    }


}
