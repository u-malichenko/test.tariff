package ru.malichenko.test.api;

import org.eclipse.microprofile.graphql.*;
import ru.malichenko.test.dto.PackageOfServicesDto;
import ru.malichenko.test.dto.TariffDto;
import ru.malichenko.test.enums.CategoryType;
import ru.malichenko.test.service.PackageOfServicesService;
import ru.malichenko.test.service.TariffService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@GraphQLApi
public class TariffResourceGraphQL {

    @Inject
    TariffService tariffService;

    @Inject
    PackageOfServicesService packageOfServicesService;

    @Query("allTariffs")
    @Description("Get all Tariffs")
    @Transactional
    public List<TariffDto> getAllTariff() {
        return tariffService.getAll();
    }

    @Query
    @Description("Get a Tariff By Id")
    public TariffDto getTariff(@Name("tariffId") long id) {
        return tariffService.getById(id);
    }

    @Mutation
    @Description("Create Tariff from TariffDto")
    public TariffDto createTariff(@Valid TariffDto tariffDto) {
        tariffService.create(tariffDto);
        return tariffDto;
    }

    @Mutation
    @Description("Create PackageOfServices from PackageOfServicesDto and tariffId")
    public PackageOfServicesDto createPackageOfServices(@Valid PackageOfServicesDto packageOfServicesDto, Long tariffId) {
        packageOfServicesService.create(packageOfServicesDto, tariffId);
        return packageOfServicesDto;
    }

    @Mutation
    @Description("Update Tariff from TariffDto")
    public TariffDto updateTariff(@Valid TariffDto tariffDto) {
        tariffService.update(tariffDto);
        return tariffDto;
    }

    @Mutation
    @Description("Update PackageOfServices by id from PackageOfServicesDto")
    public PackageOfServicesDto updatePackageOfServices(Long id, @Valid PackageOfServicesDto packageOfServicesDto) {
        packageOfServicesService.update(id, packageOfServicesDto);
        return packageOfServicesDto;
    }

    @Mutation
    @Description("Update Tariff by id from TariffDto")
    public TariffDto updateTariff(Long id, @Valid TariffDto tariffDto) {
        tariffService.update(id, tariffDto);
        return tariffDto;
    }

    @Mutation
    @Description("Update PackageOfServices from PackageOfServicesDto")
    public PackageOfServicesDto updatePackageOfServices(@Valid PackageOfServicesDto packageOfServicesDto) {
        packageOfServicesService.update(packageOfServicesDto);
        return packageOfServicesDto;
    }

    @Mutation
    @Description("Delete Tariff by id")
    public PackageOfServicesDto deleteTariff(long id) {
        return packageOfServicesService.delete(id);
    }

    @Mutation
    @Description("Delete PackageOfServices by id")
    public TariffDto deletePackageOfServices(long id) {
        return tariffService.delete(id);
    }

    @Query
    @Description("Get Tariffs With Partial Match Title")
    public List<TariffDto> getTariffsWithPartialMatchTitle(String partialTitle) {
        return tariffService.getWithPartialMatchTitle(partialTitle);
    }

    @Query
    @Description("Get Tariffs With Archived")
    public List<TariffDto> getTariffsWithArchived() {
        return tariffService.getByArchived();
    }

    @Query
    @Description("Get Tariffs By Category And Value")
    public List<TariffDto> getTariffsByCategoryAndValue(@Valid CategoryType categoryType, long value) {
        return tariffService.getByCategoryAndValue(categoryType, value);
    }

    @Query
    @Description("Get Tariffs With Unlimited Voice Package")
    public List<TariffDto> getTariffsWithUnlimitedVoice() {
        return tariffService.getWithUnlimitedVoice();
    }

    @Query
    @Description("Get Tariffs With Unlimited Internet Package")
    public List<TariffDto> getTariffsWithUnlimitedInternet() {
        return tariffService.getWithUnlimitedInternet();
    }
}
