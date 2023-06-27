package week.week_2023_05_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * @ClassName Code_03_PrintZigZagWithStar
 * @Author Duys
 * @Description
 * @Date 2023/6/2 15:27
 **/
// 来自华为OD
// 如果n = 1，打印
// 1***
// 如果n = 2，打印
//         1***
// 3***    2***
// 如果n = 3，打印
//                 1***
//         3***    2***
// 4***    5***    6***
// 如果n = 4，打印
//                         1***
//                 3***    2***
//         4***    5***    6***
// 10**    9***    8***    7***
// 输入一个数n，表示有多少行，从1开始输出，
// 奇数行输出奇数个数，奇数行正序，偶数行输出偶数个数，偶数行逆序
// 每个数后面加*补满四位，中间空4个，第n行顶格输出
public class Code_03_PrintZigZagWithStar {
    public static int MAXN = 100001;

    public static char[] space = new char[MAXN];

    public static int n, m;

    public static void main(String[] args) throws IOException {
        System.out.println("提醒，请输入n : ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            m = n * 8; // 4个字符+4个空格
            Arrays.fill(space, 0, m, ' ');
            boolean flag = true; // 开始是从左往右，true表示总左往右
        }
    }

    // flag - 是从左往右还是从右往左
    // start - 打印到哪一个数字了
    public static void fill(boolean flag, int start, int number) {
        if (flag) {
            for (int i = m - number * 8, j = 1; j <= number; i += 8, start++, j++) {
                insert(start, i);
            }
        } else {

        }
    }

    public static void insert(int cur, int i) {
        int end = i + 4;
        // 当前占几位
        int bit = cur > 999 ? 4 : (cur > 99 ? 3 : (cur > 9) ? 2 : 1);
        int offset = bit == 4 ? 1000 : (bit == 3 ? 100 : (bit == 2 ? 10 : 1));
        while (offset > 0) {
            space[i++] = (char) (((cur / offset) % 10) + '0');// 有依次取出每一位
            offset /= 10;
        }
        // 如果不够，则补位 *
        while (i < end) {
            space[i++] = '*';
        }
    }
}
