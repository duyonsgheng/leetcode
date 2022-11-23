package custom.code_2022_11;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1146
 * @Author Duys
 * @Description
 * @Date 2022/11/17 15:10
 **/
// 1146. 快照数组
public class LeetCode_1146 {
    // ["SnapshotArray","set","set","snap","get","set","snap","set","set","get","get"]
    //[   [3],      [1,18],  [1,4],  [],   [0,0],[0,20],[],   [0,2],[1,1],[1,1],[1,0]]
    public static void main(String[] args) {
        SnapshotArray1 sa = new SnapshotArray1(3);
        sa.set(1, 18);
        sa.set(1, 4);
        System.out.println(sa.snap());
        System.out.println(sa.get(0, 0));
        sa.set(0, 20);
        System.out.println(sa.snap());
        sa.set(0, 2);
        sa.set(1, 1);
        System.out.println(sa.get(1, 1));
        System.out.println(sa.get(1, 0));
        // [null,null,0,1,2,15,3,4,15]
    }

    static class SnapshotArray {

        Map<Integer, Map<Integer, Integer>> snapMap;
        Map<Integer, Integer> map;
        int snapIndex;

        public SnapshotArray(int length) {
            snapMap = new HashMap<>();
            map = new HashMap<>();
            snapIndex = 0;
        }

        public void set(int index, int val) {
            map.put(index, val);
        }

        public int snap() {
            snapMap.put(snapIndex, new HashMap<>(map));
            return snapIndex++;
        }

        public int get(int index, int snap_id) {
            if (!snapMap.containsKey(snap_id)) {
                return 0;
            }
            return snapMap.get(snap_id).getOrDefault(index, 0);
        }
    }

    static class SnapshotArray1 {

        Map<Integer, Map<Integer, Integer>> snapMap;
        int[] arr;
        int snapIndex;

        public SnapshotArray1(int length) {
            snapMap = new HashMap<>();
            arr = new int[length];
            snapIndex = 0;
        }

        public void set(int index, int val) {
            arr[index] = val;
            if (!snapMap.containsKey(snapIndex)) {
                snapMap.put(snapIndex, new HashMap<>());
            }
            Map<Integer, Integer> cur = snapMap.get(snapIndex);
            cur.put(index, val);
        }

        public int snap() {
            if (snapIndex > 0 && !snapMap.containsKey(snapIndex)) {
                snapMap.put(snapIndex, new HashMap<>(snapMap.getOrDefault(snapIndex - 1, new HashMap<>())));
            }
            return snapIndex++;
        }

        public int get(int index, int snap_id) {
            if (!snapMap.containsKey(snap_id)) {
                return 0;
            }
            return snapMap.get(snap_id).getOrDefault(index, 0);
        }
    }

}
