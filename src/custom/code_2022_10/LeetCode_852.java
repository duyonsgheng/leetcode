package custom.code_2022_10;

/**
 * @ClassName LeetCode_852
 * @Author Duys
 * @Description
 * @Date 2022/10/8 16:26
 **/
// 852. 山脉数组的峰顶索引
public class LeetCode_852 {

    // 题目要求 log n 的解法
    // 只能二分
    // 山峰的顶点，满足 左边是小于，右边也是小于的
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int left = 1;
        int right = n - 2;
        int ans = 0;
        while (left <= right) {
            int m = (left + right) >> 1;
            // 继续往左边找
            if (arr[m] > arr[m + 1]) {
                ans = m;
                right = m - 1;
            }
            // 否则往右边找
            else {
                left = m + 1;
            }
        }
        return ans;
    }

}
