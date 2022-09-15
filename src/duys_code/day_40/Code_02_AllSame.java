package duys_code.day_40;

/**
 * @ClassName Code_02_AllSame
 * @Author Duys
 * @Description
 * @Date 2021/12/22 11:17
 **/
public class Code_02_AllSame {

    // 来自腾讯
    // 比如arr = {3,1,2,4}
    // 下标对应是：0 1 2 3
    // 你最开始选择一个下标进行操作，一旦最开始确定了是哪个下标，以后都只能在这个下标上进行操作
    // 比如你选定1下标，1下标上面的数字是1，你可以选择变化这个数字，比如你让这个数字变成2
    // 那么arr = {3,2,2,4}
    // 下标对应是：0 1 2 3
    // 因为你最开始确定了1这个下标，所以你以后都只能对这个下标进行操作，
    // 但是，和你此时下标上的数字一样的、且位置连成一片的数字，会跟着一起变
    // 比如你选择让此时下标1的数字2变成3，
    // 那么arr = {3,3,3,4} 可以看到下标1和下标2的数字一起变成3，这是规则！一定会一起变
    // 下标对应是：0 1 2 3
    // 接下来，你还是只能对1下标进行操作，那么数字一样的、且位置连成一片的数字(arr[0~2]这个范围)都会一起变
    // 决定变成4
    // 那么arr = {4,4,4,4}
    // 下标对应是：0 1 2 3
    // 至此，所有数都成一样的了，你在下标1上做了3个决定(第一次变成2，第二次变成3，第三次变成4)，
    // 因为联动规则，arr全刷成一种数字了
    // 给定一个数组arr，最开始选择哪个下标，你随意
    // 你的目的是一定要让arr都成为一种数字，注意联动效果会一直生效
    // 返回最小的变化数
    // arr长度 <= 200, arr中的值 <= 10^6

    public static int allSame(int[] arr) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            ans = Math.min(ans, process(arr, i - 1, i, i + 1));
        }
        return ans;
    }

    // left 0.。。left是我的左边区域
    // right 0...right是我的右边区域
    // mid 是中间已经全部变成了 arr[mid] 的区域，
    public static int process(int[] arr, int left, int mid, int right) {
        // 看看我的左边能通过不变化能扩展到最左的位置是哪里
        while (left >= 0 && arr[left] == arr[mid]) {
            left--;
        }
        // 看看我的右边能通过不变化能扩展到最左的位置是哪里
        while (right < arr.length && arr[right] == arr[mid]) {
            right++;
        }
        // 已经到最后了
        if (left == -1 && right == arr.length) {
            return 0;
        }
        int p1 = Integer.MAX_VALUE;
        if (left >= 0) {
            // 变成我的左边，步骤+1
            p1 = process(arr, left - 1, left, right) + 1;
        }
        int p2 = Integer.MAX_VALUE;
        if (right < arr.length) {
            p2 = process(arr, left, right, right++) + 1;
        }
        return Math.min(p1, p2);
    }
}
