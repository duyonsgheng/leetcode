package custom.code_2023_01;

/**
 * @ClassName LeetCode_1802
 * @Author Duys
 * @Description
 * @Date 2023/1/4 9:35
 **/
// 1802. 有界数组中指定下标处的最大值
public class LeetCode_1802 {

    // 二分
    public int maxValue(int n, int index, int maxSum) {
        int left = 1;
        int right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (valid(mid, n, index, maxSum)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // 从index开始往两边下降，并且下标每相差1，值就减少1，直到达到数组边界或者为1后，保持1不变。
    public boolean valid(int mid, int n, int index, int maxsum) {
        int left = index;
        int right = n - index - 1;
        return mid + cal(mid, left) + cal(mid, right) <= maxsum;
    }

    public long cal(int big, int len) {
        if (len + 1 < big) {
            int small = big - len;
            return (long) (big - 1 + small) * len / 2;
        } else {
            int ones = len - big + 1;
            return (long) big * (big - 1) / 2 + ones;
        }
    }
}
