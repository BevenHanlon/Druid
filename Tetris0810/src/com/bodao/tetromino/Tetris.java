package com.bodao.tetromino;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ��Ϸ�����࣬��Ϸ�����
 * 
 * @author 59112
 */
public class Tetris extends JPanel {
	// ����
	private static final int ROW = 20;
	// ����
	private static final int COL = 10;
	// ÿһ�����ӵĴ�С
	private static final int CELL_SIZE = 26;
	// ���Ӷ�ά���� ����˹�����ǽ
	private Cell[][] wall = new Cell[ROW][COL];
	// ��Ҫ����Ķ���˹����
	private Tetromino tetromino;
	// ��һ��Ҫ�����Ķ���˹����
	private Tetromino nextOne;
	// ���ٵ�����
	private int lines;
	// �÷�
	private int score;
	//����һ�����鱣�治ͬ�ķ���
	private int[] grade = {0,1,10,50,100};
	// �ȼ�
	private int level = 1;
	//��Ϸ����״̬
	private static final int RUNNING=0;
	//��Ϸ��ͣ״̬
	private static final int GAME_PAUSE =1;
	//��Ϸʧ��
	private static final int OVER = 2;
	//��Ϸ״̬
	private int state = RUNNING;
	
	// ͼƬ��Դ
	public static BufferedImage TERIS;// ����
	public static BufferedImage GAME_OVER;// ��Ϸ����
	public static BufferedImage I;
	public static BufferedImage J;
	public static BufferedImage L;
	public static BufferedImage O;
	public static BufferedImage T;
	public static BufferedImage S;
	public static BufferedImage Z;
	public static BufferedImage PAUSE;// �ݶ�
	// ��ʼ����̬��ԴͼƬ
	static {
		try {// ͼƬ��ȡ����ȡӲ����ͨ����ǰ������ĳһ��ͼƬ��Դ
			TERIS = ImageIO.read(Tetris.class.getResourceAsStream("TETRIS.png"));
			GAME_OVER = ImageIO.read(Tetris.class.getResourceAsStream("game-over.png"));
			I = ImageIO.read(Tetris.class.getResourceAsStream("I.png"));
			J = ImageIO.read(Tetris.class.getResourceAsStream("J.png"));
			L = ImageIO.read(Tetris.class.getResourceAsStream("L.png"));
			O = ImageIO.read(Tetris.class.getResourceAsStream("O.png"));
			T = ImageIO.read(Tetris.class.getResourceAsStream("T.png"));
			S = ImageIO.read(Tetris.class.getResourceAsStream("S.png"));
			J = ImageIO.read(Tetris.class.getResourceAsStream("J.png"));
			Z = ImageIO.read(Tetris.class.getResourceAsStream("Z.png"));
			PAUSE = ImageIO.read(Tetris.class.getResourceAsStream("pause.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * ��д���Ļ�ͼ���� ����Ϸ�е����� g ���� ��ͼƬ���֣�ͼ�� �ȵ�
	 */
	@Override
	public void paint(Graphics g) {
		// ����Ϸ�е�����
		/*
		 * ʹ�û��ʵ��û�ͼƬ�ķ��� ������ ����1 ͼƬ���� ����2 ͼƬ���x������ ����3 ͼƬ���y������ ����4 ͼƬ�۲��߶���
		 * Ĭ�����ó�null
		 */
		g.drawImage(TERIS, 0, 0, null);
		// ����ƽ��15������
		g.translate(15, 15);
		// ��ǽ
		paintWall(g);
		// ������˹����
		paintTetromino(g);
		// ����һ�������Ķ���˹����
		paintNextOne(g);
		// ���������������ȼ���
		piantCount(g);
		//����Ϸ״̬
		paintState(g);
	}
	
	//����Ϸ״̬
	private void paintState(Graphics g){
		switch (state) {
		case GAME_PAUSE:
			/*
			 * -15��ԭ����Ϊ���Ҷ���˹�����ǽ��λ��
			 * ����ƽ����15�����أ���ͣͼƬ
			 * ��Ҫ������ͬһ���꣬Ҫ��ȥ15
			 * ��������λ��
			 */
			g.drawImage(PAUSE, -15,-15, null);
			break;
		case OVER:
			g.drawImage(GAME_OVER, -15, -15, null);
			break;
		}
	}
	
	
	/**
	 * ��������
	 * 
	 * @param g
	 */
	private void piantCount(Graphics g) {
		int x = 290;
		int y = 160;
		// ����������� �������� ������ �����С
		Font font = new Font("Droid Serif", Font.BOLD, 23);
		// ���û�������
		g.setFont(font);
		// ������ɫ
		g.setColor(new Color(102, 119, 153));
		// ������
		g.drawString("SCORE:" + score, x, y);
		y += 56;
		// ������
		g.drawString("LINES:" + lines, x, y);
		y += 56;
		// ���ȼ���
		g.drawString("LEVEL:" + level, x, y);

	}

	/**
	 * ����һ�������Ķ���˹����
	 * 
	 * @param g
	 */
	private void paintNextOne(Graphics g) {
		if (nextOne == null) {
			return;
		}
		Cell[] cells = nextOne.cells;
		for (Cell cell : cells) {
			int x = (cell.getCol() + 10) * CELL_SIZE;
			int y = (cell.getRow() + 1) * CELL_SIZE;
			g.drawImage(cell.getImage(), x, y, null);
		}

	}

	/**
	 * ��ǽ
	 * 
	 * @param g
	 */
	private void paintWall(Graphics g) {
		// wall[2][2] = new Cell(2,2,T);
		// wall[5][8] = new Cell(5, 8, I);
		// ������ά����
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				// ��ʼ�� ǽ�ϵĸ���
				Cell c = wall[i][j];
				int x = j * CELL_SIZE;
				int y = i * CELL_SIZE;
				if (c == null) {// ������
					g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
				} else {
					// ��ͼƬ
					g.drawImage(c.getImage(), x, y, null);
				}

			}
		}

	}

	/**
	 * ������˹����ķ���
	 * 
	 * @param g
	 */
	public void paintTetromino(Graphics g) {
		// I s = new I();//������s��
		if (tetromino == null) {
			return;
		}
		// ��ȡ�����ĸ����Ӷ���
		Cell[] cells = tetromino.cells;
		// ��������
		for (Cell cell : cells) {
			// ��ȡ���ӵ��к���
			int row = cell.getRow();
			int col = cell.getCol();
			// ͨ�����м����������ϻ�ͼ������λ��
			int x = col * CELL_SIZE;// ����С�����Ϳ���26
			int y = row * CELL_SIZE;
			// ��ͼƬ
			g.drawImage(cell.getImage(), x, y, null);
		}
	}

	/**
	 * ������Ϸ ������
	 * 
	 * @param args
	 */
	public void action() {
		tetromino = Tetromino.randomTetromino();
		nextOne = Tetromino.randomTetromino();
		// ��������һ�����̼����¼�
		/**
		 * �������̼����� KeyListener�Ǹ��ӿ� ʹ������KeyAdapter ���̼�����ʵ���� Ҳ�ǳ����࣬ʹ�������ڲ����
		 * ��ʽ����������
		 */
		KeyListener keyL = new KeyAdapter() {

			/**
			 * ��д���̰��µ��Ǹ����� KeyEvent �����¼�
			 */
			@Override
			public void keyPressed(KeyEvent e) {
				/*
				 * 
				 */
				switch (state) {
				case RUNNING:
					runningPressedAction(e);
					break;
				case GAME_PAUSE://��Ϸ��ͣ�µĲ���
					pausePressedAction(e);
					break;
				case OVER://��Ϸʧ��״̬�µĲ���
					gameOverPressedAction(e);
					break;
				
				}
				
				// repiant �ػ� ֮����ٵ���paint����
				repaint();// �ػ�
			}

		};
		// ��ȡ����
		// this.requestFocus(true);
		requestFocus();
		// �������Ӽ�����
		addKeyListener(keyL);
		//��ʱ��
		Timer timer = new Timer();
		//������ʱ����
		TimerTask tt = new TimerTask(){

			@Override
			public void run() {//��ʱ����ִ��
				
			if(state ==RUNNING){
				//���� 
				softDrop();
				}
				repaint();
			}
			
		};
		//������ʱ��
		timer.schedule(tt, 100,1000);
	}
	
	/**
	 * ��Ϸ ʧ�ܽ���״̬�Ĳ���
	 * @param e
	 */
	protected void gameOverPressedAction(KeyEvent e) {
		switch (e.getKeyCode()) {
		// s��ʾ���¿�ʼ��Ϸ�������������³�ʼ��
		case KeyEvent.VK_S:
			state = RUNNING;
			score = 0;
			lines = 0;
			wall = new Cell[ROW][COL];
			tetromino = Tetromino.randomTetromino();
			nextOne = Tetromino.randomTetromino();
			level = 1;
			break;

		case KeyEvent.VK_Q:
			System.exit(0);
			break;
		}
	}

	/**
	 * ��ͣ״̬�°����Ĳ���
	 * @param e
	 */
	protected void pausePressedAction(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_C:
			state = RUNNING;
			break;
		case KeyEvent.VK_Q:
			System.exit(0);
			break;	
		}
	}

	/**
	 * ��ת����
	 */
	public void rotateAction(){
		tetromino.rotateClockWise();//˳ʱ��
		if (isOutOfBonds()||isBlock()) {//��������赲��
			tetromino.rotateAntiClockWise();//��ʱ��
		}
	}
	
	
	/**
	 * �����ƶ�����
	 */
	public  void  moveLeftAction(){
		tetromino.moveLeft();
		if(isOutOfBonds() || isBlock()){//����֮��������赲��
			tetromino.moveRight();
		}
	}
	/**
	 * �����ƶ�����
	 */
	public  void moveRightAction(){
		tetromino.moveRight();
		if(isOutOfBonds() || isBlock()){//����֮��������赲��
			tetromino.moveLeft();//������
		}
	}
	
	/**
	 * ���������
	 * @return
	 */
	public void softDrop(){
		if(canDown()){
			tetromino.moveDown();
		}else{
			//����ǽ��
			landUpWall();
			//�ж��Ƿ������Ϸ
			if (isGameOver()) {
				state = OVER;
			}else{
				//������
				int line = destroyLine();
				score+=grade[line];
				this.lines=line;
				//����һ������˹���鸳ֵ����Ҫ����ĸ��� 
				tetromino = nextOne;
				//��һ��Ҫ����������������
				nextOne =Tetromino.randomTetromino();
			}
			
		}
	}
	/**
	 * Ӳ�������
	 */
	public void hardDrop(){
		while (canDown()) {
				tetromino.moveDown();
		}
		landUpWall();//�䵽ǽ��
		if (isGameOver()) {
			state = OVER;
		}else{
			//����
			int line = destroyLine();
			//�ı�ҳ���ϵ�����
			this.lines+=line;
			//�ӷ�
			this.score+=grade[line];
			//���³�ʼ������˹����
			tetromino = nextOne;
			nextOne = Tetromino.randomTetromino();
		}
	}
	
	/**
	 * �ж���Ϸ�Ƿ����
	 * @return
	 */
	private boolean isGameOver(){
		//ѭ������ǽ�ĵ�һ�е�ÿһ��Ԫ��������ǿ�
		for (int i = 0; i < COL; i++) {
			if(wall[0][i]!=null || wall[1][i] !=null){
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 *  �ж��Ƿ����
	 */
	public boolean isOutOfBonds() {
		boolean isOutOfBonds = false;
		// ��ȡ����˹����ĸ���
		Cell[] cells = tetromino.cells;
		for (Cell cell : cells) {
			int row = cell.getRow();
			int col = cell.getCol();
			if (col < 0 || col > COL - 1 || row < 0 || row > ROW - 1) {
				isOutOfBonds = true;
				break;
			}
		}
		return isOutOfBonds;
	}
	/**
	 * �Ƿ����赲
	 * @param args
	 */
	 public  boolean  isBlock(){
		 Cell[] cells = tetromino.cells;
		 for (Cell cell : cells) {
			int row = cell.getRow();
			int col = cell.getCol();
			/*
			 * �ó�����˹���������ȥǽ��
			 * ������û�и��Ӷ���
			 */
			if(wall[row][col]!=null){
				return true;
			}
		}
		 return false;
	 }
	/**
	 * �ж��Ƿ���Լ���������
	 */
	 public boolean  canDown(){
		 Cell[] cells  =tetromino.cells;
		 //���䵽���һ����
		 for(Cell cell:cells){
			 int row  = cell.getRow();
			 if(row==ROW-1){//�����һ��
				 return false;
			 }
		 }
		 //�����������µ�ǽ���и���
		 for(Cell cell:cells){
			 int row = cell.getRow();
			 int col = cell.getCol();
			//ǽ�ϵ�ǰ���ӵ���һ���и���
			 if(wall[row+1][col]!=null){
				 return false;
			 }
		 }
		 return true;
	 }
	 
	 /**
	  * ����ǽ��
	  * @param args
	  */
	 public void landUpWall(){
		 Cell[] cells = tetromino.cells;
		 for (Cell cell : cells) {
			int row = cell.getRow();
			int col = cell.getCol();
			/*
			 * ����ǰ����˹�����еĸ��Ӹ�ֵ��
			 * ǽ�ϵĶ�Ӧ����λ��
			 */
			wall[row][col]=cell;
		}
	 }
	 /**
	  * �ж��Ƿ�����
	  * @param args
	  */
	 public boolean isFullLine(int row){
		 boolean flag = true;
		 //��������ǽ��ĳһ��
		 for (int i = 0; i < COL; i++) {
			if (wall[row][i]==null) {
				return false;
			}
		}
		 return true;
	 }
	/**
	 * ������
	 * @param args
	 */
	 public int destroyLine(){
		 //��������ǽ
		 int l =0;
		 for (int i = 0; i < ROW; i++) {
			if (isFullLine(i)) {
				l++;
				//����һ�е�Ԫ�ظ�ֵ����ǰ��
				for (int j = i; j>0; j--) {
					//��㸴�ƣ������е���һ�и���10��Ԫ�أ�����һ��������
					Cell[] cArray = Arrays.copyOf(wall[j-1], 10);
					//�������鸳ֵ����ǰ��
					wall[j] = cArray;
				}
				
				/**
				 *����һ��Ԫ���ÿ�
				 * 
				 */
			Arrays.fill(wall[0], null);

			
			}
		}
		 return l;
 }
	 
	/**
	 * ����״̬�µİ������� 
	 * @param e
	 */
	 
	private void runningPressedAction(KeyEvent e) {
		// ��ӡ���̵Ķ�Ӧ����
		// System.out.println(e.getKeyCode());
		// ͨ�������¼���ȡ������
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:// ��� 37
			moveLeftAction();// �����ƶ�
			break;
		case KeyEvent.VK_RIGHT:// �Ҽ� 39
			moveRightAction();// �����ƶ�
			break;
		case KeyEvent.VK_DOWN:// �¼� 40
			softDrop(); 
			break;
		case KeyEvent.VK_UP://�ϼ� 38
			//��ת
			rotateAction();
			break;
		case KeyEvent.VK_P://��ͣ��Ϸ
			state = GAME_PAUSE;
			break;
		case KeyEvent.VK_Q:
			//�˳���Ϸ
			System.exit(0);
			break;
		case KeyEvent.VK_SPACE://�ո��
			hardDrop();//
			break;
		}
	}

	public static void main(String[] args) {
		// �����߿�(����)
		JFrame frame = new JFrame();
		// ���ñ߿��С
		frame.setSize(525, 550);
		// //�������
		Tetris tetris = new Tetris();
		// //�����ŵ��߿���
		frame.add(tetris);
		// //���ô���λ�þ���
		frame.setLocationRelativeTo(null);
		// ����Ĭ�Ϲرմ��ڵ�ʱ��ر�Ӧ�ó���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ȥ���߿�
		frame.setUndecorated(true);
		// ������Ϊ���ϲ�Ĵ���
		frame.setAlwaysOnTop(true);
		// ��ʾ�߿�,ִ�е�ʱ��ᾡ���ܿ�ĵ���paint����
		frame.setVisible(true);
		// ������Ϸ
		tetris.action();
	}

}
