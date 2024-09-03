package hope.class91;

import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code03_GroupBuyTickets
 * @date 2024年09月03日 下午 04:29
 */
// 组团买票
// 景区里一共有m个项目，景区的第i个项目有如下两个参数：
// game[i] = { Ki, Bi }，Ki、Bi一定是正数
// Ki代表折扣系数，Bi代表票价
// 举个例子 : Ki = 2, Bi = 10
// 如果只有1个人买票，单张门票的价格为 : Bi - Ki * 1 = 8
// 所以这1个人游玩该项目要花8元
// 如果有2个人买票，单张门票的价格为 : Bi - Ki * 2 = 6
// 所以这2个人游玩该项目要花6 * 2 = 12元
// 如果有5个人买票，单张门票的价格为 : Bi - Ki * 5 = 0
// 所以这5个人游玩该项目要花5 * 0 = 0元
// 如果有更多人买票，都认为花0元(因为让项目倒贴钱实在是太操蛋了)
// 于是可以认为，如果有x个人买票，单张门票的价格为 : Bi - Ki * x
// x个人游玩这个项目的总花费是 : max { x * (Bi - Ki * x), 0 }
// 单位一共有n个人，每个人最多可以选1个项目来游玩，也可以不选任何项目
// 所有员工将在明晚提交选择，然后由你去按照上面的规则，统一花钱购票
// 你想知道自己需要准备多少钱，就可以应付所有可能的情况，返回这个最保险的钱数
// 数据量描述 :
// 1 <= M、N、Ki、Bi <= 10^5
// 来自真实大厂笔试，没有在线测试，对数器验证
public class Code03_GroupBuyTickets {


    public static int enough(int n, int[][] games) {
        // 那一个项目，再来一人，能挣更多的 钱
        // 大根堆
        PriorityQueue<Game> heap = new PriorityQueue<>((a, b) -> b.earn() - a.earn());
        for (int[] game : games) {
            heap.add(new Game(game[0], game[1]));
        }
        int ans = 0;

        for (int i = 0; i < n; i++) {
            // 把一个个人推送到最挣钱的项目去
            if (heap.peek().earn() <= 0) { // 如果所有的项目都0了，不需要算了
                break;
            }
            Game cur = heap.poll();
            ans += cur.earn();
            cur.people++;
            heap.add(cur);
        }
        return ans;
    }

    public static class Game {
        public int ki; // 折扣系数
        public int bi; // 门票价格
        public int people;// 之前的人数

        public Game(int a, int b) {
            ki = a;
            bi = b;
            people = 0;
        }

        public int earn() {
            // bi -(people+1)*ki：当前的人，门票的原价就减少了，当前门票的价格
            // people * ki : 当前的人来了，之前所有的人，票价都要 减去 ki
            return bi - (people + 1) * ki - people * ki;
        }
    }
}
