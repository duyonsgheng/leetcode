package custom.code_2022_10;

import java.util.Arrays;

/**
 * @ClassName LeetCode_849
 * @Author Duys
 * @Description
 * @Date 2022/10/8 15:52
 **/
// 849. 到最近的人的最大距离
public class LeetCode_849 {
    // 动态规划
    // 从左往右来一遍
    // 从右往左来一遍
    public static int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, n);
        Arrays.fill(right, n);
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                left[i] = 0;
            } else {
                if (i > 0) {
                    left[i] = left[i - 1] + 1;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (seats[i] == 1) {
                right[i] = 0;
            } else {
                if (i < n - 1) {
                    right[i] = right[i + 1] + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, Math.min(left[i], right[i]));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 0};
        System.out.println(maxDistToClosest(arr));
    }

    public int maxDistToClosest2(int[] seats) {
        int count1 = 0;
        int count2 = 0;
        int i = 0, j = seats.length - 1;
        // count1记录开头连续0的个数
        while (seats[i] == 0) {
            count1++;
            i++;
        }
        // count2记录结尾连续0的个数
        while (seats[j] == 0) {
            count2++;
            j--;
        }
        // countmax记录从第一个1到最后一个1之间，连续0的最大值
        int countmid = 0, countmax = 0;
        for (int k = i + 1; k <= j; k++) {
            if (seats[k] == 0) {
                countmid++;
            } else {
                countmax = Math.max(countmax, countmid);
                countmid = 0;
            }
        }
        // 返回count1, count2, (countmax+1)/2三者中最大值
        return Math.max(Math.max(count1, count2), (countmax + 1) / 2);
    }

}
