package ru.malichenko.test.model;

import lombok.*;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PageRequest {

    @QueryParam("pageNum")
    @DefaultValue("0")
    private int pageNum;

    @QueryParam("pageSize")
    @DefaultValue("10")
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
