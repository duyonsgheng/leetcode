package week.week_2022_06_03;

/**
 * @ClassName Code_04_StarNumber
 * @Author Duys
 * @Description
 * @Date 2022/6/23 17:16
 **/
// 一个字符串s，表示仓库的墙 与 货物，其中'|'表示墙,'*'表示货物。
// 给定一个起始下标start和一个终止下标end，
// 找出子串中 被墙包裹的货物 数量
// 比如
// s = "|**|**|*"
// start = 1, end = 7
// start和end截出的子串是 "**|**|*"
// 被 '|'包裹的 '*' 有两个，所以返回2
// 现在给定一系列的start，startIndices[]，和对应一系列的end ,endIndices[]
// 返回每一对[start,end]的截出来的货物数量
// 数据规模：
// 字符串s长度<=10^5
// startIndices长度 == endIndices长度 <=10^5
public class Code_04_StarNumber {

    // 1.先从左往右找离当前位置最近的 | 在什么位置
    // 2.从右往左找离当前位置最近的 | 在什么位置
    public static int[] number(String s, int[] starts, int[] ends) {
        if (s == null || s.length() <= 0) {
            return null;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] sum = new int[n];
        int pre = -1;
        int num = 0;
        for (int i = 0; i < n; i++) {
            pre = str[i] == '|' ? i : pre;
            num += str[i] == '*' ? 1 : 0;
            sum[i] = num;
            left[i] = pre;
        }
        pre = -1;
        for (int i = n - 1; i >= 0; i--) {
            pre = str[i] == '|' ? i : pre;
            right[i] = pre;
        }
        int m = starts.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = process(starts[i], ends[i], left, right, sum);
        }
        return ans;
    }

    // **|****|****|****|****
    public static int process(int start, int end, int[] left, int[] right, int[] sum) {
        int leftI = right[start];
        int rightI = left[end];
        if (leftI == -1 || rightI == -1 || leftI >= rightI) {
            return 0;
        }
        return leftI == 0 ? sum[rightI] : sum[rightI] - sum[leftI - 1];
    }
}
