package dataSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Csvwriter {

  @SuppressWarnings("rawtypes")
  public static File createCSVFile(double[] exportData,String outPutPath,
                   String fileName) {
    File csvFile = null;
    BufferedWriter csvFileOutputStream = null;
    try {
      File file = new File(outPutPath);
      if (!file.exists()) {
        file.mkdir();
      }
      csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
      csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
        csvFile), "UTF-8"), 399);
      for (double d : exportData) {
    	  csvFileOutputStream.write(String.valueOf(d));
    	  csvFileOutputStream.write(",");
      }
      csvFileOutputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        csvFileOutputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return csvFile;
  }
  
  
}
