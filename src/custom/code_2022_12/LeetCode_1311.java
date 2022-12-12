package custom.code_2022_12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName LeetCode_1311
 * @Author Duys
 * @Description
 * @Date 2022/12/7 16:23
 **/
// 1311. 获取你好友已观看的视频
public class LeetCode_1311 {

    public static List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        // 把好友分层
        Integer[] indexs = new Integer[n];
        indexs[id] = 0;
        Queue<Integer> queue = new LinkedList<>();
        int l = 1;
        // 直属下级属于第一层
        for (int i = 0; i < friends[id].length; i++) {
            int idx = friends[id][i];
            queue.add(idx);
            indexs[idx] = l;
        }
        while (l < level) {
            int size = queue.size();
            l++;
            while (size > 0) {
                int cur = queue.poll();
                for (int i = 0; i < friends[cur].length; i++) {
                    int idx = friends[cur][i];
                    if (indexs[idx] == null) {
                        queue.add(idx);
                        indexs[idx] = l;
                    }
                }
                size--;
            }
        }
        Map<String, Integer> cont = new HashMap<>();
        Set<String> set = new HashSet<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (String str : watchedVideos.get(cur)) {
                set.add(str);
                cont.put(str, cont.getOrDefault(str, 0) + 1);
            }
        }
        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans, (a, b) -> cont.get(a) == cont.get(b) ? a.compareTo(b) : cont.get(a) - cont.get(b));
        return ans;
    }


    public static void main(String[] args) {
        // [["bjwtssmu"],["aygr","mqls"],["vrtxa","zxqzeqy","nbpl","qnpl"],["r","otazhu","rsf"],["bvcca","ayyihidz","ljc","fiq","viu"]]
        //[[3,2,1,4],[0,4],[4,0],[0,4],[2,3,1,0]]
        //3
        //1
        List<List<String>> vd = new ArrayList<>();
        vd.add(Arrays.asList("bjwtssmu"));
        vd.add(Arrays.asList("aygr", "mqls"));
        vd.add(Arrays.asList("vrtxa", "zxqzeqy", "nbpl", "qnpl"));
        vd.add(Arrays.asList("r", "otazhu", "rsf"));
        vd.add(Arrays.asList("bvcca", "ayyihidz", "ljc", "fiq", "viu"));
        int[][] fr = new int[][]{{3, 2, 1, 4}, {0, 4}, {4, 0}, {0, 4}, {2, 3, 1, 0}};
        List<String> list = watchedVideosByFriends(vd, fr, 3, 1);
        // ["ayyihidz","bjwtssmu","bvcca","fiq","ljc","viu"]
        System.out.println(list);
    }
}
