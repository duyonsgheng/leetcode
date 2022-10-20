package week.week_2022_10_003;

/**
 * @ClassName Code_02_LeetCode_927
 * @Author Duys
 * @Description
 * @Date 2022/10/20 10:34
 **/
// 927. 三等分
public class Code_02_LeetCode_927 {

    // 思路：
    // 1.先统计整个数组1的数量是否是3的整数倍
    // 2.然后1的数量除以3 = d，第一段开头的1从1开始 第二段开头的1从 1+d开始 第三段开头的1从2*d +1 开始
    // 3.以第三段开头的1开始到最后，其他的也同步走，开始校验，直到第三段全部校验完，看看3段是否一路都相等

    public int[] threeEqualParts(int[] arr) {
        // 1.统计1的数量
        int ones = 0;
        for (int num : arr) {
            ones += num == 1 ? 1 : 0;
        }
        if (ones % 3 != 0) {
            return new int[]{-1, -1};
        }
        if (ones == 0) {
            return new int[]{-1, -1};
        }
        int n = arr.length;
        int d = ones / 3;
        // 找出每一段开始的1
        int s1 = -1;
        int s2 = -1;
        int s3 = -1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                cnt++;
                if (s1 == -1 && cnt == 1) {
                    s1 = i;
                }
                if (s2 == -1 && cnt == d + 1) {
                    s2 = i;
                }
                if (s3 == -1 && cnt == 2 * d + 1) {
                    s3 = i;
                }
            }
        }
        // 找到了三段的开始位置，然后以最后一段到末尾位置开始校验
        // 这里使用第三段为基准，因为对于二进制来说，前面有几个0不影响，但是后面的0为影响
        while (s3 < n) {
            if (arr[s1] != arr[s2] || arr[s2] != arr[s3]) {
                // 一定搞不定了
                return new int[]{-1, -1};
            }
            s1++;
            s2++;
            s3++;
        }
        // 如果通过了验证
        return new int[]{s1 - 1, s2};
    }
}
