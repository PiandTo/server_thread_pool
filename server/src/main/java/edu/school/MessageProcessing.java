package edu.school;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageProcessing implements Runnable{
	private final Socket socket;

	public MessageProcessing(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		String inline = null;

		try (
				PrintWriter printWriter = new PrintWriter(this.socket.getOutputStream(), true);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				) {
			while ((inline = bufferedReader.readLine()) != null) {
				printWriter.println(Thread.currentThread().getName() + ": "  + inline);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
