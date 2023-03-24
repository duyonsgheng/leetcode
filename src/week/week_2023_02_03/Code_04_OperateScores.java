package week.week_2023_02_03;

import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName Code_04_OperateScores
 * @Author Duys
 * @Description
 * @Date 2023/2/16 13:41
 **/
// 来自TikTok美国笔试
// 给定一个长度为N的一维数组scores, 代表0~N-1号员工的初始得分
// scores[i] = a, 表示i号员工一开始得分是a
// 给定一个长度为M的二维数组operations,
// operations[i] = {a, b, c}
// 表示第i号操作为 :
// 如果a==1, 表示将目前分数<b的所有员工，分数改成b，c这个值无用
// 如果a==2, 表示将编号为b的员工，分数改成c
// 所有操作从0~M-1, 依次发生
// 返回一个长度为N的一维数组ans，表示所有操作做完之后，每个员工的得分是多少
// 1 <= N <= 10的6次方
// 1 <= M <= 10的6次方
// 0 <= 分数 <= 10的9次方
public class Code_04_OperateScores {
    // 有序表+桶
    public int[] operateScores(int[] scores, int[][] operations) {
        int n = scores.length;
        Node[] nodes = new Node[n];
        TreeMap<Integer, Bucket> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
            if (!map.containsKey(scores[i])) {
                map.put(scores[i], new Bucket());
            }
            map.get(scores[i]).add(nodes[i]);
        }
        for (int[] op : operations) {
            if (op[0] == 1) {
                // 把低于当前分数的桶全部找到，然后合并
                // 大于等于 op[1]-1 的最大
                Integer key = map.floorKey(op[1] - 1);
                if (key != null && !map.containsKey(op[1])) {
                    map.put(op[1], new Bucket());
                }
                while (key != null) {
                    map.get(op[1]).merge(map.get(key));
                    map.remove(key);
                    // 继续下一个key，直到全部合并完成
                    key = map.floorKey(op[1] - 1);
                }
            } else {
                // 把当前编号op[1]的分数，改为 op[2]
                Node cur = nodes[op[1]];
                // 断开连接
                cur.conectLastNext();
                if (!map.containsKey(op[2])) {
                    map.put(op[2], new Bucket());
                }
                map.get(op[2]).add(cur);
            }
        }
        int[] ans = new int[n];
        for (Map.Entry<Integer, Bucket> entry : map.entrySet()) {
            int score = entry.getKey();
            Bucket bucket = entry.getValue();
            Node cur = bucket.head.next;
            while (cur != bucket.tail) {
                ans[cur.index] = score;
                cur = cur.next;
            }
        }
        return ans;
    }

    class Node {
        public int index;
        public Node last;
        public Node next;

        public Node(int i) {
            index = i;
        }

        public void conectLastNext() {
            last.next = next;
            next.last = last;
        }
    }

    class Bucket {
        public Node head;
        public Node tail;

        public Bucket() {
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.last = head;
        }

        public void add(Node node) {
            node.last = tail.last;
            node.next = tail;
            tail.last.next = node;
            tail.last = node;
        }

        public void merge(Bucket bucket) {
            // 不是空桶
            if (bucket.head.next != bucket.tail) {
                tail.last.next = bucket.head.next;
                bucket.head.next.last = tail.last;
                bucket.tail.last.next = tail;
                tail.next = bucket.tail.last;
                // 空桶，首尾连好
                bucket.head.next = bucket.tail;
                bucket.tail.next = bucket.head;
            }
        }
    }
}
