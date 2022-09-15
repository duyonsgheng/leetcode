package week.week_2022_01_02;

/**
 * @ClassName Code_02_StringNumberConvertBinaryAndHexadecimal
 * @Author Duys
 * @Description
 * @Date 2022/4/1 14:54
 **/
// 来自兴业数金
// 给定一个字符串形式的数，比如"3421"或者"-8731"
// 如果这个数不在-32768~32767范围上，那么返回"NODATA"
// 如果这个数在-32768~32767范围上，那么这个数就没有超过16个二进制位所能表达的范围
// 返回这个数的2进制形式的字符串和16进制形式的字符串，用逗号分割
public class Code_02_StringNumberConvertBinaryAndHexadecimal {
    // 首先这是一个业务性质的题目
    // 先校验，然后转换形式
    public static String convert(String nums) {
        // -32768~32767 最多6个字符
        if (nums == null || nums.length() <= 0 || nums.length() > 6) {
            return "NODATA";
        }
        int n = Integer.valueOf(nums);
        if (n < -32768 || n > 32767) {
            return "NODATA";
        }
        // 16个二级制位，0-15
        // 0111 1111 1111 1111 1111  -> 65535
        // 如果是负数，那么第16位一定是1，否则是 0
        int info = (n < 0 ? (1 << 15) : 0) | (n & 65535);
        StringBuilder sb = new StringBuilder();
        // 一个16进制位用4个二进制位表示
        for (int i = 15; i >= 0; i--) {
            sb.append((info & (1 << i)) == 0 ? '1' : '0');
        }
        sb.append(",0x");
        // 4个二进制位 1111
        for (int i = 12; i >= 0; i -= 4) {
            // 当前取出的这4个二进位代表的数字是多大
            int cur = (info & (15 << i)) >> i;
            if (cur < 10) {
                sb.append(cur);
            } else {
                switch (cur) {
                    case 10:
                        sb.append('a');
                        break;
                    case 11:
                        sb.append('b');
                        break;
                    case 12:
                        sb.append('c');
                        break;
                    case 13:
                        sb.append('d');
                        break;
                    case 14:
                        sb.append('e');
                        break;
                    case 15:
                        sb.append('f');
                        break;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "0";
        System.out.println(convert(num1));

        String zuo = "457";
        System.out.println(convert(zuo));

        String num2 = "-32768";
        System.out.println(convert(num2));

        String num3 = "32768";
        System.out.println(convert(num3));

        String num4 = "32767";
        System.out.println(convert(num4));

        String num5 = "-1";
        System.out.println(convert(num5));
        // 0x01c9
        //     0000 0001 1100 1001
    }

}
