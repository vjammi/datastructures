package io.dev.v2.dev.functional.functional101;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalPlay {

    public FunctionalPlay() {

    }

//    public static void main(String[] args) {
//        Functional functional = new Functional();
//        //functional.filterOnes(lists);
//    }

    public List<String> filterOnes(List<List<String>> lists) {

        //orders.flatMap(order -> order.getLineItems().stream())
        //Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        //Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));

        List<String> list = lists.stream()
                .flatMap(line -> Stream.of(line.get(0).split((","))))
                .map(string -> string +"-" +string)
                .filter(string -> !string.contains("1"))
                .collect(Collectors.toList());
        System.out.println(list);

        return list;
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
