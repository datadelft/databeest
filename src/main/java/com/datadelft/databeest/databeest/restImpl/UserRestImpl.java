package com.datadelft.databeest.databeest.restImpl;

import com.datadelft.databeest.databeest.DataBeestApplication;
import com.datadelft.databeest.databeest.constants.DatabeestConstants;
import com.datadelft.databeest.databeest.rest.UserRest;
import com.datadelft.databeest.databeest.service.UserService;
import com.datadelft.databeest.databeest.utils.DatabeestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// implementation of the UserRest Interface, gets called trough ip:port/user/* API calls

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;
    @Autowired
    private DataBeestApplication dataBeestApplication;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);
        }catch(Exception e){
            e.printStackTrace();
        }
        return DatabeestUtils.getResponsEntity(DatabeestConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
