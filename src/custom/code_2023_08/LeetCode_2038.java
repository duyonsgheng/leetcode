package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2038
 * @date 2023年08月02日
 */
// 2038. 如果相邻两个颜色均相同则删除当前颜色
// https://leetcode.cn/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/
public class LeetCode_2038 {
    public boolean winnerOfGame(String colors) {
        // 分别统计 A 和 B删除了几个
        int[] arr = new int[]{0, 0};
        char c = 'C';
        int cnt = 0;
        for (int i = 0; i < colors.length(); i++) {
            char cur = colors.charAt(i);
            // 统计连续的
            if (cur != c) {
                c = cur;
                cnt = 1;
            } else {
                cnt += 1;
                // 连续的大于等于了3个，可以删除一个，记录下来
                if (cnt >= 3) {
                    arr[c - 'A']++;
                }
            }
        }
        return arr[0] > arr[1];
    }
}
