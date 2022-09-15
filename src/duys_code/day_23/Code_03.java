package duys_code.day_23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_03
 * @Author Duys
 * @Description
 * @Date 2021/11/11 16:19
 **/
public class Code_03 {
    /**
     * 超级水王问题
     * 扩展1：摩尔投票
     * 扩展2：给定一个正数K，返回所有出现次数>N/K的数
     */

    // 找到一个数是在整个数组中出现了 > N/2 次的数，如果有，这个数就是水王数
    // 思路：
    // 1.依次排除掉两个不相同的数
    // 2.最后剩下的数就是可能是水王数，然后遍历一遍数组，验证一下
    public static void printHalfMajor(int[] arr) {
        // 准备两个指针
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            if (arr[left] != arr[right]) {
                left++;
                right--;
            } else {
                left++;
            }
        }
        int num = arr[left];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == num) {
                count++;
            }
        }
        if (count > n / 2) {
            System.out.println(num);
        } else {
            System.out.println("no such number.");
        }
    }

    // 找出谁出现次数最多的
    // 准备一个后选着，准备一个count
    public static void printHalfMajor1(int[] arr) {
        int cand = 0;
        int count = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 当没有血量的时候我们就认为当前没有候选的
            if (count == 0) {
                cand = arr[i];
                count = 1;
            }
            // 有候选的，count++
            else if (arr[i] == cand) {
                count++;
            }
            // 出现了一个异类，把候选者干掉了一个
            else {
                count--;
            }
        }
        if (count == 0) {
            System.out.println("no such number.");
        }
        count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == cand) {
                count++;
            }
        }
        if (count > n / 2) {
            System.out.println(cand);
        } else {
            System.out.println("no such number.");
        }
    }

    // 找出在arr中出现了 次数大于 n/k 的数
    public static void printHalfMajor(int[] arr, int K) {
        // 比如我们数组是4个数，k是2，那么我们最多这样数的个数有几个，只有1个吧，如果有两个了，那么我们整个数组长度肯定不是4
        // 所以我们准备 k-1个候选人
        if (K < 2) {
            System.out.println("the value of K is invalid.");
            return;
        }
        int n = arr.length;
        // 候选人，以及候选人对应的计数
        Map<Integer, Integer> cands = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (cands.containsKey(arr[i])) {
                cands.put(arr[i], cands.get(arr[i]) + 1);
            } else {
                // 只能有这么多个候选人
                if (cands.size() == K - 1) {
                    // 出现了一个异类。已经有的候选者计数需要减少。如果减少为0的就从候选者里面移除
                    checkCands(cands);
                } else {
                    cands.put(arr[i], 1);
                }
            }
        }
        // 收集每个真实者的真实次数
        HashMap<Integer, Integer> reals = getCounts(arr, cands);
        boolean hasPrint = false;
        for (Integer key : reals.keySet()) {
            if (reals.get(key) > n / K) {
                hasPrint = true;
                System.out.print(key + " ");
            }
        }
        System.out.println(hasPrint ? "" : "no such number.");
    }

    public static HashMap<Integer, Integer> getCounts(int[] arr, Map<Integer, Integer> map) {
        HashMap<Integer, Integer> reals = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                if (reals.containsKey(arr[i])) {
                    reals.put(arr[i], reals.get(arr[i]) + 1);
                } else {
                    reals.put(arr[i], 1);
                }
            }
        }
        return reals;
    }

    public static void checkCands(Map<Integer, Integer> map) {
        List<Integer> removeList = new ArrayList<>();
        for (Integer key : map.keySet()) {
            Integer value = map.get(key);
            if (value == 1) {
                removeList.add(key);
            } else {
                map.put(key, value - 1);
            }
        }
        for (Integer i : removeList) {
            map.remove(i);
        }
    }
}
