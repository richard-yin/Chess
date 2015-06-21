package io.github.richardyin.chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Online P2P chess app
 * @author Richard
 *
 */
public class ChessGui {
	
	public static void main(String[] args) throws ScriptException, FileNotFoundException {
		// Load script from a file into a string
		Scanner scriptScanner = new Scanner(new File("chess.js"));
		String script = scriptScanner.useDelimiter("\\A").next();
		scriptScanner.close();
		
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(script);
	}
}
