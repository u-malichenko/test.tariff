package ru.malichenko.test.api;

import org.eclipse.microprofile.graphql.*;
import ru.malichenko.test.dto.PackageOfServicesDto;
import ru.malichenko.test.service.PackageOfServicesService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@GraphQLApi
public class PackageOfServicesFetcher {

    @Inject
    PackageOfServicesService packageOfServicesService;

    @Query("allPackageOfServices")
    @Description("Get all PackageOfServices")
    @Transactional
    public List<PackageOfServicesDto> getAll() {
        return packageOfServicesService.getAll();
    }

    @Query
    @Description("Get a PackageOfServices By Id")
    public PackageOfServicesDto getPackageOfServices(@Name("id") long id) {
        return packageOfServicesService.getById(id);
    }
}
