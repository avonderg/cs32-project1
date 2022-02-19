package edu.brown.cs.student.main;

import java.util.List;

public interface Command {

  /**
   * Returns a String output if tokens contain certain command. Returns null otherwise.
   * @param tokens
   * @return
   */
  String checkCommand(List<String> tokens);

}
