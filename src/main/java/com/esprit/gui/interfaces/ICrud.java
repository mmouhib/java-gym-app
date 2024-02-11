package com.esprit.gui.interfaces;

import java.util.List;

public interface ICrud<T> {
    // crud operations
    T save(T t);

    void update(T t);

    void delete(int id);

    T findById(int id);

    List<T> list();

}
