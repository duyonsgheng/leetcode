package duys_code.day_39;

/**
 * @ClassName Code_02_Valid
 * @Author Duys
 * @Description
 * @Date 2021/12/21 10:06
 **/
public class Code_02_Valid {
    // 来自腾讯
    // 给定一个长度为n的数组arr，求有多少个子数组满足 :
    // 子数组两端的值，是这个子数组的最小值和次小值，最小值和次小值谁在最左和最右无所谓
    // n<=100000（10^5） n*logn  O(N)

    // 可见山峰对问题的一个扩展
    // 使用单调栈，大的数压小的数，当数值一样的时候，压在一起，记录个数，
    // 当不满足小压大的时候 结算答案
    public static int nums(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int n = arr.length;
        // 代替了栈，一个是值，一个是出现的次数
        int[] values = new int[n];
        int[] times = new int[n];
        int size = 0;
        int ans = 0;
        // 先从左往右算一遍
        for (int i = 0; i < n; i++) {
            // 如果当前的数不大于栈顶的元素，开始弹出
            while (size != 0 && values[size - 1] > arr[i]) {
                size--;
                // 当前值比栈顶小，需要弹出栈顶，那么有多少个，就和我们当前数形成的子数组是达标的，但是在我们大的数内部也是有效的
                ans += times[size] + cn2(times[size]);
            }
            // 该弹出的都弹出了，该往里面放入了
            if (size != 0 && values[size - 1] == arr[i]) {
                times[size - 1]++;
            } else {
                values[size] = arr[i];
                times[size++] = 1;
            }
        }
        // 在这里如果栈里还剩下的元素，需要结算
        // 栈里剩下的元素，外部有影响的已经全部结算了、
        // 只需要结算内部的
        while (size != 0) {
            ans += cn2(times[--size]);
        }

        // 从右往左算依次，这一次只算弹出的，不算内部的
        for (int i = n - 1; i >= 0; i--) {
            // 如果当前的数不大于栈顶的元素，开始弹出
            while (size != 0 && values[size - 1] > arr[i]) {
                // 当前值比栈顶小，需要弹出栈顶，那么有多少个，就和我们当前数形成的子数组是达标的，但是在我们大的数内部也是有效的
                ans += times[--size];
            }
            // 该弹出的都弹出了，该往里面放入了
            if (size != 0 && values[size - 1] == arr[i]) {
                times[size - 1]++;
            } else {
                values[size] = arr[i];
                times[size++] = 1;
            }
        }
        return ans;
    }

    public static int cn2(int n) {
        return (n * (n - 1)) >> 1;
    }

    // 我们可以使用Node { int num,int times} 两个变量来封装栈中的元素
}
