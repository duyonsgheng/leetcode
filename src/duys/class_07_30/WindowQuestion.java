package duys.class_07_30;


import java.util.LinkedList;

/**
 * @ClassName WindowQuestion
 * @Author Duys
 * @Description 滑动窗口问题1
 * @Date 2021/7/30 13:51
 **/
public class WindowQuestion {
    /**
     * 题意：
     * 假设一个固定大小为W的窗口，依次划过arr，
     * 返回每一次滑出状况的最大值
     * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
     * 返回：[5,5,5,4,6,7]
     */
    public static int[] findMAXByWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // qmax 窗口最大值的更新结构
        // 放下标
        LinkedList<Integer> queue = new LinkedList();
        // 返回值
        int N = arr.length;
        int[] ans = new int[N - w + 1];
        int index = 0;
        for (int R = 0; R < N; R++) {
            // 此时窗口R 向右边走，来一个位置，如果双端队列里面不为空，比较队列尾部元素是不是小于当前的，
            //如果小于，不好意思出队列，因为双端队列需要维持从左到右依次从大到小的规则进行放入数据
            while (!queue.isEmpty() && arr[queue.peekLast()] < arr[R]) {
                queue.pollLast();
            }
            // 放下标
            queue.addLast(R);
            // 去掉过期的数据 比如当前窗口大小是3 R来到了下标3 的地方，那么下标是0的，如果在队列里面，就应该过期，需要出队列
            // 3-3 = 0
            if (queue.peekFirst() == R - w) {
                queue.pollFirst();
            }
            // 看看当前窗口是不是已经满足 大小了 下标从0 开始算的，那么窗口大小就应该-1
            if (R >= w - 1) {
                ans[index++] = arr[queue.peekFirst()];
            }
        }
        return ans;
    }
}
