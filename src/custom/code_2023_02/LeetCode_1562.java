package custom.code_2023_02;

import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName LeetCode_1562
 * @Author Duys
 * @Description
 * @Date 2023/2/14 10:28
 **/
// 1562. 查找大小为 M 的最新分组
public class LeetCode_1562 {
    public static int findLatestStep(int[] arr, int m) {
        if (m == arr.length) {
            return m;
        }
        // 3,5,1,2,4  m=1
        // 0 0 0 0 0
        // 0,6
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, arr.length + 1); // 连续1的空间，逆推的话，那么开始的时候 整片区间都是连续的
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i]; // 哪一个位置现在需要变成1
            // 小于等于num 位置，之前产生的最大连续空间在哪里
            Map.Entry<Integer, Integer> entry = map.floorEntry(num);
            int l = entry.getKey();
            int r = entry.getValue();
            if (num - l - 1 == m) {
                return i;
            }
            if (r - num - 1 == m) {
                return i;
            }
            map.put(l, num);
            map.put(num, r);
        }
        return -1;
    }

    public static void main(String[] args) {
        findLatestStep(new int[]{3, 5, 1, 2, 4}, 1);
    }
}
