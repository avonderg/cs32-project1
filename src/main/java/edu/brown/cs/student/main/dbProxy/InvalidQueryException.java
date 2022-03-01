package edu.brown.cs.student.main.dbProxy;

public class InvalidQueryException extends Exception{

  public InvalidQueryException(String str){
    super(str);
  }
}
