package io.github.richardyin.chess;

import ictk.boardgame.chess.ChessPiece;

import javax.swing.JButton;

public class GridButton extends JButton {
	private ChessPiece occupant;

	public GridButton() {

	}

	public ChessPiece getOccupant() {
		return occupant;
	}

	public void setOccupant(ChessPiece occupant) {
		this.occupant = occupant;
	}
}
