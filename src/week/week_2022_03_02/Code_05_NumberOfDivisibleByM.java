package week.week_2022_03_02;

/**
 * @ClassName Code_05_NumberOfDivisibleByM
 * @Author Duys
 * @Description
 * @Date 2022/3/23 13:28
 **/
public class Code_05_NumberOfDivisibleByM {
// 来自微软
// 给定一个数组arr，给定一个正数M
// 如果arr[i] + arr[j]可以被M整除，并且i < j，那么(i,j)叫做一个M整除对
// 返回arr中M整除对的总数量

    // 暴力解答
    public static int num1(int[] arr, int m) {
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(arr[i] + arr[j]) % m == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // 最优解
    public static int num2(int[] arr, int m) {
        int n = arr.length;
        int[] cnts = new int[m];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cur = (arr[i] + m) % m;
            ans += cnts[(m - cur) % m];
            cnts[cur]++;
        }
        return ans;
    }
}
