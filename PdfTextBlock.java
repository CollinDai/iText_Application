/**
 * 
 */
package pdfPrinter;

import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * PdfTextBlock.java
 * 
 * @author Peike Dai
 * Date: 2012/06/07
 * Platform: MyEclipse Java 8.6， Windows7 Ultimate
 * Version: 1.0
 * 
 * Module Operation
 * ================
 * 提供一个用来向PDF添加文字块的模块，用户可以通过设置文字块位置、宽度、
 * 字体样式、文字大小、行间距等来自定义打印入PDF内的文字段落。
 *	 Default Value
 * 	--------------
 *	默认字体为Japanese，可用setFontStyle修改
 *	默认添加位置为上一个Element的下方。可设置文字块的位置到任何地方，
 *	通过输入百分比值，来定位文字块左上角位于x轴和y轴的位置。
 *	(0, 0)将文字块定位于文档的左上角。
 *	设置文字块绝对位置时需输入其宽度，用文字块占页宽百分比来设置。
 *
 * 
 * Public Interface
 * ================
 * PdfTextBlock(String txtblk);	// Promotion Constructor
 *  PdfTextBlock(String text);
 *  PdfTextBlock(String text, int alignment);
 *  PdfTextBlock(String text, float leading);
 *  PdfTextBlock(String text, boolean isJapanese);
 *  PdfTextBlock(String txtblk, boolean isJapanese, float widthPercentage, 
 *  				float leading, int alignment);
 *  
 *  chain method available:
 *  -----------------------
 *  setAlignment(int alignment);				// set the alignment when it's not 
 *  											// been set in an absolute position
 *  isUnderlined(boolean underline);			// set if the text is underlined
 *  setFontSize(float fixedFontSize);			// set the font size of this text
 *  setWidthPercentage(float widthPercentage);	// set the width this text occupied 
 *  											// the whole page width
 *  
 *  setPosition(float xpercent, float ypercent);// set the position of this text.
 * 
 */
public class PdfTextBlock extends PdfElement {
	private Paragraph textblock;
	private PdfPTable texttable;
	private PdfPCell textcell;
	private float widthpercentage;
	private Font font;

	/**
	 * Font is set Japanese as default
	 */
	public PdfTextBlock() {
		this("");
	}
	
	public PdfTextBlock(String txtblk) {
		this(txtblk, 0);
	}
	
	public PdfTextBlock(String txtblk, int alignment) {
		this(txtblk, alignment, 0);
	}

	public PdfTextBlock(String txtblk, float leading) {
		this(txtblk, leading, 0, 0);
	}
	

	public PdfTextBlock(String txtblk, float leading, int alignment) {
		this(txtblk, leading, alignment, 0);
	}
	
	public PdfTextBlock(String txtblk, int alignment, int fontstyle) {
		this(txtblk, 16f, alignment, fontstyle);
	}
	
	

	/**
	 * Add date: 2012/06/27
	 * Add ability to change font style;0: Gothic; 1:Min
	 * modified: 2012/07/10
	 * delete parameter: isjapanese
	 * @param txtblk
	 * @param leading
	 * @param alignment
	 * @param fontstyle
	 */
	public PdfTextBlock(String txtblk, float leading, int alignment, int fontstyle) {
		textblock = new Paragraph();
		texttable = new PdfPTable(1);
		switch (fontstyle) {
		default:
			font = new Font(baseFontJp);
			break;
		case 1:
			font = this.setFontMin();
			break;
		case 2:
			font = this.setFontMaruGo();
			break;
		case 3:
			font = this.setFontKaisho();
			break;
		}
		textblock.setFont(font);
		textblock.add(txtblk);
		this.textcell = new PdfPCell(this.textblock);
		this.textcell.setBorder(Rectangle.NO_BORDER);
		switch (alignment) {
		case -1:
			textcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			break;
		case 0:
			textcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			break;
		case 1:
			textcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			break;
		default:
			textcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		}
		this.textcell.setLeading(leading, 0f);
		texttable.addCell(this.textcell);
	}
	/**
	 * Set the table width percentage that the table will occupy in the page. If
	 * input is invalid, it's set 80%
	 * 
	 * @param widthPercentage
	 * @return
	 */
	public PdfTextBlock setWidthPercentage(float widthPercentage) {
		if (widthPercentage >= 0f && widthPercentage <= 100f) {
			this.widthpercentage = widthPercentage;			
			texttable.setWidthPercentage(this.widthpercentage);
		} else
			this.widthpercentage = 80f;
		return this;
	}

	

	/**
	 * Set the leading (line space) of textblock, default value is 16
	 * 
	 * @param fixedLeading
	 */
	public PdfTextBlock setLeading(float fixedLeading) {
		this.textcell.setLeading(fixedLeading, 0f);
		return this;
	}

	/**
	 * Set font size of textblock, default value is 12
	 * 
	 * @param fixedFontSize
	 */
	public PdfTextBlock setFontSize(float fixedFontSize) {
		font.setSize(fixedFontSize);
		this.textblock.setFont(font);
		return this;
	}
	
	/**
	 * Added date:2012/07/13
	 * Used for 講座ポップ
	 * @param pagewidth
	 * @return
	 */
	public PdfTextBlock adaptPaper(float pagewidth) {
		while (getTextWidth(textblock) >= (pagewidth - 72)) {
			font.setSize(font.getSize() - 1);
		}
		return this;
	}
	
	public PdfTextBlock setBold() {
		font.setStyle(Font.BOLD);
		this.textblock.setFont(font);
		return this;
	}
	
	public PdfTextBlock setItalic() {
		font.setStyle(Font.ITALIC);
		this.textblock.setFont(font);
		return this;
	}
//	
//	public PdfTextBlock isBold(boolean val){
//		if (val) {
//			font.setStyle(Font.BOLD);
//			this.textblock.setFont(font);
//		}
//		return this;
//	}

	/**
	 * Set the absolute position of text block with percentage Once you set the
	 * absolute position of text block, you need to use "Document.addAbsolute"
	 * method. "Document.add" will add this block right below the last added
	 * element
	 * 
	 * @param xPos
	 *            the percentage of X
	 * @param yPos
	 *            the percentage of Y
	 * @return
	 */
	protected boolean setPosition(PdfContentByte canvas, float pagewidth,
			float pageheight) {
		if (xPercentage > 100 || xPercentage < 0 || yPercentage > 100
				|| yPercentage < 0)
			return false;

		float textwidth = this.getTextWidth(textblock);
		this.texttable.setTotalWidth(textwidth + 6f);
		float xPos = (pagewidth - textwidth) * this.xPercentage / 100;
		float yPos = pageheight
				- ((pageheight - this.texttable.getTotalHeight())
				* this.yPercentage / 100);		
		if (xPos >= 0 && yPos >= 0 ) {
			this.texttable.writeSelectedRows(0, -1, xPos, yPos, canvas);			
		} 
		return true;
	}
	
	
	/**
	 * set if this text is underlined
	 * @param underline
	 * @return
	 */
	public PdfTextBlock setUnderlined(){
		this.textblock.getFont().setStyle(Font.UNDERLINE);
		return this;
	}

	public PdfTextBlock setMarginTop(float margin) {
		try {
			texttable.setSpacingBefore(margin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public void addText(String text) {
		this.textblock.add(text);
	}
	
	@Override
	protected Element getPdfElement() {
		return texttable;
	}

	public float getHeight() {
		return texttable.getTotalHeight();
	}
	
	protected Phrase getPhrase(){
		return textblock;
	}
	
	private float getTextWidth(Paragraph text){
		BaseFont bf_font = font.getCalculatedBaseFont(false);
		String content = text.getContent();
		float fontsize = text.getFont().getSize();
		if (fontsize == -1f) {
			fontsize = 12f;
		}
		float width_string = bf_font.getWidthPointKerned(content, fontsize);
		return width_string;
	}

	public String toString() {
		return textblock.toString();
	}
	

}
