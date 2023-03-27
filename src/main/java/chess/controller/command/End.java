package chess.controller.command;

import java.util.List;

import chess.controller.dto.GameResultBySideDto;
import chess.controller.dto.ScoreBySideDto;
import chess.domain.board.*;
import chess.domain.position.Position;
import chess.domain.piece.Piece;

public class End implements CommandStatus {

    private final ResultCalculator resultCalculator;

    public End(ResultCalculator resultCalculator) {
        this.resultCalculator = resultCalculator;
    }

    @Override
    public CommandStatus start() {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 시작할 수 없습니다.");
    }

    @Override
    public CommandStatus restart(Long previousGameId) {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 이전 게임으로 재시작할 수 없습니다.");
    }

    @Override
    public CommandStatus move(Position sourcePosition, Position targetPosition) {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 기물을 움직일 수 없습니다.");
    }


    @Override
    public CommandStatus end() {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 종료할 수 없습니다.");
    }

    @Override
    public CommandStatus printGameResult() {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 게임 결과 상태로 전이될 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean canPrintGameResult() {
        return true;
    }

    @Override
    public List<Piece> getPieces() {
        throw new IllegalStateException("[ERROR] 게임 종료 상태에서는 기물들을 반환할 수 없습니다.");
    }

    @Override
    public String getTurnDisplayName() {
        throw new IllegalStateException("[ERROR] 종료 상태에서는 턴 이름을 반환할 수 없습니다.");
    }

    @Override
    public ScoreBySideDto getScoreBySide() {
        return new ScoreBySideDto(resultCalculator.getScoreBySide());
    }

    @Override
    public GameResultBySideDto getGameResultBySide() {
        return new GameResultBySideDto(resultCalculator.getGameResultBySide());
    }
}
