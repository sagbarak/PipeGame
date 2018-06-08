package searcher;

public class Square implements Moveable{

	int row;
	int column;
	Direction dirOut;
	Direction dirIn;
	int counter;
	/*-----------------Constructors------------------------*/
	public Square() {}
	
	public Square(int row,int column) {
		this.row=row;
		this.column=column;
		this.counter=1;
	}
	
	public Square(Square sq) {
		this.row=sq.row;
		this.column=sq.column;
	}
	/*------------------Getters/Setters---------------------*/
	
	public void increaseCounter() {
		this.counter++;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public void setCounter(int counter) {
		this.counter=counter;
	}
	public int getCounter() {
		return this.counter;
	}
	/*---------------------------------------------------*/
	
	/*Returns true if at least one of his neighbors can connect*/
	@Override
	public boolean moveIndex(int rows,int columns,char[][] board) {

		if(this.dirOut==Direction.right) 
		{
			if(this.getColumn()<columns-1 ) 
			{
				if(board[this.getRow()][this.getColumn()+1] =='-' || board[this.getRow()][this.getColumn()+1]=='7' ||  board[this.getRow()][this.getColumn()+1]=='J'||  board[this.getRow()][this.getColumn()+1]=='g') {
					return true;
				}
			}
		}
		if(this.dirOut==Direction.left) {
			if(this.getColumn()>0) 
			{
				if(board[this.getRow()][this.getColumn()-1] =='-' || board[this.getRow()][this.getColumn()-1]=='F' ||  board[this.getRow()][this.getColumn()-1]=='L'||  board[this.getRow()][this.getColumn()-1]=='g') {
					return true;
				}
			}

		}
		if(this.dirOut==Direction.up) {
			if(this.getRow()>0) 
			{
				if(board[this.getRow()-1][this.getColumn()] =='|' || board[this.getRow()-1][this.getColumn()]=='7' ||  board[this.getRow()-1][this.getColumn()]=='F'||  board[this.getRow()-1][this.getColumn()]=='g') {
					return true;
				}
			}

		}
		if(this.dirOut==Direction.down) {
			if(this.getRow()<rows-1 ) 
			{
				if(board[this.getRow()+1][this.getColumn()] =='|' || board[this.getRow()+1][this.getColumn()]=='L' ||  board[this.getRow()+1][this.getColumn()]=='J'||  board[this.getRow()+1][this.getColumn()]=='g') {
					return true;
				}
			}
		}
		return false;
	}
}

