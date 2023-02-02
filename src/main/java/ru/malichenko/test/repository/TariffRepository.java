package ru.malichenko.test.repository;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.smallrye.graphql.api.Context;
import org.hibernate.Metamodel;
import org.hibernate.type.EntityType;
import ru.malichenko.test.enums.CategoryType;
import ru.malichenko.test.filter.TariffFilter;
import ru.malichenko.test.model.PackageOfServices;
import ru.malichenko.test.model.Tariff;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.ListAttribute;

import java.util.List;

@ApplicationScoped
public class TariffRepository implements PanacheRepository<Tariff> {

    @Inject
    PackageOfServicesRepository packageOfServicesRepository;

    @Inject
    EntityManager entityManager;

    @Inject
    Context context;

    public List<Tariff> findByPockedCategoryAndValue(CategoryType categoryType, long value) {
        return packageOfServicesRepository.findByCategoryAndValue(categoryType, value).stream().map(PackageOfServices::getTariff).toList();
    }

    public List<Tariff> findWithPartialMatchTitle(String partialTitle) {
        String partialTitleQuery = ("%" + partialTitle + "%");
        return list("SELECT t FROM Tariff t WHERE t.title LIKE ?1", partialTitleQuery);
    }

    public List<Tariff> findAllByCriteria() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tariff> criteriaQuery = criteriaBuilder.createQuery(Tariff.class);
        Root<Tariff> root = criteriaQuery.from(Tariff.class);
        DataFetchingEnvironment dfe = context.unwrap(DataFetchingEnvironment.class);
        DataFetchingFieldSelectionSet selectionSet = dfe.getSelectionSet();
        if (selectionSet.contains("packageOfServices")) {
            root.fetch("packageOfServices", JoinType.LEFT);
        }
        criteriaQuery.select(root).distinct(true);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Tariff findByIdWithCriteria(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tariff> criteriaQuery = criteriaBuilder.createQuery(Tariff.class);
        Root<Tariff> root = criteriaQuery.from(Tariff.class);
        DataFetchingEnvironment dfe = context.unwrap(DataFetchingEnvironment.class);
        DataFetchingFieldSelectionSet selectionSet = dfe.getSelectionSet();
        if (selectionSet.contains("packageOfServices")) {
            root.fetch("packageOfServices", JoinType.LEFT);
        }
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public List<Tariff> findByCategoryAndValueWithCriteria(CategoryType categoryType, long value) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tariff> criteriaQueryTariff = criteriaBuilder.createQuery(Tariff.class);
        CriteriaQuery<PackageOfServices> criteriaQueryPackageOfServices = criteriaBuilder.createQuery(PackageOfServices.class);

        Root<Tariff> rootTariff = criteriaQueryTariff.from(Tariff.class);
        Root<PackageOfServices> rootPackageOfServices = criteriaQueryTariff.from(PackageOfServices.class);

//        Path<PackageOfServices> owner = rootTariff.get("packageOfServices");
////        Path<String> name = owner.get("value");

        DataFetchingEnvironment dfe = context.unwrap(DataFetchingEnvironment.class);
        DataFetchingFieldSelectionSet selectionSet = dfe.getSelectionSet();
        if (selectionSet.contains("packageOfServices")) {
            rootTariff.fetch("packageOfServices", JoinType.LEFT);
        }
//        criteriaQueryTariff.multiselect(rootInflow.get("inflowID"), rootInflow.get("name"), rootOutflow.get("count"), rootOutflow.get("dateRange"));
//        criteriaQueryTariff.where(criteriaBuilder.equal(owner.get("value"), value)).where(criteriaBuilder.equal(owner.get("category"), categoryType));

        criteriaQueryTariff.where(criteriaBuilder.equal(rootTariff.get("packageOfServices").get("value"), value)).where(criteriaBuilder.equal(rootTariff.get("packageOfServices").get("category"), categoryType));
        return entityManager.createQuery(criteriaQueryTariff).getResultList();

//        CriteriaQuery<Tariff> q = criteriaBuilder.createQuery(Tariff.class);
//        Root<Tariff> ent1 = q.from(Tariff.class);
//
//        Subquery<PackageOfServices> subq = q.subquery(PackageOfServices.class);
//        Root<PackageOfServices> ent2 = subq.from(PackageOfServices.class);
//
//        Path<Integer> all = ent2.join("id", JoinType.LEFT).get("tariff_id");
//        Predicate lessThan = criteriaBuilder.lessThanOrEqualTo(all,1);
////        Predicate lessThan = criteriaBuilder.lte(all,1);
//        Predicate correlatedSubqJoin = criteriaBuilder.equal(ent1,ent2);
//        subq.where(lessThan, correlatedSubqJoin);
//
//        q.select(ent1);
//        q.where(criteriaBuilder.exists(subq).not());
//        return entityManager.createQuery(q).getResultList();
    }

    public List<Tariff> findByCriteria(TariffFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tariff> criteriaQuery = criteriaBuilder.createQuery(Tariff.class);
        Root<Tariff> root = criteriaQuery.from(Tariff.class);
        Predicate predicate = null;
        if (filter.getTitle() != null)
            predicate = filter.getTitle().generateCriteria(criteriaBuilder, root.get("title"));
        if (filter.getIsArchived() != null)
            predicate = (predicate == null ?
                    filter.getIsArchived().generateCriteria(criteriaBuilder, root.get("isArchived")) :
                    criteriaBuilder.and(predicate, filter.getIsArchived().generateCriteria(criteriaBuilder, root.get("isArchived"))));
        if (predicate != null)
            criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
