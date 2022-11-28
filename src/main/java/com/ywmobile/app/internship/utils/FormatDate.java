package com.ywmobile.app.internship.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class FormatDate {

    public Date FormatDate(String date) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date_of_birth_fm = simpleDateFormat.parse(date);

        return date_of_birth_fm;
    }
}
