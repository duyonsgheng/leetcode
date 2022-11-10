package custom.code_2022_11;

/**
 * @ClassName LeetCode_1094
 * @Author Duys
 * @Description
 * @Date 2022/11/10 9:35
 **/
// 1094. 拼车
public class LeetCode_1094 {

    // 差分
    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001];
        for (int[] cur : trips) {
            diff[cur[1]] += cur[0];
            // 说明没到最后一战
            if (cur[2] < diff.length) {
                diff[cur[2]] -= cur[0];
            }
        }
        if (diff[0] > capacity) {
            return false;
        }
        for (int i = 1; i < diff.length; i++) {
            diff[i] += diff[i - 1];
            if (diff[i] > capacity) {
                return false;
            }
        }
        return true;
    }
}
