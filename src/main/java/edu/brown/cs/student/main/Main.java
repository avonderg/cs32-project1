package edu.brown.cs.student.main;

// look into using these imports for your REPL!
import java.io.IOException;

import edu.brown.cs.student.main.bloom.BloomCommand;
import edu.brown.cs.student.main.csvReader.HashMapData;
import edu.brown.cs.student.main.csvReader.Reader;
import edu.brown.cs.student.main.dbProxy.CreateStudentsCommand;
import edu.brown.cs.student.main.dbProxy.DbCommand;
import edu.brown.cs.student.main.kdTree.KDTreeCommand;
import edu.brown.cs.student.main.repl.Command;
import edu.brown.cs.student.main.repl.REPL;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.Spark;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {

  // use port 4567 by default when running server
  private static final int DEFAULT_PORT = 4567;

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) throws IOException {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  /**
   * @throws IOException
   */
  private void run() throws IOException {
    // set up parsing of command line flags
    OptionParser parser = new OptionParser();

    // "./run --gui" will start a web server
    parser.accepts("gui");

    // use "--port <n>" to specify what port on which the server runs
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
        .defaultsTo(DEFAULT_PORT);

    OptionSet options = parser.parse(args);
    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }


    // Initialize a Data object to pass into a Reader object
    HashMapData data = new HashMapData();
    Reader csvReader = new Reader(data);

    // Initialize a command object to pass into the REPL object
    Command[] commands = {new BloomCommand(), new KDTreeCommand(), new DbCommand(), new CreateStudentsCommand()};
    REPL reader = new REPL("(\\S+)", commands);

    // Run the repl.
    reader.runREPL();
  }

  private void runSparkServer(int port) {
    // set port to run the server on
    Spark.port(port);

    // specify location of static resources (HTML, CSS, JS, images, etc.)
    Spark.externalStaticFileLocation("src/main/resources/static");
  }
}
