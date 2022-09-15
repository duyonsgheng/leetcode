package duys_code.day_02;

/**
 * @ClassName Code_02_Cola
 * @Author Duys
 * @Description 携程原题：可乐问题
 * @Date 2021/9/16 13:36
 **/
public class Code_02_Cola {
    /*
     * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
     * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
     * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
     * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
     * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
     * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
     * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
     * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
     * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
     */
    // 题目已经限制优先使用大面值的钱，那么就从大面值的钱开始算，看看从大面值到小面值 一共能搞定多少瓶可乐，
    // 如果每一种面值搞到最后还剩下的钱喝张数记下来，下一种面值开始使用的时候，优先使用前面剩下的

    // m瓶可乐，单价x，100面值的a张，50面值的b张，10面值的c张
    public static int putTimes(int m, int a, int b, int c, int x) {
        if (m <= 0 || x <= 0 || (a <= 0 && b <= 0 && c <= 0)) {
            return 0;
        }
        int[] qian = {100, 50, 10};
        int[] zhang = {a, b, c};
        // 需要投递几次
        int puts = 0;

        // 之前面值搞定可乐的时候还剩下的总钱数
        int preQianRest = 0;
        // 之前面值搞定可乐的时候还剩下的张数
        int preZhangRest = 0;

        // 从大面值开始搞
        for (int i = 0; i < qian.length && m != 0; i++) {

            // 第一次使用当前面值去搞定可乐的时候, 那么当前面值参与搞定第一瓶可乐，需要掏出多少张呢？就是curQianFirstBuyZhang
            // 比如 5/2 我需要向上取整，那么就是 5+2-1/2 = 3
            // 意思就是之前剩下的肯定搞不定一瓶可乐了，那么至少要用到现在的钱几张然后一起来搞定一瓶可乐
            int curQianFirstBuyZhang = (x - preQianRest + (qian[i] - 1)) / qian[i];
            // 当前面值的钱剩下的张数够用
            if (zhang[i] >= curQianFirstBuyZhang) {
                // 凑出了第一瓶可乐的钱，存在找零的情况，比如可乐1230 一瓶 前面剩下的钱是1000 , 现在正在用50的面值来玩儿，之前剩下的，加本次5张50的，还需要找零20块
                giveRest(qian, zhang, i + 1, (preQianRest + qian[i] * curQianFirstBuyZhang) - x, 1);
                puts += curQianFirstBuyZhang + preZhangRest;
                zhang[i] -= curQianFirstBuyZhang;
                // 买了一瓶了
                m--;
            }
            // 当前面值剩下的不够用，往搞不定，剩下的钱喝张数，往后传递，让下一个面值来搞
            else {
                preQianRest += qian[i] * zhang[i];
                preZhangRest += zhang[i];
                // 下一个面值的继续
                continue;
            }

            // 之前的面值遗留的尾巴搞定了
            // 这里开始计算本次面值购买一瓶可乐需要用几张
            int curQianBuyOnceColaZhang = (x + qian[i] - 1) / qian[i];

            // 用当前面值的钱，一共可以搞定多少瓶可乐
            int curQianBuyColas = Math.min(zhang[i] / curQianBuyOnceColaZhang, m);

            // 使用当前面值每买一瓶需要找零
            int onceTimeBuyRest = qian[i] * curQianBuyOnceColaZhang - x;
            giveRest(qian, zhang, i + 1, onceTimeBuyRest, curQianBuyColas);


            puts += curQianBuyColas * curQianBuyOnceColaZhang;

            m -= curQianBuyColas;
            zhang[i] -= curQianBuyOnceColaZhang * curQianBuyColas;
            preQianRest = qian[i] * zhang[i];
            preZhangRest = zhang[i];
        }
        return m == 0 ? puts : -1;
    }

    // 当前需要从哪一个位置钱开始找零，也就是哪一个位置张数需要开始增加了
    // oneTimeRest 使用当前面值每买一瓶需要找零的钱，
    // times 买了几瓶可乐，需要找零几次
    public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
        for (; i < qian.length; i++) {
            zhang[i] += (oneTimeRest / qian[i]) * times;
            // 往后面的面值传递本次面值搞不定的剩下
            oneTimeRest %= qian[i];
        }
    }
}
