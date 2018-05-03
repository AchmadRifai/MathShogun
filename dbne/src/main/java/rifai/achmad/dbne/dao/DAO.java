package rifai.achmad.dbne.dao;

import java.util.List;

public interface DAO<T> {
    void insert(T v);
    void delete(T w);
    void update(T a,T b);
    List<T>all();
}
