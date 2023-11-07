package custom.code_2023_09;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2201
 * @date 2023年09月11日
 */
// 2201. 统计可以提取的工件
// https://leetcode.cn/problems/count-artifacts-that-can-be-extracted/
public class LeetCode_2201 {

    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] d : dig) {
            if (!map.containsKey(d[0])) {
                map.put(d[0], new HashMap<>());
            }
            map.get(d[0]).put(d[1], 1);
        }
        int count = 0;
        for (int i = 0; i < artifacts.length; i++) {
            count += check(artifacts, i, map);
        }
        return count;
    }

    // 513721199301180310
    public int check(int[][] arr, int row, Map<Integer, Map<Integer, Integer>> map) {
        for (int i = arr[row][0]; i <= arr[row][2]; i++) {
            for (int j = arr[row][1]; j <= arr[row][3]; j++) {
                if (!map.containsKey(i)) {
                    return 0;
                } else {
                    if (!map.get(i).containsKey(j)) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }
}
