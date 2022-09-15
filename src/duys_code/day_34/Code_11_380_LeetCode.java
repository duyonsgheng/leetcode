package duys_code.day_34;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_11_380_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 * @Date 2021/12/7 17:21
 **/
public class Code_11_380_LeetCode {


    // 为了 getRandom 要求所有的数字等概率的返回
    // 那么我们准备两张表 key - index  和 index - key
    // 删除的时候，用最后一个位置来替换要删除的位置，但是擦掉最后一个数
    class RandomizedSet {

        private Map<Integer, Integer> keyIndexMap;
        private Map<Integer, Integer> indexKeyMap;
        private int size;

        public RandomizedSet() {
            size = 0;
            keyIndexMap = new HashMap<>();
            indexKeyMap = new HashMap<>();
        }

        public boolean insert(int val) {
            if (keyIndexMap.containsKey(val)) {
                return false;
            }
            keyIndexMap.put(val, size);
            indexKeyMap.put(size++, val);
            return true;
        }

        public boolean remove(int val) {
            if (!keyIndexMap.containsKey(val)) {
                return false;
            }
            int deleteIndex = keyIndexMap.get(val);
            int lastIndex = --size;// 最后一个位置的数
            int lastKey = indexKeyMap.get(lastIndex);
            keyIndexMap.put(lastKey, deleteIndex);
            indexKeyMap.put(deleteIndex, lastKey);
            keyIndexMap.remove(val);
            indexKeyMap.remove(lastIndex);
            return true;
        }

        public int getRandom() {
            if (size == 0) {
                return -1;
            }
            int randomIndex = (int) (Math.random() * size);
            return indexKeyMap.get(randomIndex);
        }
    }
}
