package duys_code.day_00_sort;

/**
 * @ClassName Code_05_MegerSort
 * @Author Duys
 * @Description
 * @Date 2021/11/12 15:29
 **/
public class Code_05_MergeSort {
    /**
     * 数组划两半 L到M ，M+1 到R
     * 1.分别遍历左边和左边，谁小就拷贝谁，相等就拷贝左边
     * 2.谁越界，拷贝剩下那一半的数据的数组
     */

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        // 当只有一个数的时候，不需要继续了，因为已经有序了
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int index = 0;
        int left = L;
        int right = M + 1;
        while (left <= M && right <= R) {
            // 谁小 拷贝谁，相等先拷贝左边的
            help[index++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }
        // 处理上面循环里面，没还有遍历完的数据
        while (left <= M) {
            help[index++] = arr[left++];
        }
        while (right <= R) {
            help[index++] = arr[right++];
        }
        // 最后把数据还给arr
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 8, 6, 5, 2, 1, 9};
        mergeSort(arr);
        for (int i : arr) {
            System.out.print(" " + i);
        }
    }
}
