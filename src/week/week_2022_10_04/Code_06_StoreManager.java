package week.week_2022_10_04;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName Code_06_StoreManager
 * @Author Duys
 * @Description
 * @Date 2022/10/27 15:42
 **/
// 设计一个仓库管理器，提供如下的方法：
// 1) void supply(String item, int num, int price)
// 名字叫item的商品，个数num，价格price
// 2) int sell(String item, int num)
// 卖出叫item的商品，个数num个，价格从低到高，返回卖出总价
// 如果商品很多，每种商品的数量可能很多，该怎么设计这个结构
public class Code_06_StoreManager {
    public static class StoreManager {
        private Map<String, Store> map;

        public StoreManager() {
            map = new HashMap<>();
        }

        public void supply(String item, int num, int price) {
            if (!map.containsKey(item)) {
                map.put(item, new Store());
            }
            map.get(item).add(num, price);
        }

        public int sell(String item, int num) {
            return map.get(item).remove(num);
        }
    }

    public static class Store {
        // 每一个价格对应的数量
        private Map<Integer, Integer> priceMap;

        // 价格组成的小根堆
        private PriorityQueue<Integer> heap;

        public Store() {
            priceMap = new HashMap<>();
            heap = new PriorityQueue<>();
        }

        public void add(int num, int price) {
            if (priceMap.containsKey(price)) {
                priceMap.put(price, priceMap.get(price) + num);
            } else {
                priceMap.put(price, num);
                heap.add(price);
            }
        }

        public int remove(int num) {
            int money = 0;
            while (!heap.isEmpty() && num != 0) {
                int price = heap.poll();
                int nums = priceMap.get(price);
                if (num >= nums) {
                    money += price * nums;
                    priceMap.remove(price);
                    num -= nums;
                } else {
                    money += price * num;
                    priceMap.put(price, nums - num);
                    heap.add(price);
                    break;
                }
            }
            return money;
        }
    }
}
