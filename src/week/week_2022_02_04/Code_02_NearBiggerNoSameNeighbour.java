package week.week_2022_02_04;

/**
 * @ClassName Code_02_NearBiggerNoSameNeighbour
 * @Author Duys
 * @Description
 * @Date 2022/3/23 16:14
 **/
public class Code_02_NearBiggerNoSameNeighbour {
    // 来自微软
// 给定一个正数num，要返回一个大于num的数，并且每一位和相邻位的数字不能相等
// 返回达标的数字中，最小的那个
// 10^9
    // 例如num = 174 那么 175符合
    // 例如num = 899 那么 901符合
    // 思路考虑进位后
    public static int near(int num) {

        // 我们给num转成char的时候，在前面补0，可以满足进位
        char[] raw = ("0" + String.valueOf(num + 1)).toCharArray();
        process(raw);
        return Integer.valueOf(String.valueOf(raw));
    }

    // 0 1 0 0
    public static void process(char[] raw) {
        for (int i = 1; i < raw.length; i++) {
            if (raw[i - 1] == raw[i]) {
                addOne(raw, i);
                for (int j = i + 1; j < raw.length; j++) {
                    raw[j] = '0';
                }
                process(raw);
                return;
            }
        }
    }

    public static void addOne(char[] raw, int i) {
        boolean up = true;
        while (up && raw[i] == '9') {
            raw[i--] = '0';
        }
        // 把最后的高位补1，产生的进位
        raw[i]++;
    }

    public static void main(String[] args) {
        System.out.println(near(998));
    }
}
