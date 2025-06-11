package com.my.pharmacy.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundError extends RuntimeException {

        public UserNotFoundError(long id){
            super("User with id " + id + " not found");
        }

        public UserNotFoundError(String username){
            super("User with username " + username + " not found");
        }

    }

