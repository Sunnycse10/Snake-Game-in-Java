import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GameFrame()
	{
		GamePanel panel= new GamePanel();
		this.add(panel);
		this.setTitle("snake");
		this.setBounds(0,0,610, 640);
		//this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

}
