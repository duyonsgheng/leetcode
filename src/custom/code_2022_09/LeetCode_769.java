package custom.code_2022_09;

/**
 * @ClassName LeetCode_769
 * @Author Duys
 * @Description
 * @Date 2022/9/19 16:50
 **/
// 769. 最多能完成排序的块
public class LeetCode_769 {

    public int maxChunksToSorted(int[] arr) {
        int ans = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                ans++;
            }
        }
        return ans;
    }
}
