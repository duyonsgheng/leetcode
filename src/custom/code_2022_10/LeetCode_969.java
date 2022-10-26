package custom.code_2022_10;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_969
 * @Author Duys
 * @Description
 * @Date 2022/10/25 11:15
 **/
// 969. 煎饼排序
public class LeetCode_969 {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        for (int i = arr.length; i > 1; i--) {
            int index = 0;
            for (int j = 1; j < i; j++) {
                if (arr[j] >= arr[index]) {
                    index = j;
                }
            }
            if (index == i - 1) {
                continue;
            }
            reverse(arr, index);
            reverse(arr, i - 1);
            ans.add(index + 1);
            ans.add(i);
        }
        return ans;
    }

    public void reverse(int[] arr, int end) {
        for (int i = 0, j = end; i < j; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
