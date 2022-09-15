package custom.code_2022_08;

/**
 * @ClassName LeetCode_443
 * @Author Duys
 * @Description
 * @Date 2022/8/11 14:49
 **/
// 443. 压缩字符串
public class LeetCode_443 {
    public int compress(char[] chars) {
        int n = chars.length;
        int i = 0, j = 0;
        while (i < n) {
            int curI = i;
            // 找连续相同
            while (curI < n && chars[curI] == chars[i]) curI++;
            int count = curI - i;// 有几个
            chars[j++] = chars[i];// 记录新得返回
            if (count > 1) {
                int start = j;
                int end = start;
                while (count != 0) {
                    chars[end++] = (char) ((count % 10) + '0');
                    count /= 10;
                }
                // 需要吧数字逆序
                reverse(chars, start, end - 1);
                j = end;
            }
            i = curI;
        }
        return j;
    }

    public void reverse(char[] str, int s, int e) {
        while (s < e) {
            char tmp = str[s];
            str[s] = str[e];
            str[e] = tmp;
            s++;
            e--;
        }
    }
}
