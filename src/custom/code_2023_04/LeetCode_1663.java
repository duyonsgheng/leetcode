package custom.code_2023_04;

/**
 * @ClassName LeetCode_1663
 * @Author Duys
 * @Description
 * @Date 2023/4/25 14:07
 **/
public class LeetCode_1663 {
    public String getSmallestString(int n, int k) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            // 尽可能的满足当前位置最小，那么侧面考虑，后面的都足够大
            int cur = Math.max(1, k - (n - i) * 26);
            k -= cur;
            buffer.append((char) ('a' + cur - 1));
        }
        return buffer.toString();
    }
}
