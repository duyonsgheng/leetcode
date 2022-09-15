package duys_code.day_09;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName Code_04_EnvelopesProblem
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/russian-doll-envelopes/
 * @Date 2021/10/14 11:23
 **/
public class Code_04_EnvelopesProblem {
    /**
     * 俄罗斯套娃问题
     */
    // 当宽度一样的时候高度越大排前面
    public static int maxEnvelopes(int[][] envelopes) {
        Envelope[] arr = sort(envelopes);
        int[] ends = new int[envelopes.length];
        ends[0] = arr[0].h;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 0; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) >> 1;
                if (arr[i].h > ends[m]) {
                    l = m + 1;
                } else
                    r = m - 1;
            }
            right = Math.max(right, l);
            ends[l] = arr[i].h;
        }
        return right + 1;
    }

    public static Envelope[] sort(int[][] ma) {
        Envelope[] res = new Envelope[ma.length];
        for (int i = 0; i < ma.length; i++) {
            res[i] = new Envelope(ma[i][0], ma[i][1]);
        }
        Arrays.sort(res, new EnvelopeComparator());
        return res;
    }


    public static class EnvelopeComparator implements Comparator<Envelope> {

        @Override
        public int compare(Envelope o1, Envelope o2) {
            // 宽度一样的时候，高度大的在前面
            return o1.l != o2.l ? o1.l - o2.l : o2.h - o1.h;
        }
    }


    public static class Envelope {
        public int l;
        public int h;

        public Envelope(int weight, int hight) {
            l = weight;
            h = hight;
        }
    }
}
