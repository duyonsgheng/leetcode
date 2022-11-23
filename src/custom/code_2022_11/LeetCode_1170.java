package custom.code_2022_11;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName LeetCode_1170
 * @Author Duys
 * @Description
 * @Date 2022/11/22 14:18
 **/
// 1170. 比较字符串最小字母出现频次
public class LeetCode_1170 {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++)
            cnt[i] = findMinMax(words[i].toCharArray());
        Arrays.sort(cnt);
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int f = findMinMax(queries[i].toCharArray());
            // 二分去找 , 在words统计中小于最右的位置
            int l = 0, r = n - 1, index = n;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (cnt[mid] > f) {
                    index = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            ans[i] = n - index;
        }
        return ans;
    }

    // 每一个字符串种最小字符出现的次数
    public int findMinMax(char[] arr) {
        int ans = 0;
        int t = 128;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == t) {
                ans++;
            } else if (arr[i] < t) {
                ans = 1;
                t = arr[i];
            }
        }
        return ans;
    }
}
