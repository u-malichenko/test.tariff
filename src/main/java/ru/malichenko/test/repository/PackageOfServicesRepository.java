package ru.malichenko.test.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

//Сервис должен предоставлять возможность поиска тарифов:
//
//        Нечеткий поиск по названию тарифа.
//        По наличию безлимитного интернета.
//        По наличию безлимитных вызовов.
//        По флагу архива.
@ApplicationScoped
public class PackageOfServicesRepository implements PanacheRepository<PanacheRepository> {
    List<PanacheRepository> findByTitle(String title) {
        return list("title", title);
    }
}
