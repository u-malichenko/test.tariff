package ru.malichenko.test.service;

import io.quarkus.panache.common.Page;
import ru.malichenko.test.dto.TariffDto;
import ru.malichenko.test.enums.CategoryType;
import ru.malichenko.test.mapper.TariffMapper;
import ru.malichenko.test.model.PageRequest;
import ru.malichenko.test.model.Tariff;
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
public class TariffService {
    public static final long UNLIMITED = -1;

    @Inject
    TariffMapper tariffMapper;

    @Inject
    TariffRepository tariffRepository;

    public TariffDto getById(Long id) {
        return tariffMapper.toDto(tariffRepository.findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    public List<TariffDto> getAll() {
        return tariffMapper.toDtoList(tariffRepository.findAll().list());
    }

    @Transactional
    public List<TariffDto> getAll(PageRequest pageRequest) {
        return tariffMapper.toDtoList(tariffRepository.findAll()
                .page(Page.of(pageRequest.getPageNum(), pageRequest.getPageSize()))
                .list());
    }

    @Transactional
    public List<TariffDto> getByTitle(String title) {
        return tariffMapper.toDtoList(tariffRepository.list("title", title));
    }

    @Transactional
    public List<TariffDto> getByArchived() {
        return tariffMapper.toDtoList(tariffRepository.list("isArchived", true));
    }

    @Transactional
    public List<TariffDto> getByCategoryAndValue(CategoryType categoryType, long value) {
        return tariffMapper.toDtoList(tariffRepository.findByPockedCategoryAndValue(categoryType, value));
    }

    @Transactional
    public List<TariffDto> getWithUnlimitedVoice() {
        return tariffMapper.toDtoList(tariffRepository.findByPockedCategoryAndValue(CategoryType.VOICE, UNLIMITED));
    }

    @Transactional
    public List<TariffDto> getWithUnlimitedInternet() {
        return tariffMapper.toDtoList(tariffRepository.findByPockedCategoryAndValue(CategoryType.INTERNET, UNLIMITED));
    }

    @Transactional
    public TariffDto create(TariffDto tariff) {
        Tariff entity = tariffMapper.toEntity(tariff);
        tariffRepository.persistAndFlush(entity);
        if (tariffRepository.isPersistent(entity)) {
            Optional<Tariff> optionalEmp = tariffRepository.findByIdOptional(entity.getId());
            entity = optionalEmp.orElseThrow(NotFoundException::new);
            return tariffMapper.toDto(entity);
        } else {
            throw new PersistenceException();
        }
    }

    @Transactional
    public TariffDto update(Long id, TariffDto tariffDto) {
        Tariff entity = tariffRepository.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Tariff with id of " + id + " does not exist.", 404);
        }
        tariffMapper.updateTariffFromDto(tariffDto, entity);
        return tariffMapper.toDto(entity);
    }

    @Transactional
    public TariffDto update(TariffDto tariff) {
        Tariff entity = tariffRepository.findById(tariff.getId());
        if (entity == null) {
            throw new WebApplicationException("Tariff with id " + tariff.getId() + " does not exist.", 404);
        }
        tariffMapper.updateTariffFromDto(tariff, entity);
        entity = tariffRepository.getEntityManager().merge(entity);
        return tariffMapper.toDto(entity);
    }

    @Transactional
    public TariffDto delete(Long id) {
        Tariff deletedTariff = tariffRepository.findById(id);
        boolean isEntityDeleted = tariffRepository.deleteById(id);
        if (!isEntityDeleted) {
            throw new WebApplicationException("Tariff with id of " + id + " does not exist.", 404);
        }
        return tariffMapper.toDto(deletedTariff);
    }

    public List<TariffDto> getWithPartialMatchTitle(String partialTitle) {
        return tariffMapper.toDtoList(tariffRepository.findWithPartialMatchTitle(partialTitle));
    }
}
