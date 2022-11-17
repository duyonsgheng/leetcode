package week.week_2022_11_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName Code_04_LeetCode_745
 * @Author Duys
 * @Description
 * @Date 2022/11/17 10:49
 **/
// 745. 前缀和后缀搜索
public class Code_04_LeetCode_745 {
    /**
     * 思路：
     * 前缀来一个
     * 后缀来一个
     * 分别记录下当前节点哪些位置的单词
     */
    class WordFilter {

        TrieNode pre;
        TrieNode suf;

        public WordFilter(String[] words) {
            pre = new TrieNode();
            suf = new TrieNode();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                // 前缀来一遍
                TrieNode cur = pre;
                for (int j = 0; j < word.length(); j++) {
                    int path = word.charAt(j) - 'a';
                    if (cur.nexts[path] == null) {
                        cur.nexts[path] = new TrieNode();
                    }
                    cur = cur.nexts[path];
                    cur.indexs.add(i);
                }
                // 后缀来一遍
                cur = suf;
                for (int j = word.length() - 1; j >= 0; j--) {
                    int path = word.charAt(j) - 'a';
                    if (cur.nexts[path] == null) {
                        cur.nexts[path] = new TrieNode();
                    }
                    cur = cur.nexts[path];
                    cur.indexs.add(i);
                }
            }
        }

        public int f(String pref, String suff) {
            // 先查询前缀的
            List<Integer> preList = null;
            TrieNode cur = pre;
            for (int i = 0; i < pref.length() && cur != null; i++) {
                cur = cur.nexts[pref.charAt(i) - 'a'];
            }
            if (cur != null) {
                preList = cur.indexs;
            }
            // 没找到
            if (preList == null) {
                return -1;
            }
            List<Integer> sufList = null;
            cur = suf;
            for (int i = suff.length() - 1; i >= 0 && cur != null; i--) {
                cur = cur.nexts[suff.charAt(i) - 'a'];
            }
            if (cur != null) {
                sufList = cur.indexs;
            }
            if (sufList == null) {
                return -1;
            }
            // 找两个list的交集中最大的
            List<Integer> small = preList.size() <= sufList.size() ? preList : sufList;
            List<Integer> big = small == preList ? sufList : preList;
            for (int i = small.size() - 1; i >= 0; i--) {
                if (bs(big, small.get(i))) {
                    return small.get(i);
                }
            }
            // 还不能使用api求交集
            /*preList.retainAll(sufList);
            return preList.size() == 0 ? -1 : preList.get(preList.size() - 1);*/
            return -1;
        }

        private boolean bs(List<Integer> sorted, int num) {
            int l = 0;
            int r = sorted.size() - 1;
            int m = 0;
            int midValue = 0;
            while (l <= r) {
                m = (l + r) / 2;
                midValue = sorted.get(m);
                if (midValue == num) {
                    return true;
                } else if (midValue < num) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return false;
        }
    }

    class TrieNode {
        TrieNode[] nexts;
        List<Integer> indexs;

        public TrieNode() {
            nexts = new TrieNode[26];
            indexs = new ArrayList<>();
        }
    }

}
