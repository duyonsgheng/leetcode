package custom.code_2022_12;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1396
 * @Author Duys
 * @Description
 * @Date 2022/12/27 14:47
 **/
// 1396. 设计地铁系统
public class LeetCode_1396 {
    class UndergroundSystem {
        Map<Integer, Integer> inmapById;
        Map<Integer, String> inmapByName;
        Map<String, Map<String, Integer>> totals;
        Map<String, Map<String, Integer>> sizes;

        public UndergroundSystem() {
            inmapById = new HashMap<>();
            inmapByName = new HashMap<>();
            totals = new HashMap<>();
            sizes = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            inmapById.put(id, t);
            inmapByName.put(id, stationName);
        }

        public void checkOut(int id, String stationName, int t) {
            int in = inmapById.get(id);
            String inName = inmapByName.get(id);
            // 当前开始站到结束站之间总共花了时间
            int sum = totals.computeIfAbsent(inName, key -> new HashMap<>()).getOrDefault(stationName, 0);
            sum += t - in;
            totals.get(in).put(stationName, sum);
            int size = sizes.computeIfAbsent(inName, key -> new HashMap<>()).getOrDefault(stationName, 0);
            size += 1;
            sizes.get(inName).put(stationName, size);
        }

        public double getAverageTime(String startStation, String endStation) {
            double total = totals.get(startStation).get(endStation);
            double size = sizes.get(startStation).get(endStation);
            return total / size;
        }
    }
}
