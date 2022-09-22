package week.week_2022_09_03;

/**
 * @ClassName Code_03_AllNumbersModToZeroMinTimes
 * @Author Duys
 * @Description
 * @Date 2022/9/22 9:57
 **/
// 来自美团
// 某天，小美在玩一款游戏，游戏开始时，有n台机器，
// 每台机器都有一个能量水平，分别为a1、a2、…、an，
// 小美每次操作可以选其中的一台机器，假设选的是第i台，
// 那小美可以将其变成 ai+10^k（k为正整数且0<=k<=9），
// 由于能量过高会有安全隐患，所以机器会在小美每次操作后会自动释放过高的能量
// 即变成 (ai+10^k)%m
// 其中%m表示对m取模，由于小美还有工作没有完成，所以她想请你帮她计算一下，
// 对于每台机器，将其调节至能量水平为0至少需要多少次操作
//（机器自动释放能量不计入小美的操作次数）。
// 第一行两个正整数n和m，表示数字个数和取模数值。
// 第二行为n个正整数a1, a2,...... an，其中ai表示第i台机器初始的能量水平。
// 1 <= n <= 30000，2 <= m <= 30000, 0 <= ai <= 10^12。
public class Code_03_AllNumbersModToZeroMinTimes {
    // 无限的模下去，直到余数是0的时候
    // 那么对于每一个数字我们如果确切的知道，把 i 变成 0 需要的最小次数，那么我们就可以知道整个数组都变成0的最小次数
    public int[] times(int n, int m, int[] arr) {
        int[] map = new int[m];
        bfs(m, map);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = arr[i];
            int min = Integer.MIN_VALUE;
            if (cur < m) {
                min = map[cur];
            } else {
                for (int add = 1; add < 1000000000; add *= 10) {
                    int mod = (int) (((long) cur + add) % m);
                    min = Math.min(min, map[mod] + 1);
                }
            }
            ans[i] = min;
        }
        return ans;
    }

    public void bfs(int m, int[] map) {
        boolean[] visited = new boolean[m];
        visited[0] = true;
        int[] queue = new int[m];
        int l = 0;
        int r = 0;
        while (l < r) {
            // 当前弹出的余数
            int cur = queue[l++];
            // 10^0 10^1 10^2 .....
            for (int i = 1; i <= 1000000000; i *= 10) {
                // 比如 当前数 是 cur = 4 m = 7 add = 100
                // 那么 哪一个数 （b+100）%7==4呢
                // 4 - (102%7) = 2
                int from = cur - (int) (i % m);
                if (from < 0) {
                    from += m;
                }
                if (!visited[from]) {
                    continue;
                }
                visited[from] = true;
                map[from] = map[cur] + 1;
                queue[r++] = from;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(102 % 7);
    }
}
