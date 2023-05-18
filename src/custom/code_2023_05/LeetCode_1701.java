package custom.code_2023_05;

/**
 * @ClassName LeetCode_1701
 * @Author Duys
 * @Description
 * @Date 2023/5/6 14:08
 **/
// 1701. 平均等待时间
public class LeetCode_1701 {
    public static double averageWaitingTime(int[][] customers) {
        double pre = customers[0][1] + customers[0][0];
        double total = pre - customers[0][0];
        // customers = [[5,2],[5,4],[10,3],[20,1]]
        for (int i = 1; i < customers.length; i++) {
            if (pre < customers[i][0]) {
                pre = customers[i][0];
            }
            pre += customers[i][1];
            total += (pre - customers[i][0]);

        }
        return total / (customers.length);
    }

    public static void main(String[] args) {
        System.out.println(averageWaitingTime(new int[][]{{5, 2}, {5, 4}, {10, 3}, {20, 1}}));
    }

}
