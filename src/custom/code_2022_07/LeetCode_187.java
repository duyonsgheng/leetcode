package custom.code_2022_07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_187
 * @Author Duys
 * @Description
 * @Date 2022/7/6 13:24
 **/
// 187. 重复的DNA序列
// 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//输出：["AAAAACCCCC","CCCCCAAAAA"]
// 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
public class LeetCode_187 {

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return ans;
        }
        // 把长度为10的字串收集
        Map<String, Integer> count = new HashMap<>();
        char[] chars = s.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.append(chars[i]);
        }
        count.put(buffer.toString(), 1);
        for (int i = 10; i < chars.length; i++) {
            buffer.append(chars[i]);
            buffer.deleteCharAt(0);
            count.put(buffer.toString(), count.getOrDefault(buffer.toString(), 0) + 1);
        }
        count.entrySet().stream().forEach(map -> {
            if (map.getValue().intValue() > 1) {
                ans.add(map.getKey());
            }
        });
        return ans;
    }

    public static void main(String[] args) {
        String str = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> repeatedDnaSequences = findRepeatedDnaSequences(str);
        for (String strs : repeatedDnaSequences)
            System.out.println(strs);
    }
}
