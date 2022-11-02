package custom.code_2022_11;

/**
 * @ClassName LeetCode_1011
 * @Author Duys
 * @Description
 * @Date 2022/11/2 16:16
 **/
// 1011. 在 D 天内送达包裹的能力
public class LeetCode_1011 {
    // 二分
    // 确定左右边界
    public int shipWithinDays(int[] weights, int days) {
        int l = 0;
        int r = 0;
        for (int i : weights) {
            l = Math.max(l, i);
            r += i;
        }
        while (l < r) {
            int mid = l + (r - l) / 2;
            int need = 1;
            int cur = 0;
            for (int w : weights) {
                if (cur + w > mid) {
                    need++;
                    cur = 0;
                }
                cur += w;
            }
            // 题意：返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
            // 如果能满足，那么我们看看，更低能到哪里去
            if (need <= days) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
