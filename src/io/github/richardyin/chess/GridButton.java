package io.github.richardyin.chess;

import ictk.boardgame.chess.Bishop;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.King;
import ictk.boardgame.chess.Knight;
import ictk.boardgame.chess.Pawn;
import ictk.boardgame.chess.Queen;
import ictk.boardgame.chess.Rook;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class GridButton extends JButton {
	private ChessPiece occupant;
	private boolean isBlack;
	private static Map<Byte, Character> referenceMap;
	private boolean isSelected;
	
	private static final Border defaultBorder = UIManager.getBorder("Button.border");
	private static final Border selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 5);

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

	public void setSelected(boolean selected) {
		isSelected = selected;
		if(selected) setBorder(selectedBorder);
		else setBorder(defaultBorder);
	}

	public boolean getSelected() {
		return isSelected;
	}
}
