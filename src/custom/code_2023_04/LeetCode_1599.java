package custom.code_2023_04;

/**
 * @ClassName LeetCode_1599
 * @Author Duys
 * @Description
 * @Date 2023/4/17 17:35
 **/
// 1599. 经营摩天轮的最大利润
public class LeetCode_1599 {
    public static void main(String[] args) {
        System.out.println(minOperationsMaxProfit1(new int[]{8, 3}, 5, 6));
        // customers = [10,9,6], boardingCost = 6, runningCost = 4
        System.out.println(minOperationsMaxProfit1(new int[]{10, 9, 6}, 6, 4));
        System.out.println(minOperationsMaxProfit1(new int[]{10, 10, 6, 4, 7}, 3, 8));

        String query = "select #name# ,aa, #time#";
        System.out.println(query.replaceAll("#", ""));
    }

    public static int minOperationsMaxProfit1(int[] customers, int boardingCost, int runningCost) {
        // 当前摩天轮的位置
        int index = 0;
        // 之前还剩下多少人
        int pre = 0;
        // 转动了几次
        int run = 0;
        // 一共完成了多少人
        int last = 0;
        // 4 4 3
        for (int i = 0; i < customers.length; i++) {
            int cur = customers[i] + pre;
            if (cur == 0) {
                continue;
            }
            if (cur >= 4) {
                last += 4;
                pre = (cur - 4);
            } else {
                pre = 0;
                last += cur;
            }
            run++;
            index = (index + 1) % 4;
        }
        while (pre > 4) {
            run++;
            last += 4;
            pre -= 4;
            index = (index + 1) % 4;
        }
        if (pre * boardingCost >= runningCost) {
            last += pre;
            run++;
        }
/*        while (index > 0) {
            run++;
            index--;
        }*/
        //System.out.println();
        int cost = last * boardingCost - run * runningCost;
        //System.out.println("last = " + last + " run=" + run);
        return cost >= 0 ? run : -1;
    }

    public static int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int ans = -1;
        int maxP = 0;
        int totalP = 0;
        int op = 0;
        int cus = 0;
        int n = customers.length;
        for (int i = 0; i < n; i++) {
            op++;
            cus += customers[i];
            int cur = Math.min(4, cus);
            cus -= cur;
            totalP += boardingCost * cur - runningCost;
            if (totalP > maxP) {
                maxP = totalP;
                ans = op;
            }
        }
        if (cus == 0) {
            return ans;
        }
        // 看看一躺的利润是不是大于的
        int next = boardingCost * 4 - runningCost;
        if (next <= 0) {
            return ans;
        }
        if (cus > 0) {
            int full = cus / 4;
            totalP += next * full;
            op += full;
            if (totalP > maxP) {
                maxP = totalP;
                ans = op;
            }
            int rea = cus % 4;
            int reaP = rea * boardingCost - runningCost;
            totalP += reaP;
            if (totalP > maxP) {
                ans++;
            }
        }
        return ans;
    }
}
