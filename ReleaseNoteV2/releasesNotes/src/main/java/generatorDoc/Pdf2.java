package generatorDoc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import models.ProjectModel;


public class Pdf2 {
	
	ProjectModel productModel;
	Document document = new Document();
	
	public Pdf2(ProjectModel productModel) {
		super();
		this.productModel = productModel;
	}

	public void generate() {
	  try {
          PdfWriter.getInstance(document,
              new FileOutputStream("HelloWorld.pdf"));

          document.open();
          document.add(new Paragraph("A Hello World PDF document Jira."));
          document.close(); // no need to close PDFwriter?

      } catch (DocumentException e) {
          e.printStackTrace();
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      }
	}


}
