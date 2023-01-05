package edu.school.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Data
@AllArgsConstructor
public class Client {
	private final String name;
	private final Socket socket;
	private final PrintWriter printWriter;
	private final BufferedReader bufferedReader;
}
