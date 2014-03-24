package guava.presentation.sections.caches;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("UnnecessaryLocalVariable")
public class FromCallable {

    public Cache<String, String> create(){
        Cache<String, String> result = CacheBuilder.newBuilder()
                .build(); // look Ma, no CacheLoader
        return result;
    }

    public static void main(String[] args) {
        FromCallable fromCallable = new FromCallable();
        Cache<String, String> cache = fromCallable.create();

        try {
            // This method provides a simple substitute for the conventional "if cached,
            // return; otherwise create, cache and return" pattern.
            final String key = "a key";
            cache.get(key, new Callable<String>() {
                @Override
                public String call() throws IOException {
                    return Caches.intesiveQuery(key);
                }
            });
        } catch (ExecutionException e) {
            System.out.println("Exception thrown!");
        }

        // Since there is no cache loader defined this won't compile:
//        cache.get("a key");
    }
}
