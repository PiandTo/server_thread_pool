package edu.school.client;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Data
public class Client {
	private PrintWriter out;
	private Scanner in;
	private JFrame jFrame = new JFrame("Chat");
	private JTextField jTextField = new JTextField(50);
	private JTextArea jTextArea = new JTextArea(16, 50);

	public void start() {
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(new Dimension(600, 600));
		jFrame.setLocationRelativeTo(null);

		//Input
		jFrame.getContentPane().add(jTextField, BorderLayout.SOUTH);
		jTextField.setEditable(false);

		//Output
		jTextArea.setEditable(false);
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		jScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setPreferredSize(new Dimension(250, 155));
		jScrollPane.setMinimumSize(new Dimension(10, 10));
		jFrame.getContentPane().add(jScrollPane, BorderLayout.CENTER);

		jFrame.setVisible(true);
	}

	public void run() {
		String line;
		try {
			Socket socket = new Socket("localhost", 3000);
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);

			while (in.hasNextLine()) {
				line = in.nextLine();
				if (line.equals("What is your name?")) {
					jTextArea.setBackground(Color.LIGHT_GRAY);
					jTextField.setEditable(true);
					jTextArea.append(line + '\n');
				} else if (line.startsWith("Welcome ")) {
					jTextArea.append(line + '\n');
					jTextArea.append("****************************************" + '\n');
				} else {
					jTextArea.append(line + '\n');
				}
			}
		} catch (IOException e) {
			System.out.println("Error");
			System.exit(0);
		}
	}
}
