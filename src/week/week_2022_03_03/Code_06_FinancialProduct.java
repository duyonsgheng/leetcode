package week.week_2022_03_03;

import java.util.Arrays;

/**
 * @ClassName Code_06_Fiancle
 * @Author Duys
 * @Description
 * @Date 2022/3/17 17:55
 **/
public class Code_06_FinancialProduct {

    // 来自银联编程比赛
// 某公司计划推出一批投资项目。 product[i] = price 表示第 i 个理财项目的投资金额 price 。
// 客户在按需投资时，需要遵循以下规则：
// 客户在首次对项目 product[i] 投资时，需要投入金额 price
// 对已完成首次投资的项目 product[i] 可继续追加投入，
// 但追加投入的金额需小于上一次对该项目的投入(追加投入为大于 0 的整数)
// 为控制市场稳定，每人交易次数不得大于 limit。(首次投资和追加投入均记作 1 次交易)
// 若对所有理财项目中最多进行 limit 次交易，使得投入金额总和最大，请返回这个最大值的总和。
// 测试链接 : https://leetcode-cn.com/contest/cnunionpay-2022spring/problems/I4mOGz/
    // 思路：排序，然后根据阶梯来算
    public static long mod = 1000000007L;

    // 1 2 3 4 4 4 5 5 5 6 6 6
    // limit = 4
    public static int maxInvestment(int[] arr, int limit) {
        Arrays.sort(arr);
        int n = arr.length;
        long ans = 0;
        int r = n - 1;
        int l = r;
        while (limit > 0 && r != -1) {
            // 找阶梯
            while (l >= 0 && arr[l] == arr[r]) {
                l--;
            }
            // 上级阶梯的值
            int big = arr[r];
            // 下级阶梯的值
            int small = l == -1 ? 0 : arr[l];
            int curSize = n - l - 1; // 下一个
            // 本次可以投资多少次
            int all = (big - small) * curSize;
            if (limit >= all) {
                ans += get(big, small + 1, curSize);
                ans %= mod;
                limit -= all;
            } else {
                int a = limit / curSize;
                ans += get(big, big - a + 1, curSize) + (big - a) * (limit % curSize);
                ans %= mod;
                limit = 0;
            }
            r = l;
        }
        return (int) (ans % mod);
    }

    // 计算当前阶梯最大的投资金额
    public static long get(long up, long down, long num) {
        return num * ((up + down) * (up - down + 1) / 2);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 6};
        int limit = 4;
        System.out.println(maxInvestment(arr, limit));
    }
}
