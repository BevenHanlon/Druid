package com.bodao.tetromino;


/**
 * I型方块
 * @author 59112
 *
 */
public class I extends Tetromino {
	public I(){
		/*
		 * 对数组的4个格子赋值  
		 * 将数组第一个元素做成俄罗斯方块的
		 * 中心点
		 */
		cells[0]=new Cell(0,4,Tetris.I);//中心点
		cells[1]=new Cell(0,3,Tetris.I);
		cells[2]=new Cell(0,5,Tetris.I);
		cells[3]=new Cell(0,6,Tetris.I);
		//旋转状态
		states = new State[2];
		states[0]=new State(0,0,0,-1,0,1,0,2);
		states[1] =new State(0,0,-1,0,1,0,2,0);
	}
}
