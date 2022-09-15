package duys_code.day_31;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName Code_02_127_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/word-ladder/
 * @Date 2021/11/29 16:23
 **/
public class Code_02_127_LeetCode {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        // 生成所有的邻居
        Map<String, List<String>> nexts = getNexts(wordList);
        // 生成距离,所有的距离只对beginWord负责
        Map<String, Integer> dictMap = new HashMap<>();
        // 自己到自己算1
        dictMap.put(beginWord, 1);
        // 记录这条路是不是已经走过了
        Set<String> set = new HashSet<>();
        set.add(beginWord);
        // 准备一个栈，进行宽度优先遍历
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            Integer distance = dictMap.get(cur);
            List<String> strings = nexts.get(cur);
            for (String str : strings) {
                // 已经遇到了，那么直接就返回了
                if (str.equals(endWord)) {
                    return distance + 1;
                }
                if (!set.contains(str)) {
                    set.add(str);
                    queue.add(str);
                    // 当前单词到beginWord的距离增加1
                    dictMap.put(str, distance + 1);
                }
            }
        }
        return 0;
    }

    // 获取所有的邻居
    public static Map<String, List<String>> getNexts(List<String> wordList) {
        Map<String, List<String>> nexts = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            nexts.put(wordList.get(i), getNext(wordList.get(i), new HashSet<>(wordList)));
        }
        return nexts;
    }

    public static List<String> getNext(String word, Set<String> set) {
        // 每一次变一个字符
        List<String> ans = new ArrayList<>();
        char[] str = word.toCharArray();
        for (int i = 0; i < str.length; i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                char tmp = str[i];
                str[i] = j;
                // 需要在字典里，不然不生效
                if (set.contains(String.valueOf(str))) {
                    ans.add(String.valueOf(str));
                }
                str[i] = tmp;
            }
        }
        return ans;
    }
}
