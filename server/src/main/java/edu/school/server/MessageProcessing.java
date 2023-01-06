package edu.school.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageProcessing implements Runnable{
	private Client client;
	private final Socket socket;

	public MessageProcessing(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		String inline;
		String name;

		try (
				PrintWriter printWriter = new PrintWriter(this.socket.getOutputStream(), true);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				) {
			while (true) {
				printWriter.println("What is name?");
				if ((name = bufferedReader.readLine()) != null) {
					if (name.isEmpty()) {
						continue;
					}
					client = new Client(name, this.socket, printWriter, bufferedReader);
					synchronized (Clients.clients) {
						if (Clients.names.contains(name)) {
							printWriter.println("Sorry, this name is not available");
							continue;
						}
						Clients.clients.add(client);
						Clients.names.add(name);
						break;
					}
				}
			}
			welcomeLine();
			while (true) {
				if ((inline = bufferedReader.readLine()) != null && inline.equals("bye")) {
					break ;
				} else if (inline != null) {
					for (Client c: Clients.clients) {
						c.getPrintWriter().println(client.getName() + ": " + inline);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void welcomeLine() {
		for(Client c : Clients.clients ) {
			if (c.getName().equals(client.getName())) {
				client.getPrintWriter().println("Welcome " + client.getName());
			} else {
				c.getPrintWriter().println(client.getName() + " joins!");
			}
		}
	}
}
