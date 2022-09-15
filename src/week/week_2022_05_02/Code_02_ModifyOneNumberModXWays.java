package week.week_2022_05_02;

/**
 * @ClassName Code_02_ModifyOneNumberModXWays
 * @Author Duys
 * @Description
 * @Date 2022/5/12 15:10
 **/
// 来自网易
// 小红拿到了一个长度为N的数组arr，她准备只进行一次修改
// 可以将数组中任意一个数arr[i]，修改为不大于P的正数（修改后的数必须和原数不同)
// 并使得所有数之和为X的倍数
// 小红想知道，一共有多少种不同的修改方案
// 1 <= N, X <= 10^5
// 1 <= arr[i], P <= 10^9
public class Code_02_ModifyOneNumberModXWays {

    // 数据量，是10^5，和10^9，如果使用DP，超时了
    // 这里我们想一个问题，算出整个数组的合，然后来到当前位置我 sum - cur 然后去 %x  =mod
    // 然后当前数变成1-p去取模 等于 x-mod 的，有多少个，累加起来

    // 先来一个暴力解答
    public static int ways1(int[] arr, int p, int x) {
        long sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            sum -= arr[i];
            for (int j = 1; j <= p; j++) {
                if (j == arr[i]) {
                    continue;
                }
                if ((sum + j) % x == 0) {
                    ans++;
                }
            }
            sum += arr[i];
        }
        return ans;
    }

    public static int ways2(int[] arr, int p, int x) {
        long sum = 0;
        for (int v : arr) {
            sum += v;
        }
        int ans = 0;
        for (int v : arr) {
            ans += cnt(p, x, v, (int) (x - ((sum - v) % x)) % x);
        }
        return ans;
    }

    public static int cnt(int p, int x, int num, int mod) {
        // 1. 看看每一组x个，有几组，那么每一组中就有一个了
        // 2. p%x >= mod,说明还有数，比如p=10 x=3，3组，还有一个10需要算
        // 3. 如果mod == 0的时候，那么前面的我们就多算了一个
        int ans = (p / x) + ((p % x >= mod) ? 1 : 0) - (mod == 0 ? 1 : 0);

        return ans - (num <= p && p % num == mod ? 1 : 0);
    }
}
