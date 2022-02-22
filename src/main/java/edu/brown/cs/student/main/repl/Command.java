package edu.brown.cs.student.main.repl;

import java.util.List;

/**
 * REPL will only accept commands that implment this interface.
 * Objects of this type describe command(s) that should be looked for in the REPL input.
 * If the command is found, objects of this type should give the REPL and output to print.
 */
public interface Command {

  /**
   * Returns a String output if tokens contain certain command. Returns null otherwise.
   * @param tokens -- list of REPL input Strings
   * @return String output if command is found, null otherwise
   */
  String checkCommand(List<String> tokens);

}
