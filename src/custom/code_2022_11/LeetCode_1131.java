package custom.code_2022_11;

/**
 * @ClassName LeetCode_1131
 * @Author Duys
 * @Description
 * @Date 2022/11/16 10:38
 **/
// 1131. 绝对值表达式的最大值
public class LeetCode_1131 {
    // |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j| 最大
    // 去掉绝对值
    //  arr1[i] - arr1[j] + arr2[i] - arr2[j] + i -j
    //  arr1[i] - arr1[j] - arr2[i] + arr2[j] + i -j
    // -arr1[i] + arr1[j] + arr2[i] - arr2[j] + i -j
    // -arr1[i] + arr1[j] - arr2[i] + arr2[j] + i -j
    // 等价转换一波
    //  arr1[i]+arr2[i]+i -(arr1[j]+arr2[j]+j)
    //  arr1[i]-arr2[i]+i -(arr1[j]-arr2[j]+j)
    // -arr1[i]+arr2[i]+i -(-arr1[j]+arr2[j]+j)
    // -arr1[i]-arr2[i]+i -(-arr1[j]-arr2[j]+j)
    // 那么只需要求出 arr1[i]+arr2[i]+i  arr1[i]-arr2[i]+i  -arr1[i]+arr2[i]+i -arr1[i]-arr2[i]+i
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int s1 = arr1[0];
        int s2 = arr2[0];
        int a = s1 + s2, b = s1 - s2, c = -s1 + s2, d = -s1 - s2;
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < arr1.length; i++) {
            s1 = arr1[i];
            s2 = arr2[i];
            int ca = s1 + s2 + i, cb = s1 - s2 + i, cc = -s1 + s2 + i, cd = -s1 - s2 + i;
            ans = Math.max(ans, Math.max(Math.max(ca - a, cb - b), Math.max(cc - c, cd - d)));
            // 因为是当前 - 之前的
            // 当前的不可变，那么之前的越小，结果越大
            a = Math.min(ca, a);
            b = Math.min(cb, b);
            c = Math.min(cc, c);
            d = Math.min(cd, d);
        }
        return ans;
    }
}
