package duys_code.day_01;

/**
 * @ClassName Num_Code_03
 * @Author Duys
 * @Description
 * @Date 2021/9/13 17:35
 **/
public class Code_03_Num {
    /**
     * 给定一个非负整数num，
     * 如何不用循环语句，
     * 返回>=num，并且离num最近的，2的某次方
     */
    public static int process(int num) {
        // 兼容num是2的次方和不是2的次方
        int n = num - 1;
        // 下面这些位移运算，就是把num最高位为1的后面所有位置全部变成1
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        // 后面全部是1，然后+1就变成了2的次方
        return (n < 0) ? 1 : n + 1;
    }

    public static void main(String[] args) {
        System.out.println(process(111));
    }
}
