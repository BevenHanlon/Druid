package com.bodao.tetromino;

import java.awt.image.BufferedImage;

/**
 * 格子类
 * @author 59112
 * @version  1.0
 */
public class Cell {
	private int row;//行
	private int col;//列
	private BufferedImage image;//图片
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
	 * 向左移动
	 */
	public void moveLeft(){
		col--;
	}
	/**
	 * 向右移动
	 */
	public  void moveRight(){
		col++;
	}
	/**
	 * 向下 移动
	 */
	public void moveDown(){
		row++;
	}
	/**
	 * 查看格子的信息
	 */
	public  String toString(){
		return "("+row+","+col+")";
	}
	
	
	
	
	
	
	
	
}
