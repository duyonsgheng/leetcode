package duys.class_06_01;

import java.util.PriorityQueue;

/**
 * @ClassName SortArrayDistanceLessK
 * @Author Duys
 * @Description 一个数组，基本有序，其中那些无序的数，移动到有序的位置去，移动的距离不会超过K
 * @Date 2021/6/4 10:41
 **/
public class SortArrayDistanceLessK {
    /**
     * 例如 当前限制为K 那么 0 - k位置上的数，排成小根堆，
     * 只有 0 - k之间的数才能来到0位置，其他位置上的数是不可能来到0 位置的（小根堆 0 位置是最小的），
     * 不然就不满足题目条件，然后从小根堆 弹出0位置的数，一定是最小的，弹一个放一个。
     * 负载度 O(N*logK)
     */
    public static void sort(int[] arr, int k) {
        if (k <= 0) {
            return;
        }
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 先放入 0 - k-1 位置上的数到小根堆
        // 如果数组的数都不够k-1 那么就直接 0 -  数组长度-1
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }
        int index1 = 0;
        // 加一个弹出一个，弹出小根堆0位置的数，放到数组的0位置
        // index1 表示弹出的那些数，在原数组中的下标，也就是小于区域的有边界下边，也即是弹出的数，该放入到原数组什么位置去
        for (; index < arr.length; index1++, index++) {
            // 这里index 是第一次加入知后，index在数组中的位置
            heap.add(arr[index]);
            arr[index1] = heap.poll();// 弹出每次堆顶的位置放在arr从0开始，往后移动
        }
        // 如果数组中的数已经全部加入到了堆中，但是还没有弹出完
        while (!heap.isEmpty()) {
            arr[index++] = heap.poll();
        }
    }

}
