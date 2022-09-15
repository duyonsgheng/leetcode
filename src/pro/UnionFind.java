package pro;

/**
 * @ClassName UnionFind
 * @Author Duys
 * @Description
 * @Date 2022/5/26 16:47
 **/
public class UnionFind {

    public int[] parent;
    public int[] size;
    public int[] help;
    public int sets;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        help = new int[n];
        sets = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int i, int j) {
        int p1 = find(i);
        int p2 = find(j);
        if (p1 == p2) {
            return;
        }
        if (size[p1] > size[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
        } else {
            parent[p1] = p2;
            size[p2] += size[p1];
        }
        sets--;
    }

    public boolean isSameSte(int i, int j) {
        int p1 = find(i);
        int p2 = find(j);
        return p1 == p2;
    }

    private int find(int i) {
        int hi = 0;
        while (i != parent[i]) {
            help[hi++] = i;
            i = parent[i];
        }
        for (hi--; hi >= 0; hi--) {
            parent[help[hi]] = i;
        }
        return i;
    }
}
