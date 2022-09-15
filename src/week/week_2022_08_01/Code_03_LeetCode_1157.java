package week.week_2022_08_01;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_03_LeetCode_1157
 * @Author Duys
 * @Description
 * @Date 2022/8/8 13:15
 **/
// 1157. 子数组中占绝大多数的元素
// 设计一个数据结构，有效地找到给定子数组的 多数元素 。
// 子数组的 多数元素 是在子数组中出现threshold次数或次数以上的元素。
// 实现 MajorityChecker 类:
public class Code_03_LeetCode_1157 {
    class MajorityChecker {
        SegmentTree st;
        CountQuicker cq;

        public MajorityChecker(int[] arr) {
            st = new SegmentTree(arr);
            cq = new CountQuicker(arr);
        }

        public int query(int l, int r, int t) {
            int cand = st.query(l, r);
            return cq.realTimes(l, r, cand) >= t ? cand : -1;
        }

        class SegmentTree {
            int n;
            int[] candidate;
            int[] hp;

            public SegmentTree(int[] arr) {
                n = arr.length;
                candidate = new int[(n + 1) << 2];
                hp = new int[(n + 1) << 2];
                build(arr, 1, n, 1);
            }

            public int query(int l, int r) {
                return query(l + 1, r + 1, 1, n, 1)[0];
            }

            private int[] query(int L, int R, int l, int r, int rt) {
                if (L <= l && R >= r) {
                    return new int[]{candidate[rt], hp[rt]};
                }
                int m = (l + r) >> 1;
                if (R <= m) {
                    return query(L, R, l, m, rt << 1);
                } else if (L > m) {
                    return query(L, R, m + 1, r, rt << 1 | 1);
                } else {
                    int[] ansl = query(L, R, l, m, rt << 1);
                    int[] ansr = query(L, R, m + 1, r, rt << 1 | 1);
                    if (ansl[0] == ansr[0]) {
                        ansl[1] += ansr[1];
                        return ansl;
                    } else {
                        if (ansl[1] >= ansr[1]) {
                            ansl[1] -= ansr[1];
                            return ansl;
                        } else {
                            ansr[1] -= ansl[1];
                            return ansr;
                        }
                    }
                }
            }

            private void build(int[] arr, int l, int r, int rt) {
                if (l == r) {
                    candidate[rt] = arr[l - 1];
                    hp[rt] = 1;
                } else {
                    int m = (l + r) >> 1;
                    build(arr, l, m, rt << 1);
                    build(arr, m + 1, r, rt << 1 | 1);
                    int lc = candidate[rt << 1];
                    int rc = candidate[rt << 1 | 1];
                    int lh = hp[rt << 1];
                    int rh = hp[rt << 1 | 1];
                    if (lc == rc) {
                        candidate[rt] = lc;
                        hp[rt] = lh + rh;
                    } else {
                        candidate[rt] = lh >= rh ? lc : rc;
                        hp[rt] = Math.abs(lh - rh);
                    }
                }
            }
        }

        class CountQuicker {
            // 记录数出现在了哪些位置
            List<List<Integer>> cnt;

            public CountQuicker(int[] arr) {
                cnt = new ArrayList<>();
                int max = 0;
                for (int num : arr) {
                    max = Math.max(num, max);
                }
                for (int i = 0; i <= max; i++) {
                    cnt.add(new ArrayList<>());
                }
                for (int i = 0; i < arr.length; i++) {
                    cnt.get(arr[i]).add(i);
                }
            }

            public int realTimes(int l, int r, int num) {
                List<Integer> list = cnt.get(num);
                return size(list, r) - size(list, l - 1);
            }

            private int size(List<Integer> indexs, int index) {
                int l = 0;
                int r = indexs.size() - 1;
                int m = 0;
                int ans = -1;
                while (l <= r) {
                    m = (l + r) >> 1;
                    if (indexs.get(m) <= index) {
                        ans = m;
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
                return ans + 1;
            }
        }
    }
}
