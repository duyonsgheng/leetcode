package custom.code_2022_09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_816
 * @Author Duys
 * @Description
 * @Date 2022/9/23 11:12
 **/
//
public class LeetCode_816 {

    public static List<String> ambiguousCoordinates(String s) {
        s = s.substring(1, s.length() - 1);
        List<String> ans = new ArrayList<>();
        int n = s.length();
        // 把数分成两部分
        for (int i = 1; i < n; i++) {
            // 左边部分，加小数点
            for (String left : split(s, 0, i)) {
                // 右边部分加小数点
                for (String right : split(s, i, n)) {
                    ans.add("(" + left + ", " + right + ")");
                }
            }
        }
        return ans;
    }

    // 左边部分的点放在哪里
    public static List<String> split(String str, int i, int j) {
        List<String> ans = new ArrayList();
        for (int d = 1; d <= j - i; d++) {
            String left = str.substring(i, i + d);
            String right = str.substring(i + d, j);
            if ((!left.startsWith("0") || left.equals("0")) && !right.endsWith("0"))
                ans.add(left + (d < j - i ? "." : "") + right);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<String> split = ambiguousCoordinates("(00011)");
    }
}
