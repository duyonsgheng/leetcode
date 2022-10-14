package custom.code_2022_10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_890
 * @Author Duys
 * @Description
 * @Date 2022/10/12 9:39
 **/
// 890. 查找和替换模式
// hash表的应用
public class LeetCode_890 {

    // words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (word.length() != pattern.length()) {
                continue;
            }
            if (ok(word, pattern) && ok(pattern, word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    public boolean ok(String word, String pattern) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            int a = word.charAt(i) - 'a';
            int b = pattern.charAt(i) - 'a';
            if (!map.containsKey(a)) {
                map.put(a, b);
            } else if (map.get(a) != b) {
                return false;
            }
        }
        return true;
    }
}
