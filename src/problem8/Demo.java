package problem8;

import java.io.File;
import java.io.IOException;


public class Demo
{
  public static void main(String[] args) throws IOException
  {
    File file = new File("board10.txt");
    SudokuBoard board = SudokuBoard.loadBoard(file);
    board.print();
  }
}
