package ru.malichenko.test.model;

import lombok.*;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PageRequest {

    @QueryParam("pageNum")
    @DefaultValue("0")
    private int pageNum;

    @QueryParam("pageSize")
    @DefaultValue("10")
    private int pageSize;
}
