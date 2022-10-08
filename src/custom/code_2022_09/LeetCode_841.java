package custom.code_2022_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_841
 * @Author Duys
 * @Description
 * @Date 2022/9/28 15:23
 **/
// 841. 钥匙和房间
public class LeetCode_841 {
    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(3));
        rooms.add(Arrays.asList());
        System.out.println(canVisitAllRooms(rooms));
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] ans = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (ans[cur]) {
                continue;
            }
            ans[cur] = true;
            for (int next : rooms.get(cur)) {
                queue.offer(next);
            }
        }
        for (boolean a : ans) {
            if (!a) {
                return false;
            }
        }
        return true;
    }
}
