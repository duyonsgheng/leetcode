package custom.code_2022_09;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName LeetCode_854
 * @Author Duys
 * @Description
 * @Date 2022/9/21 9:11
 **/
// 854. 相似度为 K 的字符串
// 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
// 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
// 链接：https://leetcode.cn/problems/k-similar-strings
public class LeetCode_854 {
    // 宽度优先 - 一次一层
    public int kSimilarity1(String s1, String s2) {
        int n = s1.length();
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Node(s1, 0));
        visited.add(s1);
        int ans = 0;
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                Node cur = queue.poll();
                String curS = cur.cur;
                int curDest = cur.dest;
                if (curS.equals(s2)) {
                    return ans;
                }
                // 一路相等的话，找到不等的位置
                while (curDest < n && curS.charAt(curDest) == s2.charAt(curDest))
                    curDest++;
                // 遇到不等的，需要交换了
                for (int j = curDest + 1; j < n; j++) {
                    if (curS.charAt(j) == s2.charAt(j)) {
                        continue;
                    }
                    if (s2.charAt(curDest) == curS.charAt(j)) {
                        String next = swap(curS, curDest, j);
                        if (!visited.contains(next)) {
                            queue.add(new Node(next, curDest + 1));
                            visited.add(next);
                        }
                    }
                }
            }
            ans++;
        }
        return ans;
    }


    // 深度优先
    int dfsAns = 0;

    public int kSimilarity2(String s1, String s2) {
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                builder1.append(s1.charAt(i));
                builder2.append(s2.charAt(i));
            }
        }
        if (builder1.length() <= 0) {
            return 0;
        }
        dfsAns = builder1.length() - 1;
        dfs(0, 0, builder1.toString(), builder2.toString());
        return dfsAns;
    }

    public void dfs(int index, int dest, String s1, String s2) {
        if (dest > dfsAns) {
            return;
        }
        // 如果一路相等，就往后
        while (index < s1.length() && s1.charAt(index) == s2.charAt(index)) {
            index++;
        }
        if (index == s1.length()) {
            dfsAns = Math.min(dfsAns, dest);
        }
        // 给一个下限，超过了不需要算了。
        if (dest + min(s1, s2, index) >= dfsAns) {
            return;
        }
        for (int i = index + 1; i < s1.length(); i++) {
            // 找到相等的位置
            if (s1.charAt(i) == s2.charAt(index)) {
                String next = swap(s1, i, index);
                dfs(index + 1, dest + 1, next, s2);
            }
        }
    }

    // 曼哈顿距离
    public int min(String s1, String s2, int index) {
        int min = 0;
        for (int i = index; i < s1.length(); i++) {
            min += s1.charAt(i) == s2.charAt(i) ? 0 : 1;
        }
        return min;
    }

    public String swap(String cur, int i, int j) {
        char[] arr = cur.toCharArray();
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
        return new String(arr);
    }

    class Node {
        String cur;
        int dest;

        public Node(String s, int d) {
            cur = s;
            dest = d;
        }
    }
}
