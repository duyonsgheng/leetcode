package week.week_2022_10_01;

import java.util.Arrays;

/**
 * @ClassName Code_03_MakeASortedMinSwaps
 * @Author Duys
 * @Description
 * @Date 2022/10/8 13:50
 **/
// 商场中有一展柜A，其大小固定，现已被不同的商品摆满
// 商家提供了一些新商品B，需要对A中的部分商品进行更新替换
// B中的商品可以自由使用，也就是可以用B中的任何商品替换A中的任何商品
// A中的商品一旦被替换，就认为消失了！而不是回到了B中！
// 要求更新过后的展柜中，商品严格按照价格由低到高进行排列
// 不能有相邻商品价格相等的情况
// A[i]为展柜中第i个位置商品的价格，B[i]为各个新商品的价格
// 求能够满足A中商品价格严格递增的最小操作次数，若无法满足则返回-1
public class Code_03_MakeASortedMinSwaps {
    // 分析，要求A中商品价格升序，而且从b里面选，
    // 整个流程其实都是有序的操作，把B排序，然后遍历A，如果选了B中的记录，二分查找，并且记录下B中的位置，因为要求A中升序，而且B中也是有序的，所以记录一下B中选择了的数的位置
    public static int minSwaps(int[] A, int[] B) {

        //1.对B排序
        Arrays.sort(B);
        int ans = process(A, B, 0, 0, 0);
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }

    // A中0~ai范围上已经升序了
    // B中0~bi-1上的数字不能考虑了，bi-1是上一次拿 的数字，后面请在bi后面选择
    // pre 表示A[ai-1]是不是被替换了
    // 0表示没有被替换  1 表示被替换了，被B[bi-1]位置替换了
    public static int process(int[] A, int[] B, int ai, int bi, int pre) {
        if (ai == A.length) {
            return 0;
        }
        int preNum = 0;
        if (ai == 0) {
            preNum = Integer.MIN_VALUE;
        } else {
            // 没有被替换就是A中的ai-1的数
            if (pre == 0) {
                preNum = A[ai - 1];
            } else {
                // 被替换了，就是B中bi-1的数
                preNum = B[bi - 1];
            }
        }
        // 不需要替换
        int p1 = preNum < A[ai] ? process(A, B, ai + 1, bi, 0) : Integer.MAX_VALUE;
        // 使用替换
        int p2 = Integer.MAX_VALUE;
        // 再B中bi到最后中找到 > preNum的最左位置
        int findI = find(B, bi, preNum);
        if (findI != -1) {
            // 被替换了一次
            int next = process(A, B, ai + 1, findI + 1, 1);
            if (next != Integer.MIN_VALUE) {
                p2 = next + 1;
            }
        }
        return Math.min(p1, p2);
    }

    public static int find(int[] arr, int index, int num) {
        int ans = -1;
        int l = index;
        int r = arr.length - 1;
        int m = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] > num) {
                r = m - 1;
                ans = m;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
