package week.week_2022_09_03;

import java.util.Arrays;

/**
 * @ClassName Code_04_ExaminationPaperWays
 * @Author Duys
 * @Description
 * @Date 2022/9/22 10:18
 **/
// 来自美团
// 有三个题库A、B、C，每个题库均有n道题目，且题目都是从1到n进行编号
// 每个题目都有一个难度值
// 题库A中第i个题目的难度为ai
// 题库B中第i个题目的难度为bi
// 题库C中第i个题目的难度为ci
// 小美准备组合出一套试题，试题共有三道题，
// 第一题来自题库A，第二题来自题库B，第三题来自题库C
// 试题要求题目难度递增，且梯度不能过大
// 具体地说，第二题的难度必须大于第一题的难度，但不能大于第一题难度的两倍
// 第三题的难度必须大于第二题的难度，但不能大于第二题难度的两倍
// 小美想知道在满足上述要求下，有多少种不同的题目组合
//（三道题目中只要存在一道题目不同，则两个题目组合就视为不同
// 输入描述 第一行一个正整数n, 表示每个题库的题目数量
// 第二行为n个正整数a1, a2,...... an，其中ai表示题库A中第i个题目的难度值
// 第三行为n个正整数b1, b2,...... bn，其中bi表示题库B中第i个题目的难度值
// 第四行为n个正整数c1, c2,...... cn，其中ci表示题库C中第i个题目的难度值
// 1 <= n <= 20000, 1 <= ai, bi, ci <= 10^9。
public class Code_04_ExaminationPaperWays {

    // 思路：给三个数组排序
    // 然后从最后一个开始选择
    // 在第三个数组中选出满足，记录到一个临时数组中
    // 然后从第二中选择满足第一个数组的，从临时数组中去获取之前的答案，可以使用二分找，因为都是有序的了，也可以使用前缀和数组来
    //

    public int ways(int[] a, int[] b, int[] c) {
        int n = a.length;
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        int[] recodeB = new int[n];
        for (int i = 0, l = -1, r = 0; i < n; i++) {
            // 两个while 找到c中满足 大于b[i] 且 < b[i]*2 的这一段区间
            while (l + 1 < n && c[l + 1] <= b[i]) {
                l++;
            }
            while (r < n && c[r] <= b[i] * 2) {
                r++;
            }
            recodeB[i] = Math.min(r - l - 1, 0);
        }
        // 来一个前缀和
        for (int i = 1; i < n; i++)
            recodeB[i] += recodeB[i - 1];
        int ans = 0;
        // 再来依次，找到b中满足  > a[i] 且 < a[i]*2的区间
        for (int i = 0, l = -1, r = 0; i < n; i++) {
            while (l + 1 < n && b[l + 1] <= a[i])
                l++;
            while (r < n && b[r] <= a[i] * 2)
                r++;
            if (r - l - 1 > 0) {
                ans += sum(recodeB, l + 1, r - 1);
            }
        }
        return ans;
    }

    public int sum(int[] sums, int l, int r) {
        return l == 0 ? sums[r] : sums[r] - sums[l - 1];
    }
}
