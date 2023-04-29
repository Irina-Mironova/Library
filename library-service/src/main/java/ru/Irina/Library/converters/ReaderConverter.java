package ru.Irina.Library.converters;

import org.springframework.stereotype.Component;
import ru.Irina.Library.entities.Reader;
import ru.Irina.api.ReaderDto;

@Component
public class ReaderConverter implements LibraryConverter<Reader, ReaderDto> {

    @Override
    public ReaderDto entityToDto(Reader entity) {
        return new ReaderDto(entity.getReaderId(),
                entity.getReaderName(),
                entity.getDtBirth(),
                entity.getAddress(),
                entity.getPhone());
    }

    @Override
    public Reader dtoToEntity(Reader entity, ReaderDto dto) {
        if (entity == null) {
            entity = new Reader();
        }

        entity.setReaderName(dto.getReaderName());
        entity.setDtBirth(dto.getDtBirth());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());

        return entity;
    }
}

