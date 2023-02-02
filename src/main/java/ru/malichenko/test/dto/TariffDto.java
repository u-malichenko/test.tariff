package ru.malichenko.test.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@RegisterForReflection
public class TariffDto {
    private Long id;
    private OffsetDateTime createDate;
    private OffsetDateTime modifiedDate;
    private String title;
    private Boolean isArchived;
    private Boolean isRemoved;
    private List<PackageOfServicesDto> packageOfServicesDto;
}
