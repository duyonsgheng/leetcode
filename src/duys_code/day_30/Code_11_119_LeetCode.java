package duys_code.day_30;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_11_119_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/pascals-triangle-ii/
 * @Date 2021/11/29 15:29
 **/
public class Code_11_119_LeetCode {
    // rowIndex 返回杨辉三角的第rowIndex行
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                // 依然是下一行是上一行的数据推出来的
                ans.set(j, ans.get(j - 1) + ans.get(j));
            }
            // 最后一个数字是1
            ans.add(1);
        }
        return ans;
    }
}
