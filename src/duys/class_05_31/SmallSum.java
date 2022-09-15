package duys.class_05_31;

/**
 * @ClassName SmallSum
 * @Author Duys
 * @Description 小和问题 ，在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
 * @Date 2021/5/31 14:15
 **/
public class SmallSum {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5};
        System.out.println(smallSum(arr));
    }

    static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    static int process(int[] arr, int l, int r) {
        // 二分到一个位置的时候，那么，没有小和产生
        if (l == r) {
            return 0;
        }
        int mind = l + ((r - l) >> 1);

        //递归
        return process(arr, l, mind) + process(arr, mind + 1, r) + merge(arr, l, r, mind);
    }

    static int merge(int[] arr, int l, int r, int mind) {
        // 辅助数组
        int[] help = new int[r - l + 1];
        int i = 0, p1 = l, p2 = mind + 1;
        // 需要返回值
        int ans = 0;
        // 开始合并，注意这里需要计算小和，相等我们先拷贝右边的，因为相等没有小和产生，需要右边的往后滑动，
        // 因为每一边数组都是有序的，所以右边卡住的下标往后滑动的时候，数会越来越大，方便计算小和
        // [2,1,4,6] [3,4,5,6]
        while (p1 <= mind && p2 <= r) {
            // 在左右两组数组进行合并和比较的过程中，如果左边的数小于了右边的数，那么就有小和产生
            // 解释：如果组左边的数小于了右边的数，因为左右两边都是有序的，那么左边的这个数就小于右边当前位置往后的所有数。
            // 比如上面的数组 开始 左边开始是2 右边开始是3，卡住左边和右边的开始位置，左边的2小于右边3，
            // 这时候，产生小和，小于了右边的开始，意味着小于右边的每一个数，产生多少个小和，那就是右边的数的个数乘以当前左边的数 2
            ans += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            // 合并时候的拷贝，这里如果等于的时候，拷贝右边的
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 处理 p1 或者 p2 越界的情况
        while (p1 <= mind) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        // 赋值
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return ans;
    }
}
