package custom.code_2023_04;

import java.util.Arrays;

/**
 * @ClassName LeetCode_2418
 * @Author Duys
 * @Description
 * @Date 2023/4/25 9:36
 **/
// 2418. 按身高排序
public class LeetCode_2418 {

    public String[] sortPeople(String[] names, int[] heights) {
        int n = heights.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = heights[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            ans[i] = names[arr[i][1]];
        }
        return ans;
    }
}
