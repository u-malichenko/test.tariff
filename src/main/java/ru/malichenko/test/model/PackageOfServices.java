package ru.malichenko.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.malichenko.test.enums.CategoryType;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "demo_package_of_services")
public class PackageOfServices extends BaseEntity {

    @NotEmpty(message = "Title may not be empty")
    @Size(min = 1, max = 128, message = "Title must be between 1 and 128 characters long")
    @Column(name = "TITLE", nullable = false , length = 128)
    private String title;

    @Enumerated(EnumType.ORDINAL)
    @NotEmpty(message = "Field value may not be empty")
    @Column(name = "CATEGORY", nullable = false)
    private CategoryType category;

    @NotEmpty(message = "Field value may not be empty")
    @Column(name = "VALUE", nullable = false)
    private Long value;

    @NotEmpty(message = "Field removed may not be empty")
    @Column(name = "REMOVED", nullable = false , columnDefinition = "boolean default false")
    private Boolean isRemoved;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TARIFF_ID", nullable = false)
    private Tariff tariff;
}
