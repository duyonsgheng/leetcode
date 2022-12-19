package custom.code_2022_12;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1357
 * @Author Duys
 * @Description
 * @Date 2022/12/13 16:49
 **/
// 1357. 每隔 n 个顾客打折
public class LeetCode_1357 {

    class Cashier {
        int limit;
        int discount;
        Map<Integer, Integer> map;
        int cur = 0;

        public Cashier(int n, int discount, int[] products, int[] prices) {
            this.limit = n;
            this.discount = discount;
            map = new HashMap<>();
            for (int i = 0; i < products.length; i++) {
                map.put(products[i], prices[i]);
            }
        }

        public double getBill(int[] product, int[] amount) {
            cur++;
            // 打折
            double sum = 0;
            for (int i = 0; i < product.length; i++) {
                sum += map.get(product[i]) * amount[i];
            }
            if (cur == limit) {
                sum -= ((sum * discount) / 100);
                cur = 0;
            }
            return sum;
        }
    }
}
