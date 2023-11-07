package hope.binarySearch;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code03_RobotPassThroughBuilding
 * @date 2023年11月01日 9:58
 */
// 机器人跳跃问题
// 机器人正在玩一个古老的基于DOS的游戏
// 游戏中有N+1座建筑，从0到N编号，从左到右排列
// 编号为0的建筑高度为0个单位，编号为i的建筑的高度为H(i)个单位
// 起初机器人在编号为0的建筑处
// 每一步，它跳到下一个（右边）建筑。假设机器人在第k个建筑，且它现在的能量值是E
// 下一步它将跳到第个k+1建筑
// 它将会得到或者失去正比于与H(k+1)与E之差的能量
// 如果 H(k+1) > E 那么机器人就失去H(k+1)-E的能量值，否则它将得到E-H(k+1)的能量值
// 游戏目标是到达第个N建筑，在这个过程中，能量值不能为负数个单位
// 现在的问题是机器人以多少能量值开始游戏，才可以保证成功完成游戏
// 测试链接 : https://www.nowcoder.com/practice/7037a3d57bbd4336856b8e16a9cafd71
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code03_RobotPassThroughBuilding {
    public static int MAXN = 100001;

    public static int[] arr = new int[MAXN];

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            int l = 0;
            int r = 0;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
                r = Math.max(r, arr[i]);
            }
            out.println(compute(l, r, r));
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(int l, int r, int max) {
        int m, ans = -1;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (ok(m, max)) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return ans;
    }

    public static boolean ok(int start, int max) {
        for (int i = 1; i <= n; i++) {
            if (start <= arr[i])
                // 失去能量
                start -= arr[i] - start;
            else
                // 获得能量
                start += start - arr[i];

            if (start >= max)
                return true;

            if (start < 0)
                return false;
        }
        return true;
    }
}
