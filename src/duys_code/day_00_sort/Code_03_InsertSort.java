package duys_code.day_00_sort;

/**
 * @ClassName Code_03_InsertSort
 * @Author Duys
 * @Description 插入排序
 * @Date 2021/11/12 14:32
 **/
public class Code_03_InsertSort {
    /**
     * 过程：
     * 1.0到0范围上有序
     * 2.0到1范围上有序 如果arr[1] > arr[0] 往前浮动
     * 3.0到2范围上有序。
     */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int cur = i;
            while (cur > 0 && arr[cur] <= arr[cur - 1]) {
                swap(arr, cur, cur - 1);
                cur--;
            }
        }
    }

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int cur = i;
            while (cur > 0 && arr[cur] <= arr[cur - 1]) {
                swap(arr,cur,cur-1);
                cur--;
            }
        }
    }

    public static void insertSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 8, 6, 5, 2, 1, 9};
        sort(arr);
        for (int i : arr) {
            System.out.print(" " + i);
        }
    }
}
