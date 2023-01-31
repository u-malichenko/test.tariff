
# Tariff Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

DB генерируется Flyway, ограничения реализованы как со стороны Entity так и со стороны DB 

Название Тарифа. Обязательное, уникальное в рамках всех не удаленных тарифов - реализовано при помощи уникальных индексов в одной из миграций flyway

Две таблицы в БД объединены связью 1 Тариф Многие Пакеты по tarif_id

Изначально разрабатывалась REST версия - http://localhost/tariffs

Но позже решил попробовать что же такое GraphQL и основное api тут  http://localhost/graphql/schema.graphql

# Docker command
The application can be packaged:
./gradlew build

Create a Docker container that runs the application in JVM mode:
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/tariff-jvm .

Create and run DB container:
docker run --name tariff-db-dev -p 5433:5432 -e POSTGRES_DB=tariff-dev -e POSTGRES_USER=tariff-dev -e POSTGRES_PASSWORD=tariff-dev -d postgres:alpine

Run the container with a link do the Postgres database container and override the datasource url with environment variable:
docker run -i --rm -p 80:80 --link tariff-db-dev -e QUARKUS_DATASOURCE_JDBC_URL='jdbc:postgresql://tariff-db-dev/postgres' quarkus/tariff-jvm


# GraphQL query example

http://localhost/q/graphql-ui

http://localhost/graphql/schema.graphql

query tariffs {
tariffsByCategoryAndValue(categoryType: VOICE, value: -1)
{
id
title
isRemoved
isArchived
createDate
packageOfServicesDto
{
title
id
value
isRemoved
}

}
}

query tariffs {
tariffsByUnlimitedInternet
{
id
title
isRemoved
isArchived
createDate
packageOfServicesDto
{
title
id
value
isRemoved
}

}
}

query tariffs {
tariffsWithPartialMatchTitle(partialTitle: "ul")
{
id
title
isRemoved
isArchived
createDate
packageOfServicesDto
{
title
id
value
isRemoved
}

}
}

По всем вопросам пишите, буду рад ответить..