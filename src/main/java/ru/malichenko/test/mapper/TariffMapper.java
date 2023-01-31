package ru.malichenko.test.mapper;

import org.mapstruct.*;
import ru.malichenko.test.dto.PackageOfServicesDto;
import ru.malichenko.test.dto.TariffDto;
import ru.malichenko.test.model.PackageOfServices;
import ru.malichenko.test.model.Tariff;

import java.util.List;

@Mapper(componentModel = "cdi", uses = {PackageOfServicesMapper.class})
public interface TariffMapper {
    @Named(value = "all")
    @Mapping(source = "packageOfServices", target = "packageOfServicesDto")
    TariffDto toDto(Tariff entity);

    @Mapping(source = "packageOfServicesDto", target = "packageOfServices")
    @Named(value = "all")
    Tariff toEntity(TariffDto dto);

    @IterableMapping(qualifiedByName = "all")
    List<TariffDto> toDtoList(List<Tariff> entity);

    @IterableMapping(qualifiedByName = "all")
    List<Tariff> toEntityList(List<TariffDto> entity);

    @Mapping(source = "packageOfServicesDto", target = "packageOfServices")
    void updateTariffFromDto(TariffDto dto, @MappingTarget Tariff entity);
}
