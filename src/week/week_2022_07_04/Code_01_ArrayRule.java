package week.week_2022_07_04;

/**
 * @ClassName Code_01_ArrayRule
 * @Author Duys
 * @Description
 * @Date 2022/7/28 9:02
 **/
// 一个数组如果满足 :
// 升降升降升降... 或者 降升降升...都是满足的
// 给定一个数组，
// 1，看有几种方法能够剔除一个元素，达成上述的要求
// 2，数组天然符合要求返回0
// 3，剔除1个元素达成不了要求，返回-1，
// 比如：
// 给定[3, 4, 5, 3, 7]，返回3
// 移除0元素，4 5 3 7 符合
// 移除1元素，3 5 3 7 符合
// 移除2元素，3 4 3 7 符合
// 再比如：给定[1, 2, 3, 4] 返回-1
// 因为达成不了要求
public class Code_01_ArrayRule {

    // 需要提前对数组进行预处理
    // 需要四个信息
    // 1.从当前位置往左边，能不能形成先降序再升序的序列，一直到最左边
    // 2.从当前位置往左边，能不能形成先升序再降序的序列，一直到最左边
    // 3.从当前位置往右边，能不能形成先降序再升序的序列，一直到最右边
    // 4.从当前位置往右边，能不能形成先升序再降序的序列，一直到最右边
    // 实际优化：
    // 1.如果数组从左往右遍历，那么我们就不需要前两个数组，只需要两个变量往后递推
    // 2.如果数组从右往左遍历，那么我们就不需要后两个数组，只需要两个变量往前递推
    public static int successWays(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int n = arr.length;
        boolean[] rightUp = new boolean[n];
        boolean[] rightDown = new boolean[n];
        // 右边从n-1位置开始，满足
        rightDown[n - 1] = true;
        rightUp[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            // 要想当前位置满足从i开始往右是先升再降，那么需要满足下面两个条件
            rightUp[i] = arr[i] < arr[i + 1] && rightDown[i + 1];
            rightDown[i] = arr[i] > arr[i + 1] && rightUp[i + 1];
        }
        // 如果数组天然满足
        if (rightUp[0] || rightDown[0]) {
            return 0;
        }
        // 删掉0位置的数
        int ans = rightDown[1] || rightUp[1] ? 1 : 0;
        // l = i-1
        // r = i+1
        // 左边从0位置卡开始，满足
        boolean leftUp = true;
        boolean leftDown = true;
        boolean temp = true;
        // 开始删掉当前i位置，看看能不能满足
        for (int i = 1, l = 0, r = 2; i < n - 1; i++, l++, r++) {
            boolean cur = (arr[l] < arr[r] && rightDown[r] && leftUp) || (arr[l] > arr[r] && rightUp[r] && leftDown);
            ans += cur ? 1 : 0;
            temp = leftUp;
            // 当前位置如果从右往左是升，那么需要 arr[l] > arr[i] 并且 在i-1 位置就是l的时候，是降的
            leftUp = arr[l] > arr[i] && leftDown;
            leftDown = arr[l] < arr[i] && leftUp;
        }
        // 最后n-1位置单数算
        ans += leftUp || leftDown ? 1 : 0;
        // 到最后每一个一个满足的，返回-1
        return ans == 0 ? -1 : ans;
    }
}
