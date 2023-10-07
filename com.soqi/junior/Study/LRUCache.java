import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    LinkedHashMap<Integer, Integer> cache;
    int cap;

    public LRUCache(int capcity) {
        this.cap = capcity;
        // 指定了它的容量,负载因子和访问顺序
        cache = new LinkedHashMap<Integer, Integer>(capcity, 0.75f, true) {
            //重写removeEldestEntry方法检查缓存的当前大小是否超过了容量。
            // 如果超过了容量，那么就返回true，表示应该删除最老的条目
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return this.size() > capcity;
            }
        };
    }

    public int get(int key) {
        Integer value = cache.get(key);
        return value == null ? -1 : value;
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}

