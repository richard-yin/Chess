package io.github.richardyin.chess;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Online P2P chess app
 * @author Richard
 *
 */
public class ChessGui {
	
	public static void main(String[] args) throws ScriptException {
		// Hello World testing with Nashorn
		String cmd1 = "var hello = 'Hello World';";
		String cmd2 = "print(hello);";
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(cmd1);
		engine.eval(cmd2);
	}
}
