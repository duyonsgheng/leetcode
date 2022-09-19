package custom.code_2022_09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName LeetCode_752
 * @Author Duys
 * @Description
 * @Date 2022/9/16 16:51
 **/
public class LeetCode_752 {
    public int openLock(String[] deadends, String target) {
        String start = "0000";
        if (start.equals(target)) {
            return 0;
        }
        Set<String> dead = new HashSet<>();
        for (String s : deadends) {
            dead.add(s);
        }
        if (dead.contains("0000")) {
            return -1;
        }
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        Set<String> set = new HashSet<>();
        set.add(start);

        while (!queue.isEmpty()) {
            step++;
            int cursize = queue.size();
            for (int i = 0; i < cursize; i++) {
                String curStates = queue.poll();
                for (String next : nexts(curStates)) {
                    if (!set.contains(next) && !dead.contains(next)) {
                        if (next.equals(target)) {
                            return step;
                        }
                        queue.add(next);
                        set.add(next);
                    }
                }
            }
        }
        return -1;
    }

    public static List<String> nexts(String state) {
        List<String> nexts = new ArrayList<>();
        char[] arr = state.toCharArray();
        for (int i = 0; i < 4; i++) {
            char cur = arr[i];
            arr[i] = cur == '9' ? '0' : (char) (cur + 1);
            nexts.add(String.valueOf(arr));
            arr[i] = cur == '0' ? '9' : (char) (cur - 1);
            nexts.add(String.valueOf(arr));
            arr[i] = cur;
        }
        return nexts;
    }

    // A *
    public int openLock1(String[] deadends, String target) {
        String start = "0000";
        if (start.equals(target)) {
            return 0;
        }
        Set<String> dead = new HashSet<>();
        for (String s : deadends) {
            dead.add(s);
        }
        if (dead.contains("0000")) {
            return -1;
        }
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.sum - b.sum);
        queue.add(new Node(start, target, 0));
        Set<String> set = new HashSet<>();
        set.add(start);

        while (!queue.isEmpty()) {
            int cursize = queue.size();
            for (int i = 0; i < cursize; i++) {
                Node curStates = queue.poll();
                for (String next : nexts(curStates.state)) {
                    if (!set.contains(next) && !dead.contains(next)) {
                        if (next.equals(target)) {
                            return curStates.dst + 1;
                        }
                        queue.add(new Node(next, target, curStates.dst + 1));
                        set.add(next);
                    }
                }
            }
        }
        return -1;
    }

    class Node {
        public String state;
        public int sum;
        public int dst;
        public int max;

        public Node(String ste, String target, int p) {
            state = ste;
            dst = p;
            max = get(ste, target);
            sum = dst + max;
        }

        public int get(String s, String t) {
            int res = 0;
            for (int i = 0; i < 4; i++) {
                int d = Math.abs(s.charAt(i) - t.charAt(i));
                res += Math.min(10, 10 - d);
            }
            return res;
        }
    }
}
