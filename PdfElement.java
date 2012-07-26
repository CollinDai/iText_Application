/**
 * 
 */
package pdfPrinter;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;

/**
 * PdfElement.java
 * 
 * @author Peike Dai
 * 
 * Platform: MyEclipse Java 8.6， Windows7 Ultimate
 * Version
 * =======
 * 1.0 - 2012/06/07
 * 	First release
 * 1.1 - 2012/07/10
 * 	Add method to select font style between Gothic and Min, which the former is the default
 * 
 * module operation
 * ================
 * This module provides an abstract class of pdf element. Each specific element, such as
 * table, text block, or title derived from this class could be added directly to a 
 * pdfdocument object without any other modification in class PdfDocument.
 * It has one abstract method:
 * protected abstract Element getPdfElement();
 * It should be overrided in each subclass. It should return an iText element like
 * PdfPTable, Paragraph, Chunk, etc.
 * 
 * public interface
 * ================
 * boolean setPosition(float xPosPercent, float yPosPercent);
 * 												// set the absolute position of a pdf element
 * 
 * 
 */
public abstract class PdfElement {
	protected boolean isRepeatable;
	protected static BaseFont baseFontJp;
	static {
		try {
			baseFontJp = BaseFont.createFont("HeiseiKakuGo-W5",
					"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected float xPercentage = -1f, yPercentage = -1f;

	public PdfElement resetBaseFont() {
		try {
			baseFontJp = BaseFont.createFont("HeiseiKakuGo-W5",
					"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public PdfElement setFontMinCho() {
		try {
			baseFontJp = BaseFont.createFont("HeiseiMin-W3",
					"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public PdfElement setFontMaruGothic() {
		try {
			baseFontJp = BaseFont.createFont("B:\\Program Files\\Workspaces\\MyEclipse 8.6\\PdfPrinter\\epmarugo.ttf",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	
	
	public boolean setPosition(float xPosPercent, float yPosPercent) {
		if (xPosPercent >= 0 && xPosPercent <= 100f && yPosPercent >= 0
				&& yPosPercent <= 100f) {
			xPercentage = xPosPercent;
			yPercentage = yPosPercent;
			return true;
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param canvas 
 	 * @param pagewidth 
	 * @param pageheight
	 * @return 是否需要显示添加到文档中
	 */
	protected boolean setPosition(PdfContentByte canvas, float pagewidth, float pageheight)
	{
		return false;
	}
	
	protected Font setFontMin() {
		return this.setFontMin(0);
	}
	
	protected Font setFontMin(float size) {
		try {
			if (size > 0)
				return new Font(BaseFont.createFont("HeiseiMin-W3",
						"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), size);
			else
				return new Font(BaseFont.createFont("HeiseiMin-W3",
						"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected Font setFontMaruGo() {
		return this.setFontMaruGo(0);
	}
	
	protected Font setFontMaruGo(float size) {
		try {
			if (size > 0)
				return new Font(BaseFont.createFont("B:\\Program Files\\Workspaces\\MyEclipse 8.6\\PdfPrinter\\epmarugo.ttf",
						BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED), size);
			else
				return new Font(BaseFont.createFont("B:\\Program Files\\Workspaces\\MyEclipse 8.6\\PdfPrinter\\epmarugo.ttf",
						BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected Font setFontKaisho() {
		return this.setFontKaisho(0);
	}
	
	protected Font setFontKaisho(float size) {
		try {
			if (size > 0)
				return new Font(BaseFont.createFont("B:\\Program Files\\Workspaces\\MyEclipse 8.6\\PdfPrinter\\ttf30\\DFKaiSho-Md.ttf",
						BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED), size);
			else
				return new Font(BaseFont.createFont("B:\\Program Files\\Workspaces\\MyEclipse 8.6\\PdfPrinter\\ttf30\\DFKaiSho-Md.ttf",
						BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected abstract Element getPdfElement();
	
	protected Phrase getPhrase(){
		return null;
	}
	
	public String toString() {
		return this.toString();
	}
}
