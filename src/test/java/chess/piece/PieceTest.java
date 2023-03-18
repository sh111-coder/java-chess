package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceTest {

    @Test
    @DisplayName("같은 포지션인지 여부를 반환한다.")
    void isSamePosition() {
        // given
        final Piece piece = new Rook(new Position(File.A, Rank.ONE), Side.WHITE);
        final Position positionToCompare = new Position(File.A, Rank.ONE);

        // when, then
        assertThat(piece.isSamePosition(positionToCompare)).isTrue();
    }

    @Test
    @DisplayName("같은 진영인지 여부를 반환한다.")
    void isSameSide() {
        // given
        final Piece piece = new Rook(new Position(File.A, Rank.ONE), Side.WHITE);
        final Piece sameSidePiece = new Bishop(new Position(File.C, Rank.ONE), Side.WHITE);
        final Piece differentSidePiece = new Bishop(new Position(File.C, Rank.EIGHT), Side.BLACK);

        // when, then
        assertThat(piece.isSameSide(sameSidePiece)).isTrue();
        assertThat(piece.isSameSide(differentSidePiece)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("moveTestCases")
    @DisplayName("받은 위치로 이동한다.")
    void move(Piece piece, Position positionToMove, Class<? extends Piece> pieceClass) {
        // when
        Piece movedPiece = piece.move(positionToMove);

        // then
        assertThat(piece).isInstanceOf(pieceClass);
        assertThat(movedPiece.isSamePosition(positionToMove)).isTrue();
    }

    static Stream<Arguments> moveTestCases() {
        return Stream.of(
                Arguments.arguments(new Rook(new Position(File.A, Rank.ONE), Side.WHITE), new Position(File.F, Rank.ONE), Rook.class),
                Arguments.arguments(new Knight(new Position(File.B, Rank.ONE), Side.WHITE), new Position(File.C, Rank.THREE), Knight.class),
                Arguments.arguments(new Bishop(new Position(File.C, Rank.ONE), Side.WHITE), new Position(File.E, Rank.THREE), Bishop.class),
                Arguments.arguments(new Queen(new Position(File.D, Rank.ONE), Side.WHITE), new Position(File.H, Rank.FIVE), Queen.class),
                Arguments.arguments(new King(new Position(File.E, Rank.ONE), Side.WHITE), new Position(File.E, Rank.TWO), King.class),
                Arguments.arguments(new Pawn(new Position(File.B, Rank.TWO), Side.WHITE), new Position(File.B, Rank.FOUR), Pawn.class)
        );
    }
}
