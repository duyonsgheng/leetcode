package week.week_2022_09_03;

/**
 * @ClassName Code_05_MaxLengthSameCharMChanges
 * @Author Duys
 * @Description
 * @Date 2022/9/22 10:58
 **/
// 来自字节
// 给定一个只由小写字母组成的字符串str，长度为N
// 给定一个只由0、1组成的数组arr，长度为N
// arr[i] == 0表示str中i位置的字符不许修改
// arr[i] == 1表示str中i位置的字符允许修改
// 给定一个正数m，表示在任意允许修改的位置
// 可以把该位置的字符变成a~z中的任何一个
// 可以修改m次
// 返回在最多修改m次的情况下，全是一种字符的最长子串是多长
// 1 <= N, M <= 10^5
// 所有字符都是小写
public class Code_05_MaxLengthSameCharMChanges {
    // 思路，欠债表的做法，也就是窗口
    // 窗口的右边界遇到 m==0 或者arr[i] == 0 && i位置字符 ！= cur停住，然后开始缩窗口。缩窗口的时候，m需要归还。
    public int maxLen(String str, int[] arr, int m) {
        char[] chars = str.toCharArray();
        int n = str.length();
        int ans = 0;
        for (char aim = 'a'; aim <= 'z'; aim++) {
            // 窗口 [l,r)
            int r = 0;
            int change = 0; // 当前修改了几次
            for (int l = 0; l < n; l++) {
                while (r < n) {
                    if (chars[r] == aim) {
                        r++;
                        continue;
                    }
                    // 不行了吧，扩不动了，
                    if (arr[r] == 0 || change == m) {
                        break;
                    }
                    // 往前扩展
                    change++;
                    r++;
                }
                // 长度抓一下
                ans = Math.max(r - l, ans);
                // 缩窗口，如果l位置 不是aim，并且之前是被改过的
                if (chars[l] != aim && arr[l] != 0) {
                    change--;
                }
                // 为了预防 r位置算错
                r = Math.max(l + 1, r);
            }
        }
        return ans;
    }
}
