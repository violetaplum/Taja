package taja;

import javax.swing.JFrame;
import java.net.*;
import java.sql.*;

public class Main extends JFrame {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		
		JFrame myFrame = new JFrame("Ÿ�ڿ�������"); // JFrame ��ü�� �����Ѵ�
		myFrame.add(new Gui()); // JFrame�� Gui JPanel�� �߰��Ѵ�
		myFrame.setSize(800, 596); // JFrame ������ ����
		myFrame.setLocationRelativeTo(null); // JFrame�� ������� �߾ӿ� ���� �Ѵ�
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}

}
