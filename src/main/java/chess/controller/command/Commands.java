package chess.controller.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class Commands {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int PREVIOUS_GAME_ID_INDEX = 1;

    private final List<String> commands;

    public Commands(List<String> commands) {
        validate(commands);
        this.commands = new ArrayList<>(commands);
    }

    private void validate(List<String> commands) {
        GameCommand gameCommand = GameCommand.from(commands.get(COMMAND_INDEX));
        if (gameCommand == GameCommand.MOVE && commands.size() != 3) {
            throw new IllegalArgumentException("[ERROR] move 명령어는 소스 위치와 타겟 위치를 모두 입력해야 합니다.");
        }
        if (gameCommand == GameCommand.RESTART && commands.size() != 2) {
            throw new IllegalArgumentException("[ERROR] restart 명령어는 이전 게임 ID를 형식에 맞게 정확히 입력해야합니다.");
        }
    }

    public GameCommand getCommand() {
        return GameCommand.from(commands.get(COMMAND_INDEX));
    }

    public Position generateSourcePosition() {
        validateMoveCommand();
        return generatePosition(SOURCE_POSITION_INDEX);
    }

    public Position generateTargetPosition() {
        validateMoveCommand();
        return generatePosition(TARGET_POSITION_INDEX);
    }

    private void validateMoveCommand() {
        GameCommand gameCommand = GameCommand.from(commands.get(COMMAND_INDEX));
        if (gameCommand != GameCommand.MOVE) {
            throw new IllegalArgumentException("[ERROR] move 커맨드에서만 포지션을 생성할 수 있습니다.");
        }
    }

    private Position generatePosition(int positionIndex) {
        String position = commands.get(positionIndex);
        final List<String> splitPosition = Arrays.asList(position.split(""));
        File sourceFile = File.of(splitPosition.get(FILE_INDEX));
        Rank sourceRank = Rank.of(Integer.parseInt(splitPosition.get(RANK_INDEX)));
        return new Position(sourceFile, sourceRank);
    }

    public long getPreviousGameId() {
         return Long.parseLong(commands.get(PREVIOUS_GAME_ID_INDEX));
    }
}
