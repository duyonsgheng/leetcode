package duys.class_08_13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName ACAuto
 * @Author Duys
 * @Description AC自动机
 * @Date 2021/8/16 13:15
 **/
public class AC_Auto {

    /**
     * 大文章中的字符串匹配问题
     */
    /**
     * 从若干敏感词开始：
     * 前缀树-
     * 建立一个空节点，然后，依次建立前缀树，每一个敏感词结束后，下一个敏感词，
     * 从头节点开始，看看有没有走向每一个敏感词的每一个字符的路
     */
    // 前缀树的节点
    public static class Node {
        // 如果一个节点的node为空，表示不是结尾
        public String end;
        // 当end不为的时候，endUse才有意义，表示当前node结尾的字符串有没有加入过答案
        public boolean endUse;

        public Node fail;

        public Node[] nexts;

        public Node() {
            end = null;
            endUse = false;
            fail = null;
            nexts = new Node[26];
        }
    }

    public static class ACAutomation {
        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        // 给定一个敏感词，然后构建一颗前缀树
        public void insert(String s) {
            char[] str = s.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                // 找一个合适的位置
                index = str[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                // 依次往下创建整个字符串的路
                cur = cur.nexts[index];
            }
            cur.end = s;
        }

        public void build() {
            // 宽度优先遍历，使用队列
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            // 当前节点的fail指针，为了遍历使用的
            Node cfail = null;

            while (!queue.isEmpty()) {
                // 一定是某一个节点的父节点
                cur = queue.poll();
                // 挨着遍历他的所有子节点
                // 这里是父节点给孩子节点设置fail指针
                for (int i = 0; i < 26; i++) {
                    if (cur.nexts[i] != null) {
                        // 这里，每一个节点的初始的fail都是根节点root，如果后面逻辑需要改变，就改成相应的节点
                        cur.nexts[i].fail = root;
                        cfail = cur.fail;
                        while (cfail != null) {
                            if (cfail.nexts[i] != null) {
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        public List<String> containWords(String content) {
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                // 找路
                index = str[i] - 'a';
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.endUse) {
                        break;
                    }
                    if (follow.end != null) {
                        ans.add(follow.end);
                        follow.endUse = true;
                    }
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }

    /**
     * AC自动机：增加一个指针 fail指针
     * 1、头节点的fail指针只想空
     * 2、头节点的子节点指向头
     * 3、某一个节点X，X的父节点走向X的路叫做M，通过X的父节点的fail指向Y节点，如果Y节点有M，那么X指向，从Y出发路为M的点，
     * 如果没有，那么继续找Y的路，直到fail指向空，如果到最后还是没有，那么X的fail指向头节点
     */
}
