package duys.class_06_01;

/**
 * @ClassName FastSort_code_01
 * @Author Duys
 * @Description 荷兰国旗问题 给一个数组，然后选定某一个数 x，做到  <x 部分在左边，=x部分在中间，>x部分在右边，
 * 然后返回=区域的边界
 * @Date 2021/6/1 13:58
 **/
public class FastSort_code_01 {

    static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        // 这是小于区域开始位置
        int less = L - 1;
        // 选择的数是R位置上的数，那么大于区域从R-1开始
        int more = R;
        // 开始数的指针
        int index = L;
        while (index < more) {
            // 如果相等，当前数跳下一个
            if (arr[index] == arr[R]) {
                index++;
            }
            // 如果大于了，和大于区域的前一个数交换，大于区域向前走一个位置
            else if (arr[index] > arr[R]) {
                swap(arr, index, --more);
            }
            // 如果小于了，和小于区域的下一个数交换，小于区域向前走一个位置
            else {
                swap(arr, index, ++less);
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    // 快速排序，递归版本
    public static void firstSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 把最后一个和0- n-1 位置上随机交换 ，类似荷兰国旗问题中，选定一个数，把数组分成三部分
        swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        // 把数组分成三部分，并且，返回了等于区域的边界
        int[] temp = netherlandsFlag(arr, l, r);

        // 左边部分，左边界-1
        process(arr, l, temp[0] - 1);
        // 等于部分不用管，因为等于部分相对原数组来说已经有序
        // 右边部分
        process(arr, temp[1] + 1, r);
    }
}
