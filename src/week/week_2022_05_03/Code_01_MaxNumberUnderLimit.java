package week.week_2022_05_03;

import java.util.Arrays;

/**
 * @ClassName Cod_e02_MaxNumberUnderLimit
 * @Author Duys
 * @Description
 * @Date 2022/5/16 11:15
 **/
// 来自字节
// 输入:
// 去重数组arr，里面的数只包含0~9
// limit，一个数字
// 返回:
// 要求比limit小的情况下，能够用arr拼出来的最大数字
public class Code_01_MaxNumberUnderLimit {

    // 完全考递归设计和逻辑是否清晰
    // 整体流程是啥样子的，

    /**
     * 1.先把数组排序，得到一个有序的
     * 2.然后根据limit从前到后每一位进行选择，然后往下传递，如果上层收到的信息是-1，代表当前选择有问题，需要往下降低一个数继续去递归
     */
    public static int maxNumber(int[] arr, int limit) {
        //
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        // 只能小于limit那么
        limit--;
        Arrays.sort(arr);
        int offset = 1;
        while (offset <= limit / 10) {
            offset *= 10;
        }
        int ans = process(arr, limit, offset);
        if (ans == -1) { // 以当前的offset去没搞定，那么我就降低一位，后续全部选择arr中最大的填充
            offset /= 10;// 减少一位
            int rest = 0;
            while (offset > 0) {
                rest += arr[arr.length - 1] * offset;
                offset /= 10;
            }
            return rest;
        } else {
            return ans;
        }
    }

    // offset ： 表示当前来到了哪一位选择了，比如
    // limit = 6886 offset = 1000，意思就是来到最高位做选择
    public static int process(int[] arr, int limit, int offset) {
        if (offset == 0) { // 如果我们一路下来都选择有效的数字导致来到最后一位上都能搞定，那么就可以搞定
            return limit;
        }
        // 当前来到这一位上是几
        int cur = (limit / offset) % 10;
        // 在数组中选出 <= cur,离cur最近的位置
        int near = near(arr, cur);
        // 没有比当前位置更小的了，宣告此前选择的有问题，需要上层去重新选择。
        if (near == -1) {
            return -1;
        }
        // 和当前位置一样
        else if (arr[near] == cur) {
            int next = process(arr, limit, offset / 10);
            // 后续有效，直接返回
            if (next != -1) {
                return next;
            }
            // 后续无效，但是我当前位置还有下降空间，选择比当前更小的数，继续去跑后续位置
            else if (near > 0) {
                near--;
                // 那么我前面的就和limit保持一致。中间这一位选near，后面的就去arr中找最大的填充
                return (limit / (offset * 10)) * (offset * 10) + (arr[near] * offset) + rest(arr, offset / 10);
            }
            // 后续无效，当前位置也没有更小的选择，让我的上一级去做选择了
            else {
                return -1;
            }
        }
        // 从这儿开始就没有根cur一样大，那么说明比cur更小了，后续直接返回arr中最大的填充
        else {
            return (limit / (offset * 10)) * (offset * 10) + (arr[near] * offset) + rest(arr, offset / 10);
        }
    }

    // 比如当前位置选择一个比limit相同位置要小的数字
    // 那么剩下的位置全部都是arr中最大的数来填
    public static int rest(int[] arr, int offset) {
        int rest = 0;
        while (offset > 0) {
            rest += arr[arr.length - 1] * offset;
            offset /= 10;
        }
        return rest;
    }

    // <= num ,离num最近的数字是哪一个位置，如果没有返回-1
    public static int near(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] <= num) {
                l = m + 1;
                ans = m;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }
}
