package week.week_2022_01_04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_03_LeetCode_710
 * @Author Duys
 * @Description
 * @Date 2022/3/30 16:04
 **/
// 给定一个包含 [0，n) 中不重复整数的黑名单 blacklist
// 写一个函数从 [0, n) 中返回一个不在 blacklist 中的随机整数
// 对它进行优化使其尽量少调用系统方法 Math.random()
// 1 <= n <= 1000000000
// 0 <= blacklist.length < min(100000, N)
// 测试链接: https://leetcode.com/problems/random-pick-with-blacklist/
public class Code_03_LeetCode_710 {


    class Solution {
        private int size;
        private Map<Integer, Integer> convert = new HashMap<>();

        public Solution(int n, int[] blacklist) {
            Arrays.sort(blacklist);
            int m = blacklist.length;

            // 我们把黑名单里的数字，在0~n-1上替换掉
            for (int i = 0; i < m && blacklist[i] < n; i++) {
                for (n--; n > blacklist[i]; n--) {
                    if (n == blacklist[m - 1]) {
                        m--;
                    } else {
                        // 把对应的替换的值给记录下来
                        convert.put(blacklist[i], n);
                        break;
                    }
                }
            }
            size = n;
        }

        public int pick() {
            int num = (int) (Math.random() * size);
            return convert.containsKey(num) ? convert.get(num) : num;
        }
    }
}
