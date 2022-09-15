package week.week_2022_08_01;

/**
 * @ClassName Code_05_LongestOneLetterManyNumberString
 * @Author Duys
 * @Description
 * @Date 2022/8/8 13:49
 **/
// 给定一个只由小写字母和数字字符组成的字符串str
// 要求子串必须只含有一个小写字母，数字字符数量随意
// 求这样的子串最大长度是多少
public class Code_05_LongestOneLetterManyNumberString {


    // 滑动窗口来做
    public static int manyNumberString(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        // 当前窗口内有几个小写字母了
        int letters = 0;
        // 窗口的右边界，是到不了的位置。[left,right)
        int right = 0;
        int ans = 0;

        // 每一个位置开头都看看
        for (int left = 0; left < n; left++) {
            while (right < n) {
                // 当前窗口左边界是left，右边界看看能扩到那儿
                if (letters == 1 && str[right] >= 'a' && str[right] <= 'z') {
                    break;
                }
                if (str[right] >= 'a' && str[right] <= 'z') {
                    letters++;
                }
                right++;
            }
            if (letters == 1) {
                ans = Math.max(ans, right - left);
            }
            // 当前left的位置即将出窗口，如果当前left位置是本窗口的唯一小写字母，那么需要更新letters
            if (str[left] >= 'a' && str[left] <= 'z') {
                letters--;
            }
        }
        return ans;
    }
}
