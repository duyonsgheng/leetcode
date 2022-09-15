package duys_code.day_01;

/**
 * @ClassName Array_Code_01
 * @Author Duys
 * @Description
 * @Date 2021/9/13 17:06
 **/
public class Code_01_Array {
    /**
     * 给定一个有序数组arr，代表坐落在X轴上的点
     * 给定一个正数K，代表绳子的长度
     * 返回绳子最多压中几个点？
     * 即使绳子边缘处盖住点也算盖住
     */
    // arr 坐标点
    // k -绳子的长度
    public static int process1(int[] arr, int K) {
        // 前缀和
        int N = arr.length;
        // 以绳子的末尾处压中的位置来讨论
        // 比如绳子的末尾先来到arr[0],然后arr[1] ,然后arr[2] .....
        // 类似滑动窗口
        int right = 0;
        int left = 0;
        int max = 0;
        // left 和 right 都从 0开始，
        while (left < N) {
            // left先不动，卡住left，然后向右滑动right，当距离已经满足K了，计算一次。
            // 然后left向右滑动，窗口的最大是K，然后右边进，左边出，每一次算一个答案
            while (right < N && arr[right] - arr[left] <= K) {
                right++;
            }
            max = Math.max(max, right - left);
            left++;
        }
        return max;
    }

    // O(n*logN) - 二分
    public static int process2(int[] arr, int K) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int mid = mid(arr, i, arr[i] - K);
            res = Math.max(res, i - mid + 1);
        }
        return res;
    }

    public static int mid(int[] arr, int R, int v) {
        int l = 0;
        int index = R;
        while (l <= R) {
            int mid = l + (R - l) >> 1;
            if (arr[mid] >= v) {
                index = mid;
                R = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }
}
