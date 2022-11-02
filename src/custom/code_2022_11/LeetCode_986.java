package custom.code_2022_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_986
 * @Author Duys
 * @Description
 * @Date 2022/11/1 14:09
 **/
// 986. 区间列表的交集
public class LeetCode_986 {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> ans = new ArrayList<>();
        int len1 = firstList.length;
        int len2 = secondList.length;
        int i1 = 0;
        int i2 = 0;
        while (i1 < len1 && i2 < len2) {
            int[] arr1 = firstList[i1];
            int[] arr2 = secondList[i2];
            int s = Math.max(arr1[0], arr2[0]);
            int e = Math.min(arr1[1], arr2[1]);
            if (s <= e) {
                ans.add(new int[]{s, e});
            }
            // 如果arr2 包含了arr1，那么需要多个arr1才能消化完arr2
            if (arr1[1] <= arr2[1]) {
                i1++;
            }
            //如果arr1 包含了arr2，那么需要多个arr2才能孝华完arr1
            if (arr1[1] >= arr2[1]) {
                i2++;
            }
        }
        int[][] res = new int[ans.size()][2];
        int index = 0;
        for (int[] arr : ans) {
            res[index++] = arr;
        }
        return res;
    }
}
