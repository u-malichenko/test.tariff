package ru.malichenko.test.mapper;

import org.mapstruct.*;
import ru.malichenko.test.dto.PackageOfServicesDto;
import ru.malichenko.test.model.PackageOfServices;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PackageOfServicesMapper {
    @Named(value = "all")
    @Mapping(target = "tariffId", expression = "java((entity.getTariff()!=null)?entity.getTariff().getId():null)")
    PackageOfServicesDto toDto(PackageOfServices entity);

    @Named(value = "all")
    @Mapping(target = "tariff", ignore = true)
    PackageOfServices toEntity(PackageOfServicesDto dto);

    @IterableMapping(qualifiedByName = "all")
    List<PackageOfServicesDto> toDtoList(List<PackageOfServices> entity);

    @IterableMapping(qualifiedByName = "all")
    List<PackageOfServices> toEntityList(List<PackageOfServicesDto> dto);

    @Mapping(target = "tariff", ignore = true)
    void updatePackageOfServicesFromDto(PackageOfServicesDto dto, @MappingTarget PackageOfServices entity);
}
