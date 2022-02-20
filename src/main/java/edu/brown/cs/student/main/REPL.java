package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The REPL class. Checks input for commands and prints output.
 */
public class REPL {

  private String regex;
  private Command[] commands;

  /**
   * Class constructor.
   * @param regex command delimeter; used to split the line into separate tokens.
   * @param commands array of Strings of possible commands
   */
  public REPL(String regex, Command[] commands) {
    this.regex = regex;
    this.commands = commands;
  }

  /**
   * Runs the REPL (uses BufferedReader).
   * @throws IOException
   */
  public void runREPL() throws IOException {
    // initialize buffered reader
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      // reads the first line, and initializes tokens list to store Strings parsed in the line
      String line = br.readLine();
      List<String> tokens = new ArrayList<>();
      if (line != null) {

        // uses the regex passed into the constructor to parse the input line
        Matcher m = Pattern.compile(regex).matcher(line);
        while (m.find()) {
          // adds each item matched by the regex to the tokens list
          tokens.add(m.group());
        }

        // checks tokens for each of the commands passed into the constructor
        for (Command command : commands) {

          // if the command is matched,  print the output
          String output = command.checkCommand(tokens);

          // checkCommand returns null if the command is not found in the tokens
          if (!output.equals(null)) {

            // if the command is found, print the output and
            // exit the loop (don't check for any more commands)
            System.out.println(output);
            break;
          }
        }
      } else {
        // if the next input line is null, then stop running the REPL
        break;
      }
    }
  }
}
