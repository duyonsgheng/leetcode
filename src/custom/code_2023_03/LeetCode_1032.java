package custom.code_2023_03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeeoCode_1032
 * @Author Duys
 * @Description
 * @Date 2023/3/24 10:53
 **/
// 字符流
// AC自动机
class LeeoCode_1032 {
    private class StreamChecker {
        TreeNode root;
        TreeNode tmp;

        public StreamChecker(String[] words) {
            root = new TreeNode();
            for (String word : words) {
                TreeNode cur = root;
                for (int i = 0; i < word.length(); i++) {
                    int index = word.charAt(i) - 'a';
                    if (cur.next[index] == null) {
                        cur.next[index] = new TreeNode();
                    }
                    cur = cur.next[index];
                }
                cur.isEnd = true;
            }
            root.fail = root;
            Queue<TreeNode> queue = new LinkedList<>();
            for (int i = 0; i < 26; i++) {
                if (root.next[i] != null) {
                    root.next[i].fail = root;
                    queue.add(root.next[i]);
                } else {
                    root.next[i] = root;
                }
            }
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                cur.isEnd = cur.isEnd || cur.fail.isEnd;
                for (int i = 0; i < 26; i++) {
                    if (cur.next[i] != null) {
                        cur.next[i].fail = cur.fail.next[i];
                        queue.add(cur.next[i]);
                    } else {
                        cur.next[i] = cur.fail.next[i];
                    }
                }
            }
            tmp = root;
        }

        public boolean query(char letter) {
            tmp = tmp.next[letter - 'a'];
            return tmp.isEnd;
        }
    }

    public class TreeNode {
        TreeNode[] next;
        TreeNode fail;
        boolean isEnd;

        public TreeNode() {
            next = new TreeNode[26];
        }
    }
}


