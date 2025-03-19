package com.datadelft.databeest.databeest.serviceImp;

import com.datadelft.databeest.databeest.POJO.User;

import com.datadelft.databeest.databeest.constants.DatabeestConstants;
import com.datadelft.databeest.databeest.dao.UserDao;
import com.datadelft.databeest.databeest.service.UserService;
import com.datadelft.databeest.databeest.utils.DatabeestUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

// implementation of the UserService Interface

@Slf4j
@Service    // Marks the Class as a Service Bean:
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            //log.info("Inside signUp {}", requestMap);
            if (validateSignupMap(requestMap)) {        // validates if the requestMap has all data that is required.
                User user = userDao.findByEmailId(requestMap.get("email")); // finds user by email key in the database
                if (Objects.isNull(user)) {     // if it didn't find it
                    // user doesn't already exist
                    userDao.save(getUserFromMap(requestMap));
                    return DatabeestUtils.getResponsEntity("Successfully registered", HttpStatus.OK);
                } else {        // if it did find it
                    // user already exists
                    return DatabeestUtils.getResponsEntity("Email already exists", HttpStatus.CONFLICT);
                }
            } else {  // if the request didn't have all required keys/data
                return DatabeestUtils.getResponsEntity(DatabeestConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DatabeestUtils.getResponsEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // validator method
    private boolean validateSignupMap(Map<String, String> requestMap){
        if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password"))
        {
            return true;
        } else {
            return false;
        }
    }


    private User getUserFromMap(Map<String,String> requestMap){
        User user = new User();
        // setters in User provided by @Data (Lombok)
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("False");
        user.setRole("User");
        return user;
    }
}
