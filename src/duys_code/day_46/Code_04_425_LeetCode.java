package duys_code.day_46;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Code_04_425_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/word-squares/
 * @Date 2021/10/18 13:25
 **/
public class Code_04_425_LeetCode {
    /**
     * 单词方块
     */
    /**
     * // 题目描述如下：
     * // 给定n个字符串，并且每个字符串长度一定是n，请组成单词方阵，比如：
     * // 给定4个字符串，长度都是4，["ball","area","lead","lady"]
     * // 可以组成如下的方阵：
     * // b a l l
     * // a r e a
     * // l e a d
     * // l a d y
     */
    /**
     * 以任意的单词作为第一行，那么第一列只能找第一行开头的单词的后缀单词了
     * 所有单词的所有前缀单词
     */
    public static List<List<String>> wordSquares(String[] words) {
        int len = words[0].length();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            for (int end = 0; end < len; end++) {
                String preFix = word.substring(0, end);
                if (!map.containsKey(preFix)) {
                    map.put(preFix, new ArrayList<>());
                }
                map.get(preFix).add(word);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        process(0, len, map, new LinkedList<>(), ans);
        return ans;
    }

    // index - 当前填到第几号单词，从0开始，已知到len -1
    // path - 之前作过的决定 0到index-1填的
    public static void process(int index, int len, HashMap<String, List<String>> map, LinkedList<String> path, List<List<String>> ans) {
        if (index == len) {
            ans.add(new ArrayList<>(path));
        } else {
            // 比如当前填到了第3号。那么当先单词的限制是啥，就是之前作过的决定，的第3个位置的单词拼接
            StringBuffer sb = new StringBuffer();
            for (String p : path) {
                sb.append(p.charAt(index));
            }
            String preFix = sb.toString();
            if (map.containsKey(preFix)) {
                for (String next : map.get(preFix)) {
                    // 这里做一个深度优先。需要恢复现场，不能给下一个子过程造成困扰
                    path.addLast(next);
                    process(index + 1, len, map, path, ans);
                    path.pollLast();
                }
            }
        }
    }
}
