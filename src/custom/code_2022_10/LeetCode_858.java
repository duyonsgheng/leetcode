package custom.code_2022_10;

/**
 * @ClassName LeetCode_858
 * @Author Duys
 * @Description
 * @Date 2022/10/9 9:50
 **/
// 858. 镜面反射
public class LeetCode_858 {

    public int mirrorReflection1(int p, int q) {
        int pqOr = p | q;
        int lowestBit = pqOr & (-pqOr);
        if ((p & lowestBit) == 0) return 2;
        if ((q & lowestBit) == 0) return 0;
        return 1;
    }

    public int mirrorReflection(int p, int q) {
        // 只要确保分子分母不都是偶数就行了
        while ((q & 1) == 0 && (p & 1) == 0) {
            q >>= 1;
            p >>= 1;
        }
        // p 为偶数
        if ((p & 1) == 0)
            return 2;
        // q 为偶数
        if ((q & 1) == 0)
            return 0;
        // p, q 都是奇数
        return 1;
    }

}
