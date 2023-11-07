package hope.monotoneStack;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code01_LeftRightLess
 * @date 2023年11月06日 9:27
 */
// 单调栈求每个位置左右两侧，离当前位置最近、且值严格小于的位置
// 给定一个可能含有重复值的数组 arr
// 找到每一个 i 位置左边和右边离 i 位置最近且值比 arr[i] 小的位置
// 返回所有位置相应的信息。
// 输入描述：
// 第一行输入一个数字 n，表示数组 arr 的长度。
// 以下一行输入 n 个数字，表示数组的值
// 输出描述：
// 输出n行，每行两个数字 L 和 R，如果不存在，则值为 -1，下标从 0 开始。
// 测试链接 : https://www.nowcoder.com/practice/2a2c00e7a88a498693568cef63a4b7bb
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code01_LeftRightLess {

    public static int MAXN = 1000001;

    public static int[] arr = new int[MAXN];

    public static int[] stack = new int[MAXN];

    public static int[][] ans = new int[MAXN][2];

    public static int n, r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            compute();
            for (int i = 0; i < n; i++) {
                out.println(ans[i][0] + " " + ans[i][1]);
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void compute() {
        r = 0;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            // 栈顶元素大于当前位置的数
            while (r > 0 && arr[stack[r - 1]] >= arr[i]) {
                cur = stack[--r];
                // cur位置的答案就可以直接结算
                ans[cur][0] = r > 0 ? stack[r - 1] : -1;
                // 右侧比自己小的就是当前位置
                ans[cur][1] = i;
            }
            stack[r++] = i;
        }
        // 左侧的位置先设置好
        while (r > 0) {
            cur = stack[--r];
            ans[cur][0] = r > 0 ? stack[r - 1] : -1;
            ans[cur][1] = -1;
        }
        // 修正右侧的位置
        for (int i = n - 2; i >= 0; i--) {
            if (ans[i][1] != -1 && arr[ans[i][1]] == arr[i]) {
                ans[i][1] = ans[ans[i][1]][1];
            }
        }
    }
}
