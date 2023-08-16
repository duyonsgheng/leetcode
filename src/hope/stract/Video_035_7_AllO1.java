package hope.stract;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Mr.Du
 * @ClassName Video_035_7_AllO1
 * @date 2023年08月16日
 */
// https://leetcode.cn/problems/all-oone-data-structure/
// // 全O(1)的数据结构
public class Video_035_7_AllO1 {
    class AllOne {
        Bucket head;
        Bucket tail;
        Map<String, Bucket> map;

        public AllOne() {
            map = new HashMap<>();
            head = new Bucket("", 0);
            tail = new Bucket("", Integer.MAX_VALUE);
            head.next = tail;
            tail.last = head;
        }

        public void inc(String key) {
            if (!map.containsKey(key)) {
                if (head.next.cnt == 1) {
                    map.put(key, head.next);
                    head.next.set.add(key);
                } else {
                    Bucket bucket = new Bucket(key, 1);
                    map.put(key, bucket);
                    insert(head, bucket);
                }
            } else {
                Bucket bucket = map.get(key);
                if (bucket.next.cnt == bucket.cnt + 1) {
                    map.put(key, bucket.next);
                    bucket.next.set.add(key);
                } else {
                    Bucket newB = new Bucket(key, bucket.cnt + 1);
                    map.put(key, newB);
                    insert(bucket, newB);
                }
                bucket.set.remove(key);
                if (bucket.set.isEmpty()) {
                    remove(bucket);
                }
            }
        }

        public void dec(String key) {
            Bucket bucket = map.get(key);
            if (bucket.cnt == 1) {
                map.remove(key);
            } else {
                if (bucket.last.cnt == bucket.cnt - 1) {
                    map.put(key, bucket.last);
                    bucket.last.set.add(key);
                } else {
                    Bucket newB = new Bucket(key, bucket.cnt - 1);
                    map.put(key, newB);
                    insert(bucket.last, newB);
                }
            }
            bucket.set.remove(key);
            if (bucket.set.isEmpty()) {
                remove(bucket);
            }
        }

        public String getMaxKey() {
            return tail.last.set.iterator().next();
        }

        public String getMinKey() {
            return head.next.set.iterator().next();
        }

        public void insert(Bucket cur, Bucket pos) {
            cur.next.last = pos;
            pos.next = cur.next;
            cur.next = pos;
            pos.last = cur;
        }

        public void remove(Bucket cur) {
            cur.last.next = cur.next;
            cur.next.last = cur.last;
        }

        class Bucket {
            public Set<String> set;
            public int cnt;
            public Bucket last;
            public Bucket next;

            public Bucket(String s, int c) {
                set = new HashSet<>();
                set.add(s);
                cnt = c;
            }
        }
    }
}
