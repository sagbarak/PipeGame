package searcher;

public interface Moveable {
	public enum Direction{left,right,up,down}
	boolean moveIndex(int rows,int columns,char[][] board);
}
