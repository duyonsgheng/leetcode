package custom.code_2022_09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_756
 * @Author Duys
 * @Description
 * @Date 2022/9/19 9:52
 **/
//
public class LeetCode_756 {
    Map<String, List<Character>> preIndex;

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // 统计
        preIndex = new HashMap<>();
        for (String str : allowed) {
            // 拿到前缀
            String key = str.substring(0, 2);
            char val = str.charAt(2);
            if (!preIndex.containsKey(key)) {
                preIndex.put(key, new ArrayList<>());
            }
            preIndex.get(key).add(val);
        }
        return process1(bottom);
    }

    public boolean process1(String bottom) {
        int len = bottom.length();
        if (len == 2) {
            return preIndex.containsKey(bottom);
        }
        Map<Integer, List<Character>> source = new HashMap<>();
        for (int i = 0; i < len - 1; i++) {
            String key = bottom.substring(i, i + 2);
            if (!preIndex.containsKey(key)) {
                return false;
            }
            source.put(i, preIndex.get(key));
        }
        if (process(0, source, len - 1, new char[len - 1])) {
            return true;
        }
        return false;
    }

    public boolean process(int index, Map<Integer, List<Character>> source, int size, char[] cs) {
        if (index == size) {
            return process1(new String(cs));
        }
        List<Character> cur = source.get(index);
        for (int i = 0; i < cur.size(); i++) {
            char c = cur.get(i);
            cs[index] = c;
            if (process(index + 1, source, size, cs)) {
                return true;
            }
        }
        return false;
    }
}
