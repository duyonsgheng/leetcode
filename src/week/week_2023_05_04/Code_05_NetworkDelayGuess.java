package week.week_2023_05_04;

import java.util.HashSet;

/**
 * @ClassName Code_05_NetworkDelayGuess
 * @Author Duys
 * @Description
 * @Date 2023/5/25 10:07
 **/
// 来自招商银行
// 这个题是个破题，但这可能是经常遇到的一种题，
// 讲一下思路即可，没有必要投入大多时间给这种题目
// 一共有三个服务A、B、C，网络延时分别为a、b、c
// 并且一定有：1 <= a <= b <= c <= 10^9
// 但是具体的延时数字丢失了，只有单次调用的时间
// 一次调用不可能重复使用相同的服务，
// 一次调用可能使用了三个服务中的某1个、某2个或者全部3个服务
// 比如一个调用的时间，T = 100
// 100的延时可能来自以下7种情况：
// a = 100，这次调用可能单独使用了A
// b = 100，这次调用可能单独使用了B
// c = 100，这次调用可能单独使用了C
// a + b = 100，这次调用可能组合使用了A、B
// a + c = 100，这次调用可能组合使用了A、C
// b + c = 100，这次调用可能组合使用了B、C
// a + b + c = 100，这次调用可能组合使用了A、B、C全部服务
// 那么可想而知，如果给的调用时间足够多，是可以猜测出a、b、c的
// 给定一个数组times，长度为n，并且一定有4 <= n <= 7
// times[i] = s，表示i号调用用时s，而且times中一定都是正数且没有重复值
// 请根据n次调用，猜测出a、b、c三元组可能的情况数
// 如果任何a、b、c都无法匹配上给定的调用耗时，返回0
// 测试的次数T <= 100
// 也就是说，一共最多给定100个数组，每一次让你返回a、b、c三元组可能的情况数
public class Code_05_NetworkDelayGuess {
    // 思路：解多元一次方程
    // a b c a+b a+c b+c a+b+c 7中选择
    // 使用status数组来表示这7个等式
    public static int ways(int[] times) {
        int[] status = new int[7];
        HashSet<String> ans = new HashSet<>();
        dfs(times, 0, status, ans);
        return ans.size();
    }

    public static void dfs(int[] times, int i, int[] status, HashSet<String> ans) {
        if (i == times.length) {
            int a = 0, b = 0, c = 0;
            int cnt = counts(status);
            if (cnt == 0) { // 一个单项的都没有 ，那么 a+b a+c b+c 三个等式就可以解答了
                a = (status[3] + status[4] - status[5]) / 2;
                b = (status[3] + status[5] - status[4]) / 2;
                c = (status[4] + status[5] - status[3]) / 2;
            } else if (cnt == 1) {
                if (status[0] != 0) {
                    a = status[0];
                    if (status[3] != 0) {
                        b = status[3] - a;
                    }
                    if (status[4] != 0) {
                        c = status[4] - a;
                    }
                    if (status[5] != 0) {
                        if (b != 0 && c == 0) {
                            c = status[5] - b;
                        }
                        if (c != 0 && b == 0) {
                            b = status[5] - c;
                        }
                    }
                } else if (status[1] != 0) {
                    b = status[1];
                    if (status[3] != 0) {
                        a = status[3] - b;
                    }
                    if (status[5] != 0) {
                        c = status[5] - b;
                    }
                    if (status[4] != 0) {
                        if (a != 0 && c == 0) {
                            c = status[4] - a;
                        }
                        if (c != 0 && a == 0) {
                            a = status[4] - c;
                        }
                    }
                } else {
                    c = status[2];
                    if (status[4] != 0) {
                        a = status[4] - c;
                    }
                    if (status[5] != 0) {
                        b = status[5] - c;
                    }
                    if (status[3] != 0) {
                        if (a != 0 && b == 0) {
                            b = status[3] - a;
                        }
                        if (b != 0 && a == 0) {
                            a = status[3] - b;
                        }
                    }
                }
            } else if (cnt == 2) {
                if (status[0] != 0) {
                    a = status[0];
                }
                if (status[1] != 0) {
                    b = status[1];
                }
                if (status[2] != 0) {
                    c = status[2];
                }
                if (a == 0) {
                    if (status[3] != 0) {
                        a = status[3] - b;
                    } else if (status[4] != 0) {
                        a = status[4] - c;
                    } else {
                        a = status[6] - b - c;
                    }
                } else if (b == 0) {
                    if (status[3] != 0) {
                        b = status[3] - a;
                    } else if (status[5] != 0) {
                        b = status[5] - c;
                    } else {
                        b = status[6] - a - c;
                    }
                } else {
                    if (status[4] != 0) {
                        c = status[4] - a;
                    } else if (status[5] != 0) {
                        c = status[5] - b;
                    } else {
                        c = status[6] - a - b;
                    }
                }
            } else {
                a = status[0];
                b = status[1];
                c = status[2];
            }
            if (check(status, a, b, c)) {
                ans.add(a + "_" + b + "_" + c);
            }
        } else {
            // 尝试，跑一个dfs
            for (int j = 0; j < 7; j++) {
                if (status[j] == 0) {
                    status[j] = times[i];
                    dfs(times, i + 1, status, ans);
                    status[j] = 0;
                }
            }
        }
    }

    // 统计有几个单项的
    // 比如status[0] 表示a
    // 比如status[1] 表示b
    // 比如status[2] 表示c
    public static int counts(int[] status) {
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            if (status[i] != 0) {
                cnt++;
            }
        }
        return cnt;
    }

    public static boolean check(int[] status, int a, int b, int c) {
        // 题意要求
        // a<b<c
        if (a <= 0 || b <= 0 || c <= 0 || a > b || b > c) {
            return false;
        }
        if (status[0] != 0 && status[0] != a) {
            return false;
        }
        if (status[1] != 0 && status[1] != b) {
            return false;
        }
        if (status[2] != 0 && status[2] != c) {
            return false;
        }
        if (status[3] != 0 && status[3] != a + b) {
            return false;
        }
        if (status[4] != 0 && status[4] != a + c) {
            return false;
        }
        if (status[5] != 0 && status[5] != b + c) {
            return false;
        }
        if (status[6] != 0 && status[6] != a + b + c) {
            return false;
        }
        return true;
    }
}
