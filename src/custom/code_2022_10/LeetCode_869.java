package custom.code_2022_10;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_869
 * @Author Duys
 * @Description
 * @Date 2022/10/10 10:20
 **/
// 869. 重新排序得到 2 的幂
public class LeetCode_869 {

    // 2的幂
    // 1     0
    // 10    1
    // 100   2
    // 1000  3
    public static boolean reorderedPowerOf2(int n) {
        Set<String> set = new HashSet<>();
        // 把1到1 000 000 000 2的幂的数全部记录，各个数字中每一个数字出现的次数全部记录
        for (int i = 1; i <= 1_000_000_000; i <<= 1) {
            set.add(str(i));
        }
        return set.contains(str(n));
    }

    public static String str(int n) {
        char[] cnt = new char[10];
        while (n > 0) {
            // 每一个数字出现了多少次
            cnt[n % 10]++;
            n /= 10;
        }
        return new String(cnt);
    }


}
