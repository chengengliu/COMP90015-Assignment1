/*
* Author: Chengeng Liu
* Student ID: 813174
* */
package exceptions;

/**
 * Exceptions
 */
public class PortNumberOutOfBounds extends Exception {
    public PortNumberOutOfBounds(){
        super("Port Number out of bounds. (1042 <port <= 65535");
    }
}
