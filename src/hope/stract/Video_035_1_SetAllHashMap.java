package hope.stract;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName Video_035_1_SetAllHashMap
 * @date 2023年08月16日
 */
// setAll功能的哈希表
// https://www.nowcoder.com/practice/7c4559f138e74ceb9ba57d76fd169967
public class Video_035_1_SetAllHashMap {

    public Map<Integer, int[]> map = new HashMap<>(); // key ： value times
    public int setAllValue;
    public int setAllTime;
    public int time;

    public void put(int k, int v) {
        if (map.containsKey(k)) {
            int[] cur = map.get(k);
            cur[0] = v;
            cur[1] = time++;
        } else {
            map.put(k, new int[]{v, time++});
        }
    }

    public void setAll(int v) {
        setAllValue = v;
        setAllTime = time++;
    }

    public int get(int k) {
        if (!map.containsKey(k)) {
            return -1;
        }
        int[] cur = map.get(k);
        if (cur[1] > setAllTime) {
            return cur[0];
        } else {
            return setAllValue;
        }
    }
}
