import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener,KeyListener{
	static final int SCREEN_WIDTH= 600;
	static final int SCREEN_HEIGHT= 600;
	private int GRID_SIZE=25;
	private int UNIT_SIZE= (SCREEN_WIDTH*SCREEN_HEIGHT)/(GRID_SIZE*GRID_SIZE);
	private int x[]= new int[UNIT_SIZE];
	private int y[]= new int[UNIT_SIZE];
	private int delay=100;
	private boolean gameover=false;
	private boolean helpmenu=false;
	int dirX=25;
	int dirY=25;
	public static int appleX,appleY;
	char direction='R';
	private boolean play=false;
	ArrayList<Integer>xarray= new ArrayList<Integer>();
	ArrayList<Integer>yarray= new ArrayList<Integer>();
	//ArrayList<Integer>freeX= new ArrayList<Integer>();
	//ArrayList<Integer>freeY= new ArrayList<Integer>();
	public int[][] xy= new int[24][24];
	private Timer time;
	private int bodyparts=6;
	private static int score=0;
	JButton startbutton,helpbutton,quitbutton;
	JLabel label1;
	
	GamePanel()
	{
		if(play!=true)
		{
			label1= new JLabel();
			label1.setFont(new Font("Dialog",Font.BOLD,20));
			label1.setText("WELCOME TO SNAKE GAME");
			label1.setForeground(Color.RED);
			label1.setBounds(150, 50, 400, 50);


			quitbutton= new JButton();
			quitbutton.setBounds(200, 250, 150, 50);
			quitbutton.setBackground(Color.cyan);
			quitbutton.setFocusable(false);
			quitbutton.setFont(new Font("Dialog",Font.BOLD,20));
			quitbutton.setText("QUIT");
			quitbutton.addActionListener(this);

			startbutton= new JButton();
			startbutton.setBounds(200, 150, 150, 50);
			startbutton.setBackground(Color.cyan);
			startbutton.setFocusable(false);
			startbutton.setFont(new Font("Dialog",Font.BOLD,20));
			startbutton.setText("PLAY");
			startbutton.addActionListener(this);

			helpbutton= new JButton();
			helpbutton.setBounds(200, 350, 150, 50);
			helpbutton.setBackground(Color.cyan);
			helpbutton.setFocusable(false);
			helpbutton.setFont(new Font("Dialog",Font.BOLD,20));
			helpbutton.setText("HELP");
			//helpbutton.addActionListener(this);

			this.add(startbutton);
			this.add(quitbutton);
			this.add(label1);
			this.add(helpbutton);
		}
		this.setLayout(null);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(this);
		rungame();

	}

	public void rungame() {
		// TODO Auto-generated method stub
		// newFood();
		freeCoordinates();
		GenerateRandomLocationForApple();
		time= new Timer(delay, this);
		time.start();
		
	}
	
	public void freeCoordinates() {
		// TODO Auto-generated method stub
		for(int i=0;i<24;i++)
		{
			for(int j=0;j<24;j++)
			{
				xy[i][j]=1;
			}
		}
		
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(play==true)
		{
			/*for(int i=0;i<GRID_SIZE;i++)
		{
			g.drawLine(i*SCREEN_WIDTH/GRID_SIZE, 0, i*SCREEN_WIDTH/GRID_SIZE, 600);
			g.drawLine(0,i*SCREEN_WIDTH/GRID_SIZE , 600, i*SCREEN_WIDTH/GRID_SIZE);
		}*/
			int p= ThreadLocalRandom.current().nextInt(25,35);
			g.setColor(Color.RED);
			g.fillOval(appleX, appleY, p, p);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("score: "+score, 400, 20);

			for(int i=0;i<bodyparts;i++)
			{
				if(i==0)
				{
					g.setColor(Color.YELLOW);
					g.fillOval(x[i], y[i], 28, 28);
				}
				else if(i>0 &&i<bodyparts-1)
				{
					g.setColor(Color.GREEN);
					g.fillOval(x[i],y[i], 25 , 25);
				}
				else
					g.setColor(Color.YELLOW);
				g.fillOval(x[i],y[i], 22 , 22);
				xarray.add(x[i]/25);
				yarray.add(y[i]/25);
			}
			while(xarray.size()>bodyparts)
			{
				xarray.remove(0);
				yarray.remove(0);
			}
		}
		if(gameover==true)
		{
			g.setColor(Color.MAGENTA);
			g.setFont(new Font("Arial", Font.ITALIC,30));
			g.drawString("GAME OVER!", 200, 200);
			g.drawString("YOUR SCORE: "+score, 175 , 300);
		}
		else if(helpmenu==true)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.ITALIC,30));
			g.drawString("Move Snake left: left arrow key"
					+ "\nMove Snake up: up arrow key"
					+ "\nMove Snake Down: Down arrow key"
					+ "\nMove Snake Right: Right Arrow key"
					+ "\nPress 1 to Exit"
					+ "\nPress Enter to go back to main menu", 200, 200);
			
		}
		//printarray();
		
		//System.out.println("applex"+appleX+ "appley"+appleY);
	}


	private void GenerateRandomLocationForApple() {
		/*int x = ThreadLocalRandom.current().nextInt(0, 24);
		int y = ThreadLocalRandom.current().nextInt(0, 24);
		while(xarray.contains(x)&&yarray.contains(y))
		{
			x = ThreadLocalRandom.current().nextInt(0, 24);
			y = ThreadLocalRandom.current().nextInt(0, 24);
		}*/
		/*int x = ThreadLocalRandom.current().nextInt(0, 24);
		int y = ThreadLocalRandom.current().nextInt(0, 24);*/
		
		for(int i=0;i<xarray.size();i++)
		{
			xy[xarray.get(i)][yarray.get(i)]=0;
		}

		/*while(xy[x][y]!=1)
		{
			x = ThreadLocalRandom.current().nextInt(0, 24);
			y = ThreadLocalRandom.current().nextInt(0, 24);
		}*/
		ArrayList<Pair>pair= new ArrayList<Pair>();
		for(int i=0;i<24;i++)
		{
			for(int j=0;j<24;j++)
			{
				if(xy[i][j]==1)
				{
					Pair p= new Pair(i,j);
					pair.add(p);
				}
			}
		}
		int random= ThreadLocalRandom.current().nextInt(0,pair.size());
		
		int x=pair.get(random).freeX;
		int y=pair.get(random).freeY;
		
		appleX=x*GRID_SIZE;
		appleY=y*GRID_SIZE;
		
		
	}
	public void checkApple() {
		// TODO Auto-generated method stub
		if(x[0]==appleX && y[0]==appleY)
		{
			bodyparts++;
			score++;
			GenerateRandomLocationForApple();
			
		}
		
	}
	
	private void checkCollision() {
		// TODO Auto-generated method stub
		for(int i=1;i<bodyparts;i++)
		{
			if(x[0]==x[i] && y[0]==y[i])
				{
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						System.out.println(e.getStackTrace());
					}
					play=false;
					gameover=true;
				}
				
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(play)
		{
		freeCoordinates();
		for(int i=bodyparts;i>0;i--)
		{
			x[i]=x[i-1]%600;
			y[i]=y[i-1]%600;
		}
		switch(direction)
		{
		case 'R':
			x[0]=(x[0]+dirX)%600;
			break;
		case 'U':
			if(y[0]<=0)
			{
				y[0]=600;
			}
			y[0]=(y[0]+dirY)%600;
			break;
		case 'D':
			y[0]=(y[0]+dirY)%600;
			break;
		case 'L':
			if(x[0]<=0)
			{
				x[0]=600;
			}
			x[0]=(x[0]+dirX)%600;
			break;
		}
		checkApple();
		checkCollision();
		}
		if(e.getSource()==startbutton)
		{
			play=true;
			startbutton.setVisible(false);
			label1.setVisible(false);
			quitbutton.setVisible(false);
			helpbutton.setVisible(false);
		}
		else if(e.getSource()==quitbutton)
		{
			System.exit(1);
		}
		else if(e.getSource()==helpbutton)
		{
			helpmenu=true;
		}
		
		//y[0]=(y[0]+25)%600;
		
		Toolkit.getDefaultToolkit().sync();
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			if(direction!='R')
			{
				direction='L';
				dirX=-25;
				
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(direction!='L')
			{
				direction='R';
				dirX=25;
			}
			break;
		case KeyEvent.VK_UP:
			if(direction!='D')
			{
				direction='U';
				dirY=-25;
			}
			break;
		case KeyEvent.VK_DOWN:
			if(direction!='U')
			{
				direction='D';
				dirY=25;
			}
			break;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
