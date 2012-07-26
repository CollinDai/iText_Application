/**
 * 
 */
package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author Col
 *
 */
public class TestClassPop3 {

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
		FileOutputStream out = new FileOutputStream("講座ポップ3.pdf");
		PdfDocument pdfdoc = new PdfDocument(out, false, new float[]{728f, 1031.94f}, new float[]{36, 36, 72, 36});
		// major title
		PdfTextBlock majortitle = new PdfTextBlock("自由な画材で描く", -1);
		majortitle.setFontSize(30).setWidthPercentage(100f).setBold().adaptPaper(728);
		pdfdoc.add(majortitle);
		//title
		String testtitle = newString("基礎デッと絵画");
		PdfTitle title = new PdfTitle(testtitle);
		title.setFontSize(100).setMarginTop(75).setBold().adaptPaper(728);
		pdfdoc.add(title);
		// teacher
		PdfTextBlock teacher = new PdfTextBlock("講師　永田　耕治");
		teacher.setFontSize(35).setPosition(50, 30);
		pdfdoc.addAbsolutePosition(teacher);
		// time and date
		PdfTextBlock timedates = new PdfTextBlock("1・3(土)13:00～15:30");
		timedates.setFontSize(40).setBold().setPosition(50, 76);
		pdfdoc.addAbsolutePosition(timedates);
		// 受講料，教材費
		PdfTextBlock cost = new PdfTextBlock("受講料￥5,555\n教材費￥535", 35f);
		cost.setFontSize(26).setPosition(80, 85);
		pdfdoc.addAbsolutePosition(cost);
		// grey blocks
		PdfTable blocktop = new PdfTable(1);
		blocktop.addCell("　", 1, 1f, false).setWidthPercentage(90).setPosition(50, 90);
		pdfdoc.addAbsolutePosition(blocktop);
		// don't know what it is
		PdfTextBlock dontknow = new PdfTextBlock("一流の講師とせげてみみ仲間", -1);
		dontknow.setFontSize(16).setWidthPercentage(100f).setMarginTop(760);
		pdfdoc.add(dontknow);
		// shop name
		PdfTextBlock shopname = new PdfTextBlock("相模大野レトチセルメネン", -1);
		shopname.setFontSize(24).setWidthPercentage(100f).setMarginTop(10);
		pdfdoc.add(shopname);
		
		
		pdfdoc.saveToLocal();
	}

}
