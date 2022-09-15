package week.week_2022_06_03;

/**
 * @ClassName Code_01_LeetCode_768
 * @Author Duys
 * @Description
 * @Date 2022/6/23 13:02
 **/
// 768. 最多能完成排序的块 II
// 这个问题和“最多能完成排序的块”相似，但给定数组中的元素可以重复，输入数组最大长度为2000，其中的元素最大为10**8。
//arr是一个可能包含重复元素的整数数组，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
//我们最多能将数组分成多少块？
//链接：https://leetcode.cn/problems/max-chunks-to-make-sorted-ii
public class Code_01_LeetCode_768 {

    // 1.从后往前依次找到每一个位置右边最小的值
    // 2.从前往后，看看当前值是否是小于等于当前位置的最小值，是的话可以切
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int n = arr.length;
        int[] minArr = new int[n];
        minArr[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minArr[i] = Math.min(arr[i], minArr[i + 1]);
        }
        int ans = 1;
        // 从前往后
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (max <= minArr[i]) {
                ans++;
            }
            max = Math.max(max, arr[i]);
        }
        return ans;
    }
}
