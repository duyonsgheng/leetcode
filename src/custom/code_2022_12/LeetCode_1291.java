package custom.code_2022_12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName LeetCode_1291
 * @Author Duys
 * @Description
 * @Date 2022/12/2 15:28
 **/
// 1291. 顺次数
public class LeetCode_1291 {

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            int cur = i;
            for (int j = cur + 1; j <= 9; j++) {
                cur = cur * 10 + j;
                if (cur >= low && cur <= high) {
                    ans.add(cur);
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
