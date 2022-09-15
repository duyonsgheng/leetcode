package duys.class_08_06;

/**
 * @ClassName KMP_01
 * @Author Duys
 * @Description
 * @Date 2021/8/6 16:19
 **/
public class KMP_01 {

    /**
     * 方案1：当前位置不算，前缀和后缀字串 ，不能算到整个长度
     * 例如： abc abc m
     * 从m位置看 自己不算 就是3 nextArray
     * 前缀：a ab abc abca abcac
     * 后缀：b cb abc cabc bcabc
     */

    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 在str1里画圈得位置
        int x = 0;
        // 在str2里画圈得位置
        int y = 0;
        int[] next = getNextArray(str2);
        // 配对得过程
        // 如果y越界了，说明从str1中得某一个位置开始，搞出了str2了。
        while (x < str1.length && y < str2.length) {
            // 从前到后进行比对，如果相等就往后走
            if (str1[x] == str2[y]) {
                x++;
                y++;
            }
            // 说明y=0，第一个位置就不等，那么x直接往后，str1的下一个位置继续
            else if (next[y] == -1) {
                x++;
            }
            // 说明什么？说明当前配不上，y可以往左边跳，跳到一个位置，然后进行比对，直到y=0为止。这也就是存在一个加速的原因、。
            else {
                y = next[y];
            }
        }
        // 如果x越界了呢？说明怎么都搞不定。返回-1
        return y == str2.length ? x - y : -1;
    }

    /**
     * 方案1：当前位置不算，前缀和后缀字串 ，不能算到整个长度
     * 例如： abc abc m
     * 从m位置看 自己不算 就是3 nextArray
     * 前缀：a ab abc abca abcac
     * 后缀：b cb abc cabc bcabc
     */
    // 给短的哪一个字符串求一个nextArray，nextArray的值表示，前缀和后缀相等的长度
    public static int[] getNextArray(char[] str2) {
        // 任何字符串，0位置的信息都是-1
        if (str2.length == 1) {
            return new int[]{-1};
        }
        // 表示前缀的长度
        int[] next = new int[str2.length];
        // 人为规定
        next[0] = -1;
        next[1] = 0;
        int index = 2; // 目前在哪个位置上求next数组的值
        int curNext = 0;// 当前是哪个位置的值再和i-1位置的字符比较，初始位置就是在0位置，i在2，那么谁跟1位置的字符比较，肯定是0啊
        while (index < next.length) {
            if (str2[index - 1] == str2[curNext]) {
                // 每次与index-1位置比较的都是下一跳(从curNext往前蹦)的下一个数，所以++
                next[index++] = ++curNext;
            } else if (curNext > 0) {
                curNext = next[curNext];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "aabaab";
        int[] nextArray = getNextArray(str.toCharArray());
        for (int i : nextArray) {
            System.out.print(i + " ");
        }
    }
}
