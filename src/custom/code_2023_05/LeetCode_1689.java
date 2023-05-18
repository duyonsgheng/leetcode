package custom.code_2023_05;

/**
 * @ClassName LeetCode_1689
 * @Author Duys
 * @Description
 * @Date 2023/5/5 14:13
 **/
// 1689. 十-二进制数的最少数目
public class LeetCode_1689 {
    public int minPartitions(String n) {
        int max = 0;
        for (char c : n.toCharArray()) {
            if (c - '0' > max) {
                max = c - '0';
            }
        }
        return max;
    }
}
