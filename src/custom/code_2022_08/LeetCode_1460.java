package custom.code_2022_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1460
 * @Author Duys
 * @Description
 * @Date 2022/8/24 19:57
 **/
// 1460. 通过翻转子数组使两个数组相等
public class LeetCode_1460 {

    // 只需要判断两个数组元素是否一样
    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int i : target) {
            count.put(i, count.getOrDefault(i, 0) + 1);
        }
        for (int i : arr) {
            if (!count.containsKey(i)) {
                return false;
            }
            count.put(i, count.get(i) - 1);
            if (count.get(i) == 0) {
                count.remove(i);
            }
        }
        return count.isEmpty();
    }
}
