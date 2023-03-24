package week.week_2023_03_04;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @ClassName Code_05_LeetCode_2168
 * @Author Duys
 * @Description
 * @Date 2023/3/24 9:56
 **/
// 给你一个由数字组成的字符串 s，返回 s 中独特子字符串数量
// 其中的每一个数字出现的频率都相同。
// https://leetcode.cn/problems/unique-substrings-with-equal-digit-frequency/
public class Code_05_LeetCode_2168 {
    // 其实是不对的，只是可以通过所有当前的测试用例而已
    // 可以构造出让这种方法不通过的例子，原因是这种简陋的hash函数太容易碰撞了
    // 其实这个题的最优解，依然需要使用DC3算法生成后缀数组来做
    // 但是很难，具体可以参考LongestChunkedPalindromeDecomposition问题
    // 课上会简单提一下，详细的就不讲了，因为很少考这么难
    // 课上重点讲一下这个很骚的方法，构造了简陋的hash函数，算是一种博闻强识吧
    // 时间复杂度O(N^2)，确实无法更好
    public static int equalDigitFrequency(String s) {
        long base = 1000000007; // 一个很大的质数，确保给每一个字符串构建唯一编码的时候能不重复(参考hash)
        HashSet<Long> set = new HashSet<>();
        int[] cnt = new int[10];
        for (int l = 0; l < s.length(); l++) {
            Arrays.fill(cnt, 0);
            long unionCode = 0;
            int curVal, maxCnt = 0, maxKinds = 0, allKinds = 0;
            for (int r = l; r < s.length(); r++) {
                curVal = s.charAt(r) - '0';
                unionCode = unionCode * base + curVal + 1;
                cnt[curVal]++;
                if (cnt[curVal] == 1) {
                    allKinds++;
                }
                // 比如 1 1 2 2 3
                if (cnt[curVal] > maxCnt) {
                    maxCnt = cnt[curVal];
                    maxKinds = 1;
                } else if (cnt[curVal] == maxCnt) {
                    maxKinds++;
                }
                if (maxKinds == allKinds) {
                    set.add(unionCode);
                }

            }
        }
        return set.size();
    }

}
