package custom.code_2022_12;

/**
 * @ClassName LeetCode_1362
 * @Author Duys
 * @Description
 * @Date 2022/12/14 14:21
 **/
// 1362. 最接近的因数
public class LeetCode_1362 {
    public int[] closestDivisors(int num) {
        int[] num1 = process(num + 1);
        int[] num2 = process(num + 2);
        return Math.abs(num1[0] - num1[1]) < Math.abs(num2[0] - num2[1]) ? num1 : num2;
    }

    public int[] process(int num) {
        int m = (int) Math.sqrt(num);
        if (m * m == num) {
            return new int[]{m, m};
        }
        for (int i = m; i >= 0; i--) {
            if (num % i == 0) {
                return new int[]{i, num / i};
            }
        }
        return null;
    }
}
