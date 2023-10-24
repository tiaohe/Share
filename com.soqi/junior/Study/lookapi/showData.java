package lookapi;

import com.mysql.cj.util.LRUCache;
import org.springframework.data.util.Optionals;

import java.awt.geom.GeneralPath;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class showData {
    public static void main(String[] args) {
        var f = new GeneratorMethod();

        f.f("123");
        f.f(123);

        AtomicInteger ato = new AtomicInteger();

        f.f(ato);
        LRUCache c = new LRUCache(1234);
        f.f(c);

        f.f(f);



    }


}

class GeneratorMethod {
    public <T> void f (T t) {
        System.out.println(t.getClass().getName());
    }
}


interface Generator<T> {
    public T next();
}

class Generic<T> {
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }
}
