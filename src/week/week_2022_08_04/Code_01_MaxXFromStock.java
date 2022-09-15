package week.week_2022_08_04;

/**
 * @ClassName Code_01_MaxXFromStock
 * @Author Duys
 * @Description
 * @Date 2022/8/25 8:57
 **/
// 来自神策
// 给定一个数组arr，表示连续n天的股价，数组下标表示第几天
// 指标X：任意两天的股价之和 - 此两天间隔的天数
// 比如
// 第3天，价格是10
// 第9天，价格是30
// 那么第3天和第9天的指标X = 10 + 30 - (9 - 3) = 34
// 返回arr中最大的指标X
public class Code_01_MaxXFromStock {

    // 分析
    // arr[i]+arr[j] -(j-i) -> arr[i]+i + arr[j]-j
    // arr[i]+i  越大 arr[j]-j 也越大，结果就越大
    public static int maxX(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int ans = 0;
        int pre = arr[0] + 0;
        for (int i = 1; i < arr.length; i++) {
            ans = Math.max(ans, arr[i] - i + pre);
            pre = Math.max(arr[i] + i, pre);
        }
        return ans;
    }
}
