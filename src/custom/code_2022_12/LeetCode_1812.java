package custom.code_2022_12;

/**
 * @ClassName LeetCode_1812
 * @Author Duys
 * @Description
 * @Date 2022/12/8 14:21
 **/
// 1812. 判断国际象棋棋盘中一个格子的颜色
public class LeetCode_1812 {
    public static boolean squareIsWhite(String coordinates) {
       /* int a = coordinates.charAt(0) - 'a' + 1;
        int b = coordinates.charAt(1) - '0';
        System.out.println(a + "--" + b);
        if (a % 2 == 0) { // 白色开始，b如果是偶数就是黑色
            return b % 2 != 0;
        } else {
            // 黑色开始
            return b % 2 == 0;
        }*/
        return ((coordinates.charAt(0) - 'a' + 1) + (coordinates.charAt(1) - '0')) % 2 == 1;
    }

    public static void main(String[] args) {
        System.out.println(squareIsWhite("a1"));
        System.out.println(squareIsWhite("h3"));
        System.out.println(squareIsWhite("c7"));
    }
}
