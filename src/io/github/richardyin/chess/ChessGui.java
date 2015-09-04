package io.github.richardyin.chess;

import ictk.boardgame.chess.ChessGame;

import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * Online P2P chess app
 * 
 * @author Richard
 *
 */
public class ChessGui extends JFrame {
	private ChessGridPanel gridPanel;
	ChessGame game = new ChessGame();

	public static void main(String[] args) {
		try {
			new ChessGui().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public ChessGui() throws IOException, FontFormatException {
		setResizable(false);
		setSize(800, 600);
		setTitle("Chess");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		gridPanel = new ChessGridPanel(game);
		gridPanel.setBounds(40, 40, 480, 480);
		add(gridPanel);
	}
}
