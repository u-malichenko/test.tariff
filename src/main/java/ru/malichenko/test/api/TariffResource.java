package ru.malichenko.test.api;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import ru.malichenko.test.repository.PackageOfServicesRepository;
import ru.malichenko.test.repository.TariffRepository;
import ru.malichenko.test.model.PageRequest;
import ru.malichenko.test.model.Tariff;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path(TariffResource.RESOURCE_PATH)
public class TariffResource {

    public static final String RESOURCE_PATH = "/tariffs";

    @Context
    UriInfo uriInfo;

    @Inject
    TariffRepository tariffRepository;

    @Inject
    PackageOfServicesRepository packageOfServicesRepository;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@BeanParam PageRequest pageRequest) {
        return Response.ok(((PanacheRepository) tariffRepository).findAll()
                .page(Page.of(pageRequest.getPageNum(), pageRequest.getPageSize()))
                .list()).build();
    }
//
//    @GET
//    @Path("{id}")
//    public Response getOne(@PathParam("id") Long id) {
//
//    }
//
//    @GET
//    @Path("{id}/packages")
//    public List<PackageOfServices> getPackageOfServices(@PathParam("id") Long id) {
//
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(@Valid Tariff tariff) {
        tariffRepository.persist(tariff);

        var location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .resolveTemplate("id", tariff.getId())
                .build();

        return Response.created(location).build();
    }

//    @GET
//    public long count(){
//        return tariffRepository.count();
//    }
//
//    @GET
//    public List<Tariff> list() {
//        return tariffRepository.listAll();
//    }
//todo
//    @GET
//    @Path("/{id}")
//    public Person get(Long id) {
//        return Person.findById(id);
//    }
//
//    @POST
//    @Transactional
//    public Response create(Person person) {
//        person.persist();
//        return Response.created(URI.create("/persons/" + person.id)).build();
//    }
//
//    @PUT
//    @Path("/{id}")
//    @Transactional
//    public Person update(Long id, Person person) {
//        Person entity = Person.findById(id);
//        if(entity == null) {
//            throw new NotFoundException();
//        }
//
//        // map all fields from the person parameter to the existing entity
//        entity.name = person.name;
//
//        return entity;
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @Transactional
//    public void delete(Long id) {
//        Person entity = Person.findById(id);
//        if(entity == null) {
//            throw new NotFoundException();
//        }
//        entity.delete();
//    }
//
//    @GET
//    @Path("/search/{name}")
//    public Person search(String name) {
//        return Person.findByName(name);
//    }
//
//    @GET
//    @Path("/count")
//    public Long count() {
//        return Person.count();
//    }
}
