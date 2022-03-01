package edu.brown.cs.student.main.dbProxy;

public class InvalidPermissionsException extends Exception {

  public InvalidPermissionsException(String tablePermission, String requiredPermission){
    super("User has " + tablePermission + " permission but need "
        +  requiredPermission + " for this SQL command");
  }
}
