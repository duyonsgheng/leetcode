package week.week_2021_12_02;

/**
 * @ClassName Code_02_AwayFromBlackHole
 * @Author Duys
 * @Description
 * @Date 2022/4/15 15:42
 **/

// 来自美团
// 所有黑洞的中心点记录在holes数组里
// 比如[[3,5] [6,9]]表示，第一个黑洞在(3,5)，第二个黑洞在(6,9)
// 并且所有黑洞的中心点都在左下角(0,0)，右上角(x,y)的区域里
// 飞船一旦开始进入黑洞，就会被吸进黑洞里
// 返回：
// 如果统一所有黑洞的半径，最大半径是多少，依然能保证飞船从(0,0)能到达(x,y)
// 1000  1000*1000  10^6 * 二分
public class Code_02_AwayFromBlackHole {

    // 首先我们可以定一个二分的范围。
    // 从(0,0) 到 (x,y)
    public static int maxRadius(int[][] holes, int x, int y) {
        int l = 1;
        int r = Math.max(x, y);
        int ans = 0;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (isOk(holes, m, x, y)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static boolean isOk(int[][] holes, int r, int x, int y) {
        int n = holes.length;
        UnionFind uf = new UnionFind(holes, n, r);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (touch(holes[i][0], holes[i][1], holes[j][0], holes[j][1], r)) {
                    uf.union(i, j);
                }
                if (uf.block(i, x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    // r 半径
    public static boolean touch(int x1, int y1, int x2, int y2, int r) {
        return (r << 1) >= Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
    }

    public static class UnionFind {
        public int[] parent;
        public int[] size;
        public int[] xmin;
        public int[] ymin;
        public int[] xmax;
        public int[] ymax;
        public int[] help;

        public UnionFind(int[][] holes, int n, int r) {
            parent = new int[n];
            size = new int[n];
            xmax = new int[n];
            xmin = new int[n];
            ymax = new int[n];
            ymin = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
                xmax[i] = holes[i][0] + r;
                xmin[i] = holes[i][0] - r;
                ymin[i] = holes[i][1] - r;
                ymax[i] = holes[i][1] + r;
            }
        }

        private int find(int i) {
            int helpIndex = 0;
            while (i != parent[i]) {
                help[helpIndex++] = i;
                i = parent[i];
            }
            for (helpIndex--; helpIndex >= 0; helpIndex--) {
                parent[helpIndex--] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int parenti = find(i);
            int parentj = find(j);
            if (parenti == parentj) {
                return;
            }
            int sizei = size[parenti];
            int sizej = size[parentj];
            int big = sizei >= sizej ? parenti : parentj;
            int small = big == parenti ? parentj : parenti;
            parent[small] = big;
            size[big] = sizei + sizej;
            xmin[big] = Math.min(xmin[big], xmin[small]);
            xmax[big] = Math.max(xmax[big], xmax[small]);
            ymin[big] = Math.min(ymin[big], ymin[small]);
            ymax[big] = Math.max(ymax[big], ymax[small]);
        }

        public boolean block(int i, int x, int y) {
            i = find(i);
            return (xmin[i] <= 0 && xmax[i] >= x) || (ymin[i] <= 0 && ymax[i] >= y)
                    || (xmin[i] <= 0 && ymin[i] <= 0) || (xmax[i] >= x && ymax[i] >= y);
        }
    }
}
