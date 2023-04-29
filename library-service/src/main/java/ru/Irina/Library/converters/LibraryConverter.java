package ru.Irina.Library.converters;

public interface LibraryConverter<E, D> {
    public D entityToDto(E entity);

    public E dtoToEntity(E entity, D dto);

}
