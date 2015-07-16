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

import javax.swing.JButton;

public class GridButton extends JButton {
	private ChessPiece occupant;
	private boolean isBlack;

	// Display an opaque ("black" in the font) piece if occupant colour is
	// different from square colour; use this map to convert from Piece object
	// to font
	private static Map<PieceData, Character> referenceMap;

	static {
		byte[] indices = { Pawn.INDEX, Bishop.INDEX, Knight.INDEX, Rook.INDEX,
				Queen.INDEX, King.INDEX }; // indices in the order that appears
											// in the font
		Map<PieceData, Character> charMap = new HashMap<>();
		// White pieces are 'i' to 'n' while black pieces are 'I' to 'N'
		char currentCharacter = 'I'; // different-colour pawn
		char charDifference = (char) ('A' - 'a'); // diff btwn capital,
													// lowercase
		for (byte index : indices) {
			charMap.put(new PieceData(index, false), currentCharacter);
			charMap.put(new PieceData(index, true),
					(char) (currentCharacter + charDifference));
			currentCharacter++;
		}
		referenceMap = Collections.unmodifiableMap(charMap);
	}

	public GridButton() {

	}

	public ChessPiece getOccupant() {
		return occupant;
	}

	public void setOccupant(ChessPiece occupant) {
		this.occupant = occupant;
		PieceData occupantData = new PieceData(occupant.getIndex(),
				(occupant.isBlack() == isBlack));
		setText(referenceMap.get(occupantData).toString());
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
			setBackground(Color.BLACK);
		}
	}

	private static class PieceData {
		private boolean sameColour;
		private byte index; // Refer to <PieceClassName>.INDEX

		public PieceData(byte index, boolean sameColour) {
			this.sameColour = sameColour;
			this.index = index;
		}

		// Piece data with the same type and colour are equal
		@Override
		public int hashCode() {
			int code = index;
			if (sameColour)
				code += 8; // indices are from 0 to 5
			return code;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof PieceData))
				return false;
			PieceData data = (PieceData) o;
			return data.index == index && data.index == index;
		}
	}
}
