package duys_code.day_41;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_02_PoemPro
 * @Author Duys
 * @Description
 * @Date 2021/12/29 15:39
 **/
public class Code_02_PoemPro {
    // 来自小红书
    // 有四种诗的韵律分别为: AABB、ABAB、ABBA、AAAA
    // 比如 : 1 1 3 3就属于AABB型的韵律、6 6 6 6就属于AAAA型的韵律等等
    // 一个数组arr，当然可以生成很多的子序列，如果某个子序列一直以韵律的方式连接起来，我们称这样的子序列是有效的
    // 比如, arr = { 1, 1, 15, 1, 34, 1, 2, 67, 3, 3, 2, 4, 15, 3, 17, 4, 3, 7, 52, 7, 81, 9, 9 }
    // arr的一个子序列为{1, 1, 1, 1, 2, 3, 3, 2, 4, 3, 4, 3, 7, 7, 9, 9}
    // 其中1, 1, 1, 1是AAAA、2, 3, 3, 2是ABBA、4, 3, 4, 3是ABAB、7, 7, 9, 9是AABB
    // 可以看到，整个子序列一直以韵律的方式连接起来，所以这个子序列是有效的
    // 给定一个数组arr, 返回最长的有效子序列长度
    // 题目限制 : arr长度 <= 4000, arr中的值<= 10^9
    // 离散化之后，arr长度 <= 4000,  arr中的值<= 4000

    // 大流程是i位置的字符要不要
    // 1. i位置字符不要
    // 2. 要i位置字符，然后从i位置出发看看能搞定四种第一种最近的位置在哪里，从i位置出发看看能搞定四种中的第二种最近的位置在哪里、。。。。
    // 总共5中情况取最大的

    // 1.暴力解答
    public static int maxLen1(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int[] path = new int[arr.length];
        return process1(arr, 0, path, 0);
    }

    // index做选择，
    // path 之前作过的决定
    // size 当前的长度
    public static int process1(int[] arr, int index, int[] path, int size) {
        if (index == arr.length) {
            // 就是剩下的字符都不够四个了，就没话说
            if (size % 4 != 0) {
                return 0;
            } else {
                for (int i = 0; i < size; i += 4) {
                    if (!valid(path, i)) {
                        return 0;
                    }
                }
                return size;
            }
        } else {
            int p1 = process1(arr, index + 1, path, size);
            path[size] = arr[index];
            int p2 = process1(arr, index + 1, path, size + 1);
            return Math.max(p1, p2);
        }
    }

    // AABB
    // ABAB
    // ABBA
    // AAAA
    public static boolean valid(int[] arr, int i) {
        return (arr[i] == arr[i + 1] && arr[i + 2] == arr[i + 3]) ||
                (arr[i] == arr[i + 2] && arr[i + 1] == arr[i + 3] && arr[i] != arr[i + 1]) ||
                (arr[i] == arr[i + 3] && arr[i + 1] == arr[i + 2] && arr[i] != arr[i + 1]);
    }

    // 数组需要预处理一下
    // 先排序
    // 然后看看数出现在了哪些位置
    public static int maxLen2(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int n = arr.length;
        int[] stored = Arrays.copyOf(arr, n);
        Arrays.sort(stored);
        // 实际有多少个数，每一个数用下标来对应
        Map<Integer, Integer> vmap = new HashMap<>();
        int index = 0;
        vmap.put(stored[0], index++);
        // 实际有多少个数，每一个数用下标来对应
        for (int i = 1; i < n; i++) {
            if (stored[i] != stored[i - 1]) {
                vmap.put(stored[i], index++);
            }
        }
        // 统计离散化后每一个位置的数出现了几次
        int[] sizeArr = new int[index];
        // 离散化了
        for (int i = 0; i < n; i++) {
            arr[i] = vmap.get(arr[i]);
            sizeArr[arr[i]]++;
        }
        //
        int[][] imap = new int[index][];
        for (int i = 0; i < index; i++) {
            imap[i] = new int[sizeArr[i]];
        }
        // 出现在了哪些位置
        for (int i = n - 1; i >= 0; i--) {
            imap[arr[i]][--sizeArr[arr[i]]] = i;
        }
        return process2(arr, imap, 0);
    }

    // i 是当前来到的位置
    public static int process2(int[] varr, int[][] imap, int i) {
        // 剩下的字符不够4个了，没戏了
        if (i + 4 > varr.length) {
            return 0;
        }
        // i位置的数不参与本次的计算
        int p0 = process2(varr, imap, i + 1);
        // 下面看看搞定四种的每一种，结算答案，取5种中最大的
        // AABB
        int p1 = 0;
        int rightClosedP1A2 = rightClosed(imap, varr[i], i);
        if (rightClosedP1A2 != -1) {
            for (int next = rightClosedP1A2 + 1; next < varr.length; next++) {
                if (varr[i] != varr[next]) {
                    int rightClosedP1B2 = rightClosed(imap, varr[next], next);
                    if (rightClosedP1B2 != -1) {
                        p1 = Math.max(p1, 4 + process2(varr, imap, rightClosedP1B2 + 1));
                    }
                }
            }
        }
        //ABAB
        int p2 = 0;
        for (int p2B1 = i + 1; p2B1 < varr.length; p2B1++) {
            if (varr[i] != varr[p2B1]) {
                int rightClosedP2A2 = rightClosed(imap, varr[i], p2B1);
                if (rightClosedP2A2 != -1) {
                    int rightClosedP2B2 = rightClosed(imap, varr[p2B1], rightClosedP2A2);
                    if (rightClosedP2B2 != -1) {
                        p2 = Math.max(p2, 4 + process2(varr, imap, rightClosedP2B2 + 1));
                    }
                }
            }
        }
        // ABBA
        int p3 = 0;
        for (int p3B1 = i + 1; p3B1 < varr.length; p3B1++) {
            if (varr[i] != varr[p3B1]) {
                int rightClosedP3B2 = rightClosed(imap, varr[p3B1], p3B1);
                if (rightClosedP3B2 != -1) {
                    int rightClosedP3A2 = rightClosed(imap, varr[i], rightClosedP3B2);
                    if (rightClosedP3A2 != -1) {
                        p3 = Math.max(p2, 4 + process2(varr, imap, rightClosedP3A2 + 1));
                    }
                }
            }
        }
        // AAAA
        int p4 = 0;
        int rightClosedP4A2 = rightClosed(imap, varr[i], i);
        int rightClosedP4A3 = rightClosedP4A2 == -1 ? -1 : rightClosed(imap, varr[i], rightClosedP4A2);
        int rightClosedP4A4 = rightClosedP4A3 == -1 ? -1 : rightClosed(imap, varr[i], rightClosedP4A3);
        if (rightClosedP4A4 != -1) {
            p4 = Math.max(p4, 4 + process2(varr, imap, rightClosedP4A4 + 1));
        }
        return Math.max(p0, Math.max(Math.max(p2, p1), Math.max(p3, p4)));
    }

    // 找到 v所在的位置 比i大但是离i最近的位置在哪里
    public static int rightClosed(int[][] imap, int v, int i) {
        int left = 0;
        int right = imap[v].length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (imap[v][mid] <= i) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans == -1 ? -1 : imap[v][ans];
    }

    // 贪心--比脑瓜子的
    public static int maxLen3(int[] arr) {
        // 统计某一个数出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        // 目前有多少数出现了2次
        int tow = 0;
        int ans = 0;
        // 当前数出现的次数
        int curNum = 0;
        for (int num : arr) {
            // 把数出现的次数+1
            map.put(num, map.getOrDefault(num, 0) + 1);
            // 看看当前数出现了多少次
            curNum = map.get(num);
            // 如果当前数出现了2次，那么出现2次的数的统计需要+1
            tow += curNum == 2 ? 1 : 0;
            // 如果出现2次的数已经有两个了，或者当前数出现了4次了，可以结算答案了
            // 因为AABB ABAB ABBA AAAA
            if (tow == 2 || curNum == 4) {
                ans += 4;
                map.clear();
                tow = 0;
            }
        }
        return ans;
    }

}
