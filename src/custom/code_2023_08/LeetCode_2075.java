package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2075
 * @date 2023年08月09日
 */
// 2075. 解码斜向换位密码
// https://leetcode.cn/problems/decode-the-slanted-ciphertext/description/
public class LeetCode_2075 {
    public static String decodeCiphertext(String encodedText, int rows) {
        int cols = encodedText.length() / rows; // 列
        // 开始模拟
        char[][] arr = new char[rows][cols];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = encodedText.charAt(k++);
            }
        }
        // 开始取
        StringBuffer sb = new StringBuffer();
        int col = 0;
        while (col < cols) {
            int sr = 0;
            int sc = col;
            while (sr < rows && sc < cols) {
                sb.append(arr[sr++][sc++]);
            }
            col++;
        }
        int size = sb.length()-1;
        while (size >= 0) {
            if (sb.charAt(size) == ' ') {
                sb.deleteCharAt(size);
            } else
                break;
            size--;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        decodeCiphertext("ch   ie   pr", 3);
        decodeCiphertext(" b  ac", 2);
    }
}
