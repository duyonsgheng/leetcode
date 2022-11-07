package custom.code_2022_11;

/**
 * @ClassName LeetCode_1033
 * @Author Duys
 * @Description
 * @Date 2022/11/7 9:55
 **/
// 1033. 移动石子直到连续
public class LeetCode_1033 {

    public int[] numMovesStones(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        int mid = a + b + c - max - min;
        int ansMax = max - min - 2;
        int ansMin = 0;
        if (max - mid == 2 || mid - min == 2) {
            ansMin = 1;
        } else {
            ansMin = (min + 1 == mid ? 0 : 1) + (mid + 1 == max ? 0 : 1);
        }
        return new int[]{ansMin, ansMax};
    }
}
