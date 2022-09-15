package duys_code.day_23;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Code_02_Array
 * @Author Duys
 * @Description
 * @Date 2021/11/11 15:21
 **/
public class Code_02_Array {
    /**
     * 题意：
     * 定义什么是可整合数组：
     * 一个数组排完序之后，除了最左侧的数外，有arr[i] = arr[i-1]+1
     * 则称这个数组为可整合数组
     * 比如{5,1,2,4,3}、{6,2,3,1,5,4}都是可整合数组
     * 返回arr中最长可整合子数组的长度
     */
    // 对于颗整合数组这件事我们可以重新定义，L ... R范围内无重复数字，max - min =  R-L+1 -1
    public static int maxLen(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int n = arr.length;
        int ans = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.clear();
            set.add(arr[i]);
            int max = arr[i];
            int min = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (set.contains(arr[j])) {
                    break;
                }
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);
                if (max - min == j - i) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
}
