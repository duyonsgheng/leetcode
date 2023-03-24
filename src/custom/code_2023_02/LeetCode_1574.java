package custom.code_2023_02;

/**
 * @ClassName LeetCode_1574
 * @Author Duys
 * @Description
 * @Date 2023/2/17 14:37
 **/
// 1574. 删除最短的子数组使剩余数组有序
public class LeetCode_1574 {

    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int r = 1;
        while (r < n && arr[r] >= arr[r - 1]) {
            r++;
        }
        if (r == n) {
            return 0;
        }
        //   r   l
        int l = n - 2;
        while (l >= 0 && arr[l] <= arr[l + 1]) {
            l--;
        }
        // 要么删除前面，要么删除后面
        int ans = Math.min(n - r, l + 1);
        for (int i = l + 1, j = 0; i < n; i++) {
            // r是开始递减的位置
            // l是开始递增的位置
            // 如果j从0开始一直到r都是小于i的，那么说明满足了，需要删除的就是j到i的这段
            while (j < r && arr[j] <= arr[i]) {
                j++;
            }
            ans = Math.min(ans, i - j);
        }
        return ans;
    }
}
