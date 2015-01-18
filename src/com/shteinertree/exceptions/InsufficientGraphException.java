
package com.shteinertree.exceptions;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public class InsufficientGraphException extends Exception{
    private static final String message = "This graph is insufficient!";

    public InsufficientGraphException() {
        super(message);
    }
}
