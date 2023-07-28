package week.week_2023_07_04;

/**
 * @author Mr.Du
 * @ClassName Code_01_LeetCode_2208
 * @date 2023年07月27日
 */
// https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/

public class Code_01_LeetCode_2208 {
    // 第一种做法就是优先级队列
    // 第二种做法就是手动创建堆-大根堆
    public int maxn = 100001;
    public long[] heap = new long[maxn];
    public int size;

    public int halveArray(int[] nums) {
        size = nums.length;
        long sum = 0;
        for (int i = size - 1; i >= 0; i--) {
            heap[i] = (long) nums[i] << 20; // 扩大
            sum += heap[i];
            heapify(i);
        }
        sum /= 2;
        int ans = 0;
        for (long a = 0; a < sum; ans++) {
            heap[0] /= 2;
            a += heap[0];
            heapify(0);
        }
        return ans;
    }

    // 构建大根堆，从下往上，小的往下沉
    public void heapify(int i) {
        int l = i * 2 + 1;
        while (l < size) {
            int best = l + 1 < size && heap[l + 1] > heap[l] ? l + 1 : l;
            best = heap[best] > heap[i] ? best : i;
            if (best == i) {
                break;
            }
            swap(best, i);
            i = best;
            l = i * 2 + 1;
        }
    }

    public void swap(int i, int j) {
        long tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
