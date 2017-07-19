package com.lide.app.bean.JsonToBean;

import java.util.List;

/**
 * Created by lubin on 2016/11/17.
 */

public class BaseContainerBean<T> {

    public int currentPage;
    public boolean hasNext;
    public int recordsPerPage;
    public int totalPages;
    public int totalRecords;

    public List<T> data;

    @Override
    public String toString() {
        return "BaseContainerBean{" +
                "currentPage=" + currentPage +
                ", hasNext=" + hasNext +
                ", recordsPerPage=" + recordsPerPage +
                ", totalPages=" + totalPages +
                ", totalRecords=" + totalRecords +
                ", data=" + data +
                '}';
    }
}
