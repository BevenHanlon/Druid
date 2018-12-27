package com.bodao.tetromino;

import java.awt.image.BufferedImage;

/**
 * ������
 * @author 59112
 * @version  1.0
 */
public class Cell {
	private int row;//��
	private int col;//��
	private BufferedImage image;//ͼƬ
	public Cell() {
		super();
	}
	public Cell(int row, int col, BufferedImage image) {
		super();
		this.row = row;
		this.col = col;
		this.image = image;
	}

	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	/**
	 * �����ƶ�
	 */
	public void moveLeft(){
		col--;
	}
	/**
	 * �����ƶ�
	 */
	public  void moveRight(){
		col++;
	}
	/**
	 * ���� �ƶ�
	 */
	public void moveDown(){
		row++;
	}
	/**
	 * �鿴���ӵ���Ϣ
	 */
	public  String toString(){
		return "("+row+","+col+")";
	}
	
	
	
	
	
	
	
	
}
