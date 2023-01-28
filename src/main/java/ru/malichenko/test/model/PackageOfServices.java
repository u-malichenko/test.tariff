package ru.malichenko.test.model;

import lombok.*;
import ru.malichenko.test.enums.CategoryType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
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
    private Boolean removed;

    @ManyToOne
    @JoinColumn(name = "TARIFF_ID", nullable = false)
    private Tariff tariff;

//    @Override
//    public boolean isNew() {
//        return super.isNew();
//    }
//
//    @Override
//    public Long getId() {
//        return super.getId();
//    }
//
//    @Override
//    public void setId(Long id) {
//        super.setId(id);
//    }
//
//    @Override
//    public OffsetDateTime getModifiedDate() {
//        return super.getModifiedDate();
//    }
//
//    @Override
//    public void setModifiedDate(OffsetDateTime modifiedDate) {
//        super.setModifiedDate(modifiedDate);
//    }
}
