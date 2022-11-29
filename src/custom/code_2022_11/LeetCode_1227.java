package custom.code_2022_11;

/**
 * @ClassName LeetCode_1227
 * @Author Duys
 * @Description
 * @Date 2022/11/28 15:03
 **/
// 1227. 飞机座位分配概率
public class LeetCode_1227 {
    public double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1 : 0.5;
    }
}
