package duys.class_05_31;

/**
 * @ClassName ReversePair
 * @Author Duys
 * @Description 求一个数组中的逆序对个数
 * @Date 2021/5/31 14:49
 **/
public class ReversePair {
    static int reverPair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, r, mid);
    }

    // 逆序对，就是左边大于右边
    static int merge(int[] arr, int l, int r, int m) {

        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int p1 = m;
        int p2 = r;
        int ans = 0;
        // 左边的时候，从m开始的了，那么右边就不能到m
        // [3,4,7,8] [2,5,8,10]
        // 道理和小和问题同理，只是这里从每一组的右边算到左边，如果右边组的当前位置的数，小于了左边位置当前位置的数了，
        // 那么右边位置剩下的数都是小于左边的，还是那句话，左右两个组 都是有序的了
        while (p1 >= l && p2 > m) {
            ans += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return ans;
    }
}
