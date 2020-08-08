
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{

	private int []snakexLength=new int[750];
	private int []snakeyLength=new int[750];
	
	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;
	
	private ImageIcon rightMouth;
	private ImageIcon leftMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	
	private ImageIcon titleImage;
	
	private Timer timer;
	private int delay=100;
	
	private ImageIcon snakeImage;
	
	private int lengthOfSnake=3;
	
	private int move=0;
	
	private int []enemyxPos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,
			450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	
	private int []enemyyPos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,
			450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825};

	
	private Random random=new Random();
	
	private int x1pos=random.nextInt(34);
	private int y1pos=random.nextInt(23);
	
	private int x2pos=random.nextInt(34);
	private int y2pos=random.nextInt(23);
	
	private int x3pos=random.nextInt(34);
	private int y3pos=random.nextInt(23);
	
	private int score=0;
	
	int choice=random.nextInt(9);
	
	String []articles= {"der","die","das"};
	
	public Gameplay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();
		
	}
	
	
	public void paint(Graphics g) {
		
		if(move==0) {
			snakexLength[2]=50;
			snakexLength[1]=75;
			snakexLength[0]=100;
			
			snakeyLength[2]=100;
			snakeyLength[1]=100;
			snakeyLength[0]=100;
		}
		
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		titleImage=new ImageIcon("src/snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		
		rightMouth=new ImageIcon("src/rightmouth.png");
		rightMouth.paintIcon(this,g,snakexLength[0],snakeyLength[0]);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Score: "+score, 780,30);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Length: "+lengthOfSnake, 780,50);
		
		for(int a=0;a<lengthOfSnake;a++) {
			
			
			if(a==0 && right) {
				rightMouth=new ImageIcon("src/rightmouth.png");
				rightMouth.paintIcon(this,g,snakexLength[a],snakeyLength[a]);
			}
			
			if(a==0 && left) {
				leftMouth=new ImageIcon("src/leftmouth.png");
				leftMouth.paintIcon(this,g,snakexLength[a],snakeyLength[a]);
			}
			if(a==0 && up) {
				upMouth=new ImageIcon("src/upmouth.png");
				upMouth.paintIcon(this,g,snakexLength[a],snakeyLength[a]);
			}
			if(a==0 && down) {
				downMouth=new ImageIcon("src/downmouth.png");
				downMouth.paintIcon(this,g,snakexLength[a],snakeyLength[a]);
			}
			
			if(a!=0) {
				snakeImage=new ImageIcon("src/snakeimage.png");
				snakeImage.paintIcon(this,g,snakexLength[a],snakeyLength[a]);
			}
			
		}
		
		
		
		if(enemyxPos[x1pos]==snakexLength[0] && enemyyPos[y1pos]==snakeyLength[0]) {
			
			lengthOfSnake++;
			score+=5;
			 x1pos=random.nextInt(34);
			 y1pos=random.nextInt(23);
			
			 x2pos=random.nextInt(34);
			 y2pos=random.nextInt(23);
			
			 x3pos=random.nextInt(34);
			 y3pos=random.nextInt(23);
			 
			 choice=random.nextInt(9);
			
		}
		
	
		
		
		
		
		
	
		
		int res=fun(choice,g);
		g.setFont(new Font("arial",Font.BOLD,15));
		
		color(g,res);
		g.drawString(articles[res],enemyxPos[x1pos],enemyyPos[y1pos]);
	
		color(g,(res+1)%3);
		
		g.drawString(articles[(res+1)%3],enemyxPos[x2pos],enemyyPos[y2pos]);
	
		color(g,(res+2)%3);
		
		g.drawString(articles[(res+2)%3],enemyxPos[x3pos],enemyyPos[y3pos]);
		
		for(int i=1;i<lengthOfSnake;i++) {
			
			if(snakexLength[i]==snakexLength[0] && snakeyLength[i]==snakeyLength[0]) {
				
				left=false;
				right=false;
				up=false;
				down=false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game Over",300,300);
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Space to RESTART",350,340);
				
				
			}
		}
		
		
		g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
			timer.start();
			if(right) {
				for(int r=lengthOfSnake-1;r>=0;r--) {
					snakeyLength[r+1]=snakeyLength[r];
				}
			
				for(int r=lengthOfSnake;r>=0;r--) {
					
					if(r==0) {
						snakexLength[r]+=25;
					}
					else {
						
						snakexLength[r]=snakexLength[r-1];
					}
					if(snakexLength[r]>850) {
						snakexLength[r]=25;
					}
					
				}
				repaint();
				
			}
			if(left)
			{
				for(int r=lengthOfSnake-1;r>=0;r--) {
					snakeyLength[r+1]=snakeyLength[r];
				}
			
				for(int r=lengthOfSnake;r>=0;r--) {
					
					if(r==0) {
						snakexLength[r]-=25;
					}
					else {
						
						snakexLength[r]=snakexLength[r-1];
					}
					if(snakexLength[r]<25) {
						snakexLength[r]=850;
					}
					
				}
				repaint();
				
			}
			if(up)
			{
				for(int r=lengthOfSnake-1;r>=0;r--) {
					snakexLength[r+1]=snakexLength[r];
				}
			
				for(int r=lengthOfSnake;r>=0;r--) {
					
					if(r==0) {
						snakeyLength[r]-=25;
					}
					else {
						
						snakeyLength[r]=snakeyLength[r-1];
					}
					if(snakeyLength[r]<75) {
						snakeyLength[r]=625;
					}
					
				}
				repaint();
	
	
			}
			if(down) 
			{
				for(int r=lengthOfSnake-1;r>=0;r--) {
					snakexLength[r+1]=snakexLength[r];
				}
			
				for(int r=lengthOfSnake;r>=0;r--) {
					
					if(r==0) {
						snakeyLength[r]+=25;
					}
					else {
						
						snakeyLength[r]=snakeyLength[r-1];
					}
					if(snakeyLength[r]>625) {
						snakeyLength[r]=75;
					}
					
				}
				repaint();
	
			}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			move=0;
			score=0;
			lengthOfSnake=3;
			repaint();
			
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			move++;
			if(!left) {
				right=true;
				left=false;
			}
			else {
				right=false;
				left=true; 
			}
			up=false;
			
			down=false;
			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			move++;
			if(!right) {
				right=false;
				left=true; 
			}
			else {
				
				right=true;
				left=false;
			}
			up=false;
			
			down=false;
			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			move++;
			if(!down) {
				up=true;
				down=false;
			}
			else {
				up=false;
				down=true;
			}
			right=false;
			left=false;
			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			move++;
			if(!up) {
				up=false;
				down=true;
			}
			else {
				
				up=true;
				down=false;
			}
			right=false;
			left=false;
			
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	public int fun(int n, Graphics g) {
		
		int result=1;
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,50));
		
		switch(n) {
			case 0:  
								
						g.drawString("Apfel", 50, 55);
						result=0;
					break;
			case 1:  
					
						g.drawString("Banane", 50, 55);	
					
						result=1;
					break;
		
			case 2: 
					
						g.drawString("Orange", 50, 55);
						result=1;
					break;
			case 3: 
					
						g.drawString("Mango", 50, 55);
						result=1;
					break;
			case 4:  
					
						g.drawString("Brokkoli", 50, 55);
						result=1;
					break;
			case 5:  
					
						g.drawString("Gemüse", 50, 55);
						result=2;
					break;
			case 6:  
					
						g.drawString("Kartoffel", 50, 55);
						result=1;
					break;
			case 7:  
					
						g.drawString("Kohl", 50, 55);
						result=0;
					break;
			case 8:  
					
						g.drawString("Obst", 50, 55);
						result=2;
					break;
		
		}	 
		
		
		return result;
	}
	
	public void color(Graphics g, int ch) {
		if(ch==0)
			g.setColor(Color.blue);
		else if(ch==1)
			g.setColor(Color.red);
		else
			g.setColor(Color.green);
	}
	
	
}

