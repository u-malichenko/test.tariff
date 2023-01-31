package ru.malichenko.test.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.malichenko.test.enums.CategoryType;
import ru.malichenko.test.model.PackageOfServices;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public class PackageOfServicesRepository implements PanacheRepository<PackageOfServices> {

    public List<PackageOfServices> findByCategoryAndValue(CategoryType categoryType, long value) {
        return list("SELECT p FROM PackageOfServices p WHERE p.category = ?1 and p.value = ?2 and p.isRemoved != false", categoryType, value);
    }
}
