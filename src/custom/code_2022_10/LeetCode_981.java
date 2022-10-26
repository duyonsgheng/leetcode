package custom.code_2022_10;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName LeetCode_981
 * @Author Duys
 * @Description
 * @Date 2022/10/26 13:52
 **/
// 981. 基于时间的键值存储
public class LeetCode_981 {
    static class TimeMap {
        Map<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new TreeMap<>());
            }
            map.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }
            // 返回小于等 timestamp 的最大值
            Integer tk = map.get(key).floorKey(timestamp);
            if (tk == null) {
                return "";
            }
            return map.get(key).get(tk);
        }
    }

}
