package custom.code_2022_08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_1282
 * @Author Duys
 * @Description
 * @Date 2022/8/12 9:12
 **/
// 1282. 用户分组
public class LeetCode_1282 {

    // 2,1,3,3,3,2
    //
    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
        // 组的大小，组的成员
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int size : groupSizes) {
            if (!map.containsKey(size)) {
                map.put(size, new ArrayList<>());
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int key = groupSizes[i];
            List<Integer> list = map.get(groupSizes[i]);
            if (list.size() >= key) {
                ans.add(list);
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(i);
        }
        for (List<Integer> v : map.values()) {
            ans.add(v);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 3, 3, 1, 3};
        groupThePeople(arr);
    }
}
