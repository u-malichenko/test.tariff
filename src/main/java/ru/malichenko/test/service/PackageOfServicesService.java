package ru.malichenko.test.service;

import io.quarkus.panache.common.Page;
import ru.malichenko.test.dto.PackageOfServicesDto;
import ru.malichenko.test.enums.CategoryType;
import ru.malichenko.test.mapper.PackageOfServicesMapper;
import ru.malichenko.test.model.PageRequest;
import ru.malichenko.test.model.PackageOfServices;
import ru.malichenko.test.repository.PackageOfServicesRepository;
import ru.malichenko.test.repository.TariffRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PackageOfServicesService {

    @Inject
    PackageOfServicesMapper packageOfServicesMapper;

    @Inject
    PackageOfServicesRepository packageOfServicesRepository;

    @Inject
    TariffRepository tariffRepository;

    public PackageOfServicesDto getById(Long id) {
        return packageOfServicesMapper.toDto(packageOfServicesRepository.findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    public List<PackageOfServicesDto> getAll() {
        return packageOfServicesMapper.toDtoList(packageOfServicesRepository.findAll().list());
    }

    @Transactional
    public List<PackageOfServicesDto> getAll(PageRequest pageRequest) {
        return packageOfServicesMapper.toDtoList(packageOfServicesRepository.findAll()
                .page(Page.of(pageRequest.getPageNum(), pageRequest.getPageSize()))
                .list());
    }

    @Transactional
    public List<PackageOfServicesDto> getPackageOfServicesByTitle(String title) {
        return packageOfServicesMapper.toDtoList(packageOfServicesRepository.list("title", title));
    }

    @Transactional
    public List<PackageOfServicesDto> getPackageOfServicesByCategoryAndValue(CategoryType categoryType, long value) {
        return packageOfServicesMapper.toDtoList(packageOfServicesRepository.findByCategoryAndValue(categoryType, value));
    }

    @Transactional
    public PackageOfServicesDto create(PackageOfServicesDto packageOfServicesDto, long tariffId) {
        PackageOfServices entity = packageOfServicesMapper.toEntity(packageOfServicesDto);
        entity.setTariff(tariffRepository.findByIdOptional(tariffId).orElseThrow(NotFoundException::new));
        packageOfServicesRepository.persistAndFlush(entity);
        if (packageOfServicesRepository.isPersistent(entity)) {
            Optional<PackageOfServices> optionalEmp = packageOfServicesRepository.findByIdOptional(entity.getId());
            entity = optionalEmp.orElseThrow(NotFoundException::new);
            return packageOfServicesMapper.toDto(entity);
        } else {
            throw new PersistenceException();
        }
    }

    @Transactional
    public PackageOfServicesDto update(Long id, PackageOfServicesDto packageOfServicesDto) {
        PackageOfServices entity = packageOfServicesRepository.findById(id);
        if (entity == null) {
            throw new WebApplicationException("PackageOfServices with id of " + id + " does not exist.", 404);
        }
        packageOfServicesMapper.updatePackageOfServicesFromDto(packageOfServicesDto, entity);
        entity.setTariff(tariffRepository.findByIdOptional(packageOfServicesDto.getTariffId()).orElse(entity.getTariff()));
        return packageOfServicesMapper.toDto(entity);
    }

    @Transactional
    public PackageOfServicesDto update(PackageOfServicesDto packageOfServicesDto) {
        PackageOfServices entity = packageOfServicesRepository.findById(packageOfServicesDto.getId());
        if (entity == null) {
            throw new WebApplicationException("PackageOfServices with id " + packageOfServicesDto.getId() + " does not exist.", 404);
        }
        packageOfServicesMapper.updatePackageOfServicesFromDto(packageOfServicesDto, entity);
        entity.setTariff(tariffRepository.findByIdOptional(packageOfServicesDto.getTariffId()).orElse(entity.getTariff()));
        entity = packageOfServicesRepository.getEntityManager().merge(entity);
        return packageOfServicesMapper.toDto(entity);
    }

    @Transactional
    public PackageOfServicesDto delete(Long id) {
        PackageOfServices deletedPackageOfServices = packageOfServicesRepository.findById(id);
        boolean isEntityDeleted = packageOfServicesRepository.deleteById(id);
        if (!isEntityDeleted) {
            throw new WebApplicationException("PackageOfServices with id of " + id + " does not exist.", 404);
        }
        return packageOfServicesMapper.toDto(deletedPackageOfServices);

    }
}
