package week.week_2022_04_02;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_07_PerfectPairNumber
 * @Author Duys
 * @Description
 * @Date 2022/4/19 14:54
 **/
// 来自阿里
// x = { a, b, c, d }
// y = { e, f, g, h }
// x、y两个小数组长度都是4
// 如果有: a + e = b + f = c + g = d + h
// 那么说x和y是一个完美对
// 题目给定N个小数组，每个小数组长度都是K
// 返回这N个小数组中，有多少完美对
// 本题测试链接 : https://www.nowcoder.com/practice/f5a3b5ab02ed4202a8b54dfb76ad035e
// 提交如下代码，把主类名改成Main
// 可以直接通过
public class Code_07_PerfectPairNumber {

    // 例如：
    // [2,5,7,11]
    //  -3 -2 -4
    // [10,7,5,1]
    //   3  2 4
    // 规律就是两个数组从钱往后的差值是另外一个数组从后往前的差值
    public static long perfectPairs(int[][] matrix) {
        long ans = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int[] arr : matrix) {
            StringBuilder self = new StringBuilder();
            StringBuilder minus = new StringBuilder();
            for (int i = 1; i < arr.length; i++) {
                self.append("_" + (arr[i] - arr[i - 1]));
                minus.append("_" + (arr[i - 1] - arr[i]));
            }
            ans += map.getOrDefault(minus.toString(), 0);
            map.put(self.toString(), map.getOrDefault(self.toString(), 0) + 1);
        }
        return ans;
    }
}
