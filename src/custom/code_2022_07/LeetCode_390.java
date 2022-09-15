package custom.code_2022_07;

/**
 * @ClassName LeetCode_390
 * @Author Duys
 * @Description
 * @Date 2022/7/28 13:56
 **/
// 390. 消除游戏
public class LeetCode_390 {
    public int lastRemaining(int n) {
        // 删除一波 剩下 n/2
        return n == 1 ? 1 : (n / 2 + 1 - lastRemaining(n / 2));
    }
}
