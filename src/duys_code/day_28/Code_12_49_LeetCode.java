package duys_code.day_28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_12_49_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/group-anagrams/
 * @Date 2021/11/23 16:37
 **/
public class Code_12_49_LeetCode {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> maps = new HashMap<>();
        for (String str : strs) {
            int[] record = new int[26];
            for (char c : str.toCharArray()) {
                record[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int v : record) {
                sb.append(v).append("_");
            }
            // 转成一样的key
            String key = sb.toString();
            if (!maps.containsKey(key)) {
                maps.put(key, new ArrayList<>());
            }
            maps.get(key).add(str);
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> ls : maps.values()) {
            ans.add(ls);
        }
        return ans;
    }
}
