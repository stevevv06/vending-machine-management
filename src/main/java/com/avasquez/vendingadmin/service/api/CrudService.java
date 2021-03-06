package com.avasquez.vendingadmin.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 *
 * @param <T>
 * @param <I>
 */
public interface CrudService<T, I> {
    Page<T> findAll(Pageable pageable);
    Optional<T> find(I id);
    T save(T o);
    List<T> save(List<T> o);
    void delete(I id);
}
