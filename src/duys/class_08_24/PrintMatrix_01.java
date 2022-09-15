package duys.class_08_24;

/**
 * @ClassName PrintMatrix_01
 * @Author Duys
 * @Description
 * @Date 2021/8/25 15:31
 **/
public class PrintMatrix_01 {

    public static void print(int[][] arr) {
        int a = 0;
        int b = 0;
        int c = arr.length - 1;
        int d = arr[0].length - 1;
        while (a <= c && b <= d) {
            edge(arr, a++, b++, c--, d--);
        }
    }

    public static void edge(int[][] arr, int a, int b, int c, int d) {
        if (a == c) { // 同一行数据了，直接打印
            for (int i = 0; i <= b; i++) {
                System.out.println(arr[a][i] + " ");
            }
        } else if (b == d) { // 同一列了
            for (int i = 0; i <= a; i++) {
                System.out.println(arr[i][b] + " ");
            }
        } else {
            int curRow = a; // 行
            int curCol = b; // 列

            // 四条边 ,都不包含边的最后一个点
            
            while (curCol != d) {
                System.out.println(arr[a][curCol]);
                curCol++;
            }
            while (curRow != c) {
                System.out.println(arr[curRow][d]);
                curRow++;
            }

            while (curCol != b) {
                System.out.println(arr[c][curCol]);
                curCol--;
            }

            while (curRow != a) {
                System.out.println(arr[curRow][b]);
                curRow--;
            }
        }
    }
}
