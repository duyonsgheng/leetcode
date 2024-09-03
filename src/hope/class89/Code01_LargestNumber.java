package hope.class89;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName Code01_LargestNumber
 * @date 2024年08月20日 下午 04:44
 */
// 最大数
// 给定一组非负整数nums
// 重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数
// 测试链接 : https://leetcode.cn/problems/largest-number/
public class Code01_LargestNumber {

    // 暴力展开
    public static String way1(String[] strs) {
        List<String> list = new ArrayList<>();
        f(strs, 0, list);
        list.sort((a, b) -> a.compareTo(b));
        return list.get(0);
    }

    // 全排列
    public static void f(String[] strs, int i, List<String> list) {
        if (i == strs.length) {
            StringBuffer bf = new StringBuffer();
            for (String s : strs) {
                bf.append(s);
            }
            list.add(bf.toString());
        } else {
            for (int j = i; j < strs.length; j++) {
                swap(strs, i, j);
                f(strs, i + 1, list);
                swap(strs, i, j);
            }
        }
    }

    public static void swap(String[] strs, int i, int j) {
        String tmp = strs[i];
        strs[i] = strs[j];
        strs[j] = tmp;
    }

    // 正确的排序贪心策略
    public static String way2(String[] strs) {
        Arrays.sort(strs, (a, b) -> (a + b).compareTo(b + a));
        StringBuffer bf = new StringBuffer();
        for (String s : strs) {
            bf.append(s);
        }
        return bf.toString();
    }

    // 测试链接 : https://leetcode.cn/problems/largest-number/
    public static String largestNumber(int[] nums) {
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        if (strs[0].equals("0")) {
            return "0";
        }
        StringBuffer bf = new StringBuffer();
        for (String s : strs) {
            bf.append(s);
        }
        return bf.toString();
    }
}
