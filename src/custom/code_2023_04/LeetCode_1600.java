package custom.code_2023_04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_1600
 * @Author Duys
 * @Description
 * @Date 2023/4/18 10:06
 **/
// 1600. 王位继承顺序
public class LeetCode_1600 {
    static class ThroneInheritance {
        String king = null;
        Map<String, List<String>> childs = new HashMap<>();
        Set<String> deaths = new HashSet<>();

        public ThroneInheritance(String kingName) {
            king = kingName;
        }

        public void birth(String parentName, String childName) {
            if (!childs.containsKey(parentName)) {
                childs.put(parentName, new ArrayList<>());
            }
            this.childs.get(parentName).add(childName);
        }

        public void death(String name) {
            deaths.add(name);
        }

        public List<String> getInheritanceOrder() {
            List<String> ans = new ArrayList<>();
            process(king, ans);
            return ans;
        }

        public void process(String cur, List<String> ans) {
            if (!deaths.contains(cur)) {
                ans.add(cur);
            }
            // 国王已经死了
            List<String> list = childs.get(cur);
            if (list == null || list.size() <= 0) {
                return;
            }
            for (String name : list) {
                process(name, ans);
            }
        }
    }

    public static void main(String[] args) {
        // ["ThroneInheritance","birth","birth","birth","birth","birth","birth","getInheritanceOrder","death","getInheritanceOrder"]
        //[["king"],["king","andy"],["king","bob"],["king","catherine"],["andy","matthew"],["bob","alex"],["bob","asha"],[null],["bob"],[null]]
        ThroneInheritance ti = new ThroneInheritance("king");
        ti.birth("king", "andy");
        ti.birth("king", "bob");
        ti.birth("king", "catherine");

        ti.birth("andy", "matthew");
        ti.birth("bob", "alex");
        ti.birth("bob", "asha");
        ti.death("bob");

        System.out.println(ti.getInheritanceOrder());

    }
}
