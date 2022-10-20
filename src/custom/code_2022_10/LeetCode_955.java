package custom.code_2022_10;

import java.util.Arrays;

/**
 * @ClassName LeetCode_955
 * @Author Duys
 * @Description
 * @Date 2022/10/20 16:20
 **/
// 955. 删列造序 II
public class LeetCode_955 {
    // 看这个数据量，就递归来尝试
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int ans = 0;
        String[] cur = new String[n];
        for (int i = 0; i < m; i++) {
            String[] cur2 = Arrays.copyOf(cur, n);
            // 依次增加一列比较看看是否是大于的，不然就删掉
            for (int j = 0; j < n; j++) {
                cur2[j] += strs[j].charAt(i);
            }
            if (isSorted(cur2)) {
                cur = cur2;
            } else ans++; // 不然就删除这一列
        }
        return ans;
    }

    public boolean isSorted(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}
