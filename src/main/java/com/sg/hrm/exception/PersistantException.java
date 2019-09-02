package com.sg.hrm.exception;

/**
 *
 * @author sunsingh
 */
public class PersistantException extends RuntimeException {

        public PersistantException(Long id) {
                super("Entity already exist with id " + id);
        }

}
