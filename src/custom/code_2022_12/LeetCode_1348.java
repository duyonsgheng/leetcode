package custom.code_2022_12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_1348
 * @Author Duys
 * @Description
 * @Date 2022/12/13 14:41
 **/
// 1348. 推文计数
public class LeetCode_1348 {

    class TweetCounts {
        Map<String, List<Integer>> map;
        Map<String, Integer> type;

        public TweetCounts() {
            map = new HashMap<>();
            type = new HashMap<>();
            type.put("minute", 60);
            type.put("hour", 60 * 60);
            type.put("day", 60 * 60 * 24);
        }

        public void recordTweet(String tweetName, int time) {
            map.computeIfAbsent(tweetName, key -> new ArrayList<>()).add(time);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            List<Integer> times = map.get(tweetName);
            if (times == null || times.size() == 0) {
                return new ArrayList<>();
            }
            Collections.sort(times);
            int l = 0, r = times.size() - 1;
            int s = 0;
            while (l <= r) {
                int m = l + ((r - l) >> 2);
                if (times.get(m) >= startTime) {
                    s = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            // 算整个区间有多少个位置
            int size = (endTime - startTime) / type.get(freq) + 1;
            int[] cnt = new int[size];
            for (int i = s; i < times.size() && times.get(i) <= endTime && times.get(i) >= startTime; i++) {
                // 属于哪一个位置
                int index = (times.get(i) - startTime) / type.get(freq);
                cnt[index]++;
            }
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                ans.add(cnt[i]);
            }
            return ans;
        }
    }
}
