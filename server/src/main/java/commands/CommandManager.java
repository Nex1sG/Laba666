package commands.abstractCommand;

import connectionchamber.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandManager {
    private static final Logger logger = LoggerFactory.getLogger(CommandManager.class);
    private static CommandManager instance;
    private final LinkedHashMap<String, Command> commands = new LinkedHashMap<>();

    private CommandManager() {
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    private void addCommand(Command command) {
        commands.put(command.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase(), command);
    }

    public void addCommands(Command... commands) {
        Stream.of(commands).forEach(this::addCommand);
    }

    public boolean checkIfContains(String command) {
        return commands.containsKey(command);
    }

    public Object[] executeCommand(Message msg) {
        if (msg == null) {
            logger.info("Message is null");
            return null;
        }
        if (msg.args() != null) {
            LinkedList<Object> fields = Arrays.stream(msg.args()).collect(Collectors.toCollection(LinkedList::new));
            commands.get(msg.commandName()).setQueue(fields);
        }
        return commands.get(msg.commandName()).execute();
    }

    public Collection<Command> getValues() {
        return commands.values();
    }
}
