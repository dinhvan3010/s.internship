package com.ywmobile.app.internship.DTO;

import lombok.Data;

@Data
public class PagingResponse<T>{
    private T list;

    private int totalItems;
    private int totalPages;
    private int currentPage;

    public PagingResponse(T list, int totalItems, int totalPages, int currentPage) {
        this.list = list;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
}
