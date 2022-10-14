package custom.code_2022_10;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_898
 * @Author Duys
 * @Description
 * @Date 2022/10/12 12:58
 **/
// 898. 子数组按位或操作
public class LeetCode_898 {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        cur.add(0);
        for (int x : arr) {
            Set<Integer> next = new HashSet<>();
            for (int y : cur) {
                next.add(x | y);
            }
            next.add(x);
            cur = next;
            ans.addAll(cur);
        }
        return ans.size();
    }
}
