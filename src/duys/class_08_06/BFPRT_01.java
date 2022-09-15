package duys.class_08_06;

/**
 * @ClassName BFPRT_01
 * @Author Duys
 * @Description
 * @Date 2021/8/12 11:16
 **/
public class BFPRT_01 {

    /**
     * 在无序数组中求第K小的数
     */
    /**
     * 第一种-快排的改进版
     * 和快排差不多，在数组中随机选择一个数，然后划分三个区域
     * 然后第K小的问题就在这三个区域上做文章。
     * 1.如果k恰好在等于区域，直接返回。
     * 2.如果在等于区域的左边，那么去左边迭代
     * 3.如果在等于区域右边，那么去右边迭代
     * 那么整个算法的时间复杂度O(N)
     */
    // 第K小，那么我们换算成下标的话就是k-1
    public static int getKth2(int[] arr, int k) {
        return process(arr, 0, arr.length - 1, k - 1);
    }

    // 在l到r范围上，如果排完序，在index位置的元素返回
    public static int process(int[] arr, int L, int R, int index) {
        // 只有一个数 L=R=index
        if (L == R) {
            return arr[L];
        }
        // 选择L~R范围上任意一个数来做划分值
        int p = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, p);
        // 正好在等于区域内，找到了
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index > range[1]) {
            return process(arr, range[1] + 1, R, index);
        } else {
            return process(arr, L, range[0] - 1, index);
        }
    }

    // 荷兰过期问题的划分，把数组在l到r范围上划分成三个区域， <p | =p |>p，然后返回等于区域的左边界和右边界
    public static int[] partition(int[] arr, int l, int r, int p) {
        int left = l - 1;
        int right = r + 1;
        int cur = l;
        while (cur < right) {
            // 左边界往右边扩展
            if (arr[cur] < p) {
                swap(arr, ++left, cur++);
            } else if (arr[cur] == p) {
                cur++;
            }
            // 右边界往左扩展，并且当数字和右边界第一个数交换，然后持续去看cur位置的数是满足什么样的需求
            else {
                swap(arr, --right, cur);
            }
        }
        return new int[]{left + 1, right - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * bfprt 算法(算法导论第九章)，同样为了解决 在无序数组中求第K小的数，相比第一种，
     * 随机选择一个数，随机选择这个数可能会存在非常好（O(N)）的情况和非常差(O(N^2))的情况。是概率事件（期望收敛到O(N)）
     * 那么bfprt方法是：
     * 非常讲究的选择这个数：
     * 1.把数按照5个数一组，进行分组
     * 2.把每一个组的数据在组内排序（整体不排序）（O(N)的过程）
     * 3.拿出每一组的中位数（最后一组如果是偶数，拿上中位数），组成一个新得数组
     * 4.根据获取的中为数组，然后求这个中位数数组的中位数
     *
     * 然后判断当前第K小，落在了拿一个区域
     */
}
