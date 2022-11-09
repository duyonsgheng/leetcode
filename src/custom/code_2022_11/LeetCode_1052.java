package custom.code_2022_11;

/**
 * @ClassName LeetCode_1052
 * @Author Duys
 * @Description
 * @Date 2022/11/8 10:28
 **/
// 1052. 爱生气的书店老板
public class LeetCode_1052 {

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int total = 0; // 这是不使用技巧
        int n = customers.length;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }
        // 定一个窗口
        int window = 0;
        for (int i = 0; i < minutes; i++) {
            window += customers[i] * grumpy[i];
        }
        int max = window;
        // customers = [1,0,1,2,1,1,7,5],
        //    grumpy = [0,1,0,1,0,1,0,1], minutes = 3
        for (int i = minutes; i < n; i++) {
            // 减去窗口的左边界的
            // 然后窗口一次移动一个位置
            window = window - customers[i - minutes] * grumpy[i - minutes] + customers[i] * grumpy[i];
            max = Math.max(window, max);
        }
        return total + max;
    }
}
