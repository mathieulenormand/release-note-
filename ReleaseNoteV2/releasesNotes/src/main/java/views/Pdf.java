package views;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import models.IssueModel;
import models.ProjectModel;

public class Pdf {

	private Document document;
	private ProjectModel model;

	public Pdf() {
		document = new Document(PageSize.A4.rotate());
		// model = new ProjectModel();
	}

	public void setModel(ProjectModel model) {
		this.model = model;

	}

	// public ProjectModel getModel() {
	// return model;
	// }

	public void render() {
		try {

			OutputStream file = new FileOutputStream(new File("releases_notes.pdf"));
			PdfWriter.getInstance(document, file);
			Image image = Image.getInstance("src/main/img/arcad.png");
			image.scaleAbsolute(120f, 60f);

			PdfPTable table = new PdfPTable(5);

			PdfPCell cell = new PdfPCell();
			cell.setColspan(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(3.0f);
			cell.setBackgroundColor(new BaseColor(Color.ORANGE));

			table.addCell("ISSUE");
			table.addCell("TYPE");
			table.addCell("STATES");
			table.addCell("PRIORITY");
			table.addCell("DESCRIPTION");
			table.addCell(cell);
			table.setSpacingBefore(15.0f);
			table.setSpacingAfter(15.0f);

			for (IssueModel issue : model.getIssues()) {
				table.addCell(new Paragraph(issue.getIssue()));
				table.addCell(new Paragraph(issue.getType()));
				table.addCell(new Paragraph(issue.getState()));
				table.addCell(new Paragraph(issue.getPriority()));
				table.addCell(new Paragraph(issue.getDescription()));
			}

			// document
			document.open();
			document.add(image);

			Paragraph project = new Paragraph("PROJECT" + " : " + model.getName());
			project.setAlignment(Element.ALIGN_CENTER);
			document.add(project);

			Paragraph version = new Paragraph("VERSION" + " : " + model.getVersion());
			version.setAlignment(Element.ALIGN_CENTER);
			document.add(version);

			document.add(table);

			document.add(new Paragraph("Document Generated On - " + new Date().toString()));
			document.close();
			file.close();
			System.out.println("Pdf created successfully..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
