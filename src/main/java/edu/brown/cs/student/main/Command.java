package edu.brown.cs.student.main;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Command interface for handling user command-line input
 */
public interface Command {

  /**
   * Function checking if a command has been found
   *
   * @param tokens - tokenized command line user input
   * @return - boolean indicating whether or not a command was found
   * @throws NoSuchAlgorithmException
   */
  boolean checkCommand(List<String> tokens) throws NoSuchAlgorithmException;


}
