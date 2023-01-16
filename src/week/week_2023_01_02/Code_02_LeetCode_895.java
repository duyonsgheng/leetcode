package week.week_2023_01_02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_02_LeetCode_895
 * @Author Duys
 * @Description
 * @Date 2023/1/16 9:05
 **/
// 895. 最大频率栈
public class Code_02_LeetCode_895 {
    class FreqStack {
        private int topTimes;
        private Map<Integer, List<Integer>> cntMaps = new HashMap<>();
        private Map<Integer, Integer> valueTimeMaps = new HashMap<>();

        public void push(int v) {
            // 出现次数+1
            valueTimeMaps.put(v, valueTimeMaps.getOrDefault(v, 0) + 1);
            int curTimes = valueTimeMaps.get(v);
            if (!cntMaps.containsKey(curTimes)) {
                cntMaps.put(curTimes, new ArrayList<>());
            }
            List<Integer> curList = cntMaps.get(curTimes);
            curList.add(v);
            // 更新当前出现最大次数
            topTimes = Math.max(topTimes, curTimes);
        }

        public int pop() {
            List<Integer> list = cntMaps.get(topTimes);
            int last = list.size() - 1;
            int ans = list.remove(last);
            // 如果出现次数最多的被弹出完了，那么就删掉，并且最大次数--
            if (list.size() == 0) {
                cntMaps.remove(topTimes--);
            }
            int times = valueTimeMaps.get(ans);
            if (times == 1) {
                valueTimeMaps.remove(ans);
            } else {
                valueTimeMaps.put(ans, times - 1);
            }
            return ans;
        }
    }
}
