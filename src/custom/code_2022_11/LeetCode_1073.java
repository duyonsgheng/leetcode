package custom.code_2022_11;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1073
 * @Author Duys
 * @Description
 * @Date 2022/11/9 10:02
 **/
// 1073. 负二进制数相加
public class LeetCode_1073 {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int n = Math.max(i, j) + 3;
        int[] ans = new int[n];
        int cur = 0;
        int k = n - 1;
        while (i >= 0 || j >= 0 || cur != 0) {
            if (i >= 0) {
                cur += arr1[i];
            }
            if (j >= 0) {
                cur += arr2[j];
            }
            ans[k--] = Math.abs(cur) % 2;
            if (cur < 0) {
                cur = 1;
            } else if (cur > 1) {
                cur = -1;
            } else cur = 0;
            i--;
            j--;
        }
        int index = 0;
        while (index < n - 1 && ans[index] == 0)
            index++;
        return Arrays.copyOfRange(ans, index, n);
    }
}
