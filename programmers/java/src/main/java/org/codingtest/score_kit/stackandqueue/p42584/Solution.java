package org.codingtest.score_kit.stackandqueue.p42584;

/*
문제 설명
초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때,
가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.

제한사항
prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
prices의 길이는 2 이상 100,000 이하입니다.
입출력 예
prices	return
[1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
입출력 예 설명
1초 시점의 ₩1은 끝까지 가격이 떨어지지 않았습니다.
2초 시점의 ₩2은 끝까지 가격이 떨어지지 않았습니다.
3초 시점의 ₩3은 1초뒤에 가격이 떨어집니다. 따라서 1초간 가격이 떨어지지 않은 것으로 봅니다.
4초 시점의 ₩2은 1초간 가격이 떨어지지 않았습니다.
5초 시점의 ₩3은 0초간 가격이 떨어지지 않았습니다.
 */

import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new int[]{1, 2, 3, 2, 3})));
        System.out.println(Arrays.toString(new Solution().solution(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(new Solution().solution(new int[]{5, 4, 3, 2, 1})));
        System.out.println(Arrays.toString(new Solution().solution(new int[]{3, 3, 2, 1, 4, 2})));
    }

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int time = 1;

        List<Price> stack = new ArrayList<>();
        stack.add(new Price(0, time, prices[0]));

        for (int i = 1; i < prices.length; i++) {
            time++;

            // 현재 가격이 떨어진다면 현재 가격보다 낮은 값 전부 내보냄
            while (!stack.isEmpty() && stack.get(stack.size() - 1).price > prices[i]) {
                Price price = stack.remove(stack.size() - 1);
                // 현재까지 시간 - stack 에 들어갈 때 시간 = 가격을 유지했던 시간
                answer[price.index] = time - price.time;
            }

            stack.add(new Price(i, time, prices[i]));
        }

        // 남은 가격 모두 계산
        while (!stack.isEmpty()) {
            Price price = stack.remove(stack.size() - 1);
            answer[price.index] = time - price.time;
        }

        return answer;
    }

    private static class Price {
        int index;
        int time;
        int price;

        public Price(int index, int time, int price) {
            this.index = index;
            this.time = time;
            this.price = price;
        }
    }
}