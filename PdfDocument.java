package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * PdfDocument.java
 * 
 * @author Peike Dai
 * Platform: MyEclipse Java 8.6£¬ Windows7 Ultimate
 * Version
 * --------
 *  1.0	- 2012/06/07
 *    first release
 *  1.1 - 2012/06/25
 *    add page size customization
 *  1.2 - 2012/07/10
 *    reorganize the constructor, simplifies it
 * 
 * Module Operation
 * ================
 * This module provides a Pdf document object, each pdf element, like table, 
 * text block, title, or image etc., should be added to the document object
 * and invoke "save".
 * 
 * Default margin of document is 36
 * 
 * public interface
 * ================
 * PdfDocument(); 	//default constructor with margin 36 in each side
 * PdfDocument(HttpServletResponse response, String name,
 *			boolean rotate, boolean openInline)
 *					// constructor when used online
 * PdfDocument(OutputStream output, boolean rotate, float[] rect, float[] mars);
 * 					// FINAL constructor
 *					// rect: width and height of page
 *					// mars: margin left, right, top, bottom
 * chain methods available:
 * ------------------------
 * PdfDocument add(PdfElement element); 
 * 					// add an element to one document object right below the last object 
 * 					// just added
 * PdfDocument addAbsolutePosition(PdfElement element);
 * 					// add an element on an absolute position which is set before.
 * 					// if position was not set, this method will add the element 
 * 					// as "add" method
 * 		
 *
 */
public class PdfDocument {
	private Document document;
	private PdfWriter pdfwriter;
//	private boolean rotate;
//	private PdfHeaderFooter numberandtitle;
	private Rectangle rectangle;
	private float[] margins;
	
	public PdfDocument() {
		this(false);
	}
	
	public PdfDocument(OutputStream out){
		this(out, null);
	}
	
	public PdfDocument(boolean rotate){
		this(null, rotate);
	}
	
	public PdfDocument(OutputStream out, boolean rotate){
		this(out, rotate, null);
	}
	
	/**
	 * Add date 2012/06/26
	 * @param out
	 * @param rect
	 * 			null if you want to use A4
	 */
	public PdfDocument(OutputStream out, float[] rect) {
		this(out, false, rect);
	}
	
	public PdfDocument(OutputStream out, boolean rotate, float[] rect){
		this(out, rotate, rect, null);
	}
	
	public PdfDocument(OutputStream out, float[] mars, boolean rotate){
		this(out, rotate, null, mars);
	}

	/**
	 * Added date: 2012/07/10
	 * Final constructor for PdfDocument
	 * @param output
	 * @param rotate
	 * @param rect
	 * @param mars
	 */
	public PdfDocument(OutputStream output, boolean rotate, float[] rect, float[] mars) {
		if (output == null)
			try {
				output = new FileOutputStream("null.pdf");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		if (mars != null && mars.length == 4)
			this.margins = mars;
		else
			this.margins = new float[]{36f, 36f, 36f, 36f};
		
		if (rect != null && rect.length == 2) {
			this.rectangle = new Rectangle(rect[0], rect[1]);
			document = new Document(rectangle, margins[0], margins[1], margins[2], margins[3]);
		} else if (rotate) {
			document = new Document(PageSize.A4.rotate(), margins[0], margins[1], margins[2], margins[3]);
		} else
			document = new Document(PageSize.A4, margins[0], margins[1], margins[2], margins[3]);
		try {
			pdfwriter = PdfWriter.getInstance(document, output);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.open();
	}
	
	/**
	 * Modified date: 2012/07/11
	 * change type of coodinate to float[][]
	 * @param hasheader
	 * @param _text
	 * @param coordinate
	 * @param haspagenumber
	 * @param numberpos
	 * @param |-|-3
	 * @param |-|-|
	 * @param |-8-9
	 */
	public void headerOrPagenumber(boolean hasheader, PdfElement[] _text,
			float[][] coordinate, boolean haspagenumber, int numberpos) {
		PdfHeaderFooter numberandtitle = new PdfHeaderFooter(hasheader, _text, coordinate,
				haspagenumber, numberpos);
		pdfwriter.setPageEvent(numberandtitle);
	}

	/**
	 * Added date: 2012/07/11
	 * margintop modifiable
	 * @param _text
	 * @param coordinate
	 * @param marginTop
	 */
	public void headerOrPagenumber(PdfElement[] _text, float[][] coordinate, float marginTop){
		headerOrPagenumber(_text, coordinate, marginTop, 0);
	}
	
	/**
	 * Added date: 2012/07/11
	 * final method for header and page number
	 * @param _text
	 * @param coordinate
	 * @param marginTop
	 * @param numberpos
	 */
	public void headerOrPagenumber(PdfElement[] _text, float[][] coordinate, float marginTop, int numberpos){
		PdfHeaderFooter numberandtitle = new PdfHeaderFooter(_text, coordinate, marginTop, numberpos);
		pdfwriter.setPageEvent(numberandtitle);
	}
	
	/**
	 * Added date: 2012/07/19
	 * @param _text
	 * @param coordinate
	 * @param marginTop
	 * @param numberpos
	 * @param hastotal
	 */
	public void headerOrPagenumber(PdfElement[] _text, float[][] coordinate, float marginTop, int numberpos, boolean hastotal){
		PdfHeaderFooter numberandtitle = new PdfHeaderFooter(_text, coordinate, marginTop, numberpos, hastotal);
		pdfwriter.setPageEvent(numberandtitle);
	}
	
	public PdfDocument add(PdfElement element) {
		try {
			document.add(element.getPdfElement());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PdfDocument addAbsolutePosition(PdfElement element) {
		try {
			if (!element.setPosition(pdfwriter.getDirectContent(), document
					.getPageSize().getWidth(), document.getPageSize()
					.getHeight()))
				document.add(element.getPdfElement());
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return this;
	}	
	
	/**
	 * create a new page to write
	 * @return
	 */
	public PdfDocument newPage() {
		document.newPage();
		return this;
	}
	
	/**
	 * should be renamed to "save"
	 */
	public void saveToLocal() {
		try {
			if (document != null) {
				document.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
