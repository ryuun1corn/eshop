package operations;

import java.util.List;
import java.util.UUID;

public interface Findable<T> {
    List<T> findAll();
    T findOne(UUID modelId);
}
