package com.bodao.tetromino;

import java.util.Arrays;

/**
 * 俄罗斯方块
 * 
 * @author 59112
 */
public abstract class Tetromino {
	// 四个不能是私有
	protected Cell[] cells = new Cell[4];
	//添加一个状态数组
	protected State[] states;
	//状态变量表示转到第几个状态了
	private static int index=0;

	/**
	 * 随机产生俄罗斯方块的做成静态
	 */
	public static Tetromino randomTetromino() {
		// 随机数范围 (0,7);
		int type = (int) (Math.random() * 7);
		index=0;
		// Random random = new Random();
		// int ran = new Random().nextInt(7);
		switch (type) {
		case 0:
			return new T();
		case 1:
			return new I();
		case 2:
			return new O();
		case 3:
			return new S();
		case 4:
			return new Z();
		case 5:
			return new L();
		case 6:
			return new J();
		}
		return null;
	}
    
	// 关于俄罗斯方块旋转状态的内部类
	class State {
		int row0;
		int col0;
		int row1;
		int col1;
		int row2;
		int col2;
		int row3;
		int col3;
		public State() {
		}
		public State(int row0, int col0,int row1, int col1, int row2, int col2, int row3, int col3) {
			super();
			this.row0 = row0;
			this.col0 = col0;
			this.row1 = row1;
			this.col1 = col1;
			this.row2 = row2;
			this.col2 = col2;
			this.row3 = row3;
			this.col3 = col3;
		}

		
	}

	
	
	/**
	 * 旋转  顺时针
	 */ 
	public void rotateClockWise(){
		index++; //I
		cells[1].setRow(cells[0].getRow()+states[index%states.length].row1);
		cells[1].setCol(cells[0].getCol()+states[index%states.length].col1);
		cells[2].setRow(cells[0].getRow()+states[index%states.length].row2);
		cells[2].setCol(cells[0].getCol()+states[index%states.length].col2);
		cells[3].setRow(cells[0].getRow()+states[index%states.length].row3);
		cells[3].setCol(cells[0].getCol()+states[index%states.length].col3);
	}
	/**
	 * 逆时针
	 */
	public void rotateAntiClockWise(){
		index--;
		cells[1].setRow(cells[0].getRow()+states[index%states.length].row1);
		cells[1].setCol(cells[0].getCol()+states[index%states.length].col1);
		cells[2].setRow(cells[0].getRow()+states[index%states.length].row2);
		cells[2].setCol(cells[0].getCol()+states[index%states.length].col2);
		cells[3].setRow(cells[0].getRow()+states[index%states.length].row3);
		cells[3].setCol(cells[0].getCol()+states[index%states.length].col3);
	}
	
	/**
	 * 向右移动
	 */
	public void moveRight() {
		for (Cell cell : cells) {
			cell.moveRight();
		}
	}

	/**
	 * 向左移动
	 */
	public void moveLeft() {
		for (Cell cell : cells) {
			cell.moveLeft();
		}
	}

	/**
	 * 向下移动
	 */
	public void moveDown() {
		for (Cell cell : cells) {
			cell.moveDown();
		}
	}
	
	
	@Override
	public String toString() {
		return Arrays.toString(cells);
	}
	
}
