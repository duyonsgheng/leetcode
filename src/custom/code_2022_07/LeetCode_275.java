package custom.code_2022_07;

import java.util.Arrays;

/**
 * @ClassName LeetCode_275
 * @Author Duys
 * @Description
 * @Date 2022/7/14 15:14
 **/
// 275. H 指数 II
public class LeetCode_275 {


    public static int hIndex(int[] citations) {
        if (citations == null || citations.length < 1) {
            return 0;
        }
        int l = 0;
        int n = citations.length;
        int r = n - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (citations[mid] >= n - mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return n - l;
    }

    public static void main(String[] args) {
        int[] citations = {1, 2, 100};
        System.out.println(hIndex(citations));
    }
}
