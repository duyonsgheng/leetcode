package week.week_2021_12_04;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_02_MergeArea
 * @Author Duys
 * @Description TODO
 * @Date 2022/4/14 15:40
 **/
// 来自北京北明数科信息技术有限公司
// area表示的是地区全路径,最多可能有6级,用分隔符连接,分隔符是 spliter,
// 分隔符是逗号
// 例如：
// area = 中国,四川,成都  或者  中国,浙江,杭州  或者  中国,浙江,义乌
// spliter = ,
// count表示门店数
// class AreaResource {
//     String area;
//     String spliter;
//     long count;
// }

// area = "中国,四川,成都"
// spliter = ","
// count = 10

// 现在需要把  List<AreaResource> 进行字符串转换，供下游处理，需要做到同级的地域能合并
// 比如
// area为 中国,四川,成都  有10个门店
//        中国,浙江,杭州 有25个门店
//        中国,浙江,义乌 有22个门店
//        中国,四川,成都 有25个门店
// spliter为逗号 "," 最终转化成JSON的形式，并且同级的地域需要被合并，最终生成的JSON字符串如下所示
//
// 返回: {
//    "中国":
//           {"四川":{"成都":35]},
//            "浙江":{"义乌":22,"杭州":25}}}
// 请实现下面的方法 public String mergeCount(List<AreaResource> areas)
public class Code_02_MergeArea {
    // 系统给你的原始类
    public static class AreaResource {
        public String area;
        public String spliter;
        public long count;

        public AreaResource(String a, String s, long c) {
            area = a;
            spliter = s;
            count = c;
        }
    }

    // 当然这个题  也可以使用面向对象的思想来做
    public static String mergeCount(List<AreaResource> areas) {
        Area all = new Area("", 0);
        for (AreaResource re : areas) {
            String[] sp = re.area.split(re.spliter);
            long count = re.count;
            fill(all, 0, count, sp);
        }
        return all.toString();
    }

    public static void fill(Area pre, int index, long count, String[] path) {
        if (index == path.length) {
            pre.count += count;
        } else {
            String cur = path[index];
            if (!pre.nexts.containsKey(cur)) {
                pre.nexts.put(cur, new Area(cur, 0));
            }
            fill(pre.nexts.get(cur), index + 1, count, path);
        }
    }

    //前缀树
    public static class Area {
        public String name;
        public Map<String, Area> nexts;
        public long count;

        public Area(String ne, int ct) {
            name = ne;
            count = ct;
            nexts = new HashMap<>();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (!name.equals("")) {
                sb.append("\"" + name + "\"" + ":");
            }
            if (nexts.isEmpty()) {
                sb.append(count);
            } else {
                sb.append("{");
                for (Area child : nexts.values()) {
                    sb.append(child.toString() + ",");
                }
                sb.append("}");
            }
            return sb.toString();
        }
    }
}
