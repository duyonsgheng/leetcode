package duys_code.day_00_sort;

/**
 * @ClassName Code_01_SelectSort
 * @Author Duys
 * @Description 选择排序
 * @Date 2021/11/12 14:27
 **/
public class Code_01_SelectSort {

    /**
     * 过程：
     * 1.首先在0~n-1范围上选择出最小的，把他放到0位置，
     * 2.然后在1到n-1范围上重复这个过程，把最小值放到1位置
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 比如 4 3 5 8 6 2 1 9
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            // 在剩下的位置中去找我们的最小的
            // 每一轮都去找到最小的
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 8, 6, 2, 1, 9};
        selectSort1(arr);
        for (int i : arr) {
            System.out.print(" " + i);
        }
    }

    public static void selectSort1(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            int next = i + 1;
            // 在剩下的位置中找到一个比当前更小的
            for (; next < n; next++) {
                if (arr[next] < arr[minIndex]) {
                    minIndex = next;
                }
            }
            swap(arr, minIndex, i);
        }
    }
}
