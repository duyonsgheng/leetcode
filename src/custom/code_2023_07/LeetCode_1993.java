package custom.code_2023_07;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1993
 * @date 2023年07月24日
 */
// 1993. 树上的操作
// https://leetcode.cn/problems/operations-on-tree/
public class LeetCode_1993 {
    static class LockingTree {
        int[] parent;
        int[] lockUser;
        Map<Integer, Set<Integer>> map;

        public LockingTree(int[] parent) {
            int n = parent.length;
            this.parent = parent;
            lockUser = new int[n];
            map = new HashMap<>();
            for (int i = -1; i < n; i++) {
                map.put(i, new HashSet<>());
            }
            for (int i = 0; i < n; i++) {
                map.get(parent[i]).add(i);
            }// 把当前节点放到对应的父节点里面
        }

        public boolean lock(int num, int user) {
            if (lockUser[num] == 0) {
                lockUser[num] = user;
                return true;
            }
            return false;
        }

        public boolean unlock(int num, int user) {
            if (lockUser[num] == user) {
                lockUser[num] = 0;
                return true;
            }
            return false;
        }

        public boolean upgrade(int num, int user) {
            // 判断本身和祖先结点是否上锁
            int find = num;
            while (find != -1) {
                if (lockUser[find] != 0) {
                    return false;
                }
                find = parent[find];
            }

            boolean hasSubLock = false;// 判断是否有子孙结点上锁
            Deque<Integer> deque = new LinkedList<>();
            deque.offer(num);// 当前节点入队列
            // 遍历其所有的子孙结点
            while (!deque.isEmpty()) {
                for (int next : map.get(deque.poll())) {
                    if (lockUser[next] != 0) {
                        lockUser[next] = 0;
                        hasSubLock = true;
                    }
                    deque.offer(next);
                }
            }
            if (hasSubLock) {
                lockUser[num] = user;
            }
            return hasSubLock;
        }
    }

    public static void main(String[] args) {
        LockingTree tree = new LockingTree(new int[]{-1, 0, 0, 1, 1, 2, 2});
        System.out.println(tree.lock(2, 2));
        System.out.println(tree.unlock(2, 3));
        System.out.println(tree.unlock(2, 2));
        System.out.println(tree.lock(4, 5));
        System.out.println(tree.upgrade(0, 1));
        System.out.println(tree.lock(0, 1));
    }
}
