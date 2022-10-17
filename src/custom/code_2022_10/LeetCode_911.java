package custom.code_2022_10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_911
 * @Author Duys
 * @Description
 * @Date 2022/10/17 15:05
 **/
// 911. 在线选举
public class LeetCode_911 {

    class TopVotedCandidate {

        List<int[]> list = new ArrayList<>();

        public TopVotedCandidate(int[] persons, int[] times) {
            int val = 0; // 当前的票最多的人
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < persons.length; i++) {
                // 当前这个人被投递了多少次
                map.put(persons[i], map.getOrDefault(persons[i], 0) + 1);
                if (map.get(persons[i]) >= val) {
                    val = map.get(persons[i]);
                    // 记录的票最多的
                    list.add(new int[]{times[i], persons[i]});
                }
            }
        }

        public int q(int t) {
            // 查询 <= t 最右测位置
            // 1 3 3 4 4 5 5 5 6 6 6
            // 5  二分
            int l = 0;
            int r = list.size() - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (list.get(m)[0] <= t) {
                    l = m;
                } else {
                    r = m - 1;
                }
            }
            return list.get(r)[0] <= t ? list.get(r)[1] : 0;
        }
    }


}
