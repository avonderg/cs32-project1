package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      String line = br.readLine();
      List<String> tokens = new ArrayList<>();
      if (line != null) {
        Matcher m = Pattern.compile(regex).matcher(line);
        while (m.find()) {
          tokens.add(m.group());
        }
        for (Command command : commands) {
          String output = command.checkCommand(tokens);
          if (!output.equals(null)) { // if the command is matched,  print the output
            System.out.println(output);
            break;
          }
        }
      } else {
        break;
      }
    }
  }
}
