package week.week_2023_01_01;

/**
 * @ClassName LeetCode_1539
 * @Author Duys
 * @Description
 * @Date 2023/1/5 9:19
 **/
// 1539. 第 k 个缺失的正整数
public class Code_01_LeetCode_1539 {
    // 一听到有序，首先想到二分
    // 1.二分
    // 2.因为是严格得升序，所以可以使用二分
    public int findKthPositive(int[] arr, int k) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int find = arr.length;
        while (l <= r) {
            m = (l + r) / 2;
            // 比如当前arr[m] = 19 , m之前有10个数，那么就差了9个数字
            // 如果 差了的数字 大于等于了 k，说明缺的数字再 l到m这个范围内
            if (arr[m] - (m + 1) >= k) {
                r = m - 1;
                find = m;
            } else {
                l = m + 1;
            }
        }
        // find位置表示最后的区间，如果要找的是5，当前find =7，前面缺了3个，6位置就已经缺了8个了，那么说说明 5位置往后延几个就是要找的数
        int pre = find == 0 ? 0 : arr[find - 1];
        int diff = pre - find;
        return pre + (k - diff);
    }
}
