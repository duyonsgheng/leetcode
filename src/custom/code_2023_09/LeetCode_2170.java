package custom.code_2023_09;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2170
 * @date 2023年09月06日
 */
// 2170. 使数组变成交替数组的最少操作数
// https://leetcode.cn/problems/minimum-operations-to-make-the-array-alternating/?envType=daily-question&envId=2023-09-06
public class LeetCode_2170 {
    static int N = 100010;
    static int[] m1 = new int[N], m2 = new int[N];

    public static int minimumOperations(int[] nums) {
        // 3,1,3,2,4,3
        int n = nums.length;
        Arrays.fill(m1, 0);
        Arrays.fill(m2, 0);
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            if (i % 2 == 0) {
                m1[cur]++;
                if (a == 0 || m1[cur] > m1[a]) {
                    b = a;
                    a = cur;
                } else if (cur != a && (b == 0 || m1[cur] > m1[b])) {
                    b = cur;
                }
            } else {
                m2[cur]++;
                if (c == 0 || m2[cur] > m2[c]) {
                    d = c;
                    c = cur;
                } else if (cur != c && (d == 0 || m2[cur] > m2[d])) {
                    d = cur;
                }
            }
        }
        if (a != c) return n - m1[a] - m2[c];
        else return n - Math.max(m1[a] + m2[d], m1[b] + m2[c]);
    }

    public static void main(String[] args) {
        //System.out.println(minimumOperations(new int[]{3, 1, 3, 2, 4, 3}));
        System.out.println(minimumOperations(new int[]{1, 2, 2, 2, 2}));
    }
}
