package custom.code_2022_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_1237
 * @Author Duys
 * @Description
 * @Date 2022/11/28 16:56
 **/
// 1237. 找出给定方程的正整数解
public class LeetCode_1237 {

    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            int l = 1, r = 1000;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                int cur = customfunction.f(i, m);
                if (cur > z) {
                    r = m - 1;
                } else if (cur < z) {
                    l = m + 1;
                } else {
                    ans.add(Arrays.asList(i, m));
                    break;
                }
            }
        }
        return ans;
    }

    interface CustomFunction {
        int f(int x, int y);
    }
}
