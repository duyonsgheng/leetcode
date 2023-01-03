package custom.code_2022_12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_1387
 * @Author Duys
 * @Description
 * @Date 2022/12/26 16:09
 **/
// 1387. 将整数按权重排序
public class LeetCode_1387 {

    Map<Integer, Integer> map = new HashMap<>();

    public int getKth(int lo, int hi, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = lo; i <= hi; i++)
            list.add(i);
        Collections.sort(list, (a, b) -> process(a) == process(b) ? a - b : process(a) - process(b));
        return list.get(k - 1);
    }

    public int process(int x) {
        if (!map.containsKey(x)) {
            if (x == 1) {
                map.put(x, 0);
            } else if (x % 2 == 0) {
                map.put(x, process(x / 2) + 1);
            } else {
                map.put(x, process(x * 3 + 1) + 1);
            }
        }
        return map.get(x);
    }
}
