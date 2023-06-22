package org.satal.backservice.exception;

/***
 * Завожу этот Exception для работы на тестовом сервисе.
 * В проекте будет Security - там есть этот и другие Exception.
 * Когда появится Security этот класс удалить и провести импорты из Security/
 ***/

public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException(String message) {
        super(message);
    }
}
