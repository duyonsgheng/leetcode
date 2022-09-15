package duys.class_09_13;

/**
 * @ClassName Restore
 * @Author Duys
 * @Description
 * @Date 2021/9/13 15:02
 **/
public class Restore {
    /**
     * 整型数组arr长度为n(3 <= n <= 10^4)，最初每个数字是1~200的正数且满足如下条件：
     * 1. arr[0] <= arr[1]
     * 2. arr[n-1] <= arr[n-2]
     * 3. arr[i] <= max(arr[i-1], arr[i+1])
     * 但是在arr有些数字丢失了，比如k位置的数字之前是正数，
     * 丢失之后k位置的数字为0。
     * 请你根据上述条件， 计算可能有多少种不同的arr可以满足以上条件。
     * 比如 [6,0,9] 只有还原成 [6,9,9]满足全部三个条件，所以返回1种。
     */
    // 现在给你一个数组，可能里面的数不全，请问补全整个数组有多少种方法
    public static int rest(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int N = arr.length;
        int ans = 0;
        if (arr[N - 1] == 0) {
            for (int i = 1; i <= 200; i++) {
                ans += process(arr, N - 1, i, 2);
            }
        } else {
            ans = process(arr, N - 1, arr[N - 1], 2);
        }
        return ans;
    }

    // i - 在哪一个范围上变的 [0~i]范围
    // v - 你想把i位置的数变成v
    // s - 只有0 , 1, 2三个，
    //  分别是 0-表示 之前在i+1做的决定的值是>v , i+1 位置大
    // 1 - 表示 = v , 相等
    // 2- 表示 < v ，s默认是右边比当前数小所以默认是2 i+1位置小
    // i位置是由i+1位置来决定的
    public static int process(int[] arr, int i, int v, int s) {
        // 表示已经在0位置了，要把i位置的数变成v，那么1位置做的决定是啥
        if (i == 0) {
            // 1位置如果是 >= v的，如果0位置的数是0或者0位置的数不是0但是等于v，那么有一种方法
            return (s == 0 || s == 1) && (arr[0] == 0 || arr[0] == v) ? 1 : 0;
        }
        // i>0
        if (arr[i] != 0 && arr[i] != v) {
            return 0;
        }
        int ways = 0;
        // i>0，并且 i位置的数真的可以变成V，就是i+1位置的数是大于 或者等于 v的，那么i位置的数 1~200 枚举去吧
        if (s == 0 || s == 1) {
            for (int m = 1; m < 201; m++) {
                // m相对于i-1位置来说，m是右边
                ways += process(arr, i - 1, m, m < v ? 0 : m == v ? 1 : 2);
            }
        }
        // 如果 i+1的位置比v都要小了，那么i位置的就不能是比v还要小的，否则不满足要求
        else {
            for (int m = v; m < 201; m++) {
                ways += process(arr, i - 1, m, m == v ? 1 : 2);
            }
        }
        return ways;
    }
}
