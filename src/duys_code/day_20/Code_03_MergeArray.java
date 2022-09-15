package duys_code.day_20;

/**
 * @ClassName Code_03_MergeArray
 * @Author Duys
 * @Description
 * @Date 2021/11/8 18:01
 **/
public class Code_03_MergeArray {
    /**
     * 题意：
     * 完美洗牌问题
     * 给定一个长度为偶数的数组arr，假设长度为N*2
     * 左部分：arr[L1……Ln]                 右部分： arr[R1……Rn]
     * 请把arr调整成arr[L1,R1,L2,R2,L3,R3,…,Ln,Rn]
     * 要求：时间复杂度O(N)，额外空间复杂度O(1)
     */

    /**
     * 例如 数组 ： [1,2,3,4,5,6,7,8]
     * 左边是 1 2 3 4
     * 右边是 5 6 7 8
     * 那么合并后 1该去2的位置， 2该去4的位置 ，3该去6的位置，4该去8的位置，5该去2的位置，6该去3的位置，7该去5的位置，8该去4的位置
     * 左边位置换算公式： i*2
     * 右边位置换算公司： (i-(length/2))*2 -1
     * <p>
     * 结论：当我们得数组长度是 3^(k)-1 的时候，我们整个数组可以通过下标计算，完成每一轮三个位置的数交换（而且3^(k-1)这些数可以作为每一轮的头部，每一轮循环的头），并且满足需求
     * 当我们的数组长度不是 3^(k)-1 的时候，找最近的一个。比当前长度小的，然后进行每一轮三个位置的交换，然后剩下的位置继续重复，直到全部交换结束
     */


    // 主函数。
    // 数组不能为空，并且长度必须是偶数，因为必须是左右两半个数一样多才能划分
    public static void shuffle(int[] arr) {
        if (arr == null || arr.length < 2 || (arr.length & 1) != 0) {
            return;
        }
        shuffle(arr, 0, arr.length - 1);
    }

    public static void shuffle(int[] arr, int L, int R) {
        while (R - L + 1 > 0) {// 切成一块一块的解决，每一块的长度是 3^(k) -1
            int len = R - L + 1;
            int base = 3;
            int k = 1;
            // 计算小于等于len，并且离 3^(k) -1 最近的
            // 找到这个 k 。后面我们需要根据k来找基准。就是每一轮循环下标怼的基数
            while (base <= (len + 1) / 3) {
                base *= 3;
                k++;
            }
            // 每一次处理的长度是 base -1
            int half = (base - 1) / 2;
            // 中点
            int mid = (R + L) / 2;
            // 要进行旋转的部分 左边是 l+half ... mid ，右边是 mid+1 ... mid+half
            rotate(arr, L + half, mid, mid + half);
            // 上一步旋转后，紧接着就进行调整了
            cycles(arr, L, base - 1, k);
            // 解决了base-1，剩下的部分继续
            L = L + base - 1;
        }
    }

    // 从start开始，往右的长度是len，做下标连续递推
    // 每一轮出发的位置依次是3的K次方，也就是 1 3 9 .... 这些位置的数开头
    public static void cycles(int[] arr, int start, int len, int k) {
        // 每一轮出发的位置是trigger，一共k个
        // 对于每一个trigger都进行下标连续递推
        // 出发位置是从1开始的，而数组的下标是从1开始的
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            // 之前所在的位置
            int pre = arr[trigger + start - 1];
            // 该去的位置
            int cur = modifyIndex2(trigger, len);
            while (trigger != cur) {
                int tmp = arr[cur + start - 1];
                arr[cur + start - 1] = pre;
                pre = tmp;
                cur = modifyIndex2(cur, len);
            }
            arr[cur + start - 1] = pre;
        }
    }

    // l...m 为左部分，m+1...r是右部分，左右互换
    public static void rotate(int[] arr, int l, int m, int r) {
        reverse(arr, l, m);
        reverse(arr, m + 1, r);
        reverse(arr, l, r);
    }

    // l .. r逆序的调整
    public static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int tmp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = tmp;
        }
    }

    // 给一个当前需要调整的位置，给一个整体数组长度
    // 返回经过调整后i该去的位置
    public static int modifyIndex1(int i, int len) {
        // 如果i再 整体的左半边
        if (i <= len / 2) {
            return i * 2;
        } else {
            return (i - (len / 2)) * 2 - 1;
        }
    }

    // modifyIndex1 就是一个剃刀函数，有一个优化的
    public static int modifyIndex2(int i, int len) {
        return (2 * i) % (len + 1);
    }

}
