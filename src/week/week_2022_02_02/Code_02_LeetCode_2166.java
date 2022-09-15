package week.week_2022_02_02;

/**
 * @ClassName Code_LeetCode_2166
 * @Author Duys
 * @Description
 * @Date 2022/3/29 17:55
 **/
public class Code_02_LeetCode_2166 {
    //位集 Bitset 是一种能以紧凑形式存储位的数据结构。
    //请你实现 Bitset 类。
    //Bitset(int size) 用 size 个位初始化 Bitset ，所有位都是 0 。
    //void fix(int idx) 将下标为 idx 的位上的值更新为 1 。如果值已经是 1 ，则不会发生任何改变。
    //void unfix(int idx) 将下标为 idx 的位上的值更新为 0 。如果值已经是 0 ，则不会发生任何改变。
    //void flip() 翻转 Bitset 中每一位上的值。换句话说，所有值为 0 的位将会变成 1 ，反之亦然。
    //boolean all() 检查Bitset 中 每一位 的值是否都是 1 。如果满足此条件，返回 true ；否则，返回 false 。
    //boolean one() 检查Bitset 中 是否至少一位 的值是 1 。如果满足此条件，返回 true ；否则，返回 false 。
    //int count() 返回 Bitset 中值为 1 的位的 总数 。
    //String toString() 返回 Bitset 的当前组成情况。注意，在结果字符串中，第 i 个下标处的字符应该与 Bitset 中的第 i 位一致。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/design-bitset

    class Bitset {
        // 32 位 ，需要向上取整
        private int[] bits;
        private final int size;
        private int zeros;
        private int ones;
        private boolean reverse;

        public Bitset(int n) {
            bits = new int[(n + 31) / 32];
            size = n;
            zeros = n;
            ones = 0;
            reverse = false;
        }

        // idx 位置如果是0，变成1，如果是1，不变
        public void fix(int idx) {
            int index = idx / 32; // 槽位
            int bit = idx % 32;  // 在自己的槽位里的二进制位置
            if (!reverse) { // 没有翻转过
                // 如果 当前bit位置是0
                if ((bits[index] & (1 << bit)) == 0) {
                    zeros--;
                    ones++;
                    bits[index] |= (1 << bit);
                }
            } else { // 翻转过，之前位置是0的，代表实际是1
                if ((bits[index] & (1 << bit)) != 0) {
                    zeros--;
                    ones++;
                    bits[index] ^= (1 << bit);
                }
            }
        }

        // idx 位置如果是1，变成0，如果是0，不变
        public void unfix(int idx) {
            int index = idx / 32;
            int bit = idx % 32;
            if (!reverse) {
                if ((bits[index] & (1 << bit)) != 0) {
                    zeros++;
                    ones--;
                    bits[index] ^= (1 << bit);
                }
            } else {
                if ((bits[index] & (1 << bit)) == 0) {
                    zeros++;
                    ones--;
                    bits[index] |= (1 << bit);
                }
            }
        }

        // 1 变0 ，0 变1
        public void flip() {
            reverse = !reverse;
            int tmp = zeros;
            zeros = ones;
            ones = tmp;
        }

        // 是不是所有的位置都是1
        public boolean all() {
            return ones == size;
        }

        // 是不是至少有1位是1
        public boolean one() {
            return ones > 0;
        }

        // 返回1的数量
        public int count() {
            return ones;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                int status = bits[i / 32] & (1 << (i % 32));
                sb.append(reverse ? (status == 0 ? '1' : '0') : (status == 0 ? '0' : '1'));
            }
            return sb.toString();
        }
    }
}
