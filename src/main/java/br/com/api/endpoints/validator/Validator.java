package br.com.api.endpoints.validator;

public interface Validator<T>{
    void validate(T entity);
}
