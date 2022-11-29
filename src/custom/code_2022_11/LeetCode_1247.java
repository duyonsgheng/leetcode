package custom.code_2022_11;

/**
 * @ClassName LeetCode_1247
 * @Author Duys
 * @Description
 * @Date 2022/11/29 10:24
 **/
// 1247. 交换字符使得字符串相同
public class LeetCode_1247 {
    // xxyyxyxyxx
    // xyyxyxxxyx
    // x x
    // y y 只需要一次
    // y x
    // x y 两次
    public int minimumSwap(String s1, String s2) {
        //
        int x = 0;
        int y = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            if (s1.charAt(i) == 'x') {
                x++;
            } else {
                y++;
            }
        }
        if ((x & 1) != (y & 1)) {
            return -1;
        }
        // x/2 是 x x 和 y y 这种情况 只需要一次 ，如果是 xy 和yx 这种情况，需要经过1次变成 xx yy的模式，然后再经过一次变成一样
        return x / 2 + y / 2 + x % 2 + y % 2;
    }
}
