package edu.brown.cs.student.main.dbProxy;

/**
 * This class models the InvalidQueryException and implements the Exception interface. This exception
 * is thrown when there is an issue with the SQL query syntax: either a table from the given table
 * to permissions map was not found in an SQL command or a command does not begin with the appropriate
 * keyword.
 */
public class InvalidQueryException extends Exception{

  /**
   * Constructor for the exception.
   * @param str the string to be outputted as the description for the exception
   */
  public InvalidQueryException(String str){
    super(str);
  }
}
