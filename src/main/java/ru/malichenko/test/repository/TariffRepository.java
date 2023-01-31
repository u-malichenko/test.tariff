package ru.malichenko.test.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.malichenko.test.enums.CategoryType;
import ru.malichenko.test.model.PackageOfServices;
import ru.malichenko.test.model.Tariff;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TariffRepository implements PanacheRepository<Tariff> {

    @Inject
    PackageOfServicesRepository packageOfServicesRepository;

    public List<Tariff> findByPockedCategoryAndValue(CategoryType categoryType, long value) {
        return packageOfServicesRepository.findByCategoryAndValue(categoryType, value).stream().map(PackageOfServices::getTariff).toList();
    }
    public List<Tariff> findWithPartialMatchTitle(String partialTitle) {
        String partialTitleQuery = ("%"+partialTitle+"%");
        return list("SELECT t FROM Tariff t WHERE t.title LIKE ?1", partialTitleQuery);
    }
}
