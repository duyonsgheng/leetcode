package duys_code.day_36;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_02_Ratio01Split
 * @Author Duys
 * @Description
 * @Date 2021/12/13 13:23
 **/
public class Code_02_Ratio01Split {

    // 来自京东
    // 把一个01字符串切成多个部分，要求每一部分的0和1比例一样，同时要求尽可能多的划分
    // 比如 : 01010101
    // 01 01 01 01 这是一种切法，0和1比例为 1 : 1
    // 0101 0101 也是一种切法，0和1比例为 1 : 1
    // 两种切法都符合要求，但是那么尽可能多的划分为第一种切法，部分数为4
    // 比如 : 00001111
    // 只有一种切法就是00001111整体作为一块，那么尽可能多的划分，部分数为1
    // 给定一个01字符串str，假设长度为N，要求返回一个长度为N的数组ans
    // 其中ans[i] = str[0...i]这个前缀串，要求每一部分的0和1比例一样，同时要求尽可能多的划分下，部分数是多少
    // 输入: str = "010100001"
    // 输出: ans = [1, 1, 1, 2, 1, 2, 1, 1, 3]

    // 啥意思？
    // 就是切分的每一块要求 0 和 1的比例相同
    public static int[] split(int[] arr) {

        // key - 分子
        // value中的 key是分母 ，value是当前这个比例出现了次数
        Map<Integer, Map<Integer, Integer>> preMap = new HashMap<>();
        int n = arr.length;
        int[] ans = new int[n];
        int zeroCount = 0; // 0一共出现的次数
        int oneCount = 0;// 1 一共出现的次数
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
            // 意思是全部是0或者全部是1，那么一定可以切成i+1份
            // 比如当前移动3个1了，可以切成3份，i是从0开始的，所以+1
            if (zeroCount == 0 || oneCount == 0) {
                ans[i] = i + 1;
            } else {
                int gcd = gcd(zeroCount, oneCount);
                int zero = zeroCount / gcd;
                int one = oneCount / gcd;
                if (!preMap.containsKey(zero)) {
                    preMap.put(zero, new HashMap<>());
                }
                if (!preMap.get(zero).containsKey(one)) {
                    preMap.get(zero).put(one, 1);
                } else {
                    preMap.get(zero).put(one, preMap.get(zero).get(one) + 1);
                }
                ans[i] = preMap.get(zero).get(one);
            }
        }
        return ans;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
