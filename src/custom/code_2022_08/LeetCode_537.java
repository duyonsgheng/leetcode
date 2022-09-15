package custom.code_2022_08;

/**
 * @ClassName LeetCode_537
 * @Author Duys
 * @Description
 * @Date 2022/8/23 14:24
 **/
// 537. 复数乘法
public class LeetCode_537 {
    public static String complexNumberMultiply(String num1, String num2) {
        String[] split1 = num1.split("\\+");
        String[] split2 = num2.split("\\+");

        // 两部分
        String a = split1[0];
        String b = split1[1];

        String c = split2[0];
        String d = split2[1];

        // 实 a * c
        // 虚 b * d
        // a*d = b*c
        int ai = Integer.valueOf(a) * Integer.valueOf(c);
        int bi = Integer.valueOf(b.substring(0, b.indexOf("i"))) * Integer.valueOf(d.substring(0, d.indexOf("i"))) * -1;
        int pre = ai + bi;
        int ci = Integer.valueOf(a) * Integer.valueOf(d.substring(0, d.indexOf("i")));
        int di = Integer.valueOf(b.substring(0, b.indexOf("i"))) * Integer.valueOf(c);
        return pre + "+" + (ci + di) + "i";
    }

    public static void main(String[] args) {
        String s1 = "1+-1i";
        System.out.println(complexNumberMultiply(s1, s1));
    }
}
