/**
 * 
 */
package pdfPrinter;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * PdfTable.java
 * 
 * @author Peike Dai 
 * Date: 2012/06/07 
 * Platform: MyEclipse Java 8.6， Windows7 Ultimate 
 * 
 * Version
 * =======
 * 1.0 - 2012/06/07	
 * 	first release
 * 1.1 - 2012/06/29
 * 	加入表尾、表格頁統計、表格字體自適應單元格寬度
 * 
 * Module Operation 
 * ================ 
 * 提供一个模块，用来向PDF内添加表格。
 * 
 * public interface 
 * ================
 * PdfTable();								// constructor
 * PdfTable(int colnum)						// constructor from the column number
 * addHeader(List<String> column)			// add the table header from a list of string
 * addHeader(List<String> column, 
 * 			float[] cellpercentage)			// from a list of string and each width percentage
 * addHeader(List<String> column, float headerheight,
 * 			float[] cellpercentage, boolean isJapanese)	 
 * 											// from a string list, height, each width percentage, 
 * 												and if the text is Japanese
 * addHeaderFooter(List<String> header, List<String> footer,
			String footersign, int[] footercolumn, int[] alignment, boolean multiline)
											// initiate a table with one header row and one footer 
 *											// row. footersign to fill the leftmost cell such as
 *											// "Statistics". footercolumn to indicate which columns
 *											// have footer, 0: no; other: yes. multiline indicates whether
 *											// sentence wrap is allowed in header
 * addContent(List<Object> contents, String[] properties,
 *			int[] pagestatcol, String pagestatsign, int rowsperpage, float height)
 *											//
 * addContent(List<Object> contents, String[] properties)
 * 											// add table content from a list of Object and the 
 * 												name of each property
 * addCell(String content)					// add one cell with a string
 * addCell(String content, Font font, float height, int pos)
 * 											// with string, text font, height, and position
 * 											// position:
 *							 		    		-------
 *							 					|1-2-3|
 * 												|4-5-6|
 * 												|7-8-9|
 * 												-------
 * setColumnAlignment(int[] alignments); 	// set the content text alignment in each column
 * 											// must be called before adding content. 
 * 											// the alignment value is the same as "pos" above
 * setHeaderFontSize(float fixedFontSize);	// set the font size of header. 
 * 
 * 	chain method available:
 * 	-----------------------
 *  setWidthPercentage(float widthPercentage)	
 *  										// set the total width of the table with percentage
 * 	setAlignment(int alignment)				// set the alignment; LEFT,CENTER,RIGHT: -1,0,1
 * 	setMarginTop(float margin)				// set the top margin of the table
 * 	setColumnPercentage(float[] colpercent) // set the column width with percentage
 */
public class PdfTable extends PdfElement {
	private PdfPTable pdftable;
	private int colnumber;
	private Font headerfont;
	private Font contentfont; // initiate in constructor
	private Phrase phrase;
	private float widthpercentage;
	private PdfPCell pdfcell;
	private int[] columnalignments;
	private float[] columnpercent;
	private boolean[] adaptivecolumn;

	PdfTable() {
		this(0, null, 0, 0);
	}

	PdfTable(int colnum) {
		this(colnum, null);
	}
	/**
	 * Modified date:2012/07/05	add headerfont initiation
	 * Modified date:2012/07/09	simplified it with final method
	 * @param colnum
	 *            column numbers
	 * @param colpercent
	 *            set null if want to divide by average
	 */
	PdfTable(int colnum, float[] colpercent) {
		this(colnum, 0, colpercent);
	}
	
	/**
	 * Added date:2012/06/28
	 * Modified date:2012/07/09	simplified it with final method
	 * @param colnum
	 *            column numbers
	 * @param absolutewidth
	 *            set the absolute table width
	 * @param colpercent
	 * 			  set null if want to divide by average
	 */
	PdfTable(int colnum, float absolutewidth, float[] colpercent){
		this(colnum, colpercent, absolutewidth, 0);
	}
	
	/**
	 * Added date: 2012/06/27
	 * Modified date:2012/07/09	simplified it with final method
	 * Modified date: 2012/07/05 add Gothic font option
	 * add ability to change font style
	 * @param colnum
	 * @param fontstyle
	 */	
	public PdfTable(int colnum, int fontstyle) {
		this(colnum, null, 0f, fontstyle);
	}
	
	/**
	 * Added date: 2012/07/09
	 * used for 2-1-3
	 * @param absolutewidth
	 */
	public PdfTable(int colnum, float absolutewidth) {
		this(colnum, null, absolutewidth, 0);
	}
	/**
	 * Added date: 2012/07/09
	 * Final constructor
	 * @param colnum
	 * @param colpercent
	 * @param absolutewidth
	 * @param fontstyle
	 */
	public PdfTable(int colnum, float[] colpercent, float absolutewidth, int fontstyle) {
		if (colnum > 0) {
			this.colnumber = colnum;
			this.pdftable = new PdfPTable(this.colnumber);
		}
		if (absolutewidth > 0f) {
			pdftable.setTotalWidth(absolutewidth);
			pdftable.setLockedWidth(true);
		}
		switch (fontstyle) {
		case 1:
			contentfont = this.setFontMin();
			break;
		case 2:
			contentfont = this.setFontMaruGo();
			break;
		case 3:
			contentfont = this.setFontKaisho();
			break;
		default:
			contentfont = new Font(baseFontJp);
			break;
		}
		this.headerfont = new Font(baseFontJp);
		if (colpercent != null && colpercent.length == colnum) {
			this.columnpercent = colpercent;
			this.setColumnPercentage(this.columnpercent);
		}
	}
	
	/**
	 * add a column as the header of a table, default header height is 16
	 * 
	 * @param column
	 * @throws DocumentException
	 */
	public void addHeader(List<String> column) {
		if (this.columnpercent == null
				|| this.columnpercent.length != this.colnumber) {
			float[] cellwidths = new float[column.size()];
			for (int i = 0; i < column.size(); ++i) {
				cellwidths[i] = 100f / column.size();
			}
			addHeader(column, cellwidths);
		} else 
			addHeader(column, this.columnpercent);
		
	}

	/**
	 * add the header of a table. the position of text in each column is "5";
	 * @param column
	 * @param headerheight
	 * 					to be configured
	 * @param cellpercentage
	 * @param isJapanese
	 * @throws DocumentException
	 */
	public void addHeader(List<String> column, float[] cellpercentage) {
		addHeader(column, cellpercentage, 0);
	}
	
	public void addHeader(List<String> column, float height) {
		addHeader(column, this.columnpercent, height);
	}
	
	public void addHeader(List<String> column, float height, float leading) {
		addHeader(column, this.columnpercent, height, leading);
	}
	public void addHeader(List<String> column, float[] cellpercentage, float height) {
		addHeader(column, cellpercentage, height, 0);
	}
	
	/**
	 * Added date: 2012/07/09
	 * Final method
	 * @param column
	 * @param cellpercentage
	 * @param height
	 * @param leading
	 */
	public void addHeader(List<String> column, float[] cellpercentage, float height, float leading) {
		if (column.size() == 0)
			return;
		if (this.colnumber == 0 || this.colnumber != column.size())
			this.colnumber = column.size();
		if (pdftable == null)
			pdftable = new PdfPTable(this.colnumber);

		for (String col : column) {
			this.addCell(col, headerfont, 5, height, leading);
		}

		if (cellpercentage != null && cellpercentage.length == this.colnumber)
			this.setColumnPercentage(cellpercentage);
		// pdftable.setHeaderRows(1);
		pdftable.setHeaderRows(1);
		pdftable.setSpacingBefore(10f);
	}
	
	public PdfTable setFooter(int footerrownumber) {
		pdftable.setHeaderRows(footerrownumber + 1);
		pdftable.setFooterRows(footerrownumber);
		return this;
	}
	
	/**
	 * added date: 2012/07/19
	 * used for the last big boss
	 * @param rows
	 * @return
	 */
	public PdfTable setHeader(int headerrownumber) {
		pdftable.setHeaderRows(headerrownumber);
		return this;
	}
	/**
	 * Added date:2012/06/29 加入表尾栏
	 * 
	 * @param header
	 * @param footer
	 * @param footersign
	 * 				string before the footer row when it is total statistics, usually "合計"
	 * @param footercolumn
	 * 				set which columns have footer, 0: no footer; other: has footer
	 * @param alignment
	 * 				alignment for each column in footer row
	 * @param multiline
	 * 				set if wrap is allowed in header; false: height is fixed 16;
	 */
	public void addHeaderFooter(List<String> header, List<String> footer,
			String footersign, int[] footercolumn, int[] alignment, boolean multiline) {
		if (this.colnumber == 0 || this.colnumber != header.size())
			this.colnumber = header.size();
		headerfont = new Font(baseFontJp);
		if (pdftable == null)
			pdftable = new PdfPTable(this.colnumber);
		float height;
		if (multiline) 
			height = 0;
		else
			height = 16;
		for (String h : header) {
			this.addCell(h, height, 5, headerfont);
		}
		// add footer in specific column
		// 確定“合計”在哪一列
		int statstringcol = -1;
		for (int i = 0; i < footercolumn.length; ++i) {
			if (footercolumn[i] != 0) {
				statstringcol = i - 1;
				break;
			}
		}
		this.columnalignments = alignment;
		for (int i = 0; i < footercolumn.length; ++i) {
			if (i == statstringcol && footersign != "") {
				addCell(footersign, contentfont, 5);
				continue;
			} 
			if (footercolumn[i] == 0)
				addCell("", false, headerfont, 5);
			else
				addCell(footer.get(i), contentfont, columnalignments[i], 16, 0);
		}
		this.setColumnPercentage(this.columnpercent);
		pdftable.setHeaderRows(2);
		pdftable.setFooterRows(1);
	}


	/**
	 * Add the contents of a table. Before adding content, you must add
	 * table header.
	 * @param contents
	 */
	public void addContent(List<Object> contents, String[] properties,
			boolean isJapanese) {
		if (contents == null || contents.size() == 0) {
			this.emptyRow();
			return;
		}
		try {
			Method[] methods = new Method[properties.length];
			for (int index = 0; index < properties.length; index++) {
				methods[index] = (new PropertyDescriptor(properties[index],
						contents.get(0).getClass())).getReadMethod();
			}

			// if (isJapanese) {
			// contentfont = new Font(baseFontJp);
			// }
			for (int i = 0; i < contents.size(); i++) {
				for (int j = 0; j < properties.length; j++) {
					Object obj = methods[j].invoke(contents.get(i));
					if (obj == null) {
						this.addCell("", contentfont, 1);
					} else {
						if (this.columnalignments == null)
							this.addCell(obj.toString());
						else
							this.addCell(obj.toString(), contentfont, this.columnalignments[j]);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// contentfont.setSize(10f);
	}
	
	

	/**
	 * Added date:2012/06/29 
	 * used for table with statistics per page
	 * @param contents
	 * @param properties
	 * @param pagestatcol columns that need to be calculated sum
	 * @param pagestatsign string used before the page statistics row, usually "小計". "" if don't need
	 * @param rowsperpage rows per page
	 * @param height row height of table contents. set 0 if don't want them to be fixed
	 */
	public void addContent(List<Object> contents, String[] properties,
			int[] pagestatcol, String pagestatsign, int rowsperpage, float height) {
		if (contents == null || contents.size() == 0){
			this.emptyRow();
			return;			
		}			
		int currentRow = 1, statstringcol = -1;
		int[] pagestats = new int[properties.length];
		for (int i=0; i<pagestatcol.length; ++i) {
			if (pagestatcol[i] != 0) {
				statstringcol = i - 1;
				break;
			}
		}
		try {
			Method[] methods = new Method[properties.length];
			for (int index = 0; index < properties.length; index++) {
				methods[index] = (new PropertyDescriptor(properties[index],
						contents.get(0).getClass())).getReadMethod();
			}

			for (int i = 0; i < contents.size(); i++) {
				if (currentRow == rowsperpage){
					currentRow = 1;					
					// add page stats line
					for (int j=0; j<properties.length; ++j){
						if (j == statstringcol && pagestatsign != ""){
							addCell(pagestatsign, contentfont, 5);
							continue;
						}
						if (pagestatcol[j] == 0)
							addCell("", false, headerfont, 5);
						else
							addCell(Integer.toString(pagestats[j]), contentfont, columnalignments[j], height, 0);
					}
					for (int j=0; j<pagestats.length; ++j)
						pagestats[j] = 0;
				}
				for (int j = 0; j < properties.length; j++) {					
					Object obj = methods[j].invoke(contents.get(i));
					if (obj == null) {
						this.addCell("", contentfont, 1);
					} else {
						if (pagestatcol[j] != 0)
							pagestats[j] += Integer.parseInt(obj.toString());
						if (this.columnalignments == null) {
							this.addCell(obj.toString());
						} else { 
							if (pagestatcol[j] != 0)
								pagestats[j] += Integer.parseInt(obj.toString());
							this.addCell(obj.toString(), contentfont, this.columnalignments[j]);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Added date: 2012/07/04 for monthly report 2
	 * used for adding contents to a table by column. no addHeader is needed
	 * first row will be aligned center
	 * @param contents
	 * @param properties
	 */
	public void addContent(List<Object> contents, String[] properties) {
		if (contents == null || contents.size() == 0){
			this.emptyRow();
			return;
		}			
			
		try {
			Method[] methods = new Method[properties.length];
			for (int index = 0; index < properties.length; index++) {
				methods[index] = (new PropertyDescriptor(properties[index],
						contents.get(0).getClass())).getReadMethod();
			}

			for (int i = 0; i < properties.length; i++) {
				for (int j = 0; j < contents.size(); j++) {
					Object obj = methods[i].invoke(contents.get(j));
					if (obj == null) {
						this.addCell("", contentfont, 1);
					} else {
						if (this.columnalignments == null)
							this.addCell(obj.toString());
						else if (i == 0)
							this.addCell(obj.toString(), contentfont, 5);
						else
							this.addCell(obj.toString(), contentfont, this.columnalignments[j]);
					}
				}
			}
			/**
			 * after all table contents are added, add a stat row
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A row to indicate the table is empty
	 */
	private void emptyRow(){
		
		for (int i=0; i<this.colnumber; ++i){
			this.addCell("***", contentfont, 2);
		}
	}
	
	/**
	 * Set font size of textblock, default value is 12
	 * 
	 * @param fixedFontSize
	 */
	public PdfTable setHeaderFontSize(float fixedFontSize) {
		headerfont.setSize(fixedFontSize);
		return this;
	}
	
	public PdfTable setContentFontSize(float fixedFontSize) {
		contentfont.setSize(fixedFontSize);
		return this;
	}
	
	/**
	 * Table header and contents together in one parameter
	 * @param table
	 */
	public void addTable(List<Object> table){
		
	}
	
	private void addCell(String content){
		this.addCell(content, contentfont, 1);		
	}
	
	/**
	 * 
	 * @param content
	 * @param font
	 * @param height
	 * @param pos
	 * @param -------
	 * @param |1-2-3|
	 * @param |4-5-6|
	 * @param |7-8-9|
	 * @param -------
	 */
	private void addCell(String content, Font font, int pos) {
		addCell(content, true, font, pos);
	}
	
	/**
	 * Added date:2012/07/02
	 * @param text
	 * @param font
	 * @param pos
	 * @param height
	 * @param leading
	 */
	private void addCell(String text, Font font, int pos, float height, float leading){
		addCell(text, height, pos, -1, true, font, leading);
	}
	
	/**
	 * Used for text block
	 * @param text
	 * @param leading
	 * @return
	 */
	public PdfTable addCell(String text,  float leading) {
		phrase = new Phrase();
		this.setContentFontSize(10);
		phrase.setFont(contentfont);		
		phrase.add(text);	
		
		pdfcell = new PdfPCell(phrase);
		if (leading > 0)
			pdfcell.setLeading(leading, 0);
		pdfcell.setBorderWidth(0);
		pdfcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		pdftable.addCell(pdfcell);
		return this;
	}
	
	/**
	 * Modified date: 2012/07/05
	 * mainly used for table content
	 * @param text
	 * @param height
	 * @param alignment
	 * @param noborderside
	 * @param -3-
	 * @param 1-2
	 * @param -4-
	 * @return
	 */
	public PdfTable addCell(String text, float height, int alignment, int noborderside){
		this.setContentFontSize(10f);
		this.addCell(text, height, alignment, 0, true, noborderside, 0, contentfont, 0, 0, 0);
		return this;
	}
	
	/**
	 * used to add content
	 * @param text
	 * @param height
	 * @param alignment
	 * @return
	 */
	public PdfTable addCell(String text, float height, int alignment) {
		addCell(text, height, alignment, 0);
		return this;
	}
	/**
	 * add one cell to table; default font style is japanese
	 * used to add header
	 * @param text
	 * @param bgcolor
	 *            0: White; 1: Grey
	 * @return
	 */
	public PdfTable addCell(String text, int bgcolor, float height) {
		this.setContentFontSize(10f);
		addCell(text, height, 5, bgcolor, true, contentfont, 0);
		return this;
	}
	
	/**
	 * Added date: 2012/07/18
	 * for the last pdf in class
	 * @param text
	 * @param bgcolor
	 * @param height
	 * @param alignment
	 * @return
	 */
	public PdfTable addCell(String text, int bgcolor, float height, int alignment) {
		this.addCell(text, bgcolor, height, 0, 0, alignment);
		return this;
	}
	
	/**
	 * Added date: 2012/07/13
	 * used for seperator line in 講座ポップ3	
	 * @param text
	 * @param bgcolor
	 * @param height
	 * @param hasBorder
	 * @return
	 */
	public PdfTable addCell(String text, int bgcolor, float height, boolean hasBorder) {
		addCell(text, height, 0, bgcolor, hasBorder,  contentfont,  0);
		return this;
	}

	/**
	 * Added date:2012/06/29
	 * add ability to modify header height
	 * @param text
	 * @param height
	 * @param alignment
	 * @param font
	 * @return
	 */
	public PdfTable addCell(String text, float height, int alignment, Font font) {
		addCell(text, height, alignment, -1, true, font, 0);
		return this;
	}
	
	
	/**
	 * Added date: 2012/06/29
	 * @param content
	 * @param hasBorder
	 * @param font
	 * @param pos
	 */
	private PdfTable addCell(String content, boolean hasBorder, Font font, int pos) {
		addCell(content, 0, pos, -1, hasBorder, font, 0);
		return this;
	}
	
	/**
	 * Added date:2012/06/29
	 * Final methods
	 * @param text
	 * @param height
	 * @param alignment
	 * @param bgcolor
	 * @param hasBorder
	 * @param font
	 */
	private void addCell(String text, float height, int alignment, int bgcolor, boolean hasBorder, Font font, float leading) {
		this.addCell(text, height, alignment, bgcolor, hasBorder, 0, 0, font, leading, 0, 0);
	}
	
	/**
	 * 2012/07/09
	 * For 继续该当者、daily report
	 * @param text
	 * @param height
	 * @param alignment
	 * @param hasBorder
	 * @return
	 */
	public PdfTable addCell(String text, float height, int alignment, boolean hasBorder) {
		this.addCell(text, height, alignment, 0, hasBorder, 0, 0, contentfont, 0, 0, 0);
		return this;
	}
	
	public PdfTable addCell(String text, float height, int alignment, boolean hasBorder, float fontsize) {
		addCell(text, height, hasBorder, 0, 0, fontsize, alignment);
		return this;
	}

	/**
	 * Added date:2012/07/05 designed for 會員調查報告書, mainly used for header
	 * @param text
	 * @param bgcolor
	 * @param height
	 * @param rowspan
	 * @return
	 */
	public PdfTable addCell(String text, int bgcolor, float height, int rowspan, int colspan, int alignment){
		this.addCell(text, height, 5, bgcolor, true, 0, 0, headerfont, 0, rowspan, colspan);
		return this;
	}
	
	
	/**
	 * Added date:2012/07/05 for 會員調查報告書, mainly used for content
	 * @param text
	 * @param bgcolor
	 * @param height
	 * @param noborderside
	 * @param rowspan
	 * @param colspan
	 * @param alignment
	 * @return
	 */
	public PdfTable addCell(String text, int bgcolor, float height, int noborderside, int rowspan, int colspan, int alignment){
		this.addCell(text, height, alignment, bgcolor, true, noborderside, 0, this.contentfont, 0, rowspan, colspan);
		return this;
	}
	
	/**
	 * Added date: 2012/07/05
	 * @param text
	 * @param height
	 * @param hasBorder
	 * @param rowspan
	 * @param colspan
	 * @return
	 */
	public PdfTable addCell(String text, float height, boolean hasBorder, int rowspan, int colspan) {
		this.addCell(text, height, 0, 0, hasBorder, 0, 0, contentfont, 0, rowspan, colspan);
		return this;
	}
	
	/**
	 * Added date:2012/07/11
	 * used for adding very small character in attend list 2-2-1
	 * @param text
	 * @param height
	 * @param hasBorder
	 * @param rowspan
	 * @param colspan
	 * @param fontsize
	 * @param alignment
	 * @return
	 */
	public PdfTable addCell(String text, float height, boolean hasBorder, int rowspan, int colspan, float fontsize, int alignment) {
		if (fontsize > 0) {
			Font font = new Font(baseFontJp, fontsize);
			this.addCell(text, height, alignment, 0, hasBorder, 0, 0, font, 0, rowspan, colspan);
		} else
			this.addCell(text, height, alignment, 0, hasBorder, 0, 0, contentfont, 0, rowspan, colspan);
		return this;
	}
	
	/**
	 * Added date: 2012/07/18
	 * for 講座案內手稿
	 * @param text
	 * @param height
	 * @param hasBorder
	 * @param rowspan
	 * @param colspan
	 * @param fontsize
	 * @param alignment
	 * @param fontstyle
	 * @return
	 */
	public PdfTable addCell(String text, float height, float leading, boolean hasBorder, int rowspan, int colspan, float fontsize, int alignment, int fontstyle) {
		if (fontstyle > 0) {
			Font font = null;
			switch (fontstyle) {
			case 1:
				font = this.setFontMin(fontsize);
				break;
			case 2:
				font = this.setFontMaruGo(fontsize);
				break;
			case 0:
			default:
				break;
			}
			this.addCell(text, height, alignment, 0, hasBorder, 0, 0, font, leading, rowspan, colspan);
		} else 
			this.addCell(text, height, alignment, 0, hasBorder, 0, 0, contentfont, leading, rowspan, colspan);
		
		return this;
	}
	
	/**
	 * Added date: 2012/07/12
	 * @param text
	 * @param height
	 * @param fontsize
	 * @param alignment
	 * @param rowspan
	 * @param colspan
	 * @return
	 */
	public PdfTable addCell(String text, float height, float fontsize, int alignment, int rowspan, int colspan) {
		this.addCell(text, height, 0, fontsize, alignment, 0, rowspan, colspan);
		return this;
	}
	
	/**
	 * Added date: 2012/07/12
	 * @param text
	 * @param height
	 * @param leading
	 * @param fontsize
	 * @param alignment
	 * @param rowspan
	 * @param colspan
	 * @return
	 */
	public PdfTable addCell(String text, float height, float leading, float fontsize, int alignment, int noborderside, int rowspan, int colspan) {
		this.addCell(text, height, leading, fontsize, 0, alignment, noborderside, rowspan, colspan);
		return this;
	}
	
	public PdfTable addCell(String text, float height, float leading, float fontsize, int fontstyle, int alignment, int noborderside, int rowspan, int colspan) {
		if (fontstyle > 0) {
			Font font = null;
			switch (fontstyle) {
			case 1:
				font = this.setFontMin(fontsize);
				break;
			case 2:
				font = this.setFontMaruGo(fontsize);
				break;
			case 0:
			default:
				break;
			}
			this.addCell(text, height, alignment, 0, true, noborderside, 0, font, leading, rowspan, colspan);
		} else if (fontstyle <= 0f && fontsize > 0) {
			Font font = new Font(baseFontJp, fontsize);
			this.addCell(text, height, alignment, 0, true, noborderside, 0, font, leading, rowspan, colspan);
		} else
			this.addCell(text, height, alignment, 0, true, noborderside, 0, contentfont, leading, rowspan, colspan);
		
		return this;
	}
	
	/**
	 * add dated: 2012/07/18
	 * for the last big boss pdf
	 * This has nearly everything, so it is easier to maintain when all cells are added with this method
	 * More convenience for complicated table
	 * @param text
	 * @param height
	 * @param leading
	 * @param fontsize
	 * @param fontstyle
	 * @param alignment
	 * @param hasborder
	 * @param noborderside
	 * @param rowspan
	 * @param colspan
	 * @return
	 */
	public PdfTable addCell(String text, float height, float leading, float fontsize, int fontstyle, int alignment, int noborderside, int hasborderside, int rowspan, int colspan) {
		if (fontstyle > 0) {
			Font font = null;
			switch (fontstyle) {
			case 1:
				font = this.setFontMin(fontsize);
				break;
			case 2:
				font = this.setFontMaruGo(fontsize);
				break;
			case 0:
			default:
				break;
			}
			this.addCell(text, height, alignment, 0, true, noborderside, hasborderside, font, leading, rowspan, colspan);
		} else if (fontstyle <= 0f && fontsize > 0) {
			Font font = new Font(baseFontJp, fontsize);
			this.addCell(text, height, alignment, 0, true, noborderside, hasborderside, font, leading, rowspan, colspan);
		} else
			this.addCell(text, height, alignment, 0, true, noborderside, hasborderside, contentfont, leading, rowspan, colspan);
		
		return this;
	}
	
	/**
	 * Added date: 2012/07/05
	 * Final Final version of addCell
	 * @param text
	 * @param height
	 * @param alignment
	 * @param bgcolor set 0 or under if no need
	 * @param hasBorder
	 * @param noborderside set 0 or under if no need
	 * @param hasborderside set 0 if no need
	 * @param font
	 * @param leading set 0 or under if no need
	 * @param rowspan set 0 or under if no need
	 * @param colspan set 0 or under if no need
	 * @return
	 */
	private PdfTable addCell(String text, float height, int alignment, int bgcolor, boolean hasBorder, int noborderside, int hasborderside, Font font, float leading, int rowspan, int colspan) {
		phrase = new Phrase();
		phrase.setFont(font);
		phrase.add(text);
		
		pdfcell = new PdfPCell(phrase);
		if (height > 0)
			pdfcell.setFixedHeight(height);
		if (rowspan > 0) 
			pdfcell.setRowspan(rowspan);
		if (colspan > 0)
			pdfcell.setColspan(colspan);
		if (leading > 0)
			pdfcell.setLeading(leading, 0);
		switch (bgcolor) {
		case 0:
			pdfcell.setBackgroundColor(BaseColor.WHITE);
			break;
		case 1:
			pdfcell.setBackgroundColor(new GrayColor(0.9f));
			break;
		default:
			break;
		}
		if (!hasBorder){
			pdfcell.disableBorderSide(Rectangle.LEFT);
			pdfcell.disableBorderSide(Rectangle.RIGHT);
			pdfcell.disableBorderSide(Rectangle.TOP);
			pdfcell.disableBorderSide(Rectangle.BOTTOM);
		}
		
		switch (noborderside) {
		case 1:
			pdfcell.disableBorderSide(Rectangle.LEFT);
			break;
		case 2:
			pdfcell.disableBorderSide(Rectangle.RIGHT);
			break;
		case 3:
			pdfcell.disableBorderSide(Rectangle.TOP);
 			break;
		case 4:
			pdfcell.disableBorderSide(Rectangle.BOTTOM);
			break;
		default:
			break;
		}
		
		switch (hasborderside) {
		case 1:
			pdfcell.setBorder(Rectangle.NO_BORDER);
			pdfcell.setBorder(Rectangle.LEFT);
			break;
		case 2:
			pdfcell.setBorder(Rectangle.NO_BORDER);
			pdfcell.setBorder(Rectangle.RIGHT);
			break;
		case 3:
			pdfcell.setBorder(Rectangle.NO_BORDER);
			pdfcell.setBorder(Rectangle.TOP);
			break;
		case 4:
			pdfcell.setBorder(Rectangle.NO_BORDER);
			pdfcell.setBorder(Rectangle.BOTTOM);
			break;
		default:
			break;
		}

		switch (alignment) {
		case 1:
			pdfcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfcell.setVerticalAlignment(Element.ALIGN_TOP);
			break;
		case 2:
			pdfcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfcell.setVerticalAlignment(Element.ALIGN_TOP);
			break;
		case 3:
			pdfcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pdfcell.setVerticalAlignment(Element.ALIGN_TOP);
			break;
		case 4:
			pdfcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			break;
		case 5:
			pdfcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			break;
		case 6:
			pdfcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pdfcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			break;
		case 7:
			pdfcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfcell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			break;
		case 8:
			pdfcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfcell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			break;
		case 9:
			pdfcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pdfcell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			break;
		default:
			pdfcell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			pdfcell.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
			break;
		}
		pdftable.addCell(pdfcell);
		return this;
	}

	/**
	 * Default value of width percentage every table is 80%
	 * 
	 * @param widthPercentage
	 *            a value from 0 to 100 to represent width percentage
	 * @return
	 */
	public PdfTable setWidthPercentage(float widthPercentage) {
		if (widthPercentage >= 0f && widthPercentage <= 100f) {
			this.widthpercentage = widthPercentage;
			this.pdftable.setWidthPercentage(this.widthpercentage);
		} else
			this.widthpercentage = 100f;
		return this;
	}

	/**
	 * 
	 * @param alignment
	 *            -1 - left; 0 - center; 1 - right;
	 */
	public PdfTable setAlignment(int alignment) {
		switch (alignment) {
		case -1:
			pdftable.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
			break;
		case 0:
			pdftable.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			break;
		case 1:
			pdftable.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			break;
		default:
			pdftable.setHorizontalAlignment(Rectangle.ALIGN_JUSTIFIED);
		}
		return this;
	}
	
	/**
	 * set the text in each column. won't work if  alignment length differ from colnumber
	 * column alignment must be set before add content
	 * @param alignment
	 * 			
	 */
	public void setColumnAlignment(int[] alignments) {
		if (alignments.length != this.colnumber)
			return;
		this.columnalignments = alignments;
	}

	public PdfTable setMarginTop(float margin) {
		try {
			pdftable.setSpacingBefore(margin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * Set the width percentage that occupy the total width,
	 * If float size is incorrect, columns are set apart on average 
	 * as default. 
	 * @param colpercent
	 * @return
	 */
	public PdfTable setColumnPercentage(float[] colpercent) {
		try {
			if ( colpercent == null || colpercent.length != pdftable.getNumberOfColumns()) {
				colpercent = new float[pdftable.getNumberOfColumns()];
				for (int i = 0; i < pdftable.getNumberOfColumns(); ++i) {
					colpercent[i] = 100 / pdftable.getNumberOfColumns();
				}
			}
			pdftable.setWidths(colpercent);

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return this;
	}

	
	protected boolean setPosition(PdfContentByte canvas, float pagewidth,
			float pageheight) {
		if (xPercentage > 100 || xPercentage < 0 || yPercentage > 100
				|| yPercentage < 0)
			return false;
		float tablewidth = pagewidth * this.pdftable.getWidthPercentage() / 100;
		this.pdftable.setTotalWidth(tablewidth);
		float xPos = (pagewidth - tablewidth) * this.xPercentage / 100;
		float yPos = pageheight * ( 1 - this.yPercentage / 100);
		if (xPos >= 0 && yPos >= 0 ) {
			this.pdftable.writeSelectedRows(0, -1, xPos, yPos, canvas);			
		} 
		return true;
	}
	
	
	public PdfTable isBold(boolean val){
		if (val){
			this.contentfont.setStyle(Font.BOLD);			
		}
		return this;
	}
	
	/**
	 * check if the text length extends the cell width. if yes, resize it to half of its origin size 
	 * @param content
	 * @param cellwidth
	 * @param font
	 * @return
	 */
	private Font getNewFont(String content, float cellwidth, Font font) {
		BaseFont bf_font = font.getCalculatedBaseFont(false);
		float fontsize = font.getSize();
		if (fontsize == -1f) 
			fontsize = 12f;
		float width_string = bf_font.getWidthPointKerned(content, fontsize);
		if (cellwidth <= width_string){
			Font newfont = new Font(baseFontJp);
			newfont.setSize(fontsize / 2);
			return newfont;
		} else 
			return font;
	}

	/**
	 * get the width of column j from a fixed-width table and j's percentage 
	 * @param totalWidth
	 * @param colpct float array 
	 * @param j
	 * @return
	 */
	private float getCellWidth(float totalWidth, float[] colpct, int j) {
		float sum = 0;
		for (float f : colpct)
			sum += f;
		float percent = colpct[j] / sum;
		float cellwidth = totalWidth * percent;
		return cellwidth;
	}
	
//	private Font 
	@Override
	protected Element getPdfElement() {
		return pdftable;
	}	
}
