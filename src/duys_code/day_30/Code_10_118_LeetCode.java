package duys_code.day_30;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_10_118_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/pascals-triangle/
 * @Date 2021/11/29 13:59
 **/
public class Code_10_118_LeetCode {
    // 杨辉三角
    // 给定一个num 返回杨辉三角的前num行
    public List<List<Integer>> generate(int numRows) {
        // 1. 1
        // 2. 1 1
        // 3. 1 2 1
        // 4. 1 3 3 1
        // 每一行的值都是我的左上角和上边的数相加得到，每一行的第一个数和最后一个数都是1
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ans.add(new ArrayList<>());
            // 每一行的第一个都是1
            ans.get(i).add(1);
        }
        for (int i = 1; i < numRows; i++) {
            // 每一行的值
            for (int j = 1; j < i; j++) {
                ans.get(i).add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
            }
            // 最后一个数是1
            ans.get(i).add(1);
        }
        return ans;
    }
}
