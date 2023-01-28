package ru.malichenko.test.model;
//        - Название. Обязательное, уникальное в рамках всех не удаленных тарифов, <= 128 символов.

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "demo_tariff")
//,uniqueConstraints = {@UniqueConstraint(name = "UniqueTitleAndRemoved", columnNames = {"title", "removed"})})
public class Tariff extends BaseEntity {

    @NotEmpty(message = "Title may not be empty")
    @Size(min = 1, max = 128, message = "Title must be between 1 and 128 characters long")
    @Column(name = "TITLE", nullable = false, length = 128)
    private String title;

    @NotEmpty(message = "Field archived may not be empty")
    @Column(name = "ARCHIVED", nullable = false, columnDefinition = "boolean default false")
    private Boolean isArchived;

    @NotEmpty(message = "Field removed may not be empty")
    @Column(name = "REMOVED", nullable = false, columnDefinition = "boolean default false")
    private Boolean isRemoved;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    final private List<PackageOfServices> packageOfServices = new ArrayList<>();

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
