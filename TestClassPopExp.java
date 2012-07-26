/**
 * 
 */
package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 体験ポップ B5
 * @author peike dai
 *
 */
public class TestClassPopExp {

	/**
	 * @param args
	 */
	private static String newString(String text) {
		char[] stringchar = text.toCharArray();
		String finalstring = "";
		if (text.length() >= 5) {
			for (char c : stringchar)
				finalstring += c + " ";
		} else 
			for (char c : stringchar)
				finalstring += c + "　";
		return finalstring;
			
	}
	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream("体験ポップ.pdf");
		PdfDocument pdfdoc = new PdfDocument(out, new float[]{728f, 515.7f});
		// title
		String testtitle = newString("日本画(土)");
		PdfTextBlock title = new PdfTextBlock(testtitle);
		title.setFontSize(100).setBold().adaptPaper(728f).setPosition(50, 35);
		pdfdoc.addAbsolutePosition(title);
		// times and dates
		PdfTextBlock timedates = new PdfTextBlock("1月28日　(土)　13:00～15:30");
		timedates.setFontSize(40).setPosition(50, 55);
		pdfdoc.addAbsolutePosition(timedates);
		//体験費，教材費
		PdfTextBlock cost = new PdfTextBlock("体験費￥5,555　教材費￥535");
		cost.setFontSize(20).setPosition(80, 80);
		pdfdoc.addAbsolutePosition(cost);
		// top title
		PdfTextBlock toptitle = new PdfTextBlock("一日体験講習会");
		toptitle.setFontSize(18).setItalic().setPosition(90, 5);
		pdfdoc.addAbsolutePosition(toptitle);
		// top separator
		PdfTable blocktop = new PdfTable(1);
		blocktop.addCell("　", 1, 2f).setWidthPercentage(90).setPosition(50, 8.9f);
		pdfdoc.addAbsolutePosition(blocktop);
		
		pdfdoc.saveToLocal();
	}
}
