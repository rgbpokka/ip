package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Duke {
    public TaskList taskList;
    public UserInterface userInterface;
    public Storage storage;
    public Parser parser;

    public Duke(String filePath) {
        userInterface = new UserInterface();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
            userInterface = new UserInterface(taskList);
            parser = new Parser(taskList, userInterface, storage);
        } catch (IOException e) {
            taskList = new TaskList();
            userInterface.showLoadingError();
            userInterface = new UserInterface(taskList);
            parser = new Parser(taskList, userInterface, storage);
        }
    }

    public void run() {
        userInterface.greet();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            try {
                parser.parse(input);
            } catch (InvalidCommandException | MissingToDoDescriptionException |
                    MissingDeadlineDescriptionException | MissingEventDescriptionException e) {
                System.out.println(e.getMessage());
            }

            if (input.equals("bye")) break;
        }
    }

    public static void main(String[] args) {
        String s = System.getProperty("user.dir");
        new Duke(s+ "/data/duke.txt").run();
    }
}