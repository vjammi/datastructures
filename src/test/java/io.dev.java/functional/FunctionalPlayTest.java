package dev.vjammi.ds.v2.dev.functional;

import dev.vjammi.ds.v2.dev.functional.functional101.FunctionalPlay;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FunctionalPlayTest {


    @Test
    void shouldFilterOnes() {
        List<List<String>> lists = getLists();
        FunctionalPlay subject = new FunctionalPlay();
        List<String> list = subject.filterOnes(lists);

        assertNotNull(list);
        //assertThat(list)
                //.has()
                //.allMatch();

    }
    private static List<List<String>> getLists() {
        List<List<String>> lists = new ArrayList<>();

        List<String> list1 = new ArrayList<>();
        list1.add("1, 2, 3, 4, 5, 6");
        lists.add(list1);

        List<String> list2 = new ArrayList<>();
        list2.add("11, 22, 33, 44, 55, 66");
        lists.add(list2);
        return lists;
    }

}