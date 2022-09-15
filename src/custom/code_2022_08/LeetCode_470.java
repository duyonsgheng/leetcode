package custom.code_2022_08;

/**
 * @ClassName LeetCode_470
 * @Author Duys
 * @Description
 * @Date 2022/8/15 10:30
 **/
//
public class LeetCode_470 {

    public int rand10() {
        int a = 7;
        int b = 7;
        while (a == 7) a = rand7(); // 1 ~ 6上的数
        while (b == 6 || b == 7) b = rand7(); // 1到5上的数
        return (a & 1) == 0 ? b : b + 5;// 偶数返回，奇数就+5
    }

    public int rand7() {
        return 0;
    }
}
