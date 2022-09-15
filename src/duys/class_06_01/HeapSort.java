package duys.class_06_01;

/**
 * @ClassName HeapSort
 * @Author Duys
 * @Description 堆排序-属于火车撞了都不能忘的
 * @Date 2021/6/4 9:41
 **/
public class HeapSort {
    /**
     * 堆-只有两种操作，一种是向下，一种上向上
     * 一个节点的父亲节点 (i-1)/2
     * 一个节点的子节点 左子节点 (i*2)+1 右子节点 (i*2)+2
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 组成一个堆，两种方式，一种直接向下创建，一个由少变多的过程，时间复杂度O(N*logN)
        /*for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }*/
        // 另外一种向上(由多边少，时间复杂度O(N))，因为树形结构从底层到顶层是一个边少的过程
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        // 把第0位置，和arr.length -1 位置交换，因为上面初始化堆的时候，是一个大根堆，最大的数在0位置
        // 把0位置最大的数，浮动到数组的最后一个位置上去，同时数组的最后一个位置，不参与堆重构过程
        swap(arr, 0, --heapSize);
        // 依次减少堆重构的过程中用到的数，也就是把已经有序的数组右边逐渐从迭代过程中剔除
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    // arr[index] 刚来的数，往上，和父比较，如果比父还大就交换位置，大的数往上浮动
    public static void heapInsert(int[] arr, int index) {
        // 不用管越界问题，因为最多index 来到0位置
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // arr[index] 能否向下移动
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左孩子的下标
        while (left < heapSize) { // 如果左下边还有孩子节点
            // 因为右孩子的下标是 index*2 +2，也就是left+1 ，这两个还在谁大，谁就赋给largest
            // 1.只有左孩子 left -> largest
            // 2.同时有左右孩子，那么谁大就给largest
            // 解释：下面这个赋值过程，前面意思是如果存在两个孩子，那么就比较大小，如果只有左孩子，直接赋值
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父和左右孩子较大的比较，谁大，谁就赋给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            // 如果满足了，那么交换，然后把index向下走，继续孩子节点
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
