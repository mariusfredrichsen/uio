package assignement1;

import java.util.Map;

public abstract class Mapper<T, R> {
    protected final Map<R, LinkedQueue<T>> layer;
    protected int count = 0;

    protected Mapper(Map<R, LinkedQueue<T>> layer) {
        this.layer = layer;
    }

    abstract void transform(T input);
}
