package custom.code_2022_09;

/**
 * @ClassName LeetCode_794
 * @Author Duys
 * @Description
 * @Date 2022/9/21 11:21
 **/
// 794. 有效的井字游戏
public class LeetCode_794 {

    // 1.X先手，O为后手，那么结束时候，X数量一定是大于O的数量的
    // 2.当有3个相同的字符出现再任何一行，一列，对角线时，都结束，所有位置非空也结束
    // 3.先手获胜，X比O多一个，后手获胜，X和O的数量相同
    public boolean validTicTacToe(String[] board) {
        int x = 0;
        int o = 0;
        for (String b : board) {
            for (char c : b.toCharArray()) {
                x += c == 'X' ? 1 : 0;
                o += c == 'O' ? 1 : 0;
            }
        }
        return !((x != o && o != x - 1) // 如果不存在这种情况，直接没赢
                || o != x - 1 && win(board, 'X') // x先手没赢
                || o != x && win(board, 'O')); // o后手没赢
    }

    public boolean win(String[] arr, char p) {
        for (int i = 0; i < 3; i++) {
            // 任何一列 ,一列
            if (p == arr[0].charAt(i) && p == arr[1].charAt(i) && p == arr[2].charAt(i) ||
                    p == arr[i].charAt(0) && p == arr[i].charAt(1) && p == arr[i].charAt(2)) {
                return true;
            }
        }
        // 对角线
        return (p == arr[0].charAt(0) && p == arr[1].charAt(1) && p == arr[2].charAt(2))
                || (p == arr[2].charAt(0) && p == arr[1].charAt(1) && p == arr[0].charAt(2));
    }
}
