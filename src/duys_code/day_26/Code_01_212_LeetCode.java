package duys_code.day_26;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName Code_01_212_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/word-search-ii/
 * @Date 2021/11/18 16:11
 **/
public class Code_01_212_LeetCode {

    /**
     * 首先我们把words 每一个元素建立前缀树
     */
    public List<String> findWords(char[][] board, String[] words) {
        if (words == null || words.length <= 0
                || board == null || board.length <= 0
                || board[0] == null || board[0].length <= 0) {
            return new ArrayList<>();
        }
        TrieNode head = new TrieNode(); // 建一个头节点
        Set<String> set = new HashSet<>();
        for (String word : words) {
            if (set.contains(word)) {
                continue;
            }
            fillTrie(head, word);
            set.add(word);
        }
        // 整体答案
        List<String> ans = new ArrayList<>();
        // 每一次走过得路径
        List<Character> path = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 尝试从每一个位置出发，上下左右去搞
                process(board, i, j, head, ans, new LinkedList<>());
            }
        }
        return ans;
    }

    public static int process(char[][] board, int row, int col, TrieNode head,
                              List<String> ans, LinkedList<Character> path) {
        char curChar = board[row][col];
        // 由于不能走回头路，所以，在深度优先遍历得时候，先把走过的位置全部置为0
        if (curChar == 0) {
            return 0;
        }
        int curIndex = curChar - 'a';
        // 没有路了或者之前这个答案已经被收集了，那么都不用继续了
        //  head.nexts[curIndex].pass == 0 重要的剪枝，之前已经收集过当前链路的答案，就不用重复收集了
        if (head.nexts[curIndex] == null || head.nexts[curIndex].pass == 0) {
            return 0;
        }
        // 走到下一个节点去
        head = head.nexts[curIndex];
        // 当前走到了这个位置，加入到路径种
        path.addLast(curChar);
        int next = 0;
        if (head.end) {//刚刚来到得位置就是一个单词得结束位置，收集一下
            next++;
            // 不能重复收集
            head.end = false;
            ans.add(getAns(path));
        }

        // 开始上下左右来玩儿了
        board[row][col] = 0;

        if (row > 0) {
            next += process(board, row - 1, col, head, ans, path);
        }
        if (row < board.length - 1) {
            next += process(board, row + 1, col, head, ans, path);
        }
        if (col > 0) {
            next += process(board, row, col - 1, head, ans, path);
        }
        if (col < board[0].length - 1) {
            next += process(board, row, col + 1, head, ans, path);
        }
        // 恢复现场，因为是深度优先遍历得
        board[row][col] = curChar;
        path.pollLast();// 上面加入了也需要回退，其他得位置尝试不同得单词
        // 每次把搞定了的给减去，剪枝，下一回遇到了，意味着我这一条链路上的答案
        head.pass -= next;
        return next;
    }

    public static String getAns(LinkedList<Character> path) {
        char[] str = new char[path.size()];
        int index = 0;
        for (Character chars : path) {
            str[index++] = chars;
        }
        return String.valueOf(str);
    }

    public static void fillTrie(TrieNode head, String word) {
        head.pass++;
        char[] str = word.toCharArray();
        int index = 0;
        TrieNode cur = head;
        for (int i = 0; i < str.length; i++) {
            index = str[i] - 'a';
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new TrieNode();
            }
            cur = cur.nexts[index];
            cur.pass++;
        }
        cur.end = true;
    }

    public static class TrieNode {
        public TrieNode[] nexts;
        public int pass; // 意思就是有多少单词通过本节点，因为收集得时候要求不重复，所以收集过了，就不需要重复搞了
        public boolean end;

        public TrieNode() {
            nexts = new TrieNode[26];
            pass = 0;
            end = false;
        }
    }
}
