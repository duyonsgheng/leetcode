package custom.code_2022_07;

/**
 * @ClassName LeetCode_201
 * @Author Duys
 * @Description
 * @Date 2022/7/6 14:14
 **/
// 201. 数字范围按位与
// 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
public class LeetCode_201 {

    public static int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            // 依次抹去最右侧的1
            right = right & (right - 1);
        }
        return right;
    }

}
