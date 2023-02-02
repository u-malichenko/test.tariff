package ru.malichenko.test.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;
import ru.malichenko.test.enums.CategoryType;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@RegisterForReflection
public class PackageOfServicesDto {
    private Long id;
    private OffsetDateTime createDate;
    private OffsetDateTime modifiedDate;
    private String title;
    private CategoryType category;
    private Long value;
    private Boolean isRemoved;
    private Long tariffId;
}
