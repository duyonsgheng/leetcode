package custom.code_2023_05;

/**
 * @ClassName LeetCode_1702
 * @Author Duys
 * @Description
 * @Date 2023/5/6 14:26
 **/
// 1702. 修改后的最大二进制字符串
public class LeetCode_1702 {
    // 11不需要操作
    // 10 能变成01 变小了，不需要操作
    // 00 变成 10或者01，变成更大的，10
    // 01 1的右边有0，应该先把0往左边浮动，然后变成01，最后把00变成10
    public String maximumBinaryString(String binary) {
        int n = binary.length();
        char[] chars = binary.toCharArray();
        for (int i = 0, j = n - 1; i < j; i++) {
            // 10，11 往下走
            if (chars[i] == '1') {
                continue;
            }
            // 当前是00 变成10
            else if (chars[i + 1] == '0') {
                chars[i] = '1';
            }
            // 01
            else {
                while (i < j && chars[j] != '0') {
                    j--;
                }
                if (i < j) {
                    chars[j] = '1';
                    chars[i] = '1';
                    chars[i + 1] = '0';
                }
            }
        }
        return new String(chars);
    }
}
