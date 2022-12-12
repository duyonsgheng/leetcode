package custom.code_2022_12;

/**
 * @ClassName LeetCode_1318
 * @Author Duys
 * @Description
 * @Date 2022/12/8 18:00
 **/
// 1318. 或运算的最小翻转次数
public class LeetCode_1318 {
    public static int minFlips(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int t = (c & (1 << i)) == 0 ? 0 : 1;
            int ai = (a & (1 << i)) == 0 ? 0 : 1;
            int bi = (b & (1 << i)) == 0 ? 0 : 1;
            if (t == 1) {
                if (ai == 0 && bi == 0) {
                    ans++;
                }
            } else {
                if (ai == 1 && bi == 1) {
                    ans += 2;
                } else if (ai == 1 || bi == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minFlips(2, 6, 5));
        System.out.println(minFlips(4, 2, 7));
        System.out.println(minFlips(1, 2, 3));
    }
}
