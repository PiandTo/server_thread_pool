package edu.school;

import java.io.IOException;

public class Program {
	public static void main(String[] args) {
		Server server = new Server(3000);
		try {
			server.start();
		} catch (IOException x) {
			System.out.println("Error occured");
			System.exit(0);
		}
	}
}
