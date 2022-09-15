package duys_code.day_32;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Code_09_202_LeetCode
 * @Author Duys
 * @Description 力扣;https://leetcode-cn.com/problems/happy-number/
 * @Date 2021/12/6 11:09
 **/
public class Code_09_202_LeetCode {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            while (n != 0) {
                // 拿出当前n的最后一位数字
                int r = n % 10;
                sum += r * r;
                n /= 10;
            }
            n = sum;
            if (set.contains(n)) {
                break;
            }
            set.add(n);
        }
        return n == 1;
    }

    public boolean isHappy1(int n) {
        while (n != 1 && n != 4) {
            int sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;
        }
        return n == 1;
    }

    public static void main(String[] args) {
        System.out.println(141 % 10);
    }
}
