package custom.code_2023_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_1733
 * @Author Duys
 * @Description
 * @Date 2023/5/11 17:38
 **/
// 1733. 需要教语言的最少人数
public class LeetCode_1733 {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            Set<Integer> set = new HashSet<>();
            for (int m : languages[i]) {
                set.add(m);
            }
            map.put(i + 1, set);
        }
        Set<Integer> no = new HashSet<>();
        for (int[] arr : friendships) {
            if (!check(map, arr[0], arr[1])) {
                no.add(arr[0]);
                no.add(arr[1]);
            }
        }
        int ans = Integer.MAX_VALUE;
        int[] yes = new int[n + 1];
        for (int fre : no) {
            // 当前人会的语言
            Set<Integer> set = map.get(fre);
            for (int lan : set) {
                yes[lan]++;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < yes.length; i++) {
            max = Math.max(max, yes[i]);
        }
        return no.size() - max;
    }

    public boolean check(Map<Integer, Set<Integer>> map, int i, int j) {
        Set<Integer> set1 = map.get(i);
        Set<Integer> set2 = map.get(j);
        for (int a : set1) {
            for (int b : set2) {
                if (a == b) {
                    return true;
                }
            }
        }
        return false;
    }
}
