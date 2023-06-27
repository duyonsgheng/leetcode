package week.week_2023_05_03;

/**
 * @ClassName Code_01_PrintFromInnerLoop
 * @Author Duys
 * @Description
 * @Date 2023/5/18 9:17
 **/
// 保证一定是n*n的正方形，实现从里到外转圈打印的功能
// 如果n是奇数，中心点唯一，比如
// a b c
// d e f
// g h i
// e是中心点，依次打印 : e f i h g d a b c
// 如果n是偶数，中心点为最里层2*2的右下点
// 比如
// a b c d e f
// g h i j k l
// m n o p q r
// s t u v w x
// y z 0 1 2 3
// 4 5 6 7 8 9
// 最里层是
// o p
// u v
// v是中心点，依次打印 : v u o p q w ....
public class Code_01_PrintFromInnerLoop {
    // 遇到这种题
    // 第一种：从(0 0)点开始打印   左上角点 (0,0) 右下角点 (n-1,n-1) 每一次 左上角点 ++ 操作，右下角点 -- 操作，然后根据打印方向每一次撸四条边
    // 第二种：从中心点开始往外打印  左上角点 ((n-1)/2,(n-1)/2) 右下角点 (n/2,n/2) ，每一次左上角点 -- 操作，右下角点 ++ 操作，然后根据打印方向撸过四条边

    public static void print(char[][] m) {
        int n = m.length;
        for (int a = (n - 1) / 2, b = (n - 1) / 2, c = n / 2, d = n / 2; a >= 0; a--, b--, c++, d++) {
            loop(m, a, b, c, d);
        }
        System.out.println();
    }

    // 既然顺时针打印
    //  (a,b)
    //
    //            (c,d)
    public static void loop(char[][] arr, int a, int b, int c, int d) {
        if (a == c) { // 只有一个点
            System.out.print(arr[a][c]);
        } else {
            //
            for (int row = a + 1; row <= c; row++) {
                System.out.print(arr[row][d]);
            }
            for (int col = d - 1; col >= b; col--) {
                System.out.print(arr[c][col]);
            }
            for (int row = c - 1; row >= a; row--) {
                System.out.print(arr[row][b]);
            }
            for (int col = b + 1; col <= d; col++) {
                System.out.print(arr[a][col]);
            }
        }
    }
}
