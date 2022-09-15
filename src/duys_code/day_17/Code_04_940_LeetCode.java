package duys_code.day_17;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName Code_04_940_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/distinct-subsequences-ii/
 * @Date 2021/10/28 16:27
 **/
public class Code_04_940_LeetCode {
    /**
     * 给定一个字符串Str
     * 返回Str的所有子序列中有多少不同的字面值
     */

    public static int distinctSubseqII1(String s) {
        HashSet<String> set = new HashSet();
        //set.add("");
        char[] ch = s.toCharArray();
        process1(ch, 1, "" + ch[0], set);
        return set.size();
    }

    public static void process1(char[] chars, int i, String path, HashSet<String> set) {
        if (i == chars.length) {
            set.add(path);
            return;
        }
        process1(chars, i + 1, path + chars[i], set);
        process1(chars, i + 1, path, set);
        process1(chars, i + 1, "" + chars[i], set);

    }

    /**
     * 我们把前一个的迁移下来，然后前一个元素每一个元素都加上当前元素
     * 比如 abbcdd
     * a : {}{a}
     * b : 旧的：{}{a} 新的：{b}{ab}
     * b ：旧的：{}{a}{b}{ab} 新的：{b}{ab}{bb}{abb} 有重复的出现了，要减去之前已经出现的相同字符，再之前位置新增的个数
     */
    public static int distinctSubseqII2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int m = 1000000007;
        char[] str = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int all = 1;// 这里我们算一个空集，
        for (int i = 0; i < str.length; i++) {
            int newAdd = all; // 新的，在之前的基础上都加一个当前位置的元素，个数还是没变
            int curAll = all; // 算总共的，需要把之前的记录
            // 本次总共的
            curAll = (newAdd + curAll) % m;
            // 要去掉之前如果有相同元素出现的时候，新增的
            if (!map.containsKey(str[i])) {
                map.put(str[i], 0);
            }
            // 因为curAll已经 %过m的，可能 map.get(str[i]) 是一个比较大的数，所以需要+m然后整体在%m
            curAll = (curAll - map.get(str[i]) + m) % m;
            // 本次的赋值，进行下了一轮计算
            all = curAll;
            map.put(str[i], newAdd);
        }
        // 排除空集
        return all - 1;
    }

    public static void main(String[] args) {
        String s = "bccaccbaabbcghfgghg";
    }
}
