package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; 
		for (int i = 0; i<board.getRows(); i++	) {
			for (int j = 0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
		
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {

		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column,row).toPosition());
	}
	
	public void initialSetup() {
		//First row Whites
		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		board.placePiece(new Knight(board, Color.BLACK), new Position(0, 1));
		board.placePiece(new Bishop(board, Color.BLACK), new Position(0, 2));
		board.placePiece(new Queen(board, Color.BLACK), new Position(0, 3));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new Bishop(board, Color.BLACK), new Position(0, 5));
		board.placePiece(new Knight(board, Color.BLACK), new Position(0, 6));
		board.placePiece(new Rook(board, Color.BLACK), new Position(0, 7));
		//First row Blacks
		board.placePiece(new Rook(board, Color.WHITE), new Position(7, 0));
		board.placePiece(new Knight(board, Color.WHITE), new Position(7, 1));
		board.placePiece(new Bishop(board, Color.WHITE), new Position(7, 2));
		board.placePiece(new Queen(board, Color.WHITE), new Position(7, 3));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
		board.placePiece(new Bishop(board, Color.WHITE), new Position(7, 5));
		board.placePiece(new Knight(board, Color.WHITE), new Position(7, 6));
		board.placePiece(new Rook(board, Color.WHITE), new Position(7, 7));
		
		for (int i=0; i < 8; i++) {
			board.placePiece(new Pawn(board, Color.BLACK), new Position(1, i));
		}
		for (int i=0; i < 8; i++) {
			board.placePiece(new Pawn(board, Color.WHITE), new Position(6, i));
		}
		
		
	}
	
	
}
