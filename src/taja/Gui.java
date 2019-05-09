package taja;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JPanel implements ActionListener, KeyListener {
	public static JLabel[] arrJlabel = new JLabel[9];
<<<<<<< HEAD

=======
	public static JLabel[] printAllJlabel2;
>>>>>>> pr/1
	public static int speed = 500;// ���ڰ� �������� �ӵ��� �������� ������ ������ �����Ѵ�. �ʱⰪ�� 1000
	private JLabel tajaLabel,askNickname,askName,resultNickname,resultName, resultAc, resultTime;
	private JButton startButton, quitButton; // JButton
	// ����
	private JTextField insertDap, inputNickname,inputName; // JTextField ����
	private Random myRandom = new Random(); // �����Լ� ����
	totalPlayTime total_play_time = new totalPlayTime(); // �ð� Ÿ�̸� ������ Ŭ���� ����
	Rain data_rain = new Rain(); // ���ڰ� ���������ϴ� ������ Ŭ���� ����
	WordData word_create = new WordData(); // ������ �����ϴ� Ŭ���� ����
	private float tryCount = 0; // �õ� Ƚ�� ����
	private float correctCount = 0; // ���� Ƚ�� ����
	private int correctPercent; // ���߷� ����
	public static ImageIcon icon, buttonIcon, buttonOnclick; // ImageIcon ����
	private String studentNickname,studentName; // �й��� , �л��̸� ���� String���� ����
	Connection con1 = null;
	private TextArea ta1 = new TextArea();
	ArrayList<JLabel> arrayList = new ArrayList<>();
	private JLabel rankLabel;



	public Gui() {

		setSize(800, 500); // �г� ����� 800x600���� �����Ѵ�
		setLayout(null); // ��ġ�� ���밪 ��ġ�� �����ϱ� ������, ���̾ƿ��� null�� �����Ѵ�
		icon = new ImageIcon("img/sky.jpg");
		//this.setBackground(Color.lightGray);
		tajaLabel = new JLabel();
		tajaLabel.setText("Ÿ�ڿ�������");
		tajaLabel.setFont(new Font("����",Font.BOLD,30));
		tajaLabel.setBounds(300, 80, 250, 40);
		tajaLabel.setForeground(Color.white);

		add(tajaLabel);


		startButton = new JButton("����");
		startButton.setForeground(Color.blue);
		startButton.setBounds(620, 235, 100, 43); // strartButton�� ��ǥ��,���� ����
		add(startButton); // Gui JPanel�� startButton�� �߰��Ѵ�.
		startButton.addActionListener(this); // startButton�� ActionListener �߰�



		askNickname = new JLabel();
		askNickname.setText("�г���: ");
		askNickname.setFont(new Font("����",Font.BOLD,25));
		askNickname.setBounds(100, 200, 180, 40);
		askNickname.setForeground(Color.white);
		askNickname.setBackground(Color.white);
		askNickname.setLocation(150,200);
		add(askNickname);
		askName = new JLabel();
		askName.setText("�̸�: ");
		askName.setFont(new Font("����",Font.BOLD,25));
		askName.setBounds(100, 280, 180, 40);
		askName.setForeground(Color.white);
		askName.setBackground(Color.white);
		askName.setLocation(150,280);
		add(askName);


		inputNickname = new JTextField(10);
		inputNickname.setFont(new Font("����",Font.BOLD,32));
		inputNickname.setBounds(300, 200, 180, 40);
		add(inputNickname);

		inputName = new JTextField(10);
		inputName.setFont(new Font("����",Font.BOLD,32));
		inputName.setBounds(300, 280, 180, 40);
		add(inputName);


		total_play_time.playTime.setBounds(720, 480, 200, 50);
		total_play_time.playTime.setFont(new Font("Dialog", Font.BOLD, 30));
		add(total_play_time.playTime);
		total_play_time.playTime.setVisible(false); // Ÿ�̸��߰��ϰ�, �Ⱥ��̰� �Ѵ�
		word_create.create();// word_createŬ�������� �ܾ �����ϴ� create�޼ҵ带 �����Ѵ�

	}

	@Override
	public void actionPerformed(ActionEvent e) { // ��ư�̺�Ʈ ����
		if (e.getSource() == startButton) { // startButton�� Ŭ���ϰ� �Ǹ�
			studentNickname = inputNickname.getText(); // studentNickname�� inputNickname�ؽ�Ʈ�ʵ忡
			studentName=inputName.getText();
			new JFrame(studentName);
			firstStart(); // firstStart �޼ҵ� ����

		}else if (e.getSource() == quitButton) { // quit��ư�� ������
			System.exit(0); // ���α׷��� �����Ѵ�

		}
	}

	@Override
	public void keyPressed(KeyEvent e) { // Ű�̺�Ʈ ����
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) { // ���Ͱ� ������
			tryCount++; // �õ� ȸ�� 1 ����
			removeAnswer(); // ����ó�� �޼ҵ� ����
			try {
				endAnswer2(); // ��� ������ ���� �޼ҵ� ����
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			//endAnswer();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void removeAnswer() { // ����ó�� �޼ҵ�
		for (int i = 0; i < 9; i++) {
			if (insertDap.getText().equals(
					word_create.arr.get(i))) { /* �Էµ� ���� ��̸���Ʈ�� ��� ���� ��ġ�ϸ� */
				arrJlabel[i].setVisible(false);
				correctCount++;
			} // �� JLabel�� �Ⱥ��̰� �ϰ�, correctCount(���� ��)�� 1 ������Ų��
		}
		insertDap.setText("");
		/*
		 * JLabel Visible False ��, TextLabel�� ��ĭ���� �ξ �ٷ� �Է��Ҽ� �ְ� �Ѵ�.
		 */

	}
	//--------------------------------------------------------------start DB
	private void endAnswer2() throws Exception
	{

		// ������ ������ ���� �޼ҵ�(������ ��� ������ ��)
		if (arrJlabel[0].isVisible() == false && arrJlabel[1].isVisible() == false && arrJlabel[2].isVisible() == false
				&& arrJlabel[3].isVisible() == false && arrJlabel[4].isVisible() == false
				&& arrJlabel[5].isVisible() == false && arrJlabel[6].isVisible() == false
				&& arrJlabel[7].isVisible() == false && arrJlabel[8].isVisible() == false) { // ���
			/* JLabel�� ������ ������(��,�Ϸ�Ǹ�) */

// ������ ���Դ� �ܾ���� ���ӻ�ܿ� ǥ���� �ش�

			data_rain.stop(); // �꼺�� ������ ����
			total_play_time.stop(); // �ð� Ÿ�̸� ������ ����.
			correctPercent = Math.round((correctCount / tryCount)
					* 100); /*
			 * ���߷��� �����. ����ȸ��/��Ƚ�� * 100�ؼ� �Ҽ��� ����
			 */
			//icon = new ImageIcon("img/background3.jpg"); // ����� background3����
			// �ٲ۴�
			/*
			 */
			int flag=JOptionPane.showConfirmDialog(null, "�г���:  "+
					studentNickname+"\n"+
					"�̸�:  "+studentName+"\n"+"�ð�:  "+
					Integer.toString(total_play_time.gamePlayTime-1) + "��\n��Ȯ��:  "+
					Integer.toString(correctPercent) + "%\n","���",JOptionPane.YES_OPTION);
			if(JOptionPane.YES_OPTION==flag) {
				insertDap.setVisible(false);
				total_play_time.playTime.setVisible(false);

/*
			resultNickname = new JLabel("�г���:  "+studentNickname);
			resultNickname.setBounds(300, 156, 200, 100);
			resultNickname.setFont(new Font("����", Font.BOLD, 22));
			resultNickname.setForeground(Color.white);
			add(resultNickname);// ���â�� �̸����̺��� �����ؼ� �߰��Ѵ�


			resultName = new JLabel("�̸�:  "+studentName);
			resultName.setBounds(300, 206, 200, 100);
			resultName.setFont(new Font("����", Font.BOLD, 22));
			resultName.setForeground(Color.white);
			add(resultName);// ���â�� �̸����̺��� �����ؼ� �߰��Ѵ�

			resultTime = new JLabel("�ð�:  "+(Integer.toString(total_play_time.gamePlayTime-1) + "��"));



			resultTime.setBounds(300, 256, 200, 100);
			resultTime.setFont(new Font("����", Font.BOLD, 22));
			resultTime.setForeground(Color.white);
			add(resultTime); // ���â�� �ð� ���̺��� �����ؼ� �߰��Ѵ�

			resultAc = new JLabel("��Ȯ��:  "+Integer.toString(correctPercent) + "%");



			resultAc.setBounds(300, 306, 200, 100);
			resultAc.setFont(new Font("����", Font.BOLD, 22));
			resultAc.setForeground(Color.white);
			add(resultAc); // ���â�� ���߷� ���̺��� �����ؼ� �߰��Ѵ�

<<<<<<< HEAD
			Connection con1 = null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");
			System.out.println("Connected");
			String insert = "insert into score(id,name,time) values(?,?,?)";
			String id = studentNickname;
			String name = studentName;
			String time = Integer.toString(total_play_time.gamePlayTime);
			PreparedStatement inps = con1.prepareStatement(insert);
			inps.setString(1,id);
			inps.setString(2,name);
			inps.setString(3,time);
			int rs = inps.executeUpdate();
			System.out.println("I Inserted "+rs+"!!");

			String sql = "select name,time from score order by time asc";
			PreparedStatement ps = con1.prepareStatement(sql);
			ResultSet rss = ps.executeQuery();

			String str="";
			int countString=0;

			if(rss.next())
			{
				do{
					String showName = rss.getString(1);
					String showTime = rss.getString(2);
					countString++;
					str+="���� : "+countString+"  //  �̸� : "+showName+"   //  �ð� : "+showTime+"\n";

				}
				while(rss.next());
				String[] strArray = str.split("\n");
/*
				for(int i=0;i<strArray.length;i++)
				{
					System.out.println(strArray[i]);

				}*/

				/*JLabel jl = new JLabel(strArray[0]);
				jl.setSize(20,150);
				jl.setForeground(Color.blue);
				jl.setBackground(Color.white);
				jl.setFont(new Font("����",Font.BOLD,15));
				jl.setLocation(200,50);
				this.add(jl);*/




				/*rankLabel = new JLabel("Rank");
				rankLabel.setSize(150,20);
				rankLabel.setForeground(Color.black);
				rankLabel.setOpaque(true);
				rankLabel.setFont(new Font("����",Font.BOLD,15));
				rankLabel.setLocation(30,50);
				this.add(rankLabel);*/


				/*ta1.setSize(500,400);
=======
			quitButton = new JButton("Ȯ��");
			quitButton.setBounds(340, 480, 80, 30);
			add(quitButton);
			quitButton.addActionListener(this);
			}*/// �����ϱ� ��ư�� ���� �������� ��������
				// ActionListener �߰��Ѵ�
				//--------------------------------------------------------------------DB start
				quitButton = new JButton("Ȯ��");
				quitButton.setBounds(340, 480, 80, 30);
				add(quitButton);
				quitButton.addActionListener(this);
/*
			Class.forName("oracle.jdbc.driver.OracleDriver");
		con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");
		System.out.println("Connected");
		String insert = "insert into score(id,name,time) values(?,?,?)";
		String id = studentNickname;
		String name = studentName;
		String time = Integer.toString(total_play_time.gamePlayTime);
		PreparedStatement inps = con1.prepareStatement(insert);
		inps.setString(1,id);
		inps.setString(2,name);
		inps.setString(3,time);
		int rs = inps.executeUpdate();
		System.out.println("I Inserted "+rs+"!!");

		String sql = "select name,time from score order by time asc";
		PreparedStatement ps = con1.prepareStatement(sql);
		ResultSet rss = ps.executeQuery();

		String str="";

		int nummm=0;
		if(rss.next())
		{
			do{
				String showName = rss.getString(1);
				String showTime = rss.getString(2);
				printAllJlabel2[nummm]=new JLabel("�̸� : "+showName+"\t�ð� : "+showTime+"\n");
				nummm++;
			}
			while(rss.next());

			for(int i=0;i<printAllJlabel2.length;i++)
			{
				printAllJlabel2[i].setBounds(0, 0, 150, 30); // printAllJlabel2�� ���� ����
				printAllJlabel2[i].setFont(new Font("����", Font.BOLD, 25)); // printAllJlabel2�� ��Ʈ ����
				printAllJlabel2[i].setForeground(Color.WHITE);

				printAllJlabel2[i].setLocation(100, i*50); // printAllJlabel2��
																				// ��ġ��
																				// �����Ѵ�.
				add(printAllJlabel2[i]); // arrJLabel�� �гο� �߰��Ѵ�
			}




		//------------------------------------------------------------------DB end

		}
		con1.close();
	}
	}
}
*/


				Class.forName("oracle.jdbc.driver.OracleDriver");
				con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");
				System.out.println("Connected");
				String insert = "insert into score(id,name,time) values(?,?,?)";
				String id = studentNickname;
				String name = studentName;
				int time = total_play_time.gamePlayTime-1;
				PreparedStatement inps = con1.prepareStatement(insert);
				inps.setString(1,id);
				inps.setString(2,name);
				inps.setInt(3,time);
				int rs = inps.executeUpdate();
				System.out.println("I Inserted "+rs+"!!");

				String sql = "select name,time from score order by time asc";
				PreparedStatement ps = con1.prepareStatement(sql);
				ResultSet rss = ps.executeQuery();

				String s[] = null;

				ArrayList<JLabel> arr=new ArrayList<JLabel>();
				int c=0;


				if(rss.next()) {
					do{
						String showName = rss.getString(1);
						int showTime = rss.getInt(2);


						JLabel jj=new JLabel((c+1)+"��:  "+showName+"     "+showTime+"��");
						arr.add(jj);

						c++;
						if(c==5)
							break;
					}
					while(rss.next());
				}



				/*ArrayList<JLabel> arrayList = new ArrayList<>();
				for(int i=0;i<strArray.length;i++)
				{
					arrayList.add(new JLabel(strArray[i]));
					arrayList.get(i).setForeground(Color.white);
					arrayList.get(i).setFont(new Font("����",Font.BOLD,30));
					arrayList.get(i).setLocation(200,50*i);
					add(arrayList.get(i));
					arrayList.get(i).setVisible(true);
				}
				ta1.setSize(500,400);
>>>>>>> pr/1
				ta1.setForeground(new Color(0,0,255));
				ta1.setBackground(new Color(200,255,0));
				ta1.setFont(new Font("����",Font.BOLD,15));
				ta1.setEditable(false);////////////////////////
				ta1.setLocation(30, 100);// ������ġ
				ta1.setText(str);
				this.add(ta1);
*/
<<<<<<< HEAD
				for(int i=0;i<strArray.length;i++)
				{
					arrayList.add(new JLabel(strArray[i]));
					/*arrayList.get(i).setSize(300,15);*/
					arrayList.get(i).setBounds(30,30*(i+1),300,15);
					arrayList.get(i).setForeground(Color.blue);
					arrayList.get(i).setBackground(Color.white);
					arrayList.get(i).setFont(new Font("����",Font.BOLD,14));
					/*arrayList.get(i).setLocation(30,30*(i+1));*/
					this.add(arrayList.get(i));
					/*arrayList.get(i).setVisible(true);*/
				}
=======
>>>>>>> pr/1




				con1.close();

			quitButton = new JButton("Ȯ��");
			quitButton.setBounds(340, 480, 80, 30);
			add(quitButton);
			quitButton.addActionListener(this);



			}

<<<<<<< HEAD
=======
				con1.close();
				printAllJlabel2=new JLabel[c];
				System.out.println(printAllJlabel2.length);
				int cc=0;
				for(JLabel j2:arr) {
					printAllJlabel2[cc]=j2;
>>>>>>> pr/1


					printAllJlabel2[cc].setBounds(0, 0, 500, 30); // printAllJlabel2�� ���� ����
					printAllJlabel2[cc].setFont(new Font("����", Font.BOLD, 25)); // printAllJlabel2�� ��Ʈ ����
					printAllJlabel2[cc].setForeground(Color.WHITE);

					printAllJlabel2[cc].setLocation(100,(cc+1)*50);
					add(printAllJlabel2[cc]);
					cc++;
				}

			}// �����ϱ� ��ư�� ���� �������� ��������
			// ActionListener �߰��Ѵ�
			//--------------------------------------------------------------------DB start



			//------------------------------------------------------------------DB end

		}
	}



//--------------------------------------------------------------end DB



	private void firstStart() { // �����ϱ� ��ư�� ������ ���� �޼ҵ�(���ӽ���)

		new JFrame(studentName+"�� ����!");
		insertDap = new JTextField(2);
		insertDap.addKeyListener(this); // �ؽ�Ʈ�ʵ忡 Ű �̺�Ʈ �߰�(����)
		insertDap.setFont(new Font("����",Font.BOLD,15));
		insertDap.setBounds(320, 493, 150, 30);
		add(insertDap); // ���� �Է��ϴ� �ؽ�Ʈ�ʵ带 �������� ���� �߰��Ѵ�

		tajaLabel.setVisible(false);
		askNickname.setVisible(false);
		askName.setVisible(false);

		startButton.setVisible(false); // ���� ��ư �Ⱥ��̰�
		inputNickname.setVisible(false);
		inputName.setVisible(false);
		insertDap.setVisible(true); // �� �Է�â ���̰� ��

		// ��� �̹����� �ι�° �̹����� �ٲ�

		word_create.shuffle();// word_createŬ�������� �ܾ������ ���� shuffle�޼ҵ带 �����Ѵ�.
		for (int i = 0; i < arrJlabel.length; i++) {
			arrJlabel[i] = new JLabel(
					word_create.arr.get(i)); /*
			 * JLabel�� ���ʴ�� �������� ���Ե� ��̸���Ʈ
			 * Ű������ �ϴ� �ؽ����� ������ �̸����� ����
			 */

			arrJlabel[i].setBounds(0, 0, 150, 30); // arrJLabel�� ���� ����
			arrJlabel[i].setFont(new Font("����", Font.BOLD, 25)); // arrJLabel�� ��Ʈ ����
			arrJlabel[i].setForeground(Color.WHITE);
			int num=myRandom.nextInt(300) -200;
			int num2=(i * 90)+30;
			arrJlabel[i].setLocation(num2, num); // arrJLabel��
			// ��ġ��
			// �����Ѵ�.
			add(arrJlabel[i]); // arrJLabel�� �гο� �߰��Ѵ�

		}


		total_play_time.playTime.setVisible(true); // �ð�Ÿ�̸Ӹ� ���̰� ��
		total_play_time.start(); // �ð� Ÿ�̸� ������ ����
		data_rain.start(); // �꼺�� ������ ����

	}
	public void paintComponent(Graphics g) {
		g.drawImage(icon.getImage(), 0, 0, null); // 0,0��ǥ���� �̹����� �Ѹ�
		setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
		super.paintComponent(g);
	}


}

