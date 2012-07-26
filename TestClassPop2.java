/**
 * 
 */
package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *  講座ポップ2 A4
 * @author Peike Dai
 *
 */
public class TestClassPop2 {

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
		FileOutputStream out = new FileOutputStream("講座ポップ2.pdf");
		PdfDocument pdfdoc = new PdfDocument(out, true);
		// title1
		String testtitle = newString("日本画(土)");
		PdfTextBlock title = new PdfTextBlock(testtitle);
		title.setFontSize(80).setBold().adaptPaper(595).setPosition(50, 20);
		pdfdoc.addAbsolutePosition(title);
		// times and date 1
		PdfTextBlock timedate1 = new PdfTextBlock("1・3　(土)　13:00～15:30");
		timedate1.setFontSize(40).setBold().setPosition(50, 35);
		pdfdoc.addAbsolutePosition(timedate1);
		// title2
		String testtitle2 = newString("基礎デッサソと水彩画");
		PdfTextBlock title2 = new PdfTextBlock(testtitle2);
		title2.setFontSize(80).setBold().adaptPaper(842).setPosition(50, 65);
		pdfdoc.addAbsolutePosition(title2);
		// times and date 1
		PdfTextBlock timedate2 = new PdfTextBlock("1・3　(土)　13:00～15:30");
		timedate2.setFontSize(40).setBold().setPosition(50, 80);
		pdfdoc.addAbsolutePosition(timedate2);
		// separator 1
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{36, 175}, new float[]{806, 175}));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{36, 430}, new float[]{806, 430}));
		
		
		pdfdoc.saveToLocal();

	}

}
