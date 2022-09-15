package week.week_2022_05_01;

/**
 * @ClassName Code_03_FindDuplicateOnlyOne
 * @Author Duys
 * @Description
 * @Date 2022/5/7 16:09
 **/
// 来自学员问题，真实大厂面试题
// 1、2、3...n-1、n、n、n+1、n+2...
// 在这个序列中，只有一个数字有重复(n)
// 这个序列是无序的，找到重复数字n
// 这个序列是有序的，找到重复数字n
public class Code_03_FindDuplicateOnlyOne {

    // 1.如果整个数组有序，且是1,2,3,4...n,n+1,n+2..
    // 方法1：二分，按照题意，数据是0位置是1，那么i位置是i+1
    // 方法2：使用数组累加和 - 1+2+3+...+2n 剩下的就是重复的（注意越界）
    public static int findDuplicateSorted(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            // 重复了
            if (m - 1 >= 0 && arr[m - 1] == arr[m] || (m + 1 < arr.length && arr[m] == arr[m + 1])) {
                ans = arr[m];
                break;
            }
            if (m - l == arr[m] - arr[l]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    // 2.如果不是有序的，还是根据题意i位置上是i+1，下标循环怼，在怼的过程总，如果有重复了一定会遇到环。
    public static int findDuplicateNoSorted(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        // 很形象的快慢指针\
        // 快指针一次走两步
        // 慢指针一次走一步
        int slow = arr[0];
        int fast = arr[arr[0]];
        while (fast != slow) {
            slow = arr[slow];
            fast = arr[arr[fast]];
        }
        // 出现了第一次相遇了，快指针回到远点
        fast = 0;
        while (fast != slow) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return fast;
    }
}
