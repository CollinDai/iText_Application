/**
 * 
 */
package pdfPrinter;


import java.util.ArrayList;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * PdfHeaderFooter.java
 * 
 * @author Peike Dai
 * Date: 2012/06/21
 * Platform: MyEclipse Java 8.6， Windows7 Ultimate
 * Version
 * ========
 * 1.0 - 2012/06/21
 * 	First release
 * 1.1 - 2012/07/19
 * 	Add the total page number option
 * 
 * 模块说明
 * =======
 * 提供一个功能用来显示页码和控制标题内容的重复。通过重写iText中的onStartPage来实现
 * 在每新的一页中添加文字到绝对位置，重写onEndPage来实现页码显示。页码显示位置可以是
 * 页底正中、页底右、和页顶右。
 * 
 * 公共接口
 * =======
 * 此模块被PdfDocument所使用，属于package内部模块。
 *
 */
public class PdfHeaderFooter extends PdfPageEventHelper{
	ArrayList<Paragraph> texts;
//	int[][] coordinate;
	float[][] coordinate;
	boolean hasHeader;
	boolean hasPageNumber;
	boolean hasTotleNumber;
	float marginTop;
	int numberPos;
	PdfTemplate total;
	PdfPTable pagenumbertable;
	PdfHeaderFooter(){		
	}
	
	/**
	 * 
	 * @param hasheader
	 * @param _text
	 * @param coordinate
	 * @param haspagenumber
	 * @param pos
	 */
	protected PdfHeaderFooter(boolean hasheader, PdfElement[] _text, float[][] coordinate, boolean haspagenumber, int numberpos){
		this.hasHeader = hasheader;
		this.hasPageNumber = haspagenumber;
		texts = new ArrayList<Paragraph>();
		if (_text != null)
			for (PdfElement s : _text)
				texts.add(new Paragraph(s.getPhrase()));
		this.coordinate = coordinate;
		this.numberPos = numberpos;
	}

	/**
	 * Added date: 2012/07/11
	 * coodinate set to float, add margin top option, more customizable
	 * @param _text
	 * @param coordinate
	 * @param marginTop
	 * @param numberpos
	 */
	protected PdfHeaderFooter(PdfElement[] _text, float[][] coordinate, float margintop, int numberpos) {
		this(_text, coordinate, margintop, numberpos, false);
	}
	

	/**
	 * Added date: 2012/07/19
	 * used for the last big boss pdf
	 * @param _text
	 * @param coordinate
	 * @param margintop
	 * @param numberpos
	 * @param hastotalnumber
	 */
	protected PdfHeaderFooter(PdfElement[] _text, float[][] coordinate, float margintop, int numberpos, boolean hastotalnumber) {
		this.texts = new ArrayList<Paragraph>();
		if (_text != null)
			for (PdfElement s : _text)
				texts.add(new Paragraph(s.getPhrase()));
		this.coordinate = coordinate;
		this.marginTop = margintop;
		this.numberPos = numberpos;
		this.hasTotleNumber = hastotalnumber;
	}
	
	public void onStartPage(PdfWriter writer, Document document) {
		if (!this.texts.isEmpty()) {
			Phrase nullphrase = new Phrase(this.marginTop, "\n是\n");
			try {
				document.add(nullphrase);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < texts.size(); ++i) {
				float xper = (float) this.coordinate[i][0];
				int xPos = (int)(document.getPageSize().getWidth() * xper / 100);
				float yper = (float) this.coordinate[i][1];
				int yPos = (int)(document.getPageSize().getHeight() * (1 - yper / 100));
				ColumnText.showTextAligned(writer.getDirectContent(),
						Element.ALIGN_CENTER, this.texts.get(i), xPos,
						yPos, 0);
			}
		}
	}
    
    /**
     * Adds the page number
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
	public void onEndPage(PdfWriter writer, Document document) {
		if (this.numberPos > 0) {
			float width = document.getPageSize().getWidth();
			float height = document.getPageSize().getHeight();
			try {
				if (total == null)
					total = writer.getDirectContent().createTemplate(30, 16);
				if (this.hasTotleNumber)
					this.pagenumbertable = new PdfPTable(2);
				else
					this.pagenumbertable = new PdfPTable(1);
				pagenumbertable.setTotalWidth(50f);
				pagenumbertable.setLockedWidth(true);
				pagenumbertable.getDefaultCell().setFixedHeight(20);
				pagenumbertable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				pagenumbertable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				pagenumbertable.addCell(Integer.toString(writer.getPageNumber()));
				if (this.hasTotleNumber) {
					PdfPCell cell = new PdfPCell(Image.getInstance(total));
					cell.setBorder(Rectangle.NO_BORDER);
					pagenumbertable.addCell(cell);
				}
				switch (numberPos) {
				case 3:
					pagenumbertable.writeSelectedRows(0, -1, width * 0.9f, height * 0.95f, writer.getDirectContent());
//					ColumnText.showTextAligned(writer.getDirectContent(),
//							Element.ALIGN_CENTER, new Phrase("< "+String.valueOf(writer
//									.getPageNumber())+" >"), width * 0.95f, height * 0.95f, 0);
					break;
				case 9:
					pagenumbertable.writeSelectedRows(0, -1, width * 0.9f, height * 0.05f, writer.getDirectContent());
//					ColumnText.showTextAligned(writer.getDirectContent(),
//							Element.ALIGN_CENTER, new Phrase(String.valueOf(writer
//									.getPageNumber())), width * 0.93f, height * 0.05f, 0);
					break;
				case 8:
					pagenumbertable.writeSelectedRows(0, -1, width * 0.5f, height * 0.05f, writer.getDirectContent());
//					ColumnText.showTextAligned(writer.getDirectContent(),
//							Element.ALIGN_CENTER, new Phrase("< "+String.valueOf(writer
//									.getPageNumber())+" >"), width * 0.5f, height * 0.05f, 0);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				throw new ExceptionConverter(e);
			}
		}
	}
	
	/**
     * Fills out the total number of pages before the document is closed.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                new Phrase("/ "+String.valueOf(writer.getPageNumber() - 1)),
                2, 2, 0);
    }
}
