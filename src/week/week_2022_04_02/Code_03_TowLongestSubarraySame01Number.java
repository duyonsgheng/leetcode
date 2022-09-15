package week.week_2022_04_02;

/**
 * @ClassName Code_08_TowLongestSubarraySame01Number
 * @Author Duys
 * @Description
 * @Date 2022/4/7 17:30
 **/


// 来自百度
// 给出一个长度为n的01串，现在请你找到两个区间，
// 使得这两个区间中，0和1的个数完全相等
// 这两个区间可以相交，但是不可以完全重叠，即两个区间的左右端点不可以完全一样
// 现在请你找到两个最长的区问，满足以上要求。
public class Code_03_TowLongestSubarraySame01Number {


    public static int longest(int[] arr) {
        int leftZero = -1;
        int leftOne = -1;
        int rightZero = -1;
        int rightOne = -1;
        // 分别统计
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                leftZero = i;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                leftOne = i;
                break;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 0) {
                rightZero = i;
                break;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                rightOne = i;
                break;
            }
        }
        int p1 = rightOne - leftOne;
        int p2 = rightZero - leftZero;
        return Math.max(p1, p2);
    }
}
