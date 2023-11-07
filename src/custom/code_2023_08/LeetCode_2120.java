package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2120
 * @date 2023年08月21日
 */
// 2120. 执行所有后缀指令
// https://leetcode.cn/problems/execution-of-all-suffix-instructions-staying-in-a-grid/
public class LeetCode_2120 {
    int step = 0;
    int len = 0;
    int sx = -1, sy = -1;

    public int[] executeInstructions(int n, int[] startPos, String s) {
        char[] arr = s.toCharArray();
        sx = startPos[0];
        sy = startPos[1];
        len = n;
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            step = 0;
            process(i, sx, sy, arr);
            ans[i] = step;
        }
        return ans;
    }

    public void process(int index, int x, int y, char[] arr) {
        if (index >= arr.length) {
            return;
        }
        if (arr[index] == 'U') {
            x = x - 1;
        } else if (arr[index] == 'D') {
            x = x + 1;
        } else if (arr[index] == 'L') {
            y = y - 1;
        } else if (arr[index] == 'R') {
            y = y + 1;
        }
        if (x >= 0 && x < len && y >= 0 && y < len) {
            step++;
            process(index + 1, x, y, arr);
        }
    }
}
