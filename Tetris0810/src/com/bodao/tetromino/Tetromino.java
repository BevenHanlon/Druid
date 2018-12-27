package com.bodao.tetromino;

import java.util.Arrays;

/**
 * ����˹����
 * 
 * @author 59112
 */
public abstract class Tetromino {
	// �ĸ�������˽��
	protected Cell[] cells = new Cell[4];
	//���һ��״̬����
	protected State[] states;
	//״̬������ʾת���ڼ���״̬��
	private static int index=0;

	/**
	 * �����������˹��������ɾ�̬
	 */
	public static Tetromino randomTetromino() {
		// �������Χ (0,7);
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
    
	// ���ڶ���˹������ת״̬���ڲ���
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
	 * ��ת  ˳ʱ��
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
	 * ��ʱ��
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
	 * �����ƶ�
	 */
	public void moveRight() {
		for (Cell cell : cells) {
			cell.moveRight();
		}
	}

	/**
	 * �����ƶ�
	 */
	public void moveLeft() {
		for (Cell cell : cells) {
			cell.moveLeft();
		}
	}

	/**
	 * �����ƶ�
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
