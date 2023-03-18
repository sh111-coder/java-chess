package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String GAME_COMMAND_DELIMITER = " ";
    private static final int GAME_COMMAND_INDEX = 0;
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final int CORRECT_START_END_SPLIT_SIZE = 1;
    private static final int CORRECT_MOVE_SPLIT_SIZE = 3;

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> inputGameCommand() {
        String input = scanner.nextLine();
        final List<String> splitGameCommand = Arrays.asList(input.split(GAME_COMMAND_DELIMITER));
        final String gameCommand = splitGameCommand.get(GAME_COMMAND_INDEX);

        validateGameCommandInput(gameCommand);
        validateGameCommandFormat(splitGameCommand);

        return splitGameCommand;
    }

    private void validateGameCommandInput(final String gameCommand) {
        if (isWrongGameCommand(gameCommand)) {
            throw new IllegalArgumentException("[ERROR] " + START_COMMAND + ", " + END_COMMAND + ", " + MOVE_COMMAND + "만 입력해주세요.");
        }
    }

    private boolean isWrongGameCommand(final String gameCommand) {
        return !gameCommand.equals(START_COMMAND) && !gameCommand.equals(END_COMMAND) && !gameCommand.equals(
                MOVE_COMMAND);
    }

    private void validateGameCommandFormat(final List<String> splitGameCommand) {
        final String gameCommand = splitGameCommand.get(GAME_COMMAND_INDEX);
        final int splitGameCommandSize = splitGameCommand.size();
        if (isWrongStartOrEndCommandFormat(splitGameCommandSize, gameCommand)) {
            throw new IllegalArgumentException("[ERROR] " +  START_COMMAND + "또는 " + END_COMMAND + "커맨드는 "
                    + START_COMMAND + "또는 " + END_COMMAND + "만 입력해야 합니다.");
        }

        if (isWrongMoveCommandFormat(splitGameCommandSize, gameCommand)) {
            throw new IllegalArgumentException("[ERROR] move 명령어는 소스 위치와 타겟 위치를 모두 입력해야 합니다.");
        }
    }

    private boolean isWrongStartOrEndCommandFormat(final int splitGameCommandSize, final String gameCommand) {
        return (gameCommand.equals(START_COMMAND) || gameCommand.equals(END_COMMAND))
                && splitGameCommandSize != CORRECT_START_END_SPLIT_SIZE;
    }

    private boolean isWrongMoveCommandFormat(final int splitGameCommandSize, final String gameCommand) {
        return gameCommand.equals(MOVE_COMMAND) && splitGameCommandSize != CORRECT_MOVE_SPLIT_SIZE;
    }
}
