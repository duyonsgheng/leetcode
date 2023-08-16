package hope.bitmap;

/**
 * @author Mr.Du
 * @ClassName Video_032_2_BitsetTest
 * @date 2023年08月15日
 */
// https://leetcode.cn/problems/design-bitset/description/
// 2166. 设计位集
public class Video_032_2_BitsetTest {
    class Bitset {
        private final int n;
        private int[] set;
        private int zeros;
        private int ones;

        private boolean flip; // 是否被翻转了，如果是，则1和0的意义互换

        public Bitset(int size) {
            n = size;
            set = new int[(size + 31) / 32];
            zeros = n;
            ones = 0;
            flip = false;
        }

        public void fix(int idx) {
            int index = idx / 32;
            int bit = idx % 32;
            if (!flip) {
                if ((set[index] & (1 << bit)) == 0) {
                    set[index] |= (1 << bit);
                    ones++;
                    zeros--;
                }

            } else {
                if ((set[index] & (1 << bit)) != 0) {
                    set[index] ^= (1 << bit);
                    zeros--;
                    ones++;
                }
            }
        }

        public void unfix(int idx) {
            int index = idx / 32;
            int bit = idx % 32;
            if (!flip) {
                if ((set[index] & (1 << bit)) != 0) {
                    set[index] ^= (1 << bit);
                    ones--;
                    zeros++;
                }
            } else {
                if ((set[index] & (1 << bit)) == 0) {
                    set[index] |= (1 << bit);
                    zeros++;
                    ones--;
                }
            }
        }

        public void flip() {
            flip = !flip;
            int tmp = zeros;
            zeros = ones;
            ones = tmp;
        }

        public boolean all() {
            return ones == n;
        }

        public boolean one() {
            return ones > 0;
        }

        public int count() {
            return ones;
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            for (int i = 0, k = 0, num = 0, status = 0; i < n; k++) {
                num = set[k];
                for (int j = 0; j < 32 && i < n; j++, i++) {
                    status = (num >> j) & 1;
                    status ^= flip ? 1 : 0;
                    sb.append(status);
                }
            }
            return sb.toString();
        }
    }
}
