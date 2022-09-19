package custom.code_2022_09;

/**
 * @ClassName LeetCode_754
 * @Author Duys
 * @Description
 * @Date 2022/9/19 9:40
 **/
// 754. 到达终点数字
public class LeetCode_754 {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0) {
            target -= ++k;
        }
        return target % 2 == 0 ? k : k + 1 + k % 2;
    }
}
