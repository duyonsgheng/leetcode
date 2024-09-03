package hope.class91;

/**
 * @author Mr.Du
 * @ClassName Code06_LongestSameZerosOnes
 * @date 2024年09月03日 下午 05:42
 */
// 两个0和1数量相等区间的最大长度
// 给出一个长度为n的01串，现在请你找到两个区间
// 使得这两个区间中，1的个数相等，0的个数也相等
// 这两个区间可以相交，但是不可以完全重叠，即两个区间的左右端点不可以完全一样
// 现在请你找到两个最长的区间，满足以上要求
// 返回区间最大长度
// 来自真实大厂笔试，没有在线测试，对数器验证
public class Code06_LongestSameZerosOnes {
    public static int len(int[] arr) {
        // 两种可能性
        // 最左的1到最右的1 从l~r-1 和 l+1 ~r
        // 最左的0到最右的0 从l~r-1 和 l+1 ~r
        int leftZero = -1;
        int rightZero = -1;

        int leftOne = -1;
        int rightOne = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                leftZero = i;
                break;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                leftOne = i;
                break;
            }
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 0) {
                rightZero = i;
                break;
            }
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                rightOne = i;
                break;
            }
        }
        int p1 = rightZero - leftZero;
        int p2 = rightOne - leftOne;
        return Math.max(p1, p2);
    }
}
