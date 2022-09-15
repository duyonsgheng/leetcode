package duys_code.day_32;

import java.util.HashMap;

/**
 * @ClassName Code_03_166_LeetCode
 * @Author Duys
 * @Description 力扣;https://leetcode-cn.com/problems/fraction-to-recurring-decimal/
 * @Date 2021/12/3 16:56
 **/
public class Code_03_166_LeetCode {

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder rs = new StringBuilder();
        rs.append((numerator > 0) ^ (denominator > 0) ? "-" : "");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        rs.append(num / den);
        // 是一个能除尽得值
        num %= den;
        if (num == 0) {
            return rs.toString();
        }
        rs.append(".");
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(num, rs.length());
        // 开始
        while (num != 0) {
            num *= 10;
            rs.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                rs.insert(index, "(");
                rs.append(")");
                break;
            } else {
                map.put(num, rs.length());
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        int nu = 1;
        int nu1 = -2;
        System.out.println((nu > 0) ^ (nu1 > 0) ? "-" : "");
    }
}
