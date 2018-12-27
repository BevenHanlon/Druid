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
 * 游戏控制类，游戏面板类
 * 
 * @author 59112
 */
public class Tetris extends JPanel {
	// 行数
	private static final int ROW = 20;
	// 列数
	private static final int COL = 10;
	// 每一个格子的大小
	private static final int CELL_SIZE = 26;
	// 格子二维数组 俄罗斯方块的墙
	private Cell[][] wall = new Cell[ROW][COL];
	// 正要下落的俄罗斯方块
	private Tetromino tetromino;
	// 下一个要出场的俄罗斯方块
	private Tetromino nextOne;
	// 销毁的行数
	private int lines;
	// 得分
	private int score;
	//定义一个数组保存不同的分数
	private int[] grade = {0,1,10,50,100};
	// 等级
	private int level = 1;
	//游戏运行状态
	private static final int RUNNING=0;
	//游戏暂停状态
	private static final int GAME_PAUSE =1;
	//游戏失败
	private static final int OVER = 2;
	//游戏状态
	private int state = RUNNING;
	
	// 图片资源
	public static BufferedImage TERIS;// 背景
	public static BufferedImage GAME_OVER;// 游戏结束
	public static BufferedImage I;
	public static BufferedImage J;
	public static BufferedImage L;
	public static BufferedImage O;
	public static BufferedImage T;
	public static BufferedImage S;
	public static BufferedImage Z;
	public static BufferedImage PAUSE;// 暂定
	// 初始化静态资源图片
	static {
		try {// 图片读取器读取硬盘上通过当前的类找某一张图片资源
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
	 * 重写面板的画图方法 画游戏中的内容 g 画笔 画图片，字，图形 等等
	 */
	@Override
	public void paint(Graphics g) {
		// 画游戏中的内容
		/*
		 * 使用画笔调用画图片的方法 画背景 参数1 图片对象 参数2 图片起点x的坐标 参数3 图片起点y的坐标 参数4 图片观察者对象
		 * 默认设置成null
		 */
		g.drawImage(TERIS, 0, 0, null);
		// 画笔平移15个像素
		g.translate(15, 15);
		// 画墙
		paintWall(g);
		// 画俄罗斯方块
		paintTetromino(g);
		// 画下一个出场的俄罗斯方块
		paintNextOne(g);
		// 画行数，分数，等级数
		piantCount(g);
		//画游戏状态
		paintState(g);
	}
	
	//画游戏状态
	private void paintState(Graphics g){
		switch (state) {
		case GAME_PAUSE:
			/*
			 * -15的原因是为了找俄罗斯方块的墙的位置
			 * 曾经平移了15个像素，暂停图片
			 * 需要跟背景同一坐标，要减去15
			 * 才是零点的位置
			 */
			g.drawImage(PAUSE, -15,-15, null);
			break;
		case OVER:
			g.drawImage(GAME_OVER, -15, -15, null);
			break;
		}
	}
	
	
	/**
	 * 画各种数
	 * 
	 * @param g
	 */
	private void piantCount(Graphics g) {
		int x = 290;
		int y = 160;
		// 创建字体对象 字体名字 字体宽度 字体大小
		Font font = new Font("Droid Serif", Font.BOLD, 23);
		// 设置画笔字体
		g.setFont(font);
		// 设置颜色
		g.setColor(new Color(102, 119, 153));
		// 画行数
		g.drawString("SCORE:" + score, x, y);
		y += 56;
		// 画分数
		g.drawString("LINES:" + lines, x, y);
		y += 56;
		// 画等级数
		g.drawString("LEVEL:" + level, x, y);

	}

	/**
	 * 画下一个出场的俄罗斯方块
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
	 * 画墙
	 * 
	 * @param g
	 */
	private void paintWall(Graphics g) {
		// wall[2][2] = new Cell(2,2,T);
		// wall[5][8] = new Cell(5, 8, I);
		// 遍历二维数组
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				// 初始化 墙上的格子
				Cell c = wall[i][j];
				int x = j * CELL_SIZE;
				int y = i * CELL_SIZE;
				if (c == null) {// 画矩形
					g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
				} else {
					// 画图片
					g.drawImage(c.getImage(), x, y, null);
				}

			}
		}

	}

	/**
	 * 画俄罗斯方块的方法
	 * 
	 * @param g
	 */
	public void paintTetromino(Graphics g) {
		// I s = new I();//创建的s型
		if (tetromino == null) {
			return;
		}
		// 获取它的四个格子对象
		Cell[] cells = tetromino.cells;
		// 遍历数组
		for (Cell cell : cells) {
			// 获取格子的行和列
			int row = cell.getRow();
			int col = cell.getCol();
			// 通过行列计算出在面板上画图的像素位置
			int x = col * CELL_SIZE;// 假设小正方型宽是26
			int y = row * CELL_SIZE;
			// 画图片
			g.drawImage(cell.getImage(), x, y, null);
		}
	}

	/**
	 * 启动游戏 动起来
	 * 
	 * @param args
	 */
	public void action() {
		tetromino = Tetromino.randomTetromino();
		nextOne = Tetromino.randomTetromino();
		// 给面板添加一个键盘监听事件
		/**
		 * 创建键盘监听器 KeyListener是个接口 使用子类KeyAdapter 键盘监听的实现类 也是抽象类，使用匿名内部类的
		 * 方式来创建对象
		 */
		KeyListener keyL = new KeyAdapter() {

			/**
			 * 重写键盘按下的那个方法 KeyEvent 键盘事件
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
				case GAME_PAUSE://游戏暂停下的操作
					pausePressedAction(e);
					break;
				case OVER://游戏失败状态下的操作
					gameOverPressedAction(e);
					break;
				
				}
				
				// repiant 重绘 之后快速调用paint方法
				repaint();// 重绘
			}

		};
		// 获取焦点
		// this.requestFocus(true);
		requestFocus();
		// 给面板添加监听器
		addKeyListener(keyL);
		//定时器
		Timer timer = new Timer();
		//创建定时任务
		TimerTask tt = new TimerTask(){

			@Override
			public void run() {//定时任务执行
				
			if(state ==RUNNING){
				//下落 
				softDrop();
				}
				repaint();
			}
			
		};
		//启动定时器
		timer.schedule(tt, 100,1000);
	}
	
	/**
	 * 游戏 失败结束状态的操作
	 * @param e
	 */
	protected void gameOverPressedAction(KeyEvent e) {
		switch (e.getKeyCode()) {
		// s表示重新开始游戏，所以属性重新初始化
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
	 * 暂停状态下按键的操作
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
	 * 旋转控制
	 */
	public void rotateAction(){
		tetromino.rotateClockWise();//顺时针
		if (isOutOfBonds()||isBlock()) {//出界或者阻挡了
			tetromino.rotateAntiClockWise();//逆时针
		}
	}
	
	
	/**
	 * 向左移动控制
	 */
	public  void  moveLeftAction(){
		tetromino.moveLeft();
		if(isOutOfBonds() || isBlock()){//出界之后或者有阻挡的
			tetromino.moveRight();
		}
	}
	/**
	 * 向右移动控制
	 */
	public  void moveRightAction(){
		tetromino.moveRight();
		if(isOutOfBonds() || isBlock()){//出界之后或者有阻挡的
			tetromino.moveLeft();//向左移
		}
	}
	
	/**
	 * 软下落控制
	 * @return
	 */
	public void softDrop(){
		if(canDown()){
			tetromino.moveDown();
		}else{
			//落在墙上
			landUpWall();
			//判断是否结束游戏
			if (isGameOver()) {
				state = OVER;
			}else{
				//销毁行
				int line = destroyLine();
				score+=grade[line];
				this.lines=line;
				//将下一个俄罗斯方块赋值给正要下落的格子 
				tetromino = nextOne;
				//下一个要下落的重新随机生成
				nextOne =Tetromino.randomTetromino();
			}
			
		}
	}
	/**
	 * 硬下落控制
	 */
	public void hardDrop(){
		while (canDown()) {
				tetromino.moveDown();
		}
		landUpWall();//落到墙上
		if (isGameOver()) {
			state = OVER;
		}else{
			//销行
			int line = destroyLine();
			//改变页面上的行数
			this.lines+=line;
			//加分
			this.score+=grade[line];
			//重新初始化俄罗斯方块
			tetromino = nextOne;
			nextOne = Tetromino.randomTetromino();
		}
	}
	
	/**
	 * 判断游戏是否结束
	 * @return
	 */
	private boolean isGameOver(){
		//循环遍历墙的第一行的每一个元素如果不是空
		for (int i = 0; i < COL; i++) {
			if(wall[0][i]!=null || wall[1][i] !=null){
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 *  判断是否出界
	 */
	public boolean isOutOfBonds() {
		boolean isOutOfBonds = false;
		// 获取俄罗斯方块的格子
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
	 * 是否有阻挡
	 * @param args
	 */
	 public  boolean  isBlock(){
		 Cell[] cells = tetromino.cells;
		 for (Cell cell : cells) {
			int row = cell.getRow();
			int col = cell.getCol();
			/*
			 * 拿出俄罗斯方块的行列去墙上
			 * 查找有没有格子对象
			 */
			if(wall[row][col]!=null){
				return true;
			}
		}
		 return false;
	 }
	/**
	 * 判断是否可以继续往下落
	 */
	 public boolean  canDown(){
		 Cell[] cells  =tetromino.cells;
		 //下落到最后一行了
		 for(Cell cell:cells){
			 int row  = cell.getRow();
			 if(row==ROW-1){//是最后一行
				 return false;
			 }
		 }
		 //下落中再往下的墙上有格子
		 for(Cell cell:cells){
			 int row = cell.getRow();
			 int col = cell.getCol();
			//墙上当前格子的下一行有格子
			 if(wall[row+1][col]!=null){
				 return false;
			 }
		 }
		 return true;
	 }
	 
	 /**
	  * 落在墙上
	  * @param args
	  */
	 public void landUpWall(){
		 Cell[] cells = tetromino.cells;
		 for (Cell cell : cells) {
			int row = cell.getRow();
			int col = cell.getCol();
			/*
			 * 将当前俄罗斯方块中的格子赋值给
			 * 墙上的对应行列位置
			 */
			wall[row][col]=cell;
		}
	 }
	 /**
	  * 判断是否满行
	  * @param args
	  */
	 public boolean isFullLine(int row){
		 boolean flag = true;
		 //遍历整个墙的某一行
		 for (int i = 0; i < COL; i++) {
			if (wall[row][i]==null) {
				return false;
			}
		}
		 return true;
	 }
	/**
	 * 销毁行
	 * @param args
	 */
	 public int destroyLine(){
		 //遍历整个墙
		 int l =0;
		 for (int i = 0; i < ROW; i++) {
			if (isFullLine(i)) {
				l++;
				//把上一行的元素赋值给当前行
				for (int j = i; j>0; j--) {
					//深层复制，将满行的上一行复制10个元素，生成一个新数组
					Cell[] cArray = Arrays.copyOf(wall[j-1], 10);
					//将新数组赋值给当前行
					wall[j] = cArray;
				}
				
				/**
				 *将第一行元素置空
				 * 
				 */
			Arrays.fill(wall[0], null);

			
			}
		}
		 return l;
 }
	 
	/**
	 * 运行状态下的按键控制 
	 * @param e
	 */
	 
	private void runningPressedAction(KeyEvent e) {
		// 打印键盘的对应的码
		// System.out.println(e.getKeyCode());
		// 通过键盘事件获取键盘码
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:// 左键 37
			moveLeftAction();// 向左移动
			break;
		case KeyEvent.VK_RIGHT:// 右键 39
			moveRightAction();// 向右移动
			break;
		case KeyEvent.VK_DOWN:// 下键 40
			softDrop(); 
			break;
		case KeyEvent.VK_UP://上键 38
			//旋转
			rotateAction();
			break;
		case KeyEvent.VK_P://暂停游戏
			state = GAME_PAUSE;
			break;
		case KeyEvent.VK_Q:
			//退出游戏
			System.exit(0);
			break;
		case KeyEvent.VK_SPACE://空格键
			hardDrop();//
			break;
		}
	}

	public static void main(String[] args) {
		// 创建边框(窗口)
		JFrame frame = new JFrame();
		// 设置边框大小
		frame.setSize(525, 550);
		// //创建面板
		Tetris tetris = new Tetris();
		// //将面板放到边框中
		frame.add(tetris);
		// //设置窗口位置居中
		frame.setLocationRelativeTo(null);
		// 设置默认关闭窗口的时候关闭应用程序
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 去掉边框
		frame.setUndecorated(true);
		// 设置它为最上层的窗口
		frame.setAlwaysOnTop(true);
		// 显示边框,执行的时候会尽可能快的调用paint方法
		frame.setVisible(true);
		// 启动游戏
		tetris.action();
	}

}
