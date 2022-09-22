package custom.code_2022_09;

/**
 * @ClassName LeetCode_788
 * @Author Duys
 * @Description
 * @Date 2022/9/20 14:01
 **/
// 788. 旋转数字
public class LeetCode_788 {
    public int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (ok(i, false)) {
                ans++;
            }
        }
        return ans;
    }

    // 比如当前数是 98
    // 先算8，8是不需要进行旋转的
    // 接下来就算9，只要后续有一个失败了，就表示不行
    // 9 可以进行旋转
    // 然后就到0了，
    public boolean ok(int num, boolean pre) {
        if (num == 0) {
            return pre;
        }
        int di = num % 10;
        if (di == 3 || di == 7 || di == 4) {
            return false;
        }
        if (di == 0 || di == 1 || di == 8) {
            return ok(num / 10, pre);
        }
        return ok(num / 10, true);
    }
}
