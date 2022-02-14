package edu.brown.cs.student.main;

import java.util.List;

public interface Command {

  /**
   * Returns true if tokens contain certain command. Returns false otherwise.
   * @param tokens
   * @return
   */
  boolean checkCommand(List<String> tokens);

}
