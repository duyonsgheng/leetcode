package pro;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName MinKthNumCode
 * @Author Duys
 * @Description 在无序数组中找第K小的数
 * @Date 2022/2/10 16:03
 **/
public class MinKthNumCode {
    /**
     * 改写快排
     * 1.因为是随机选择的一个数，所以可能会出现每次选择的都是不能分出3部分，导致最差情况是O(N^2)
     * 2.最好情况是选择的非常好的一个数，每次把数组分成了3部分，T(N) = T(N/2) +O(N)，过一次数组是O(N)的，每一次迭代只走其中的大于或者小于的那一部分数组元素 ，
     * 根据master公式，得出最终的复杂度是O(N)
     * 3.为啥说 快排的时间复杂度是O(N)的，因为随机选择数是概率事件，偏好和偏差都是概率，最终数学上的期望收敛到O(N)。证明略。
     */

    // 1.改写快排的方式
    public static int minKth1(int[] arr, int k) {
        int[] array = copyArray(arr);
        return process(array, 0, arr.length - 1, k - 1);
    }

    // 在L到R范围上找到第index小的数
    public static int process(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        // 随机选择一个数
        int pivot = arr[L + (int) Math.random() * (R - L + 1)];
        int[] range = partition(arr, L, R, pivot);
        // range里面这个区间是相等的。说明找到了，直接返回
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        }
        // 说明大了，需要去小的里面找
        else if (index < range[0]) {
            return process(arr, L, range[0] - 1, index);
        }
        // 说明第index小的在大于区域里面
        else {
            return process(arr, range[1] + 1, R, index);
        }
    }

    // 荷兰国旗问题
    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1; // 小于区域
        int more = R + 1; // 大于区域
        int cur = L;// 当前数
        while (cur < more) {
            // 当前数小于目标
            // 当前数和小于区域下一个数交换，当前数跳下一个
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            }
            // 当前数大于目标
            // 当前数和大于区域的前一个交换，当前数不动
            else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            }
            // 如果相等，当前数跳下一个
            else {
                cur++;
            }
        }
        // 返回等于区域的左右边界
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i <= arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // 2.使用堆来做
    public static int minKth2(int[] arr, int k) {
        // 做一个大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 维持一个大小为K的堆
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            // 如果当前更小，
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        // 剩下的一定是第K小的
        return maxHeap.peek();
    }

    // 3.改写快排的迭代方式
    public static int minKth3(int[] arr, int index) {
        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) Math.random() * (R - L + 1)];
            range = partition(arr, L, R, pivot);
            if (index < range[0]) {
                R = range[0] - 1;
            } else if (index > range[1]) {
                L = range[1] + 1;
            } else {
                return pivot;
            }
        }
        // L.R 相等的时候。
        return arr[L];
    }
}
