package ru.malichenko.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotEmpty
    @CreationTimestamp
    @Column(name = "CREATE_DATE", updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private OffsetDateTime createDate;

    @Column(name = "MODIFIED_DATE")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private OffsetDateTime modifiedDate;
//
//    public boolean isNew() {
//        return this.id == null;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public OffsetDateTime getModifiedDate() {
//        return modifiedDate;
//    }
//
//    public void setModifiedDate(OffsetDateTime modifiedDate) {
//        this.modifiedDate = modifiedDate;
//    }
}
