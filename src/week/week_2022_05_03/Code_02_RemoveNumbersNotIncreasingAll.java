package week.week_2022_05_03;

/**
 * @ClassName Code_02_RemoveNumbersNotIncreasingAll
 * @Author Duys
 * @Description
 * @Date 2022/5/19 13:32
 **/
// 来自京东
// 4.2笔试
// 给定一个数组arr，长度为N，arr中所有的值都在1~K范围上
// 你可以删除数字，目的是让arr的最长递增子序列长度小于K
// 返回至少删除几个数字能达到目的
// N <= 10^4，K <= 10^2
public class Code_02_RemoveNumbersNotIncreasingAll {

    // 这个题有点意思
    // 我们的最长递增子序列长度小于K，而数组中的元素都是 1~K范围上的，意思是我们在寻找的时候只要不满足 1~K就行
    // 递归来了
    public static int minRemove(int[] arr, int k) {
        if (arr == null || arr.length <= 0) {
            return k;
        }
        return process(arr, 0, 0, k);
    }

    // 当前来到了index位置上做抉择
    // len，前面最长递增子序列已经到了那儿了
    public static int process(int[] arr, int index, int len, int k) {
        // 如果最长递增子序列都已经达到k长度了。后续不用看了，返回无论如何搞不定
        if (len == k) {
            return Integer.MAX_VALUE;
        }
        // 来到最后了，都没有涨到k长度，后续不需要删除了
        if (index == arr.length) {
            return 0;
        }
        // 还没到最后，还没有长度K
        int cur = arr[index];
        // 之前都已经涨到了3 当前位置是3，不用删除，因为不会违反规定
        // 之前都已经涨到了3 当前位置是5，不用删除，因为不会违反规定
        if (len >= cur || len + 1 < cur) {
            return process(arr, index + 1, len, k);
        }
        //之前已经涨到了3， 如果当前是4了
        // 两种选择，1.保留 2.删除
        // 保留了
        int p1 = process(arr, index + 1, len, k);
        int p2 = Integer.MAX_VALUE;
        // 删除
        int next = process(arr, index + 1, len + 1, k);
        if (next != Integer.MAX_VALUE) {
            p2 = 1 + next;
        }
        return Math.min(p1, p2);
    }
}
