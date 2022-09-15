package pro;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName AC_1
 * @Author Duys
 * @Description
 * @Date 2022/5/30 14:58
 **/
// 一篇大文章里面的敏感词
public class AC_1 {
    // 1.前缀树
    // 2.前缀树上做KMP ，以当前节点为前缀的最大长度是哪一个，就是通过fail指针去跳的

    public static class ACAutomation {
        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        // 构建前缀树
        public void insert(String s) {
            if (s == null || s.length() < 1) {
                return;
            }
            char[] str = s.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = s;
        }

        // 搞定我们每一个节点的fail指针
        public void build() {
            // 宽度优先遍历，使用queue
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node fail = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                // 通过父级去搞定子级的fail
                // 所有的路
                for (int i = 0; i < 26; i++) {
                    // 真的有路
                    if (cur.nexts[i] != null) {
                        // 先把fail设置到root
                        cur.nexts[i].fail = root;
                        fail = cur.fail;
                        // 看看我的fail究竟应该指向谁
                        while (fail != null) {
                            // cur的父级的fail指向的节点，有走向我的路，则指过去
                            if (fail.nexts[i] != null) {
                                cur.nexts[i].fail = fail.nexts[i];
                                break;
                            }
                            // 否则，我就一直fail
                            fail = fail.fail;
                        }
                        // 我的被搞定了，那么下一次我作为父出来，去搞定我的子
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        // 开始匹配了
        public List<String> containWords(String content) {
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';

                // 首先根据fail往下走看看
                // 没路了，并且都已经是root了，没法走了
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }

                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.use) {
                        break;
                    }
                    if (follow.end != null) {
                        ans.add(follow.end);
                        follow.use = true;
                    }
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }


    // 前缀树的节点
    public static class Node {
        public String end;
        public boolean use;
        public Node fail;
        public Node[] nexts;

        public Node() {
            use = false;
            fail = null;
            end = null;
            nexts = new Node[26];
        }
    }

}
