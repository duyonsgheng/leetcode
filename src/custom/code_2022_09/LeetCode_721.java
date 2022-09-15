package custom.code_2022_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_721
 * @Author Duys
 * @Description
 * @Date 2022/9/13 18:21
 **/
// 721. 账户合并
public class LeetCode_721 {

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> ans = new ArrayList<>();
        if (accounts.isEmpty() || accounts.get(0).isEmpty()) {
            return ans;
        }
        // email 进行编号
        Map<String, Integer> emailIndex = new HashMap<>();
        // email 属于哪一个名称
        Map<String, String> emailName = new HashMap<>();
        int count = 0;
        for (List<String> list : accounts) {
            String name = list.get(0);
            int curCount = list.size();
            for (int i = 1; i < curCount; i++) {
                String email = list.get(i);
                if (emailIndex.containsKey(email)) {
                    continue;
                }
                // 没每一个email编号
                emailIndex.put(email, count++);
                emailName.put(email, name);
            }
        }
        UnionFind uf = new UnionFind(count);
        // 合并
        for (List<String> list : accounts) {
            String email = list.get(1);
            int index = emailIndex.get(email);
            int size = list.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = list.get(i);
                int nextIndex = emailIndex.get(nextEmail);
                uf.union(index, nextIndex);
            }
        }
        // 每一个集合的头，下面有哪些email
        Map<Integer, List<String>> map = new HashMap<>();
        for (String email : emailIndex.keySet()) {
            int index = uf.find(emailIndex.get(email));
            if (!map.containsKey(index)) {
                map.put(index, new ArrayList<>());
            }
            map.get(index).add(email);
        }
        // 合并后，进行封装答案
        for (List<String> emails : map.values()) {
            Collections.sort(emails);
            String name = emailName.get(emails.get(0));
            List<String> curAns = new ArrayList<>();
            curAns.add(name);
            curAns.addAll(emails);
            ans.add(curAns);
        }
        return ans;
    }

    static class UnionFind {
        private int n;
        private int[] parent;
        private int[] help;
        private int[] size;

        public UnionFind(int nn) {
            n = nn;
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int a, int b) {
            int ai = find(a);
            int bi = find(b);
            if (ai == bi) {
                return;
            }
            if (size[ai] >= size[bi]) {
                size[ai] += size[bi];
                parent[bi] = ai;
            } else {
                size[bi] += size[ai];
                parent[ai] = bi;
            }
        }

        private int find(int x) {
            int hi = 0;
            while (x != parent[x]) {
                help[hi++] = x;
                x = parent[x];
            }
            for (hi--; hi >= 0; hi--)
                parent[help[hi]] = x;
            return x;
        }
    }

    public static void main(String[] args) {
        List<List<String>> ac = new ArrayList<>();
        ac.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        ac.add(Arrays.asList("John", "johnnybravo@mail.com"));
        ac.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        ac.add(Arrays.asList("Mary", "mary@mail.com"));
        List<List<String>> lists = accountsMerge(ac);
        System.out.println();
    }

}
