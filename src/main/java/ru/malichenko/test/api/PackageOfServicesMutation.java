package ru.malichenko.test.api;

import org.eclipse.microprofile.graphql.*;
import ru.malichenko.test.dto.PackageOfServicesDto;
import ru.malichenko.test.service.PackageOfServicesService;

import javax.inject.Inject;
import javax.validation.Valid;

@GraphQLApi
public class PackageOfServicesMutation {

    @Inject
    PackageOfServicesService packageOfServicesService;

    @Mutation
    @Description("Create PackageOfServices from PackageOfServicesDto and PackageOfServicesId")
    public PackageOfServicesDto createPackageOfServices(@Valid PackageOfServicesDto packageOfServicesDto) {
        packageOfServicesService.create(packageOfServicesDto);
        return packageOfServicesDto;
    }

    @Mutation
    @Description("Update PackageOfServices by id from PackageOfServicesDto")
    public PackageOfServicesDto updateById(@Name("id") Long id, @Valid PackageOfServicesDto packageOfServicesDto) {
        packageOfServicesService.updateById(id, packageOfServicesDto);
        return packageOfServicesDto;
    }

    @Mutation
    @Description("Update PackageOfServices from PackageOfServicesDto")
    public PackageOfServicesDto update(@Valid PackageOfServicesDto packageOfServicesDto) {
        packageOfServicesService.update(packageOfServicesDto);
        return packageOfServicesDto;
    }

    @Mutation
    @Description("Delete PackageOfServices by id")
    public PackageOfServicesDto deletePackageOfServices(@Name("id") long id) {
        return packageOfServicesService.delete(id);
    }
}
