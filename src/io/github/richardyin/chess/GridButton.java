package io.github.richardyin.chess;

import ictk.boardgame.chess.Bishop;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.King;
import ictk.boardgame.chess.Knight;
import ictk.boardgame.chess.Pawn;
import ictk.boardgame.chess.Queen;
import ictk.boardgame.chess.Rook;
import ictk.boardgame.chess.Square;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class GridButton extends JButton {
	private ChessPiece occupant;
	private boolean isBlack;
	private static Map<Byte, Character> referenceMap;
	private SquareState state;
	private static List<Square> emptySquareList = new ArrayList<>(0);
	
	private static final Border defaultBorder = UIManager.getBorder("Button.border");
	private static final Border selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2);
	private static final Border moveBorder = BorderFactory.createLineBorder(Color.CYAN, 2);
	private static final Border captureBorder = BorderFactory.createLineBorder(Color.RED, 2);
	
	public enum SquareState {
		NONE, SELECTED, CAN_MOVE, CAN_CAPTURE
	}

	static {
		byte[] indices = { Pawn.INDEX, Bishop.INDEX, Knight.INDEX, Rook.INDEX,
				Queen.INDEX, King.INDEX }; // indices in the order that appears in the font
		Map<Byte, Character> charMap = new HashMap<>();
		// White pieces are 'i' to 'n' while black pieces are 'I' to 'N'
		char currentCharacter = 'I'; // different-colour pawn
		char charDifference = (char) ('a' - 'A'); // diff btwn capital, lowercase
		for (byte index : indices) {
			charMap.put(index, currentCharacter);
			charMap.put((byte) (index + ChessPiece.BLACK_OFFSET),
					(char) (currentCharacter + charDifference)); 
			currentCharacter++;
		}
		referenceMap = Collections.unmodifiableMap(charMap);
	}

	public ChessPiece getOccupant() {
		return occupant;
	}

	public void setOccupant(ChessPiece occupant) {
		this.occupant = occupant;
		if (occupant == null)
			setText("");
		else if (isBlack) { // invert colour for black squares
			setText(referenceMap.get(getOppositeIndex(occupant.getIndex()))
					.toString());
		} else
			setText(referenceMap.get(occupant.getIndex()).toString());
	}

	public boolean isBlack() {
		return isBlack;
	}

	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
		if (isBlack) {
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);
		} else {
			setBackground(Color.WHITE);
			setForeground(Color.BLACK);
		}
	}

	private byte getOppositeIndex(byte index) {
		if (index >= ChessPiece.BLACK_OFFSET)
			return (byte) (index - ChessPiece.BLACK_OFFSET);
		else
			return (byte) (index + ChessPiece.BLACK_OFFSET);
	}

	public void setState(SquareState state) {
		this.state = state;
		switch(state) {
		case NONE: setBorder(defaultBorder); break;
		case SELECTED: setBorder(selectedBorder); break;
		case CAN_MOVE: setBorder(moveBorder); break;
		case CAN_CAPTURE: setBorder(captureBorder); break;
		}
	}

	public SquareState getState() {
		return state;
	}
	
	public List<Square> getLegalDests() {
		if(occupant == null) return emptySquareList;
		return occupant.getLegalDests();
	}
}
