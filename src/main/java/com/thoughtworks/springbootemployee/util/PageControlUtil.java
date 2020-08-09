package com.thoughtworks.springbootemployee.util;

import java.util.List;
import java.util.stream.Collectors;

public class PageControlUtil<T> {

    public static<T> List<T> getDataByPaging(List<T> data, int page, int pageSize) {
        return data.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

}
