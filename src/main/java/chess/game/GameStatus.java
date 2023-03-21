package chess.game;

import java.util.Arrays;

public enum GameStatus {
    START("start"),
    MOVE("move"),
    END("end");

    private final String command;

    GameStatus(final String command) {
        this.command = command;
    }

    public static GameStatus from(String statusCommand) {
        return Arrays.stream(values())
                .filter(value -> value.command.equals(statusCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 커맨드를 입력해주세요."));
    }
}
