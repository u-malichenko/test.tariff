package ru.malichenko.test.api;

import org.eclipse.microprofile.graphql.*;
import ru.malichenko.test.dto.TariffDto;
import ru.malichenko.test.enums.CategoryType;
import ru.malichenko.test.filter.TariffFilter;
import ru.malichenko.test.service.TariffService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@GraphQLApi
public class TariffFetcher {

    @Inject
    TariffService tariffService;

    @Query
    @Description("Get all Tariffs")
    @Transactional
    public List<TariffDto> getAll() {
        return tariffService.findAllByCriteria();
    }

    @Query
    @Description("Get a Tariff By Id")
    public TariffDto getById(@Name("id") long id) {
        return tariffService.findByIdWithCriteria(id);
    }

    @Query
    @Description("Get Tariffs With Partial Match Title")
    public List<TariffDto> getWithPartialMatchTitle(@Name("partialTitle") String partialTitle) {
        return tariffService.getWithPartialMatchTitle(partialTitle);
    }

    @Query
    @Description("Get Tariffs With Archived")
    public List<TariffDto> getWithArchived() {
        return tariffService.getByArchived();
    }

    @Query
    @Description("Get Tariffs By Category And Value")
    public List<TariffDto> getByCategoryAndValue(@Valid CategoryType categoryType, @Name("value") long value) {
        return tariffService.getByCategoryAndValue(categoryType, value);
    }

    @Query
    @Description("Get Tariffs By Category And Value With Criteria")
    public List<TariffDto> getByCategoryAndValueWithCriteria(@Valid CategoryType categoryType, @Name("value") long value) {
        return tariffService.findByCategoryAndValueWithCriteria(categoryType, value);
    }

    @Query
    @Description("Get Tariffs With Unlimited Voice Package")
    public List<TariffDto> getWithUnlimitedVoice() {
        return tariffService.getWithUnlimitedVoice();
    }

    @Query
    @Description("Get Tariffs With Unlimited Internet Package")
    public List<TariffDto> getWithUnlimitedInternet() {
        return tariffService.getWithUnlimitedInternet();
    }

    @Query("TariffWithFilter")
    public List<TariffDto> findWithFilter(@Name("filter") TariffFilter filter) {
        return tariffService.findByCriteria(filter);
    }
}
