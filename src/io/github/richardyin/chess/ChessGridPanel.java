package io.github.richardyin.chess;

import ictk.boardgame.Board;
import ictk.boardgame.BoardListener;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.Square;
import ictk.boardgame.chess.ui.ChessBoardDisplay;
import ictk.boardgame.ui.BoardDisplay;
import io.github.richardyin.chess.GridButton.SquareState;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessGridPanel extends JPanel implements ChessBoardDisplay,
		BoardDisplay, BoardListener {

	private GridButton[][] buttonGrid = new GridButton[8][8];
	private boolean isWhiteOnBottom = true;
	private ChessGame game;
	private GridButton selectedButton;
	
	private List<GridButton> highlightedButtons = new ArrayList<>();
	
	private ActionListener buttonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!(e.getSource() instanceof GridButton)) return; // invalid select
			GridButton newButton = (GridButton) e.getSource();
			switch(newButton.getState()) {
			case SELECTED: return; //already selected, ignore
			case NONE: selectButton(newButton);
			default: return; // TODO implement moves
			}
		}
	};

	public ChessGridPanel(ChessGame game) throws IOException,
			FontFormatException {
		setLayout(null);

		// load font with chess icons
		try {
			Font chessFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("ChessAlpha2.ttf")).deriveFont(32f);

			// set up grid
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					buttonGrid[x][y] = new GridButton();
					buttonGrid[x][y].setBounds(60 * x, 60 * y, 60, 60);
					if (x % 2 == y % 2) {
						buttonGrid[x][y].setBlack(false);
					} else {
						buttonGrid[x][y].setBlack(true);
					}
					buttonGrid[x][y].setFont(chessFont);
					add(buttonGrid[x][y]);
					buttonGrid[x][y].addActionListener(buttonListener);
				}
			}
			this.game = game;
			setBoard(game.getBoard());
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Could not load chess font",
					"Error", JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}

	@Override
	public Board getBoard() {
		// TODO Auto-generated method stub
		return game.getBoard();
	}

	@Override
	public void setBoard(Board board) {
		if (board instanceof ChessBoard) {
			ChessBoard chessBoard = (ChessBoard) board;
			for (int x = 0; x <= 7; x++) {
				for (int y = 0; y <= 7; y++) {
					Square sq = chessBoard.getSquare(x + 1, y + 1);
					buttonGrid[x][y].setOccupant(sq.getOccupant());
				}
			}
			if (game.getBoard() != board)
				game.setBoard(board);
		} else
			throw new IllegalArgumentException("Wrong type of board given");
	}
	
	private void selectButton(GridButton newButton) {
		for(GridButton button : highlightedButtons) {
			button.setState(SquareState.NONE);
		}
		highlightedButtons.clear();
		selectedButton = newButton;
		selectedButton.setState(SquareState.SELECTED);
		highlightedButtons.add(selectedButton);
		if(selectedButton.getOccupant() == null) return;
		
		// highlight possible moves if it is that player's turn
		boolean isBlackMove = game.getPlayerToMove() == 1; // 0 for white, 1 for black
		if(isBlackMove == selectedButton.getOccupant().isBlack()) return;
		for(Square square : selectedButton.getLegalDests()) {
			int x = square.getX() - 1;
			int y = square.getY() - 1;
			if(square.isOccupied()) buttonGrid[x][y].setState(SquareState.CAN_CAPTURE);
			else buttonGrid[x][y].setState(SquareState.CAN_MOVE);
			highlightedButtons.add(buttonGrid[x][y]);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean getSideToMoveOnBottom() {
		return true;
	}

	@Override
	public int getVisibleCoordinates() {
		return 0xffffffff;
	}

	@Override
	public boolean isLowerCaseCoordinates() {
		return false;
	}

	@Override
	public boolean isWhiteOnBottom() {
		return isWhiteOnBottom;
	}

	@Override
	public void setLowerCaseCoordinates(boolean arg0) {
		// not supported
	}

	@Override
	public void setSideToMoveOnBottom(boolean arg0) {
		// not supported
	}

	@Override
	public void setVisibleCoordinates(int arg0) {
		// not supported

	}

	@Override
	public void setWhiteOnBottom(boolean b) {
		// not supported
	}

	@Override
	public void boardUpdate(Board board, int updateType) {

	}
}
