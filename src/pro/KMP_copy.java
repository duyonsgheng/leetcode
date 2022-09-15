package pro;

/**
 * @ClassName KMP_COPY
 * @Author Duys
 * @Description
 * @Date 2022/5/26 16:01
 **/
public class KMP_copy {

    // kmp两个步骤
    // 第一个先拿到我们的next数组，
    // 第二 拿到了我们的next数组的时候开始往下搞

    public static int indexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s2.length() > s1.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = nextArray(str2);
        // 准备两个变量 x y 分别代表在s1和s2中画圈的位置
        int x = 0;
        int y = 0;
        // 开始来搞
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) { // 相等就继续往下
                x++;
                y++;
            }
            // 不相等了，且s2中到了没有前缀的位置了，说明当前x搞不定，该去下一个x继续
            else if (y == -1) {
                x++;
            } else {
                // 去前缀相同的地方往下推一步，继续和我们的x位置比较
                y = next[y];
            }
        }
        // 从上面的while出来了，两种情况。
        // y越界了，说明找到了
        // x 越界了，说明没找到
        return y == str2.length ? x - y : -1;
    }

    // 求nextArr 有讲究
    // 人为规定 next[0] = -1 next[1] =0
    public static int[] nextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        //
        int index = 2;
        int cur = next[index - 2];
        while (index < str2.length) {
            //来到i位置 ，我们看的是i-1位置的信息，看看i-1位置的前缀的下一个是不是和i-1字符相等
            if (str2[index - 1] == str2[cur]) {
                next[index++] = ++cur;// 下一次该去下一个位置了
            }
            // 如果不相等，并且cur还可以往前，继续去
            else if (cur > 0) {
                cur = next[cur];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }
}
