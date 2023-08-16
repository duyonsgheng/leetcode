package hope.bitmap;

/**
 * @author Mr.Du
 * @ClassName Video_032_1_Bitset
 * @date 2023年08月15日
 */
public class Video_032_1_Bitset {

    class Bitset {
        public int[] set;
        public int size;

        public Bitset(int n) {
            size = n;
            // 一个整数32位，可以表示32个数字，所以需要向上取整
            set = new int[(n + 31) / 32];
        }

        // 增加一个数
        public void add(int num) {
            set[num / 32] |= 1 << (num % 32);
        }

        // 删除这个数
        public void remove(int num) {
            set[num / 32] &= ~(1 << (num % 32));
        }

        // 如果不存在这个数则加上，存在了则删除
        public void reverse(int num) {
            set[num / 32] ^= (1 << (num % 32));
        }

        public boolean contains(int num) {
            return ((set[num / 32] >> (num % 32)) & 1) == 1;
        }
    }
}
