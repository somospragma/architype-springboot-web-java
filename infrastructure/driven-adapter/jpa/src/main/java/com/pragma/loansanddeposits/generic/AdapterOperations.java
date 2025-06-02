package com.pragma.loansanddeposits.generic;

import com.pragma.loansanddeposits.mapper.GenericDataMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

import static java.util.stream.StreamSupport.stream;

public abstract class AdapterOperations<E, D, I, R extends CrudRepository<D, I> & QueryByExampleExecutor<D>, M extends GenericDataMapper<E, D>> {
    protected R repository;
    private final M mapper;


    @SuppressWarnings("unchecked")
    protected AdapterOperations(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    protected D toData(E entity) {
        return entity != null ? mapper.toData(entity) : null;
    }

    protected E toEntity(D data) {
        return data != null ? mapper.toEntity(data) : null;
    }

    public E save(E entity) {
        D data = toData(entity);
        return toEntity(repository.save(data));
    }

    public List<E> saveAll(List<E> entities) {
        List<D> dataList = entities.stream().map(this::toData).toList();
        Iterable<D> saved = repository.saveAll(dataList);
        return toList(saved);
    }

    public E findById(I id) {
        return toEntity(repository.findById(id).orElse(null));
    }

    public List<E> findByExample(E entity) {
        return toList(repository.findAll(Example.of(toData(entity))));
    }

    public List<E> findAll() {
        return toList(repository.findAll());
    }

    public List<E> toList(Iterable<D> iterable) {
        return stream(iterable.spliterator(), false).map(this::toEntity).toList();
    }
}
