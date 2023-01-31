package ru.malichenko.test.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
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
