package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.mongo.loantrace.generic;

import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.helper.mapper.GenericDataMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static java.util.stream.StreamSupport.stream;

public abstract class MongoAdapterOperations<E, D, I, R extends MongoRepository<D, I>
        & QueryByExampleExecutor<D>, M extends GenericDataMapper<E ,D>> {
    protected R repository;
    protected final M mapper;

    @SuppressWarnings("unchecked")
    protected MongoAdapterOperations(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    protected D toData(E entity) {
        return mapper.toData(entity);
    }

    protected E toEntity(D data) {
        return data != null ? mapper.toEntity(data) : null;
    }

    public E save(E entity) {
        D data = toData(entity);
        return toEntity(saveData(data));
    }

    protected List<E> saveAllEntities(List<E> entities) {
        List<D> list = entities.stream().map(this::toData).toList();
        return toList(saveData(list));
    }

    private List<E> toList(Iterable<D> iterable) {
        return stream(iterable.spliterator(), false).map(this::toEntity).toList();
    }

    private D saveData(D data) {
        return repository.save(data);
    }

    protected Iterable<D> saveData(List<D> data) {
        return repository.saveAll(data);
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
}
