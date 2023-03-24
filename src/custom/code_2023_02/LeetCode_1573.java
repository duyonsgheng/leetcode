package custom.code_2023_02;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1573
 * @Author Duys
 * @Description
 * @Date 2023/2/17 13:02
 **/
// 1573. 分割字符串的方案数
public class LeetCode_1573 {

    public int numWays(String s) {
        int mod = 1_000_000_007;
        int n = s.length();
        long ans = 0;
        List<Integer> ones = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }
        int all = ones.size();
        if (all % 3 != 0) {
            return 0;
        }
        // 0个1，则分为3部分，就是 n-1个位置选出两个
        if (all == 0) {
            ans = (n - 1) * (n - 2) / 2;
        } else {
            int i1 = all / 3;// 第二部分开头一个1的位置
            int i2 = all / 3 * 2; // 第三部分开头一个1的位置
            // 第一部分最右一个1到第二部分开头之间有多少位置
            int cnt1 = ones.get(i1) - ones.get(i1 - 1);
            // 第二部分结尾的1到第三部分的开头的1的位置数
            int cnt2 = ones.get(i2) - ones.get(i2 - 1);
            ans = (long) cnt1 * cnt2;
        }
        return (int) (ans % mod);
    }
}
