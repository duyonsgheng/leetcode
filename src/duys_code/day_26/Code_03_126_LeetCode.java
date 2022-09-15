package duys_code.day_26;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName Code_03_126_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/word-ladder-ii/
 * @Date 2021/11/19 11:19
 **/
public class Code_03_126_LeetCode {
    // 1.把wordList 和 beginWord 所有的变化全部存下来

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (beginWord == null || endWord == null || wordList == null || wordList.size() <= 0) {
            return res;
        }
        wordList.add(beginWord);
        Map<String, List<String>> nexts = getNexts(wordList);
        Map<String, Integer> dict = getDistances(beginWord, nexts);
        LinkedList<String> path = new LinkedList<>();
        getShortPaths(beginWord, endWord, nexts, dict, path, res);
        return res;
    }

    // cur 当前来到的字符串
    // to 要去的字符串
    // nexts -> 每一个字符串的邻居有哪些
    // dict -> start到 字符串的距离
    // path 来到cur之前，经过了哪些
    // res 结果
    public static void getShortPaths(String cur, String to, Map<String, List<String>> nexts,
                                     Map<String, Integer> dict, LinkedList<String> path, List<List<String>> res) {
        path.add(cur);
        if (to.equals(cur)) {
            res.add(new LinkedList<>(path));
        } else {
            for (String next : nexts.get(cur)) {
                if (dict.get(next) == dict.get(cur) + 1) {
                    getShortPaths(next, to, nexts, dict, path, res);
                }
            }
        }
        // 这一轮算完了，需要现场还原
        path.pollLast();
    }

    // 获取所有的变化路径产生的亲戚
    public static Map<String, List<String>> getNexts(List<String> words) {
        Set<String> set = new HashSet<>(words);
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            map.put(words.get(i), getNext(words.get(i), set));
        }
        return map;
    }

    public static List<String> getNext(String word, Set<String> words) {
        List<String> ans = new LinkedList<>();
        char[] str = word.toCharArray();
        for (int i = 0; i < str.length; i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                if (str[i] == j) {
                    continue;
                }
                char tmp = str[i];
                str[i] = j;
                if (words.contains(String.valueOf(str))) {
                    ans.add(String.valueOf(str));
                }
                // 还原
                str[i] = tmp;
            }
        }
        return ans;
    }

    // 生成距离表，只是对start负责，深度优先遍历
    public static Map<String, Integer> getDistances(String start, Map<String, List<String>> nexts) {
        Map<String, Integer> dist = new HashMap<>();
        dist.put(start, 0);
        // 深度优先遍历，队列
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        Set<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String str : nexts.get(cur)) {
                if (!set.contains(str)) {
                    dist.put(str, dist.get(cur) + 1);
                    queue.add(str);
                    set.add(str);
                }
            }
        }
        return dist;
    }
}
