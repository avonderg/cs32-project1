package edu.brown.cs.student.main.dbProxy;

/**
 * This class serves as an InvalidPermissionsException, to be thrown when the table one is trying
 * to query does not have the appropriate access control level in order for the command to be
 * executed.
 */
public class InvalidPermissionsException extends Exception {

  /**
   * Constructor for the exception.
   * @param tablePermission the permission given to the proxy for the table
   * @param requiredPermission the permissions required to perform the command on the table
   */
  public InvalidPermissionsException(String tablePermission, String requiredPermission){
    super("User has " + tablePermission + " permission but need "
        +  requiredPermission + " for this SQL command");
  }
}
