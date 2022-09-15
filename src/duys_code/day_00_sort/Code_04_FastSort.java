package duys_code.day_00_sort;

/**
 * @ClassName Code_04_FastSort
 * @Author Duys
 * @Description 快速排序
 * @Date 2021/11/12 15:22
 **/
public class Code_04_FastSort {
    /**
     * 荷兰国旗问题，整个数组
     * 1：
     * 1.< x 的放左边
     * 2.= x 的放中间
     * 3.> x 的放右边
     * <p>
     * 把小于区域设置成数组的最左边
     * 把大于区域设置成数组的最右边
     * 然后三种情况处理
     * 2：
     * 1.<= x的放左边
     * 2.> x 的放右边
     */
    public static void fastSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // less 位置是有序的。
        int less = partition(arr, l, r);
        process1(arr, l, less - 1);
        process1(arr, less + 1, r);
    }

    // 以arr[r] 为分界
    // <= arr[r] 的放左边
    // > arr[r] 的放右边
    public static int partition(int[] arr, int l, int r) {
        if (l > r) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        // 小于等于区域目前在数组的最左边
        // 遇到小于等于当前数的，当前数和小于区域的下一个数交换，小于区域往右扩展
        int cur = l;
        int less = l - 1;
        while (cur < r) {
            if (arr[cur] <= arr[r]) {
                swap(arr, ++less, cur++);
            } else {
                cur++;
            }
        }
        // 把arr[r] 的值换到最后小于等于区域的下一个
        swap(arr, ++less, r);
        return less;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void fastSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] range = netherlandsFlag(arr, l, r);
        process2(arr, l, range[0] - 1);
        process2(arr, range[1] + 1, r);
    }

    // 荷兰国旗问题
    // 以arr[r] 为例分成三块区域
    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;
        int big = r; // 选择了r位置的数，那么右边界就在r位置上
        int cur = l;
        while (cur < big) {
            // 当前数和小于区域下一个数交换，当前数跳下一个
            if (arr[cur] < arr[r]) {
                swap(arr, cur++, ++less);
            }
            // 等于区域的直接跳下一个
            else if (arr[cur] == arr[r]) {
                cur++;
            }
            // 大于区域的，只交换，大于区域左扩，但是当前数位置不变，因为需要重复看调过来的数是哪一种
            else {
                swap(arr, --big, cur);
            }
        }
        // 最后把r位置的数换到big位置，因为big位置数一定是大于r的
        swap(arr, big, r);
        // 那么整个等于区域就是 [less+1 ... big]
        return new int[]{less + 1, big};
    }

    public static void main(String[] args) {
        int[] arr = {2,0,2,1,1,0};
        fastSort2(arr);
        for (int i : arr) {
            System.out.print(" " + i);
        }
    }
}
