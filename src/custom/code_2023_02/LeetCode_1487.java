package custom.code_2023_02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_1487
 * @Author Duys
 * @Description
 * @Date 2023/2/2 14:49
 **/
// 1487. 保证文件名唯一
public class LeetCode_1487 {

    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        // "kaido","kaido(1)","kaido","kaido(1)"
        // kaido [0,1]
        // kaido(1) [0,1]
        Set<String> set = new HashSet<>();
        String[] ans = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            if (!set.contains(name)) {
                map.put(name, 1);
            } else {
                // 取出之前的编号
                int id = map.getOrDefault(name, 1);
                while (set.contains(name + "(" + id + ")")) {
                    id++;
                }
                map.put(name, id + 1);
                name += "(" + id + ")";
            }
            ans[i] = name;
            set.add(name);
        }
        return ans;
    }
}
