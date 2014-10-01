
package com.shteinertree.exceptions;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public class GraphInNotConnectedException extends Exception{
    private static final String message = "This graph isn\'t connected!";

    public GraphInNotConnectedException() {
        super(message);
    }
}
