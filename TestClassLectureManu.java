package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author Col
 *
 */
public class TestClassLectureManu {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream("講座案內原稿.pdf");
		PdfDocument pdfdoc = new PdfDocument(out, true );
		// title
		PdfTitle title = new PdfTitle("講座案内原稿");
		pdfdoc.add(title);
		// text block
		PdfTextBlock shopname = new PdfTextBlock("　相模大野店　　　年　　月折込");
		shopname.setFontSize(14).setUnderlined().setPosition(5, 5);
		pdfdoc.addAbsolutePosition(shopname);
//		// page number and repeatable title
		PdfElement[] _text = {title, shopname};
		pdfdoc.headerOrPagenumber(_text, new float[][]{{50, 8.65f}, {16.625f, 7.8f}}, 16, 3, true);
		// table
		for (int j=0; j<3; ++j) {
		PdfTable table = new PdfTable(10, new float[]{0.5f, 4, 3, 0.8f, 4.3f, 2.5f, 2.7f, 1.4f, 1.6f, 1.7f});
		table.addCell("　", 12f, 5, false)
				.addCell("講座名", 1, 12f, 5)
				.addCell("講師名(肩書)", 1, 12f, 0, 2, 5)
				.addCell("内　容", 1, 12f, 5)
				.addCell("曜日・時間 ", 1, 12f, 5)
				.addCell("受講料・教材費", 1, 12f, 5)
				.addCell("体験日", 1, 12f, 0, 2, 5)
				.addCell("導　入", 1, 12f, 5);
//		tophead.setHeaderFontSize(8).setWidthPercentage(100f).setMarginTop(50);
//		pdfdoc.add(tophead);
		table.setHeader(1);
		for (int i=0; i<4; ++i) {
			//String text, float height, float leading, float fontsize, int fontstyle, int alignment, int noborderside, int hasborderside, int rowspan, int colspan
			table.addCell("手芸・工芸コース", 110f, 0, 8f, 0, 2, 0, 0, 4, 0)
				.addCell("和紙ほ是鵝口瘡", 30f, 0, 10f, 0, 4, 4, 0, 0, 0)
				.addCell("第二列上", 50f, 0, 10f, 0, 4, 4, 0, 2, 2)
				.addCell("第三列上", 85f, 0, 10f, 0, 1, 4, 0, 3, 0)
				.addCell("第四列上", 30f, 0, 10f, 0, 7, 4, 0, 0, 0)
				.addCell("第五列1", 30f, 0, 10f, 1, 7, 4, 0, 0, 0)
				.addCell("第六列上", 50f, 0, 10f, 0, 6, 4, 0, 2, 2)
				.addCell("第七列", 110f, 0, 10f, 0, 5, 0, 0, 4, 0)
				.addCell("第一列中", 55f, 0, 16f, 0, 1, 0, 2, 2, 0)
				.addCell("第四列中", 55f, 0, 10f, 0, 3, 0, 2, 2, 0)
				.addCell("第五列2", 20f, 0, 10f, 0, 3, 0, 2, 0, 0)
				.addCell("第二列中", 35f, 0, 10f, 0, 8, 0, 2, 0, 2)
				.addCell("第五列3", 35f, 0, 10f, 1, 4, 0, 2, 0, 0)
				.addCell("第六列中", 35f, 0, 10f, 0, 4, 0, 2, 0, 2)
				.addCell("第一列下", 25, 0, 8f, 0, 9, 3, 0, 0, 0)
				.addCell("第二列下左", 25, 0, 6f, 0, 9, 0, 4, 0, 0)
				.addCell("1357", 25, 0, 7f, 0, 9, 0, 4, 0, 0)
				.addCell("第三列下", 25f, 0, 10f, 0, 5, 3, 0, 0, 0)
				.addCell("第四列下", 25, 0, 8f, 0, 2, 3, 0, 0, 0)
				.addCell("第五列4", 25, 0, 10f, 0, 3, 3, 0, 0, 0)
				.addCell("第六列下左", 25, 0, 8f, 0, 5, 0, 4, 0, 0)
				.addCell("第六列下右", 25, 0, 8f, 0, 6, 0, 4, 0, 0);
		}
		// to fix table top margin problem start in page 3
		float margin;
		if (j ==0)
			margin = 48.2f;
		else 
			margin = 16;
		table.setHeaderFontSize(8).setWidthPercentage(100f).setMarginTop(margin);
		pdfdoc.add(table);
		if (j != 2)
			pdfdoc.newPage();
		}
		pdfdoc.saveToLocal();
	}

}
