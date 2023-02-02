package ru.malichenko.test.api;

import org.eclipse.microprofile.graphql.*;
import ru.malichenko.test.dto.TariffDto;
import ru.malichenko.test.enums.CategoryType;
import ru.malichenko.test.filter.TariffFilter;
import ru.malichenko.test.service.PackageOfServicesService;
import ru.malichenko.test.service.TariffService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@GraphQLApi
public class TariffMutation {

    @Inject
    TariffService tariffService;

    @Inject
    PackageOfServicesService packageOfServicesService;

    @Mutation
    @Description("Create Tariff from TariffDto")
    public TariffDto create(@Valid TariffDto tariffDto) {
        tariffService.create(tariffDto);
        return tariffDto;
    }

    @Mutation
    @Description("Update Tariff from TariffDto")
    public TariffDto update(@Valid TariffDto tariffDto) {
        tariffService.update(tariffDto);
        return tariffDto;
    }

    @Mutation
    @Description("Update Tariff by id from TariffDto")
    public TariffDto updateById(@Name("id") Long id, @Valid TariffDto tariffDto) {
        tariffService.updateById(id, tariffDto);
        return tariffDto;
    }

    @Mutation
    @Description("Delete Tariff by id")
    public TariffDto delete(@Name("id") long id) {
        return tariffService.delete(id);
    }
}
