package custom.code_2022_08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_609
 * @Author Duys
 * @Description
 * @Date 2022/8/29 17:18
 **/
//609. 在系统中查找重复文件
public class LeetCode_609 {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            // 取出每一个
            String[] vs = path.split(" ");
            // 从第1个开始
            for (int i = 1; i < vs.length; i++) {
                String[] name = vs[i].split("\\(");
                // 把每一个文件给搞出来
                name[1] = name[1].replace(")", "");
                List<String> list = map.getOrDefault(name[1], new ArrayList<>());
                list.add(vs[0] + "/" + name[0]);
                map.put(name[1], list);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                ans.add(map.get(key));
            }
        }
        return ans;
    }
}
