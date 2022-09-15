package custom.code_2022_09;

import java.util.Stack;

/**
 * @ClassName LeetCode_1475
 * @Author Duys
 * @Description
 * @Date 2022/9/1 14:23
 **/
//1475. 商品折扣后的最终价格
public class LeetCode_1475 {

    public int[] finalPrices1(int[] prices) {
        int n = prices.length;
        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = i + 1; j < n; j++) {
                if (prices[j] <= prices[i]) {
                    cur = prices[j];
                    break;
                }
            }
            ans[i] = prices[i] - cur;
        }
        return ans;
    }

    public int[] finalPrices2(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
            stack.push(prices[i]);
        }
        return ans;
    }
}
