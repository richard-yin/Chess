package io.github.richardyin.chess;

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

	public static void main(String[] args) {
		try {
			new ChessGui().setVisible(true);
		} catch (Exception e) {
			System.exit(1);
		}
	}

	public ChessGui() throws IOException, FontFormatException {
		setResizable(false);
		setSize(800, 600);
		setTitle("Chess");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		gridPanel = new ChessGridPanel();
		gridPanel.setBounds(40, 40, 480, 480);
		add(gridPanel);
	}
}
