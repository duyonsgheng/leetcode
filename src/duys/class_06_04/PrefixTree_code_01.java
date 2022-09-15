package duys.class_06_04;

import java.util.HashMap;

/**
 * @ClassName PreTree_code_01
 * @Author Duys
 * @Description 前缀树，节点不放数据，数据放在‘路上’
 * 给每个节点设置两个属性 pass 属性： 表示有多少元数据以它开头的 有就+1 和 end属性：表示有多少元素以它结尾，有就+1
 * @Date 2021/6/4 16:37
 **/
public class PrefixTree_code_01 {
    // 前缀树节点类型
    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26];
        }
    }

    public static class PreTree1 {
        private Node1 root;

        public PreTree1() {
            root = new Node1();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            // 初始都是从root开始的，但是往下走的时候，复用
            Node1 newNode = root;
            newNode.pass++;
            // 就是从第一个字符开始，往下选择那一条路
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                // 开始选择一条路，如果没有，新建
                if (newNode.nexts[path] == null) {
                    newNode.nexts[path] = new Node1();
                }
                newNode = newNode.nexts[path];
                // 每个node的pass+1
                newNode.pass++;
            }
            // 字符串结束，把最后一个node的end+1
            newNode.end++;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            // 初始都是从root开始的，但是往下走的时候，复用
            Node1 newNode = root;
            // 就是从第一个字符开始，往下选择那一条路
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                // 开始选择一条路，如果没有，表示没找到响应的字符串
                if (newNode.nexts[path] == null) {
                    return 0;
                }
                newNode = newNode.nexts[path];
            }
            // 字符串结束，把最后一个node的end返回
            return newNode.end;
        }

        /**
         * @param pre 查前缀
         * @return
         */
        public int searchPreNum(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chars = pre.toCharArray();
            // 初始都是从root开始的，但是往下走的时候，复用
            Node1 newNode = root;
            // 就是从第一个字符开始，往下选择那一条路
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                // 开始选择一条路，如果没有，表示没找到相应的前缀
                if (newNode.nexts[path] == null) {
                    return 0;
                }
                newNode = newNode.nexts[path];
            }
            // 字符串结束，把最后一个node的end返回
            return newNode.pass;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node1 node = root;
                // 删除的话，这条路上所有节点的pass 都减一
                node.pass--;
                int path = 0;
                for (int i = 0; i < chs.length; i++) {
                    path = chs[i] - 'a';
                    // 如果删除的时候发现当前节点的pass都为0了，那么这个下所有的子节点都可以不要了，防止泄露
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                // 如果删除的只是其中的某一个，那么end 也要做响应的减少
                node.end--;
            }
        }
    }


    public class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public class PreTree2 {
        private Node2 root;

        public PreTree2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node2 cur = root;
            cur.pass++;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i];
                if (!cur.nexts.containsKey(index)) {
                    cur.nexts.put(index, new Node2());
                }
                cur = cur.nexts.get(index);
                cur.pass++;
            }
            cur.end++;
        }

        //word 加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                Node2 node = root;
                int index = 0;
                node.pass--;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i];
                    // 上面已经查询了，说明真实存在的，所以这里减到为0了，说明已经没有一条线从这里经过，也就是没有了，直接可以干掉了
                    if (--node.nexts.get(index).pass == 0) {
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }

        // 所有加入的字符串，有几个是以pre为前缀的
        public int prefixNum(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }
    }
}
