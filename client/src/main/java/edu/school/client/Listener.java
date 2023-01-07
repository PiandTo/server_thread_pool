package edu.school.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Listener implements ActionListener, MouseListener {
	private Client client;

	public Listener(Client client) {
		this.client = client;
		this.client.getJTextField().addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.client.getJTextField()) {
			String text = this.client.getJTextField().getText();
			this.client.getOut().println(text);
			this.client.getJTextField().setText("");
			if (text.equals("bye")) {
				this.client.getJFrame().dispose();
				System.exit(0);
			}
		}
	}
}
