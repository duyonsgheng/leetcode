package duys_code.day_00_sort;

/**
 * @ClassName Code_02_BulleSort
 * @Author Duys
 * @Description 冒泡排序
 * @Date 2021/11/12 14:28
 **/
public class Code_02_BubbleSort {
    /**
     * 冒泡：// 非常稳定：哪怕都是有序的，依然需要比较。只是不交换
     * 每一次两个位置比较把大的数浮动都最后
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 每一轮把最大数放在最后一位
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {-64, -50, -17, -41, 48, 24, -13, 6, -7, 26, -28, 22, -35, -14, -15, -47, 0, -1, 48, 19, 31, 55, 65};
        bubbleSort(arr);
        for (int i : arr) {
            System.out.print(" " + i);
        }
    }

}
