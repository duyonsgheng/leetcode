package week.week_2023_07_01;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code_01_KillMonsterEverySkillUseOnce
 * @date 2023年07月06日
 */
// 测试链接 : https://www.nowcoder.com/questionTerminal/d88ef50f8dab4850be8cd4b95514bbbd
public class Code_01_KillMonsterEverySkillUseOnce {
    // 技能数量是10个
    // 那么我们技能就可以全排列 10! = 3*10^6 可以满足需求
    public static int MAXN = 11;

    // 伤害
    public static int[] kill = new int[MAXN];
    // 血量达到多少可以双倍伤害
    public static int[] blood = new int[MAXN];

    public static void main(String[] args) throws IOException {
        // 等待输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new ObjectOutputStream(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int t = (int) in.nval;
            for (int i = 0; i < t; i++) {
                in.nextToken();
                int n = (int) in.nval;
                in.nextToken();
                int m = (int) in.nval;
                for (int j = 0; j < n; j++) {
                    in.nextToken();
                    kill[j] = (int) in.nval;
                    in.nextToken();
                    blood[j] = (int) in.nval;
                }
                int ans = process(n, 0, m);
                out.println(ans == Integer.MAX_VALUE ? -1 : ans);
                out.flush();
            }
        }
    }

    // n 固定参数，一共多少技能
    // rest，怪兽剩余血量
    // i表示当前来到那个位置，0到i-1 都是使用过的技能，并且排列方式在kill数组和blood数组中已经排列好了，剩下的技能是 i到n-1
    public static int process(int n, int i, int rest) {
        // 怪兽没血了，可以返回了
        if (rest <= 0) {
            return i;
        }
        // 技能用完了，怪兽没打死
        if (i == n) {
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        // 剩下的技能全排列
        for (int j = i; j < n; j++) {
            // 把要用的技能换到i位置
            swap(i, j);
            // 只能单体伤害
            if (rest > blood[i]) {
                ans = Math.min(ans, process(n, i + 1, rest - kill[i]));
            } else
                // 双倍伤害
                ans = Math.min(ans, process(n, i + 1, rest - kill[i] * 2));
            swap(i, j); // 交换回来，下一个排列
        }
        return ans;
    }

    public static void swap(int i, int j) {
        int a = kill[i];
        int b = blood[i];
        kill[i] = kill[j];
        blood[i] = blood[j];
        kill[j] = a;
        blood[j] = b;
    }
}
