package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.GameResultBySide;
import chess.domain.board.ResultCalculator;
import chess.domain.board.ScoreBySide;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.command.Turn;
import chess.domain.command.CommandStatus;
import chess.domain.command.End;
import chess.domain.command.Play;
import chess.domain.piece.Pieces;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayTest {

    @Test
    @DisplayName("게임 플레이 상태에서 시작 시 새로운 플레이 상태로 전이된다.")
    void start() {
        // given
        Board board = new Board(new Pieces());
        ResultCalculator resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
        Play play = new Play(new ChessGame(board, resultCalculator), Turn.WHITE);

        // when
        CommandStatus newPlay = play.start();

        // then
        assertThat(newPlay).isInstanceOf(Play.class);
        assertThat(newPlay).isNotEqualTo(play);
    }

    @Test
    @DisplayName("게임 플레이 상태에서 이동 시 말이 이동한 상태 & 턴이 넘어간 플레이 상태로 전이된다.")
    void move() {
        // given
        Board board = new Board(new Pieces());
        ResultCalculator resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
        Play play = new Play(new ChessGame(board, resultCalculator), Turn.WHITE);
        Position sourcePosition = new Position(File.A, Rank.TWO);
        Position targetPosition = new Position(File.A, Rank.FOUR);

        // when
        CommandStatus newPlay = play.move(sourcePosition, targetPosition);

        // then
        assertThat(newPlay).isInstanceOf(Play.class);
        assertThat(newPlay).isNotEqualTo(play);
        assertThat(newPlay.getTurnDisplayName()).isEqualTo("black");
    }

    @Test
    @DisplayName("게임 플레이 상태에서 종료 시 종료 상태로 전이된다.")
    void end() {
        // given
        Board board = new Board(new Pieces());
        ResultCalculator resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
        Play play = new Play(new ChessGame(board, resultCalculator), Turn.WHITE);

        // when, then
        assertThat(play.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("게임 플레이 상태에서 기물들을 가져올 수 있다.")
    void getPieces() {
        // given
        Board board = new Board(new Pieces());
        ResultCalculator resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
        Play play = new Play(new ChessGame(board, resultCalculator), Turn.WHITE);

        // when, then
        Assertions.assertDoesNotThrow(() -> play.getPieces());
    }

    @Test
    @DisplayName("게임 플레이 상태에서 턴 이름을 가져올 수 있다.")
    void getTurnDisplayName() {
        // given
        Board board = new Board(new Pieces());
        ResultCalculator resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
        Play play = new Play(new ChessGame(board, resultCalculator), Turn.WHITE);

        // when, then
        assertThat(play.getTurnDisplayName()).isEqualTo("white");
    }
}
