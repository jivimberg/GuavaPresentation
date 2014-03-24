package guava.presentation;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Weigher;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("UnusedDeclaration")
public class Eviction {

    public void maximunSizeEviction(){
        Cache<String, String> result = CacheBuilder.newBuilder()
                .maximumSize(1000) //The cache will try to evict entries that haven't been used recently or very often
                .build();
    }

    public void sizeBasedEviction(){
        // useful if your cache values have radically different memory footprints
        Cache<String, String> Strings = CacheBuilder.newBuilder()
                .maximumWeight(100000)
                .weigher(new Weigher<String, String>() { // Define a weigher method
                    public int weigh(String k, String v) {
                        return v.length();
                    }
                })
                .build();
    }

    public void timeBasedEviction(){
        Cache<String, String> cache1 = CacheBuilder.newBuilder()
                // Only expire entries after the specified duration has passed
                // since the entry was last accessed by a read or a write.
                .expireAfterAccess(5, TimeUnit.HOURS)
                .build();

        // or

        Cache<String, String> cache2 = CacheBuilder.newBuilder()
                //Expire entries after the specified duration has passed since the entry was created
                .expireAfterWrite(5, TimeUnit.HOURS)
                .build();
    }

    public void referenceBasedEviction(){
        // Stores keys using weak references. This allows entries to be
        // garbage-collected if there are no other references to the keys.
        Cache cache1 = CacheBuilder.newBuilder()
                .weakKeys()
                .build();

        // Stores values using weak references. This allows entries to be
        // garbage-collected if there are no other references to the values.
        Cache cache2 = CacheBuilder.newBuilder()
                .weakValues()
                .build();

        // Wraps values in soft references. Softly referenced objects are
        // garbage-collected in a globally least-recently-used manner, in response to memory demand.
        Cache cache3 = CacheBuilder.newBuilder()
                .softValues()
                .build();
    }
}
