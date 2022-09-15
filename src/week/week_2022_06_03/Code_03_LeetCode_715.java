package week.week_2022_06_03;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @ClassName Code_03_LeetCode_715
 * @Author Duys
 * @Description
 * @Date 2022/6/23 15:52
 **/
// 715. Range 模块
public class Code_03_LeetCode_715 {

    class RangeModule {
        // key 是某一个区间的开始
        // value 这个区间的结束位置
        private TreeMap<Integer, Integer> map;

        public RangeModule() {
            map = new TreeMap<>();
        }

        public void addRange(int left, int right) {
            if (right <= left) {
                return;
            }
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if (start == null && end == null) {
                map.put(left, right);
            }
            // left = 3 right =7
            // [2,4]  [5,10]
            //
            else if (start != null && map.get(start) >= left) {
                map.put(start, Math.max(map.get(end), right));
            } else {
                map.put(left, Math.max(map.get(end), right));
            }
            Map<Integer, Integer> subMap = map.subMap(left, false, right, true);
            Set<Integer> set = new HashSet<>(subMap.keySet());
            map.keySet().removeAll(set);
        }

        public boolean queryRange(int left, int right) {
            Integer start = map.floorKey(left);
            if (start == null) {
                return false;
            }
            return map.get(start) >= right;
        }

        public void removeRange(int left, int right) {
            if (right <= left) {
                return;
            }
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if (end != null && map.get(end) > right) {
                map.put(right, map.get(end));
            }
            if (start != null && map.get(start) > left) {
                map.put(start, left);
            }
            Map<Integer, Integer> subMap = map.subMap(left, true, right, false);
            Set<Integer> set = new HashSet<>(subMap.keySet());
            map.keySet().removeAll(set);
        }
    }
}
