package custom.code_2022_12;

/**
 * @ClassName LeetCode_1343
 * @Author Duys
 * @Description
 * @Date 2022/12/13 10:19
 **/
// 1343. 大小为 K 且平均值大于等于阈值的子数组数目
public class LeetCode_1343 {
    public static void main(String[] args) {
        // [11,13,17,23,29,31,7,5,2,3]
        //3
        //5
        int arr[] = {11, 13, 17, 23, 29, 31, 7, 5, 2, 3}, k = 3, threshold = 5;
        System.out.println(numOfSubarrays(arr, k, threshold));
    }

    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum = 0;
        int n = arr.length;
        if (n < k) {
            return -1;
        }
        int ans = 0;
        for (int r = 0, l = -1; r < n && l < n; r++) {
            sum += arr[r];
            if (r - l == k) {
                if ((sum / k) >= threshold) {
                    ans++;
                }
                sum -= arr[++l];
            }
        }
        return ans;
    }
}
