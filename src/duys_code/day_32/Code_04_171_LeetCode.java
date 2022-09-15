package duys_code.day_32;

/**
 * @ClassName Code_04_171_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/excel-sheet-column-number/
 * @Date 2021/12/6 10:16
 **/
public class Code_04_171_LeetCode {

    public int titleToNumber(String columnTitle) {
        if (columnTitle == null || columnTitle.length() <= 0) {
            return 0;
        }
        int ans = 0;
        char[] chars = columnTitle.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            ans = ans * 26 + (chars[i] - 'A' + 1);
        }
        return ans;
    }
}
