package com.hotel.exceptions;

public class DaoException extends RuntimeException{
    public DaoException(String message){super(message);}

    public DaoException(String message,Throwable cause){super(message,cause);}

    public DaoException(Throwable cause){super(cause);}
}
