package duys_code.day_49;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_04_527_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/word-abbreviation
 * @Date 2021/10/27 15:59
 **/
public class Code_04_527_LeetCode {
    /**
     * 单词缩写，开头的字符和结尾的字符不能缩写。其他位置都可以
     * 但是如果两个单词前缀一样，那么就只能增加前缀来区分
     * 当缩写后的单词长度和缩写前 的单词长度一样，直接返回原单词就可以了
     */
    public static List<String> wordsAbbreviation(List<String> words) {
        int len = words.size();
        List<String> res = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            // 先缩写一个，然后把缩写相同得单词得位置都记录下来
            res.add(makeAbbr(words.get(i), 1));
            List<Integer> list = map.getOrDefault(words.get(i), new ArrayList<>());
            list.add(i);
            map.put(res.get(i), list);
        }
        int[] prefix = new int[len];
        for (int i = 0; i < len; i++) {
            // 至少得有一个。只有一个得那就是最佳缩写，不用处理了
            if (map.get(res.get(i)).size() <= 1) {
                continue;
            }
            List<Integer> curIndexs = map.get(res.get(i));
            map.remove(res.get(i));
            // 这就是前缀一次增加，看看能不能区分出更多了缩写
            for (int index : curIndexs) {
                prefix[index]++;
                // 如果当前区分度依然不够
                res.set(index, makeAbbr(words.get(index), prefix[index]));
                List<Integer> curList = map.getOrDefault(res.get(index), new ArrayList<>());
                curList.add(index);
                // 继续添加到map去，下一次继续处理
                map.put(res.get(index), curList);
            }
        }
        return res;
    }

    // 这就是缩写
    public static String makeAbbr(String s, int k) {
        // 如果缩写了后的长度与之前的一样，不用缩写
        if (k > s.length() - 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, k));
        sb.append(s.length() - 1 - k);
        sb.append(s.charAt(s.length() - 1));
        return sb.toString();
    }
}
