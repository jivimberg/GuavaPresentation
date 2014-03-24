package guava.presentation;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableSet;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("UnnecessaryLocalVariable")
public class FromCacheLoader {

    public LoadingCache<String, String> create(){
        LoadingCache<String, String> result = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(
                        new CacheLoader<String, String>() {
                            public String load(String key) throws IOException {
                                return Caches.intesiveQuery(key);
                            }
                        });
        return result;
    }

    public static void main(String[] args) {
        FromCacheLoader caches = new FromCacheLoader();
        LoadingCache<String, String> cache = caches.create();

        try {
            // First time will call load
            cache.get("a key");

            // Now the value is already cached
            cache.get("a key");
            cache.get("a key");
            cache.get("a key");
        } catch (ExecutionException e) {
            System.out.println("Exception thrown!");
        }
    }

    public void getAll(){
        FromCacheLoader fromCacheLoader = new FromCacheLoader();
        LoadingCache<String, String> cache = fromCacheLoader.create();

        try {
            // You can also use getAll which will call individual loads on the cache
            cache.getAll(ImmutableSet.of("key1", "key2", "key3"));

            // Or you can override the loadAll method in the cache to execute intelligent bulk loads.
        } catch (ExecutionException e) {
            System.out.println("Exception thrown!");
        }
    }
}
