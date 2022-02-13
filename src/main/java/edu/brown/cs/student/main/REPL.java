package main.java.edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REPL {

  private String regex;
  private String[] commands;

  /**
   * Class constructor.
   * @param regex command delimeter; used to split the line into separate tokens.
   * @param commands array of Strings of possible commands
   */
  public REPL(String regex, String[] commands) {
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
          for (int i=0; i<m.groupCount(); i++) {
            tokens.add(m.group(i));
          }
        }
        for (String command : commands) {
          if (tokens.contains(command)) {
            Reader reader = new Reader(tokens, command);
            break;
          }
        }
      } else {
        break;
      }
    }
  }
}
