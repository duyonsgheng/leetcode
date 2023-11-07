package custom.code_2023_09;

import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2182
 * @date 2023年09月07日
 */
// 2182. 构造限制重复的字符串
// https://leetcode.cn/problems/construct-string-with-repeat-limit/
public class LeetCode_2182 {
    public String repeatLimitedString(String s, int repeatLimit) {
        // aababab  2
        // aaaabbb
        PriorityQueue<Node> queue1 = new PriorityQueue<>((a, b) -> b.c - a.c);
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (i == 0) {
                continue;
            }
            queue1.add(new Node((char) (i + 'a'), arr[i]));
        }
        StringBuffer buffer = new StringBuffer();
        while (!queue1.isEmpty()) {
            Node cur = queue1.poll();
            if (cur.cnt <= repeatLimit) {
                for (int i = 0; i < cur.cnt; i++) {
                    buffer.append(cur.c);
                }
                continue;
            } else {
                for (int i = 0; i < repeatLimit; i++) {
                    buffer.append(cur.c);
                }
                if (queue1.isEmpty()) {
                    Node next = queue1.poll();
                    buffer.append(next.c);
                    next.cnt--;
                    if (next.cnt > 0) {
                        queue1.add(next);
                    }
                } else {
                    break;
                }
                cur.cnt -= repeatLimit;
                queue1.add(cur);
            }
        }
        return buffer.toString();

    }

    class Node {
        char c;
        int cnt;

        public Node(char ch, int c) {
            c = ch;
            cnt = c;
        }
    }
}
