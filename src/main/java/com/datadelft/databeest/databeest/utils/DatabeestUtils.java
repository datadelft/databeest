package com.datadelft.databeest.databeest.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DatabeestUtils {

    // Commonly used logic goes here. It's our cookie jar ;-)

    private DatabeestUtils() {
        // everything will be static so we can access it directly. no constructor/getters
    }


    public static ResponseEntity<String> getResponsEntity(String responsMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\"" + responsMessage + "\"}", httpStatus);
    }





}
