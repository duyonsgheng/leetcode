package duys_code.day_00_sort;

import java.util.Arrays;

/**
 * @ClassName SortAll
 * @Author Duys
 * @Description
 * @Date 2022/4/11 13:04
 **/
public class SortAll {

    // 1. 选择排序
    // 思路，
    // 从0位置开始，选择一个最小的放到0位置
    // 然后1位置，从后面选择一个最小的放到1位置
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i; j < n; j++) {
                if (arr[j] < arr[min]) {
                    swap(arr, min, j);
                }
            }
        }
    }


    //2.冒泡排序
    // 思路：
    // 每一轮从数据中选择一个最大的放到最后一个有效的位置
    public static void bulleSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int n = arr.length;
        // 从后往前撸
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // 3.插入排序
    // 思路：
    // 先让 0-0范围有序
    // 再让 0-1范围有序
    // 再 0-2范围有序
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int cur = i;
            while (cur > 0 && arr[cur] < arr[cur - 1]) {
                swap(arr, cur, cur - 1);
                cur--;
            }
        }
    }

    // 4.快速排序
    // 思路
    // 选择一个基数，然后按照 < 基数的再左边， = 基数的再中间，大于基数的再右边
    public static void fastSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        processFastSort(arr, 0, arr.length - 1);
    }

    public static void processFastSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] range = netherlandsFlag(arr, l, r);
        processFastSort(arr, l, range[0] - 1);
        processFastSort(arr, range[1] + 1, r);
    }

    // 著名的荷兰国旗问题
    // 把数组分为三个区域
    // < 区域
    // = 区域
    // > 区域
    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, l};
        }
        // 定义小区区域
        int less = l - 1;
        // 定义大于区域
        int more = r;
        int cur = l;
        while (cur < more) {
            // 当前数小于基数，当前数和小于区域下一个交换，当前数跳下一个
            if (arr[cur] < arr[r]) {
                swap(arr, cur++, ++less);
            }
            // 当前数和大于区域前一个交换，大于区域往前扩，当前数不动
            else if (arr[cur] > arr[r]) {
                swap(arr, cur, --more);
            }
            // 等于的话，就直接跳下一个数
            else {
                cur++;
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    // 5.归并排序
    // 思路，
    // 把数组划分位两半，
    // 然后开始遍历两边，谁小就拷贝谁，如果相等，就拷贝左边或者右边，
    // 如果那一边先拷贝完，剩下的就直接放到数据
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        processMergeSort(arr, 0, arr.length - 1);
    }

    public static void processMergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        // 左边有序
        processMergeSort(arr, l, mid);
        // 右边有序
        processMergeSort(arr, mid + 1, r);
        // 合并，就整体有序
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int index = 0;
        int left = l;
        int right = m + 1;
        while (left <= m && right <= r) {
            // 谁小拷贝谁
            help[index++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }
        while (left <= m) {
            help[index++] = arr[left++];
        }
        while (right <= r) {
            help[index++] = arr[right++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }


    public static void main(String[] args) {
        int[] arr = {-64, -50, -17, -41, 48, 24, -13, 6, -7, 26, -28, 22, -35, -14, -15, -47, 0, -1, 48, 19, 31, 55, 65, -234, 190, 34, 99, -30, 12, 9009, 1100, 234, 432, 78};

        int[] arr1 = Arrays.copyOf(arr, arr.length);
        long start1 = System.nanoTime();
        selectSort(arr1);
        System.out.println("选择排序 -》 " + (System.nanoTime() - start1));
        print(arr1);

        int[] arr2 = Arrays.copyOf(arr, arr.length);
        long start2 = System.nanoTime();
        bulleSort(arr2);
        System.out.println("冒泡排序 -》 " + (System.nanoTime() - start2));
        print(arr2);

        int[] arr3 = Arrays.copyOf(arr, arr.length);
        long start3 = System.nanoTime();
        insertSort(arr3);
        System.out.println("插入排序 -》 " + (System.nanoTime() - start3));
        print(arr3);

        int[] arr4 = Arrays.copyOf(arr, arr.length);
        long start4 = System.nanoTime();
        fastSort(arr4);
        System.out.println("快速排序 -》 " + (System.nanoTime() - start4));
        print(arr4);

        int[] arr5 = Arrays.copyOf(arr, arr.length);
        long start5 = System.nanoTime();
        mergeSort(arr5);
        System.out.println("归并排序 -》 " + (System.nanoTime() - start5));
        print(arr5);
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
