package edu.brown.cs.student.main.repl;

import edu.brown.cs.student.main.csvReader.HeadersData;
import edu.brown.cs.student.main.csvReader.Reader;

import java.util.List;

public class HeadersCommand implements Command{

  public HeadersCommand() {

  }

  @Override
  public String checkCommand(List<String> tokens) {
    if (tokens.get(0).equals("headers_load")) {
      HeadersData data = new HeadersData();
      Reader reader = new Reader(data);
      reader.loadData(tokens.get(1));
      return "Loaded header types";
    }
    return null;
  }
}
