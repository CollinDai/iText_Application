package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * PdfManipulator.java
 * 
 * @author Peike Dai
 * Date: 2012/06/26
 * Platform: MyEclipse Java 8.6， Windows7 Ultimate
 * 
 * version
 * =======
 * 1.0 - 2012/06/26
 * 
 * Module Operation
 * ================
 * 提供一个模块，将多页PDF放在一页中，并生成一个新PDF。
 * 
 * Public Interface
 * ================
 * PdfManipulator(HttpServletResponse response, InputStream in, float[] rect);
 * 						// Constructor with a http response to output and 
 * 						// an input stream to input. a length two array to 
 * 						// define the new pdf page size with width rect[0] 
 * 						// and height rect[1].
 * 
 * void mergeTo(int[] rowandcol);
 * 						// define how many rows int[0] and columns int[1] in 
 * 						// one page and create.
 * void saveDoc(); 		// save document and output.
 */
public class PdfManipulator {
	private Document document;
	private Rectangle rectangle, originSize, unitSize;
	private PdfWriter pdfwriter;
	private PdfReader pdfreader;
	private int pagesperpage;
	PdfImportedPage page;
	/**
	 * 
	 * @param os output stream
	 * @param in
	 * 			input stream of one pdf file. All pages sizes are presumed the same;
	 * @param rowandcol
	 *            rowandcol[0]: how many rows; rowandcol[1]: how many columns
	 * @param rect
	 * 			new pdf page size. rect[0]: width; rect[1]: height;
	 * @throws IOException
	 */
	public PdfManipulator(OutputStream os, InputStream in, float[] rect) throws IOException {
		this.pdfreader = new PdfReader(in);
		this.originSize = pdfreader.getPageSize(1);
		if (rect == null || rect.length != 2) {
			document = new Document(PageSize.A4, 36f, 36f, 36f, 36f);
		} else {
			this.rectangle = new Rectangle(rect[0], rect[1]);
			document = new Document(rectangle, 36f, 36f, 36f, 36f);
		}
		try {
			pdfwriter = PdfWriter.getInstance(document, os);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.open();
	}
	
	/**
	 * for test
	 * @throws IOException 
	 * 
	 */
	public PdfManipulator(InputStream in, float[] rect) throws IOException {
		this.pdfreader = new PdfReader(in);
		this.originSize = pdfreader.getPageSize(1);
		if (rect == null || rect.length != 2) {
			document = new Document(PageSize.A4, 36f, 36f, 36f, 36f);
		} else {
			this.rectangle = new Rectangle(rect[0], rect[1]);
			document = new Document(rectangle, 36f, 36f, 36f, 36f);
		}
		try {
			pdfwriter = PdfWriter.getInstance(document, new FileOutputStream(
					"PdfWriterLocalTest.pdf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.open();
	}

	/**
	 * rows: int[0] and columns: int[1]
	 * @param rowandcol
	 */
	public void mergeTo(int[] rowandcol) {
		if (rowandcol == null || rowandcol.length != 2){
			rowandcol = new int[]{1, 1};
		}
		this.pagesperpage = rowandcol[0] * rowandcol[1];
		PdfContentByte cb = pdfwriter.getDirectContent();
		float offsetX, offsetY, factorX, factorY;
		unitSize = new Rectangle(rectangle.getWidth() / rowandcol[1], rectangle
				.getHeight() / rowandcol[0]);
		int total = pdfreader.getNumberOfPages();
		factorX = unitSize.getWidth() / originSize.getWidth();
		factorY = unitSize.getHeight() / originSize.getHeight();

		// r: iterated row; temp: block page number in big paper. 0 => the last page
		int r, temp;
		for (int i = 0; i < total;) {

			if (i % pagesperpage == 0) {
				document.newPage();
			}
			i++;
			offsetX = rectangle.getWidth() * ((i - 1) % rowandcol[1])
					/ rowandcol[1];
			temp = i % this.pagesperpage;
			r = 1;
			if (temp == 0)
				r = rowandcol[0];
			else {
				while ((temp - rowandcol[1]) > 0) {
					r++;
					temp -= rowandcol[1];
				}
			}
			offsetY = rectangle.getHeight() * (rowandcol[0] - r)
					/ rowandcol[0];
			page = pdfwriter.getImportedPage(pdfreader, i);
			cb.addTemplate(page, factorX, 0, 0, factorY, offsetX, offsetY);
		}
	}
	
	public void saveDoc(){
		try {
//			if(this.pdfwriter!=null){
//				pdfwriter.close();
//			}
			if (document != null) {
				document.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
