package custom.code_2022_11;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1138
 * @Author Duys
 * @Description
 * @Date 2022/11/16 10:59
 **/
// 1138. 字母板上的路径
public class LeetCode_1138 {
    public static String alphabetBoardPath(String target) {
        int[][] arr = new int[6][5];
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = k++;
            }
        }
        Arrays.fill(arr[5], -1);
        arr[5][0] = k;
        char[] chars = target.toCharArray();
        int px = 0;
        int py = 0;
        int n = chars.length;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < n; i++) {
            int cur = chars[i] - 'a';
            int cx = cur / 5;
            int cy = cur % 5;
            if (px == cx && py == cy) {
                buffer.append("!");
            }
            // 要去的位置在Z，也比较特殊
            else {
                // 之前再z的时候比较特别，只能向上
                if (px == 5 && py == 0) {
                    buffer.append("U");
                    px -= 1;
                }
                boolean toZ = (cx == 5 && cy == 0);
                if (px == cx && py == cy) {
                    buffer.append("!");
                    continue;
                }
                int dx = Math.abs(cx - px);
                int dy = Math.abs(cy - py);
                // 要去的位置在之前位置的下边
                boolean toUp = cx >= px;
                boolean toRight = cy >= py;
                for (int up = 0; up < (toZ ? dx - 1 : dx); up++) {
                    buffer.append(toUp ? "D" : "U");
                }
                for (int right = 0; right < dy; right++) {
                    buffer.append(toRight ? "R" : "L");
                }
                if (toZ) {
                    buffer.append("D");
                }
                buffer.append("!");
                px = cx;
                py = cy;
            }
        }
        return buffer.toString();
    }

    /**
     * 0  1  2  3  4
     * 0  0  1  2  3  4
     * 1  5  6  7  8  9
     * 2  10 11 12 13 14
     * 3  15 16 17 18 19
     * 4  20 21 22 23 24
     * 5  25
     */
    public static void main(String[] args) {
        System.out.println(alphabetBoardPath("leet"));
        System.out.println(alphabetBoardPath("code"));
        System.out.println(alphabetBoardPath("zdz"));
    }
}
