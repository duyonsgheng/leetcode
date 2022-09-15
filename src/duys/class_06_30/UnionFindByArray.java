package duys.class_06_30;

/**
 * @ClassName UnionFindArray
 * @Author Duys
 * @Description 用数组实现并查集
 * @Date 2021/6/30 17:24
 **/
public class UnionFindByArray {
    //parent[i] = k : i 的父级是K
    private int[] parent;
    // size[i] =  k : i 所在集合的大小，当i是代表节点才有意义
    private int[] size;
    // 做路径压缩使用
    private int[] help;
    //
    private int sets;

    public UnionFindByArray(int length) {
        parent = new int[length];
        size = new int[length];
        help = new int[length];
        sets = length;
        for (int i = 0; i < length; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findParent(int i) {
        int helpIndex = 0;
        while (i != parent[i]) {
            help[helpIndex++] = i;
            i = parent[i];
        }
        for (helpIndex--; helpIndex >= 0; helpIndex--) {
            parent[help[helpIndex]] = i;
        }
        return i;
    }

    public void union(int a, int b) {
        int aHead = findParent(a);
        int bHead = findParent(b);
        if (aHead != bHead) {
            if (size[aHead] > size[bHead]) {
                size[aHead] += size[bHead];
                parent[bHead] = aHead;
            } else {
                size[bHead] += size[aHead];
                parent[aHead] = bHead;
            }
            sets--;
        }
    }

}
