package duys_code.day_41;

/**
 * @ClassName Code_01_MinSwap
 * @Author Duys
 * @Description
 * @Date 2021/12/29 15:29
 **/
public class Code_01_MinSwap {
    // 来自小红书
    // 一个无序数组长度为n，所有数字都不一样，并且值都在[0...n-1]范围上
    // 返回让这个无序数组变成有序数组的最小交换次数

    // 下标循环怼。因为已经直到了所有的数都不一样。且都是再 0到n-1范围内的数

    public static int minSwap(int[] arr) {
        // 从每一个位置出发看看。当前数是不是再应该再的位置上
        // 0再0位置上，1再1位置上
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            // 位置上就是对应的数
            while (i != arr[i]) {
                // 开始怼
                swap(arr, i, arr[i]);
                ans++;
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
