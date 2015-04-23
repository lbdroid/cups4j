package org.cups4j.test;

import java.io.FileInputStream;
import java.net.URL;

import org.cups4j.CupsPrinter;
import org.cups4j.PrintJob;
import org.cups4j.operations.ipp.IppGetPrinterAttributesOperation;
import org.cups4j.util.IppResultPrinter;

import ch.ethz.vppserver.ippclient.IppResult;

public class WindowsXPTest {
  public static void main(String[] args) {

    try {
      
      
      IppGetPrinterAttributesOperation o = new IppGetPrinterAttributesOperation(80);
      IppResult result = o.request(new URL("http://192.168.1.2:80/printers/PDFCreator"), null);
      IppResultPrinter.print(result);
      
      CupsPrinter printer = new CupsPrinter(new URL("http://192.168.1.2:80/printers/PDFCreator"), "PDFCreator", false);
      PrintJob printJob = new PrintJob.Builder(new FileInputStream("cups4j.ps")).userName("anonymous").jobName("1").build();
      printer.print(printJob);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
