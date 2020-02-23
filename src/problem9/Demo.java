package problem9;

import java.io.File;
import java.io.IOException;


public class Demo
{
  public static void main(String[] args) throws IOException
  {
    File infile = new File("Alice.jpg");

    int[][] image = IOUtils.loadImageAndConvertToGrayScale(infile);
    
    File outfile = new File("AliceGray.jpg");
    IOUtils.storeImage(outfile, image);
    
    System.out.println("done");
  }
}
