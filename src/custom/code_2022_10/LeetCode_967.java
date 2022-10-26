package custom.code_2022_10;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_967
 * @Author Duys
 * @Description
 * @Date 2022/10/25 10:56
 **/
// 967. 连续差相同的数字
public class LeetCode_967 {

    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            process(n, k, i, 1, i, list);
        }
        int[] ans = new int[list.size()];
        int index = 0;
        for (int i : list) {
            ans[index++] = i;
        }
        return ans;
    }

    // 当前结尾的数是cur
    // 来到第index位
    // 前面形成的整个数字 preSum
    public void process(int n, int k, int cur, int index, int preSum, List<Integer> ans) {
        if (index == n) {
            ans.add(preSum);
            return;
        }
        // cur+k结尾
        if (cur + k < 10) {
            process(n, k, cur + k, index + 1, preSum * 10 + cur + k, ans);
        }
        //  cur-k结尾
        if (k != 0 && cur - k > -1) {
            process(n, k, cur - k, index + 1, preSum * 10 + cur - k, ans);
        }
    }
}
