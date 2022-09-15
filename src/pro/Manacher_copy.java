package pro;

/**
 * @ClassName Manacher_copy
 * @Author Duys
 * @Description
 * @Date 2022/5/26 16:23
 **/
public class Manacher_copy {

    // 回文
    // 1.回文半径
    // 2.回文半径数组
    // 3.取得当前回文半径的中心点P
    // 4.当前半径的下一个位置R(就是我扩不到的位置)

    public static int manacher(String s) {
        if (s == null || s.length() < 1) {
            return -1;
        }
        char[] manacherStr = manacherString(s);
        int[] pArr = new int[manacherStr.length];
        int R = -1;
        int C = -1;
        int ans = Integer.MIN_VALUE;
        // 求处理串的每一个位置的回文半径
        for (int i = 0; i < manacherStr.length; i++) {
            // 当 i在R的内部（被R罩住），当前i的最小半径是i到R的距离和i相对C的对称位置的回文半径
            // 如果没没有被罩住，最小都是1
            pArr[i] = R > i ? (Math.min(pArr[2 * C - i], R - i)) : 1;
            // 看看能不能推高
            while (i + pArr[i] < manacherStr.length && i - pArr[i] > -1
                    // 我右边的下一个位置和左边的前一个位置是不是相等的，是就推高
                    && manacherStr[i + pArr[i]] == manacherStr[i - pArr[i]]) {
                pArr[i]++;
            }
            // 我当前位置半径推高了
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            // 回文半径是不是被推高了
            ans = Math.max(ans, pArr[i]);
        }
        // 这里有一个下标换算，因为处理串的长度本来就是原来字符串的长度*2+1，所以这里直接返回处理串中的半径-1即可
        return ans - 1;
    }

    // 获取当前字符串的manacher串
    public static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        // 处理串
        char[] manacherStr = new char[str.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < manacherStr.length; i++) {
            // 新串的奇数位置全部是 #
            manacherStr[i] = (i & 1) == 0 ? '#' : str[index++];
        }
        return manacherStr;
    }
}
