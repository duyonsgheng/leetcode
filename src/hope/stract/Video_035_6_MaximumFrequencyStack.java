package hope.stract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName Video_035_6_MaximumFrequencyStack
 * @date 2023年08月16日
 */
// https://leetcode.cn/problems/maximum-frequency-stack/
// 895. 最大频率栈
public class Video_035_6_MaximumFrequencyStack {
    class FreqStack {
        int topTimes;
        Map<Integer, List<Integer>> cntValues;
        Map<Integer, Integer> valuesTimes;

        public FreqStack() {
            cntValues = new HashMap<>();
            valuesTimes = new HashMap<>();
            topTimes = 0;
        }

        public void push(int val) {
            valuesTimes.put(val, valuesTimes.getOrDefault(val, 0) + 1);
            int curTopTimes = valuesTimes.get(val);
            if (!cntValues.containsKey(curTopTimes)) {
                cntValues.put(curTopTimes, new ArrayList<>());
            }
            List<Integer> list = cntValues.get(curTopTimes);
            list.add(val);
            topTimes = Math.max(topTimes, curTopTimes);
        }

        public int pop() {
            List<Integer> list = cntValues.get(topTimes);
            int ans = list.remove(list.size() - 1);
            if (list.size() == 0) {
                cntValues.remove(topTimes--);
            }
            int times = valuesTimes.get(ans);
            if (times == 1) {
                valuesTimes.remove(ans);
            } else {
                valuesTimes.put(ans, times - 1);
            }
            return ans;
        }
    }
}
