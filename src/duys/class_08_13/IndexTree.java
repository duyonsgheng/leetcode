package duys.class_08_13;

/**
 * @ClassName IndexTree
 * @Author Duys
 * @Description 也是解决累加和问题
 * @Date 2021/8/13 14:45
 **/
public class IndexTree {

    /**
     * 1.先生成前缀和数组 O(N) 的
     * 例如：[3-4]范围的累加和 = [1-4] - [1-2]
     */

    /**
     * 规律：
     * 价格index的二进制 比如 8 - 01000
     * 管得哪些位置：把index最后一个1 领出来得数， 1000 -> 0000 +1 -> 0001 到 1000（自己） 所以8管的就是 1 到 8的位置
     * 例如：01100
     * 把最后一个1拆开 就是01000 +1 -> 01001 然后到自己 01100 就是 9 带 12 所以 12位置管了 9-12位置的
     */

    private int[] tree;
    private int N;

    public IndexTree(int size) {
        N = size;
        // 0位置不用，。从1开始
        tree = new int[N + 1];
    }

    // 从1-index 的累加和
    public int sum(int index) {
        int ret = 0;
        while (index > 0) {
            ret += tree[index];
            // 从index位置往前看。每次去除最右侧的1
            index -= index & -index;
        }
        return ret;
    }

    /**
     * 调整了后，哪些位置受到影响
     *
     * @param index
     * @param a
     */
    public void add(int index, int a) {
        while (index <= N) {
            tree[index] += a;
            // 提取index最右侧的1 然后加上自己，表示哪些位置受到了影响
            // 例如：index 01011000
            // index & -index : 00001000
            // -index = ~index +1
            /**
             * 例如：3这个位置变了 0110
             * 推出哪些位置受到影响
             * 首先 0011 + 0001 -> 0100 4位置受到了影响
             * 然偶 0100 + 0100 -> 1000 8位置受到了影响
             * 。。。。。一次推，直到越界
             */
            index += index & -index;
        }
    }
}
