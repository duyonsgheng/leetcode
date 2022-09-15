package duys.class_05_31;

/**
 * @ClassName BiggerRight
 * @Author Duys
 * @Description 数组左边的数比右边数2倍还大，有多少个这样的数
 * @Date 2021/5/31 15:06
 **/
public class BiggerRight {

    // 二话不说，先归并
    static int bigger(int[] arr) {
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

    static int merge(int[] arr, int l, int r, int m) {
        int ans = 0;

        // 右边的
        int w = m + 1;
        // 遍历左边的每一个数
        for (int i = l; i <= m; i++) {
            // 当前的窗口必须在右边的数组上，所以从 m+1位置开始，到r位置结束
            // 还是同样的原理，如果左边的数都大于右边数组开始位置数的2倍了，那么左边的数先卡住，继续右边的数组往后找，直到不满足未知，
            while (w <= r && arr[i] > (arr[w] * 2)) {
                w++;
            }
            // 因为左边也是从左到右，依次增大，所以每遍历左边数组的每一个数都需要进行计算
            ans += w - m - 1;
        }
        // 下面是常规归并和合并
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return ans;

    }
}
