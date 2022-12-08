package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MessageDialog {
	public static void showMessageDialog(Component parent, String content, String title) {
		JOptionPane.showMessageDialog(parent, title, content, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void showErrorDialog(Component e, String content, String title) {
		JOptionPane.showMessageDialog( e, title, content, JOptionPane.ERROR_MESSAGE);
	}
	
	public static int showConfirmDialog(Component parent, String content, String title) {
		int choose = JOptionPane.showConfirmDialog(parent, title, content,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return choose;
	}
}
