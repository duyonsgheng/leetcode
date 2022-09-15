package duys.class_08_06;

/**
 * @ClassName FibonacciQuestion_02
 * @Author Duys
 * @Description
 * @Date 2021/8/6 16:08
 **/
public class FibonacciQuestion_02 {
    /**
     * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
     *
     * 如果某个字符串,任何0字符的左边都有1紧挨着,认为这个字符串达标
     *
     * 返回有多少达标的字符串
     */
    /**
     * 尝试：例如N = 5
     * 那么第一个位置是0的就全部不满足，所以第一个位置是1 然后调用 F(n-1)
     * 那么第二位置如果是0，那么第三个位置是1，就调用F(n-2)
     * 推出F(N) = F(N-1)+F(N-2)的一个斐波那契额数列问题。
     * 那么
     */
}
