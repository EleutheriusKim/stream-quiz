package com.mangkyu.stream.Quiz5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Quiz5 {

    private static final String[] STRING_ARR = {"aaa", "bb", "c", "dddd"};

    // 5.1 모든 문자열의 길이를 더한 결과를 출력하여라.
    public int quiz1() {
        return Arrays.stream(STRING_ARR).map(String::length)
          .reduce(0, Integer::sum);
    }

    // 5.2 문자열 중에서 가장 긴 것의 길이를 출력하시오.
    public int quiz2() {
        return Arrays.stream(STRING_ARR).sorted(Comparator.comparingInt(String::length).reversed())
          .findFirst().get().length();
    }

    // 5.3 임의의 로또번호(1~45)를 정렬해서 출력하시오.
    public List<Integer> quiz3() {
        return new Random().ints(1, 46)
          .distinct()
          .limit(6)
          .boxed()
          .sorted()
          .collect(Collectors.toList());
    }

    // 5.4 두 개의 주사위를 굴려서 나온 눈의 합이 6인 경우를 모두 출력하시오.
    public List<Integer[]> quiz4() {
        final Integer[] dice = new Integer[] {1, 2, 3, 4, 5, 6};
        return Arrays.stream(dice).flatMap(i -> Arrays.stream(dice).map(i2 -> new Integer[]{i, i2}))
          .filter(v -> v[0] + v[1] == 6)
          .collect(Collectors.toList());

    }

}
