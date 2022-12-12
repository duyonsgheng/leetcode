package custom.code_2022_12;

/**
 * @ClassName LeetCode_1310
 * @Author Duys
 * @Description
 * @Date 2022/12/6 16:50
 **/
// 1310. 子数组异或查询
public class LeetCode_1310 {
    // 有一个正整数数组arr，现给你一个对应的查询数组queries，其中queries[i] = [Li,Ri]。
    //对于每个查询i，请你计算从Li到Ri的XOR值（即arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
    //链接：https://leetcode.cn/problems/xor-queries-of-a-subarray
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] sum = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            sum[i] = sum[i - 1] ^ arr[i - 1];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = sum[queries[i][1] + 1] ^ sum[queries[i][0]];
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 8};
        int[] sum = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            sum[i] = sum[i - 1] ^ arr[i - 1];
        }
        int a = arr[0] ^ arr[1];
        int b = sum[2] ^ sum[0];
        System.out.println(a + " " + b);
    }
}
