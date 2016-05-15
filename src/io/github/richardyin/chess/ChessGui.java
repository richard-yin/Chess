package io.github.richardyin.chess;

import ictk.boardgame.chess.ChessGame;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Online P2P chess app
 * 
 * @author Richard
 *
 */
public class ChessGui extends JFrame {
	private ChessGridPanel gridPanel;
	private ChessGame game = new ChessGame();
	
	private JButton hostGameButton = new JButton("Host Game");
	private JButton joinGameButton = new JButton("Join Game");
	
	private JButton newGameButton = new JButton("Reset Game");
	private ActionListener newGameListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			game = new ChessGame();
			gridPanel.setGame(game);
		}
	};

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
		
		hostGameButton.setBounds(560, 40, 200, 40);
		add(hostGameButton);
		
		joinGameButton.setBounds(560, 80, 200, 40);
		add(joinGameButton);
		
		newGameButton.setBounds(560, 120, 200, 40);
		newGameButton.addActionListener(newGameListener);
		add(newGameButton);
	}
}
