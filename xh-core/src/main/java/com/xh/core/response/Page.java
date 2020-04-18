package com.xh.core.response;


import java.util.List;
import lombok.Data;

@Data
public class Page<T> {

    List<T> list;

    private Long total;

    private Integer pages;

    private Integer pageSize;

    private Integer pageNum;

    private Integer size;

}
