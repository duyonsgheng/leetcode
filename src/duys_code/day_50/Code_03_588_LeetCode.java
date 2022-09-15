package duys_code.day_50;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @ClassName Code_03_588_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/design-in-memory-file-system/
 * @Date 2021/10/29 14:58
 **/
public class Code_03_588_LeetCode {
    /**
     * 设计文件系统
     * 就一个前缀树
     */
    class FileSystem {
        public Node head;

        public FileSystem() {
            head = new Node("");
        }

        public List<String> ls(String path) {
            List<String> ans = new ArrayList<>();
            Node cur = head;
            // 此时的所有前缀
            String[] paths = path.split("/");
            int n = paths.length;
            for (int i = 0; i < n; i++) {
                if (!cur.nexts.containsKey(paths[i])) {
                    return ans;
                }
                // 一直知道最后一个文件的目录名称
                cur = cur.nexts.get(paths[i]);
            }
            // 是一个目录,列出下级所有目录名称
            if (cur.content == null) {
                ans.addAll(cur.nexts.keySet());
            } else {
                ans.add(cur.fileName);
            }
            return ans;
        }

        public void mkdir(String path) {
            Node cur = head;
            String[] paths = path.split("/");
            int n = paths.length;
            for (int i = 0; i < n; i++) {
                if (!cur.nexts.containsKey(paths[i])) {
                    cur.nexts.put(paths[i], new Node(paths[i]));
                }
                cur = cur.nexts.get(paths[i]);
            }
        }

        public void addContentToFile(String path, String content) {
            Node cur = head;
            String[] paths = path.split("/");
            int n = paths.length;
            // 找到最后一级的目录
            for (int i = 0; i < n - 1; i++) {
                if (!cur.nexts.containsKey(paths[i])) {
                    cur.nexts.put(paths[i], new Node(paths[i]));
                }
                cur = cur.nexts.get(paths[i]);
            }
            // 最后一级是一个文件
            if (!cur.nexts.containsKey(paths[n - 1])) {
                cur.nexts.put(paths[n - 1], new Node(paths[n - 1], ""));
            }
            cur.nexts.get(paths[n - 1]).content.append(content);
        }

        public String readContentFromFile(String path) {
            Node cur = head;
            String[] paths = path.split("/");
            int n = paths.length;
            for (int i = 0; i < n; i++) {
                if (!cur.nexts.containsKey(paths[i])) {
                    cur.nexts.put(paths[i], new Node(paths[i]));
                }
                cur = cur.nexts.get(paths[i]);
            }
            return cur.content.toString();
        }


        public class Node {
            public String fileName;
            // content == null 表示是一个目录
            // content != null 表示是一个文件
            public StringBuilder content;
            // 这里使用有序表，因为返回的时候按照字典序排列
            public TreeMap<String, Node> nexts;

            public Node(String name) {
                fileName = name;
                content = null;
                nexts = new TreeMap<>();
            }

            public Node(String name, String con) {
                fileName = name;
                content = new StringBuilder(con);
                nexts = new TreeMap<>();
            }
        }
    }
}
