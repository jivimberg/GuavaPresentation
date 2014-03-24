package guava.presentation.sections.caches;

import com.google.common.cache.*;
import com.google.common.collect.ImmutableSet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"UnnecessaryLocalVariable", "UnusedDeclaration", "ConstantConditions"})
public class Caches {

    // Creation
    FromCacheLoader fromCacheLoader;
    FromCallable fromCallable;

    // Eviction
    Eviction eviction;

    // Other goodies
    public void explicitRemoval(){
        FromCacheLoader cacheFactory = new FromCacheLoader();
        LoadingCache<String,String> cache = cacheFactory.create();

        cache.invalidate("a key"); // individual
        cache.invalidateAll(ImmutableSet.of("key1", "key2", "key3")); //bulk
        cache.invalidateAll(); // all entries
    }

    public void removalListeners(){
        RemovalListener<String, DatabaseConnection> removalListener = new RemovalListener<String, DatabaseConnection>() {
            public void onRemoval(RemovalNotification<String, DatabaseConnection> removal) {
                DatabaseConnection conn = removal.getValue();
                conn.close(); // close the connection
            }
        };

        Cache<String, DatabaseConnection> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .removalListener(removalListener)
                .build();
    }

    /**
     * Record statistics!
     */
    public static void main(String[] args) {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .recordStats()
                .build(
                        new CacheLoader<String, String>() {
                            public String load(String key) throws IOException {
                                return Caches.intesiveQuery(key);
                            }
                        });

        // Dangerous use of getUnchecked!!
        cache.getUnchecked("a key");
        cache.getUnchecked("a key");
        cache.getUnchecked("a key");

        CacheStats stats = cache.stats();
        System.out.println("stats.hitCount() = " + stats.hitCount());
        System.out.println("stats.loadCount() = " + stats.loadCount());
        System.out.println("stats.requestCount() = " + stats.requestCount());
        // etc...
    }

    //TODO see also: Refresh (which loads a new value for the key) and asMap (a concurrent map view of the cache)

    // -------------- Helpers --------------

    public static String intesiveQuery(String key) throws IOException{
        System.out.println("This query takes a long time");
        return "result";
    }

    private interface DatabaseConnection {
        public void close();
    }
}
