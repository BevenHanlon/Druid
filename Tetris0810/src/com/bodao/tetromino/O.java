package com.bodao.tetromino;
/**
 * O�ͷ���
 * @author 59112
 *
 */
public class O extends Tetromino {
	public O(){
		cells[0] = new Cell(0, 4, Tetris.O);
		cells[1] = new Cell(0, 5, Tetris.O);
		cells[2] = new Cell(1, 4, Tetris.O);
		cells[3] = new Cell(1, 5, Tetris.O);
		states = new State[2];
		states[0] = new State(0,0,0,1,1,0,1,1);
		states[1] = new State(0,0,0,1,1,0,1,1);
	}
}
