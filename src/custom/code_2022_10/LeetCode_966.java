package custom.code_2022_10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_966
 * @Author Duys
 * @Description
 * @Date 2022/10/25 10:15
 **/
//966. 元音拼写检查器
public class LeetCode_966 {

    // 哈希
    public static String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> words = new HashSet<>();
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        for (String word : wordlist) {
            words.add(word);
            map1.putIfAbsent(word.toLowerCase(), word);
            String newWord = word.toLowerCase().replace('a', '0').replace('e', '0')
                    .replace('i', '0').replace('o', '0').replace('u', '0');
            map2.putIfAbsent(newWord, word);
        }
        String[] ans = new String[queries.length];
        int index = 0;
        for (String query : queries) {
            String cur = "";
            if (words.contains(query)) {
                cur = query;
            } else if (map1.containsKey(query.toLowerCase())) {
                cur = map1.get(query.toLowerCase());
            } else {
                String newQuery = query.toLowerCase().replace('a', '0').replace('e', '0')
                        .replace('i', '0').replace('o', '0').replace('u', '0');
                if (map2.containsKey(newQuery)) {
                    cur = map2.get(newQuery);
                }
            }
            ans[index++] = cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] l1 = {"KiTe", "kite", "hare", "Hare"};
        String[] l2 = {"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"};
        spellchecker(l1, l2);
    }
}
