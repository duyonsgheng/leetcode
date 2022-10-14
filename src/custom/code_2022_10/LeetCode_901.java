package custom.code_2022_10;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName LeetCode_901
 * @Author Duys
 * @Description
 * @Date 2022/10/12 16:23
 **/
// 901. 股票价格跨度
public class LeetCode_901 {
    // [100, 80, 60, 70, 60, 75, 85]，
    // 那么股票跨度将是
    // [1, 1, 1, 2, 1, 4, 6]。
    class StockSpanner {
        // 单调栈
        Stack<Integer> prices;
        Stack<Integer> times;

        public StockSpanner() {
            prices = new Stack<>();
            times = new Stack<>();
        }

        public int next(int price) {
            int ans = 1;
            // 如果之前的小于等于我。那我可以获得他们的收益
            while (!prices.isEmpty() && prices.peek() <= price) {
                prices.pop();
                ans += times.pop();
            }
            prices.push(price);
            times.push(ans);
            return ans;
        }
    }
}
