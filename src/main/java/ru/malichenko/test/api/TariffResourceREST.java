package ru.malichenko.test.api;

import ru.malichenko.test.dto.TariffDto;
import ru.malichenko.test.model.PageRequest;
import ru.malichenko.test.service.TariffService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path(TariffResourceREST.RESOURCE_PATH)
public class TariffResourceREST {

    public static final String RESOURCE_PATH = "/tariffs";

    @Context
    UriInfo uriInfo;

    @Inject
    TariffService tariffService;

    @GET
    public Response getAll(@BeanParam PageRequest pageRequest) {
        return Response.ok(tariffService.getAll(pageRequest)).build();
    }

    @POST
    public Response create(@Valid TariffDto tariffDto) {
        tariffService.create(tariffDto);
        var location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .resolveTemplate("id", tariffDto.getId())
                .build();
        return Response.created(location).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(Long id) {
        tariffService.delete(id);
    }
}
