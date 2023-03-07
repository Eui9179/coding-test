package org.codingtest.level1.p12928;

import java.util.stream.IntStream;

class Solution {
    public int solution(int n) {
        return IntStream.rangeClosed(1, n).filter(i -> n % i == 0).sum();
    }
}
