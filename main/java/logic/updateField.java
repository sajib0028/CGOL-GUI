package logic;

import java.util.concurrent.TimeUnit;
import interfaces.Updatable;
import interfaces.UpdateShell;

//@author : Sajib Biswas
//@version 1.01	16-03-2022 Logic to Update Field

public class updateField implements Runnable {
	private Updatable gameField;
	private int pause;
	private UpdateShell shell;
	
	public updateField(Updatable gameField, int pause, UpdateShell shell) {
		this.gameField = gameField;
		this.pause = pause;
		this.shell = shell;
	}
	
	public void setPause(int pause) {
		this.pause = pause;
	}
	
	public void run() {
		try {
			long begin = System.currentTimeMillis();
			
			gameField.updateField();
			
			long diff = System.currentTimeMillis() - begin;

			if (diff < pause) 
				TimeUnit.MILLISECONDS.sleep(pause - diff);
			shell.update();
		} catch (InterruptedException iException) {
			Thread.currentThread().interrupt();
		}
	}

}
