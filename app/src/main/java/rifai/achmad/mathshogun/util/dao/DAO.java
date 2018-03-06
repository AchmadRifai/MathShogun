package rifai.achmad.mathshogun.util.dao;

import org.json.JSONException;

import java.util.List;

/**
 * Created by ai on 06/03/2018.
 */

interface DAO<T>{
    void insert(T v);
    void delete(T w);
    void update(T a,T b);
    List<T>all()throws JSONException;
}
