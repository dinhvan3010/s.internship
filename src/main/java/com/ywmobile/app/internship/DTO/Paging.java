package com.ywmobile.app.internship.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Paging {
    int a;
    int b;
    String keyword;
    int staff_id;

    public Paging(String keyword, int staff_id) {
        this.keyword = keyword;
        this.staff_id = staff_id;
    }

    public Paging(int a, int b, String keyword) {
        this.a = a;
        this.b = b;
        this.keyword = keyword;
    }
}
