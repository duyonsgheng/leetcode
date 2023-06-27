package custom.code_2023_06;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1850
 * @Author Duys
 * @Description
 * @Date 2023/6/5 9:50
 **/
// 1850. 邻位交换的最小次数
public class LeetCode_1850 {
    public int getMinSwaps(String num, int k) {
        char[] arr = num.toCharArray();
        while (k > 0) {
            nextPermutation(arr);
            k--;
        }
        return process(num.toCharArray(), arr);
    }

    public int process(char[] source, char[] target) {
        int ans = 0;
        for (int i = 0; i < target.length; i++) {
            if (source[i] == target[i]) {
                continue;
            }
            int j = i + 1;
            // 两个串中第一个一样的位置
            for (; j < target.length && target[i] != source[j]; j++) {

            }
            for (int k = j; k > i; k--) {
                ans++;
                swap(source, k, k - 1);
            }
        }
        return ans;
    }

    public void nextPermutation(char[] arr) {
        int i = arr.length - 2;
        // 依次找到，i位置较大的地方
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = arr.length - 1;
            while (j >= 0 && arr[i] >= arr[j]) {
                j--;
            }
            swap(arr, i, j);
        }
        // 后面的排序
        Arrays.parallelSort(arr, i + 1, arr.length);
    }

    public void swap(char[] cs, int i, int j) {
        char t = cs[i];
        cs[i] = cs[j];
        cs[j] = t;
    }
}
