package duys_code.day_32;

/**
 * @ClassName Code_08_191_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/number-of-1-bits/
 * @Date 2021/12/6 10:59
 **/
public class Code_08_191_LeetCode {

    public static int hammingWeight(int n) {
      /*  int bits = 0;
        int rightOne = 0;
        while (n != 0) {
            bits++;
            // 每一次把最右侧的1给提取出来
            rightOne = n & (-n);
            n ^= rightOne;
        }
        return bits;*/
        int count = 0;
        for (int i = 0; i < 32; i++) {
            int a = (1 << i);
            if ((n & a) != 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(11));
    }

}
