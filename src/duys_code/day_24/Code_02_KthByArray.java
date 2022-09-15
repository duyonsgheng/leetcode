package duys_code.day_24;

import java.util.Arrays;

/**
 * @ClassName Code_02_KthByArray
 * @Author Duys
 * @Description 题意：
 * @Date 2021/11/12 17:42
 **/
public class Code_02_KthByArray {
    /**
     * 长度为N的数组arr，一定可以组成N^2个数字对
     * 例如arr = [3,1,2]，数字对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)
     * 也就是任意两个数都可以，而且自己和自己也算数字对
     * 数字对怎么排序？
     * 第一维数据从小到大；第一维数据一样的，第二维数组也从小到大
     * 所以上面的数值对排序的结果为：(1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)
     * 给定一个数组arr，和整数k，返回第k小的数值对
     */
    /**
     * k = 55
     * 1. 给数组排序
     * 2. 比如数组10个数，那么每一个数产生多少对10个，总共是100个
     * 3. 那么我们可以明确的算出  第55小的第一位是哪一个数 arr[55-1/10]
     * 4. 算比 arr[55-1/10] 小的有几个 rest
     * 5. 还剩下 k - rest*N  ans
     * 6. 算出arr[55-1/10]有多少个数，然后 ans在所有数中是第几个
     */
    public static int[] kthMinPair2(int[] arr, int k) {
        int n = arr.length;
        if (k > n * n) { // 数字对最多就 n^2个
            return null;
        }
        // 排序
        Arrays.sort(arr);

        // 第k小的数字对，第一维数字是啥,第几小的数是从1开始的，下标是从0开始的
        int firstNum = arr[(k - 1) / n];
        // 数字比firstNum小的有几队
        int lessFirstNumSize = 0;
        // 数字等于firstNum的数有几个
        int firstNumSize = 0;
        for (int i = 0; i < n && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum) {
                lessFirstNumSize++;
            } else {
                firstNumSize++;
            }
        }
        int rest = k - (lessFirstNumSize * n);

        return new int[]{firstNum, arr[(rest - 1) / firstNumSize]};
    }

    // 记得当时快排中怎么找第K小的数吗？ 就是荷兰国旗问题的划分
    public static int[] kthMinPair(int[] arr, int k) {
        int n = arr.length;
        if (k > n * n) { // 数字对最多就 n^2个
            return null;
        }
        // 无序数组中找第K小的数，也就是找第一维数字
        int firstNum = getMinKth(arr, (k - 1) / n);
        int firstNumSize = 0;
        int lessFirstNumSize = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] < firstNum) {
                lessFirstNumSize++;
            }
            if (arr[i] == firstNum) {
                firstNumSize++;
            }
        }
        int rest = k - (lessFirstNumSize * n);
        return new int[]{firstNum, getMinKth(arr, (rest - 1) / firstNumSize)};
    }

    public static int getMinKth(int[] arr, int k) {
        int l = 0;
        int r = arr.length - 1;
        int p = 0;
        int range[] = null;
        while (l < r) {
            // 在l 到 r范围上随机找一个数
            p = arr[l + (int) Math.random() * (r - l + 1)];
            // 每次在l到r范围上都去找
            range = partition(arr, l, r, p);
            if (k < range[0]) {
                r = range[0] - 1;
            } else if (k > range[1]) {
                l = range[1] + 1;
            } else {
                return p;
            }
        }
        return arr[l];
    }

    public static int[] partition(int[] arr, int l, int r, int p) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;

        while (l < more) {
            if (arr[cur] < p) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] == p) {
                cur++;
            } else {
                swap(arr, cur, --more);
            }
        }
        // 小于区域的开头和结束位置
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
