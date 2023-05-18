package week.week_2023_04_04;

/**
 * @ClassName Code_05_ValidRangeBetweenTwoArrays
 * @Author Duys
 * @Description
 * @Date 2023/4/27 14:22
 **/
// 来自微众银行
// 给出两个长度均为n的数组
// A = { a1, a2, ... ,an }
// B = { b1, b2, ... ,bn }。
// 你需要求出其有多少个区间[L,R]满足:
// 数组A中下标在[L,R]中的元素之和在[La,Ra]之中
// 数组B中下标在[L,R]中的元素之和在[Lb,Rb]之中
// 输入
// 第一行有一个正整数N(1<=N<=100000)，代表两个数组的长度。
// 第二行有N个非负整数，范围在0到1000000000之间，代表数组中的元素。
// 第三行有N个非负整数，范围在0到1000000000之间，代表数组中的元素。
// 第四行有4个整数La,Ra,Lb,Rb，范围在0到10^18之间，代表题目描述中的参数。
// 输出
// 输出一个整数，代表所求的答案。
public class Code_05_ValidRangeBetweenTwoArrays {
    // 窗口
    // A中来一个窗口 a1位置是窗口内的和刚刚 满足小于 La a2位置是窗口内的和刚刚满足小于等于Ra
    // B中来一个窗口，b1位置是窗口内的和刚刚 满足小于 Lb b2位置是窗口内的和刚刚满足小于等于Rb
    // 那么满足要求的就是 i位置是两个窗口的共同起始位置，到 max(a1,b1)和 min(a2,b2)这之间的数都满足
    public static int ways(int[] A, int[] B, int la, int ra, int lb, int rb) {
        int n = A.length;
        int ans = 0;
        int rightA1 = 0, sumA1 = 0, rightA2 = 0, sumA2 = 0, rightB1 = 0, sumB1 = 0, rightB2 = 0, sumB2 = 0;
        for (int l = 0; l < n; l++) {
            while (rightA1 < n && sumA1 + A[rightA1] < la) {
                sumA1 += A[rightA1++];
            }
            while (rightA2 < n && sumA2 + A[rightA2] <= ra) {
                sumA2 += A[rightA2++];
            }

            while (rightB1 < n && sumB1 + B[rightB1] < lb) {
                sumB1 += B[rightB1++];
            }
            while (rightB2 < n && sumB2 + B[rightB2] <= rb) {
                sumB2 += B[rightB2++];
            }
            int left = Math.max(rightA1, rightB1);
            int right = Math.min(rightA2, rightB2);
            if (left < right) {
                ans += right - left;
            }
            // 窗口即将向前推进
            if (rightA1 == l) {
                rightA1++;
            } else {
                sumA1 -= A[l];
            }
            sumA2 -= A[l];
            if (rightB1 == l) {
                rightB1++;
            } else sumB1 -= B[l];
            sumB2 -= B[l];
        }
        return ans;
    }
}
