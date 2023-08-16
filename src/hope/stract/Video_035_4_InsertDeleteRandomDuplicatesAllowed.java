package hope.stract;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName Video_035_4_InsertDeleteRandomDuplicatesAllowed
 * @date 2023年08月16日
 */
// https://leetcode.cn/problems/insert-delete-getrandom-o1-duplicates-allowed/
// 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
public class Video_035_4_InsertDeleteRandomDuplicatesAllowed {
    class RandomizedCollection {
        Map<Integer, Set<Integer>> map;
        List<Integer> arr;

        public RandomizedCollection() {
            map = new HashMap<>();
            arr = new ArrayList<>();
        }

        public boolean insert(int val) {
            arr.add(val);
            Set<Integer> set = map.getOrDefault(val, new HashSet<>());
            set.add(arr.size() - 1);
            map.put(val, set);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            Set<Integer> set = map.get(val);
            int idx1 = set.iterator().next();
            int endv = arr.get(arr.size() - 1);
            if (val == endv) {
                set.remove(arr.size() - 1);
            } else {
                Set<Integer> set1 = map.get(endv);
                set1.add(idx1);
                arr.set(idx1, endv);
                set1.remove(arr.size() - 1);
                set.remove(idx1);
            }
            arr.remove(arr.size() - 1);
            if (set.isEmpty()) {
                map.remove(val);
            }
            return true;
        }

        public int getRandom() {
            return arr.get((int) (Math.random() * arr.size()));
        }
    }
}
