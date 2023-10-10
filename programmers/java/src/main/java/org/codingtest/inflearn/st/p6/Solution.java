package org.codingtest.inflearn.st.p6;

import java.util.*;

class Solution {
    public int solution(int[] tasks, long k) {
        int answer = 0;

        int[][] sorted = copyWithIndex(tasks);
        int queueIndex = 0;
        int queueSize = sorted.length;
        Arrays.sort(sorted, Comparator.comparingInt(s -> s[1]));

        int preValue = sorted[0][1];

        while ((long) sorted[queueIndex][1] * queueSize <= k) {
            k -= (long) sorted[queueIndex][1] * queueSize;
            sorted[queueIndex][1] = 0;
            queueIndex++;
            if (queueIndex < sorted.length) {
                int temp = sorted[queueIndex][1];
                sorted[queueIndex][1] -= preValue;
                preValue = temp;
            }
            queueSize--;
        }


        for (int[] s : sorted) {
            tasks[s[0]] = s[1];
        }

        k %= queueSize;
        if (k == 0) return tasks.length;

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == 0) continue;
            if (k == 0) return i + 1;
            k--;
        }

        return 0;
    }

    public int[][] copyWithIndex(int[] tasks) {
        int[][] result = new int[tasks.length][2];
        for (int i = 0; i < tasks.length; i++) {
            result[i][0] = i;
            result[i][1] = tasks[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{1, 2, 3}, 5));
        System.out.println(T.solution(new int[]{8, 5, 2, 9, 10, 7}, 30));
        System.out.println(T.solution(new int[]{8, 9, 12, 23, 45, 16, 25, 50}, 100));
    }
}
