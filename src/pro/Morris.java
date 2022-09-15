package pro;

/**
 * @ClassName Moiros
 * @Author Duys
 * @Description
 * @Date 2022/5/25 17:44
 **/
public class Morris {

    /**
     * 一种遍历二叉树的方式，并且时间复杂度O(N)，额外空间复杂度O(1)
     * 通过利用原树中大量空闲指针的方式，达到节省空间的目的
     *
     *
     * 细节：
     * 假设来到当前节点cur，开始时cur来到头节点位置
     * 1）如果cur没有左孩子，cur向右移动(cur = cur.right)
     * 2）如果cur有左孩子，找到左子树上最右的节点mostRight：
     * 	a.如果mostRight的右指针指向空，让其指向cur，
     * 	然后cur向左移动(cur = cur.left)
     * 	b.如果mostRight的右指针指向cur，让其指向null，
     * 	然后cur向右移动(cur = cur.right)
     * 3）cur为空时遍历停止
     */

    /**
     * 如果节点有左树，到两次，没有左树就到一次
     */

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        // 左树的最右节点
        Node mostRight = null;
        // 大的流程，只要还有节点，就继续
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 左树的最右节点不为空，并且不是当前节点，之前已经改过了
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 如果我得最右节点是空了，那么最右节点指向cur节点
                // 然后cur节点向左边走
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                // 发现mostRight不为空，说明啥，有一个问题，之前来到了这个节点，并且已经改过了
                // 需要恢复
                else {
                    mostRight.right = null;
                }
            }
            // 上面不管有没有左孩子都会处理好，现在该往右边孩子去了
            cur = cur.right;
        }
    }


    // 先序遍历，第一次来到节点的时候就打印。
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 有左树，且第一次来到左树节点的时候打印
                if (mostRight.right == null) {
                    System.out.println(cur.val);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            // 没有左树，只会来一次，直接打印
            else {
                System.out.println(cur.val);
            }
            cur = cur.right;
        }
    }

    // 中序遍历，没左树直接打印，有左树，第二次来的时候打印
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            // 没有左树，只会来一次，直接打印
            // 有左树，第二次来到自己的时候打印。因为第一次上面会continue
            System.out.println(cur.val);
            cur = cur.right;
        }
    }

    // 后续遍历，有点特殊
    // 在能到自己两次的节点，第二次的时候，逆序打印当前节点的左树右边界
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    // 如果真的能来到自己两次，那么第二次的时候就直接打印当前节点的左树右边界
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        // morris结束后，逆序打印整棵树的右边界
        printEdge(head);
    }

    // 逆序打印当前树的右边界。链表反转，打印结束再反转回来
    public static void printEdge(Node head) {
        if (head == null) {
            return;
        }
        Node res = reverseEdge(head);
        Node cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.right;
        }
        reverseEdge(res);
    }

    public static Node reverseEdge(Node head) {
        Node pre = null;
        Node cur = null;
        while (head != null) {
            // 当前节点是前节点的右节点
            cur = head.right;
            // 翻转一下
            head.right = pre;
            // 下一个节点的前节点就是我了
            pre = head;
            // 下一个节点
            head = cur;
        }
        return pre;
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int v) {
            val = v;
        }
    }

    // 练习题：给定一棵树的头节点，返回这棵树的最小高度
    // 解法1：二叉树的递归套路
    public static int minHight(Node head) {
        if (head == null) {
            return 0;
        }
        return processMinHight(head);
    }

    // 返回以x为头的整棵树的最小高度
    public static int processMinHight(Node x) {
        // 当前x节点只有自己
        if (x.left == null && x.right == null) {
            return 1;
        }

        int lefeH = Integer.MAX_VALUE;
        // 问我的左树要信息
        if (x.left != null) {
            lefeH = processMinHight(x.left);
        }
        int rightH = Integer.MAX_VALUE;
        // 问我的右树要信息
        if (x.right != null) {
            rightH = processMinHight(x.right);
        }
        // 封装我得信息，向上返回
        return Math.min(lefeH, rightH) + 1;
    }

    // 解法2：morris遍历的方式
}
