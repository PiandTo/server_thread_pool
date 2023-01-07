package edu.school.client;

public class Program {
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
		new Listener(client);
		client.run();
	}
}
