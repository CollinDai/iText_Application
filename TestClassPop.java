/**
 * 
 */
package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 講座ポップ B5
 * @author Peike Dai
 *
 */
public class TestClassPop {

	/**
	 * if char number less than 5, add 全角 space
	 * else add 半角 space
	 * @param args
	 * @throws FileNotFoundException 
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
		FileOutputStream out = new FileOutputStream("講座ポップ.pdf");
		PdfDocument pdfdoc = new PdfDocument(out, new float[]{728f, 515.7f});
		// title
		String testtitle = newString("日本画(土)");
		PdfTextBlock title = new PdfTextBlock(testtitle);
		title.setFontSize(100).setBold().adaptPaper(728f).setPosition(50, 30);
		pdfdoc.addAbsolutePosition(title);
		// 講師
		PdfTextBlock teacher = new PdfTextBlock("講師　永田　耕治");
		teacher.setFontSize(35).setBold().setPosition(50, 55);
		pdfdoc.addAbsolutePosition(teacher);
		// times and dates
		PdfTextBlock timedates = new PdfTextBlock("1・3(土)13:00～15:30");
		timedates.setFontSize(40).setBold().setPosition(50, 70);
		pdfdoc.addAbsolutePosition(timedates);
		// 受講料，教材費
		PdfTextBlock cost = new PdfTextBlock("受講料￥5,555　教材費￥535");
		cost.setFontSize(20).setBold().setPosition(80, 80);
		pdfdoc.addAbsolutePosition(cost);
		
		// grey blocks
		PdfTable blocktop = new PdfTable(1);
		blocktop.addCell("　", 1, 12f).setWidthPercentage(90).setPosition(50, 5);
		pdfdoc.addAbsolutePosition(blocktop);
		PdfTable blockbottom = new PdfTable(1);
		blockbottom.addCell("　", 1, 12f).setWidthPercentage(90).setPosition(50, 92.5f);
		pdfdoc.addAbsolutePosition(blockbottom);
		pdfdoc.saveToLocal();
		System.out.print(testtitle);
		
	}

}
