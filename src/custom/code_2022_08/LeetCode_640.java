package custom.code_2022_08;

/**
 * @ClassName LeetCode_640
 * @Author Duys
 * @Description
 * @Date 2022/8/10 9:26
 **/
// 640. 求解方程
public class LeetCode_640 {

    // equation = "x+5-3+x=6+x-2" x=2
    // equation = "x=x" "Infinite solutions"
    // equation = "2x=x"  x=0
    public static String solveEquation(String equation) {
        char[] str = equation.toCharArray();
        if (!equation.contains("=")) {
            return "No solution";
        }
        String[] split = equation.split("=");
        char[] ls = split[0].toCharArray();
        char[] rs = split[1].toCharArray();
        // 把等号左边的算出来
        int[] left = process(ls, 0, ls.length);
        // 把等号右边的算出来
        int[] right = process(rs, 0, rs.length);
        int xc = left[0] + (-right[0]);
        int sum = (-left[1]) + right[1];
        if (xc == 0) {
            return sum == 0 ? "Infinite solutions" : "No solution";
        }
        return "x=" + (sum / xc);
    }

    // 返回等号左边或者右边的值 x[0] 几个x x[1] 值是多少
    // 2x+3-4x+56-6x+100
    //[-8,159]
    public static int[] process(char[] str, int index, int end) {
        int xcount = 0;
        int sum = 0;
        int preSing = 1;
        while (index < end) {
            char cur = str[index];
            if (cur == 'x') {
                if (preSing == 1) {
                    xcount++;
                } else {
                    xcount--;
                }
                index++;
            }
            // 遇到符号了
            //2 x + 3 - 4 x + 5 6 - 3 - 6 x + 1 0 0
            else if (cur == '-' || cur == '+') {
                preSing = cur == '-' ? -1 : 1;
                index++;
            } else {
                int num = 0;
                while (index < end && str[index] >= '0' && str[index] <= '9') {
                    num = num * 10 + str[index] - '0';
                    index++;
                }
                if (preSing == -1) {
                    num = -num;
                }
                if (index < end && str[index] == 'x') {
                    xcount += num;
                    index++;
                } else {
                    sum += num;
                }
            }

        }
        return new int[]{xcount, sum};
    }

    public static void main(String[] args) {
        /*String str = "45+2x+3-5x+100-40+3x-1+3x";
        char[] chars = str.toCharArray();
        int[] arr = process(chars, 0, chars.length);*/
        String str = "2x=x";
        System.out.println(solveEquation(str));
    }
}
