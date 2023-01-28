package ru.malichenko.test.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.malichenko.test.model.Tariff;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

//Сервис должен предоставлять возможность поиска тарифов:
//
//        Нечеткий поиск по названию тарифа.
//        По наличию безлимитного интернета.
//        По наличию безлимитных вызовов.
//        По флагу архива.
@ApplicationScoped
public class TariffRepository implements PanacheRepository<Tariff> {
    List<Tariff> findByTitle(String title) {
        return list("title", title);
    }

    public List<Tariff> findArchived(){
        return list("archived", true);
    }

    public void deleteTitle(String title){
        delete("title", title);
    }
}
