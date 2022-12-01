package week.week_2022_11_05;

/**
 * @ClassName LeetCode_02_LeetCode_6
 * @Author Duys
 * @Description
 * @Date 2022/12/1 10:24
 **/
// https://leetcode.cn/problems/zigzag-conversion/
public class Code_02_LeetCode_6 {

    // 画出图，进行观察
    // 当row确定了。那么我们的第一行和最后一行都确定了
    public static String convert(String s, int row) {
        int n = s.length();
        if (row == 1 || row >= n) {
            return s;
        }
        int step = 2 * (row - 1);
        char[] arr = new char[n];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = i, nextColTop = step; j < n; j += step, nextColTop += step) {
                arr[index++] = s.charAt(j);
                // 如果是斜线，那么需要特殊处理
                if (i >= 1 && i <= row - 2 && nextColTop - i < n) {
                    arr[index++] = s.charAt(nextColTop - i);
                }
            }
        }
        return new String(arr);
    }
}
