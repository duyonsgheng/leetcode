package custom.code_2022_09;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1619
 * @Author Duys
 * @Description
 * @Date 2022/9/14 9:31
 **/
// 1619. 删除某些元素后的数组均值
public class LeetCode_1619 {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        // 1/20
        int n = arr.length;
        int total = 0;
        for (int i = n / 20; i < 19 * n / 20; i++) {
            total += arr[i];
        }
        return total / (n * 0.9);
    }
}
