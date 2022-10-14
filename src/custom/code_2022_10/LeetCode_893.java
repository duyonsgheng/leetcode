package custom.code_2022_10;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_893
 * @Author Duys
 * @Description
 * @Date 2022/10/12 10:02
 **/
// 893. 特殊等价字符串组
public class LeetCode_893 {
    // 思路
    // 之前已经遇到过 二进制位置交换了，我们把一个数的每一位数字统计一下，看看是否出现的频率一样
    // 这道题也差不多，只是再之前二进制位置交换的基础上多了一个限制，就是奇数位置和奇数位置交换，偶数位置和偶数位置交换
    // 对于一个只有小写字母的字符串来说，我们扩大,分别统计奇数和偶数位置的字符个数
    public int numSpecialEquivGroups(String[] words) {
        Set<String> set = new HashSet<>();
        int[] count = new int[52];
        for (String word : words) {
            Arrays.fill(count, 0);
            for (int i = 0; i < word.length(); i++) {
                count[word.charAt(i) - 'a' + 26 * (i % 2)]++;
            }
            set.add(Arrays.toString(count));
        }
        return set.size();
    }
}
