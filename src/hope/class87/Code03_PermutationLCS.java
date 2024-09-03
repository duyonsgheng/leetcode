package hope.class87;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code03_PermutationLCS
 * @date 2024年08月20日 上午 09:49
 */
// 两个排列的最长公共子序列长度
// 给出由1~n这些数字组成的两个排列
// 求它们的最长公共子序列长度
// n <= 10^5
// 测试链接 : https://www.luogu.com.cn/problem/P1439
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code03_PermutationLCS {
    // a数组中的数字对应的下标、
    // b数组中数字换成a数组中对应的下标，然后对b数组求一个最长递增子序列
    public static int MAXN = 100001;
    public static int[] a = new int[MAXN];
    public static int[] b = new int[MAXN];
    public static int[] where = new int[MAXN];
    public static int[] ends = new int[MAXN];
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                a[i] = (int) in.nval;
            }
            for (int i = 0; i < n; i++) {
                in.nextToken();
                b[i] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    /**
     * a 1 3 2 5 4
     * b 2 3 5 2 4
     * w   0 2 1 4 3
     */
    public static int compute() {
        for (int i = 0; i < n; i++) {
            where[a[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            b[i] = where[b[i]];
        }
        return lis();
    }

    // 最长递增子序列
    public static int lis() {
        int len = 0;
        for (int i = 0, find; i < n; i++) {
            find = bs(len, b[i]);
            if (find == -1) {
                ends[len++] = b[i];
            } else ends[find] = b[i];
        }
        return len;
    }

    // 找到最左刚刚大于等于num的位置
    public static int bs(int len, int num) {
        int l = 0, r = len - 1, m, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (ends[m] >= num) {
                r = m - 1;
                ans = m;
            } else l = m + 1;
        }
        return ans;
    }
}
