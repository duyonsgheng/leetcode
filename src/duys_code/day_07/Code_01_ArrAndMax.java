package duys_code.day_07;

/**
 * @ClassName Code_01_ArrAndMax
 * @Author Duys
 * @Description
 * @Date 2021/9/27 16:28
 **/
public class Code_01_ArrAndMax {
    /**
     * 给定一个非负数组成的数组，长度一定大于1
     * 想知道数组中哪两个数&的结果最大
     * 返回这个最大结果
     * <p>
     * 时间复杂度O(N)，额外空间复杂度O(1)
     */

    // 暴力解答
    public static int arrAndMax(int[] arr) {
        return 0;
    }

    /**
     * 非负的：相与：每一个位置能不能变成1，
     * 遍历一边：查询出第30位置是1的有几个
     * 1.小于2个，继续往低的bit位看
     * 2.等于2个，直接返回这两个数相与的结果
     * 3.大于2个，先淘汰掉哪些第30为不为1的，然后进行下一轮第29位上的数是1的
     */
    public static int arrAndMax2(int[] arr) {

        // M 指针表示垃圾区的左边界
        int M = arr.length;
        int ans = 0;
        // 都是正数
        for (int mov = 30; mov >= 0; mov--) {
            int tmp = M;
            int i = 0;
            while (i < M) {
                // 检测每一个数的 bit位置是否是1，如果不是1，那么当前数和垃圾区的前一个数交换，然后看看刚刚交换到i位置的来的数
                if ((arr[i] & (1 << mov)) == 0) {
                    swap(arr, i, --M);
                }
                // 如果当前数的当前bit位置是1，准备看下一个数
                else {
                    i++;
                }
            }
            // 如果现在M=2了表示非垃圾区0 , 1两个数
            if (M == 2) {
                return arr[0] & arr[1];
            }
            // 如果都小于了两个了，说明还需要看29 28 ...等等bit位上是不是1的数有几多个
            if (M < 2) {
                M = tmp;
            }
            // 当前bit位置为1的数有多个，那么答案的当前bit位置肯定为1，然后在看下一个低的bit位置，是否满足情况，
            // 如果每一位上为1的多个，或者没有，那么ans最后记录了每一个bit位的情况，不会错过答案
            else {
                ans |= (1 << mov);
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
