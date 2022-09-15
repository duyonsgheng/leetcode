package duys_code.day_01;

/**
 * @ClassName Code_06_
 * @Author Duys
 * @Description TODO
 * @Date 2021/9/15 15:07
 **/
public class Code_06_MasterHpAndAOE {
    /**
     * 给定两个非负数组x和hp，长度都是N，再给定一个正数range
     * x有序，x[i]表示i号怪兽在x轴上的位置；hp[i]表示i号怪兽的血量
     * 再给定一个正数range，表示如果法师释放技能的范围长度
     * 被打到的每只怪兽损失1点血量。
     * 返回要把所有怪兽血量清空，至少需要释放多少次AOE技能？
     */
    // 让每一个rang区域的最左边先掉为0。那么就需要先把每一个范围都对应的位置拿出来
    public static int minCD1(int[] x, int[] hp, int range) {
        int N = x.length;
        int[] leftCover = new int[N];
        int[] rightCover = new int[N];
        int left = 0;
        int right = 0;
        for (int i = 0; i < N; i++) {
            // 如果在i位置施法，左边能覆盖的区域和右边能覆盖的区域
            // 算左边界
            while (x[i] - x[left] > range) {
                // 左边界收缩
                left++;
            }
            // 有边界
            while (range < N && x[right] - x[i] <= range) {
                // 有边界往右扩展
                right++;
            }
            leftCover[i] = left;
            rightCover[i] = right;
        }
        return 0;
    }

    public static int process1(int[] hp, int[] leftCover, int[] rightCover) {
        int N = hp.length;
        int ans = Integer.MAX_VALUE;
        // 每一个位置怪物血量掉到0以下
        for (int i = 0; i < N; i++) {
            // 在i位置的这个区域内进行尝试每一个位置得最小
            for (int f = leftCover[i]; f <= rightCover[i]; f++) {
                if (hp[i] > 0) {
                    int[] next = aoe1(hp, leftCover[i], rightCover[i]);
                    ans = Math.min(ans, process1(next, leftCover, rightCover));
                    break;
                }
            }
        }
        return ans;
    }

    public static int[] aoe1(int[] hp, int L, int R) {
        int N = hp.length;
        int[] next = new int[N];
        for (int i = 0; i < N; i++) {
            next[i] = hp[i];
        }
        for (int i = L; i <= R; i++) {
            next[i] -= next[i] > 0 ? 1 : 0;
        }
        return next;
    }
}
