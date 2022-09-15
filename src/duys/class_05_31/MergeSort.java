package duys.class_05_31;

/**
 * @ClassName MergeSort
 * @Author Duys
 * @Description 归并
 * @Date 2021/5/31 11:31
 **/
public class MergeSort {
    public static void main(String[] args) {
        int i = 1;
        System.out.println(i++);
        System.out.println(i);
        int[] arr = {8, 5, 7, 9, 3, 4, 2, 1, 12};
        mergeSort(arr);
        for (int is : arr) {
            System.out.print(is + " ");
        }
    }

    static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + (R - L) / 2;
        // 每次二分，左边的进行归并
        process(arr, L, mid);
        // 每次二分，右边的进行归并
        process(arr, mid + 1, R);

        // 合并
        merge(arr, L, R, mid);
    }

    // 左右两边进行合并，谁小拷贝谁
    // 第一次结束，划分了左右两边，那么左右两边各自有序
    // 然后左右两边的数组各自再划分
    static void merge(int[] arr, int L, int R, int M) {
        // 准备一个辅助数组，每一次划分左右两边后，处理结果放在辅助数组中，每一次处理完后，把辅助数组赋给原数组
        int[] help = new int[R - L + 1];
        // i是辅助数组的下标
        int i = 0;
        // 两个指针
        // p1 指针表示左边数组处理的当前下标
        int p1 = L;
        // p2 指针表示右边数组处理的当前下标
        int p2 = M + 1;

        // 左边指针不能超过M。右边指针不能超过R
        while (p1 <= M && p2 <= R) {
            // 谁小拷贝谁，拷贝了哪一方下标向后移动
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 处理 p1 或者p2 越界问题
        // 如果p1 越界，因为左右两边各自有序，那么直接拷贝右边的就好了
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        // 同理，如果p2越界，直接拷贝左边剩下的
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        // 最后来赋给原数组
        for (i = 0; i < help.length; i++)
            arr[L + i] = help[i];
    }
}
