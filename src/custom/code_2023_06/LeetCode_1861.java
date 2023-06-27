package custom.code_2023_06;

/**
 * @ClassName LeetCode_1861
 * @Author Duys
 * @Description
 * @Date 2023/6/9 10:57
 **/
// 1861. 旋转盒子
public class LeetCode_1861 {
    // 行变列
    // # 石头
    // * 障碍物
    // . 空格
    public char[][] rotateTheBox(char[][] box) {
        int n = box.length;
        int m = box[0].length;
        char[][] arr = new char[m][n];
        for (int i = 0; i < n; i++) {
            int index = m - 1;
            for (int j = m - 1; j >= 0; j--) {
                if (box[i][j] == '#') {
                    box[i][index--] = '#';
                    if (index != j - 1) {
                        box[i][j] = '.';
                    }
                } else if (box[i][j] == '*') {
                    index = j - 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[j][n - i - 1] = box[i][j];
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        char[] curRow = new char[]{'#', '#', '#', '.', '.'};

    }
}
