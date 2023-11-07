package custom.code_2023_08;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2115
 * @date 2023年08月18日
 */
// 2115. 从给定原材料中找到所有可以做出的菜
// https://leetcode.cn/problems/find-all-possible-recipes-from-given-supplies/
public class LeetCode_2115 {
    // 拓扑排序的
    public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> ingred = new HashMap<>(); // 入度表
        int n = recipes.length;
        for (int i = 0; i < n; i++) {
            for (String s : ingredients.get(i)) {
                // 当前原料能做成那些菜
                List<String> strings = graph.getOrDefault(s, new ArrayList<>());
                strings.add(recipes[i]);
                graph.put(s, strings);
            }
            // 当前的菜需要多少原料
            ingred.put(recipes[i], ingredients.get(i).size());
        }
        List<String> ans = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        for (String s : supplies) {
            queue.add(s);
        }
        while (!queue.isEmpty()) {
            // 当前原料
            String cur = queue.poll();
            // 看看原料能做成那些菜
            for (String next : graph.getOrDefault(cur, new ArrayList<>())) {
                // 已经成品的菜，原料可以减1
                ingred.put(next, ingred.get(next) - 1);
                if (ingred.get(next) == 0) {
                    queue.offer(next);
                    ans.add(next);
                }
            }
        }
        return ans;
    }

    // 暴力点的
    public static List<String> findAllRecipes1(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> set = new HashSet<>();
        for (String s : supplies) {
            set.add(s);
        }
        List<String> ans = new ArrayList<>();
        int n = recipes.length;
        int k = n;
        while (k-- != 0) {
            for (int i = 0; i < n; i++) {
                if (set.contains(recipes[i])) {
                    continue;
                }
                boolean flag = true;
                for (String s : ingredients.get(i)) {
                    if (!set.contains(s)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    set.add(recipes[i]);
                    ans.add(recipes[i]);
                }
            }
            if (ans.size() == n) {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // ["bread","sandwich","burger"],
        // ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]],
        // supplies = ["yeast","flour","meat"]
        //输出：["bread","sandwich","burger"]
    }
}
