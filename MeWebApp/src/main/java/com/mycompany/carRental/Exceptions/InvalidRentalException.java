package com.mycompany.carRental.Exceptions;

public class InvalidRentalException extends Exception {
    public InvalidRentalException(String message) {
        super(message);
    }
}