package com.sg.hrm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author sunsingh
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

        public EntityNotFoundException(Long id) {
                super("Entity does not exist with id : " + id);
        }

}
