package custom.code_2022_09;

/**
 * @ClassName LeetCode_672
 * @Author Duys
 * @Description
 * @Date 2022/9/6 14:24
 **/
// 672. 灯泡开关 Ⅱ
public class LeetCode_672 {
    public int flipLights(int n, int m) {
        n = Math.min(n, 3);
        if (m == 0) return 1;
        if (m == 1) return n == 1 ? 2 : n == 2 ? 3 : 4;
        if (m == 2) return n == 1 ? 2 : n == 2 ? 4 : 7;
        return n == 1 ? 2 : n == 2 ? 4 : 8;
    }
}
