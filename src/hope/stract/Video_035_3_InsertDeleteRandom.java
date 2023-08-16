package hope.stract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName Video_035_3_InsertDeleteRandom
 * @date 2023年08月16日
 */
// https://leetcode.cn/problems/insert-delete-getrandom-o1/
// 380. O(1) 时间插入、删除和获取随机元素
public class Video_035_3_InsertDeleteRandom {

    class RandomizedSet {
        Map<Integer, Integer> map;
        List<Integer> arr;

        // 用最后一个去填删除的位置
        public RandomizedSet() {
            map = new HashMap<>();
            arr = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, arr.size());
            arr.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int idx1 = map.get(val);
            int endV = arr.get(arr.size() - 1);
            map.put(endV, idx1);
            arr.set(idx1, endV);
            map.remove(val);
            arr.remove(arr.size() - 1);
            return true;
        }

        public int getRandom() {
            return arr.get((int) (Math.random() * arr.size()));
        }
    }
}
