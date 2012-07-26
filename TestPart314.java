/**
 * 
 */
package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Part 1-4
 * envelope
 * @author Col
 *
 */
public class TestPart314 {

	/**
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
		FileOutputStream out = new FileOutputStream("envelop1.pdf");
		PdfDocument pdfdoc = new PdfDocument(out);
		PdfTable name = new PdfTable(1, 3);
		name.addCell("　山瀬　隆男　様", 70);
		name.setContentFontSize(40).setWidthPercentage(10);
		pdfdoc.add(name);
		
		PdfTable address = new PdfTable(1, 3);
		address.addCell("埼玉県春日部市", 0f);
		address.setContentFontSize(26).setWidthPercentage(8).setPosition(75, 17);
		pdfdoc.addAbsolutePosition(address);
		
		PdfTable addrnum = new PdfTable(1, 3);
		addrnum.addCell("粕壁東一四五〇五", 0f);
		addrnum.setContentFontSize(26).setWidthPercentage(8).setPosition(65f, 25);
		pdfdoc.addAbsolutePosition(addrnum);
		
		PdfTextBlock postcode = new PdfTextBlock(newString("3440062"), 0, 3);
		postcode.setFontSize(20).setPosition(64, 13);
		pdfdoc.addAbsolutePosition(postcode);
		
		PdfTable text = new PdfTable(1, 1);
		text.addCell("明細表在中", 30);
		text.setContentFontSize(22).setWidthPercentage(8).setPosition(30f, 50);
		pdfdoc.addAbsolutePosition(text);
		
		PdfTextBlock number = new PdfTextBlock("No.120", 0, 1);
		number.setFontSize(13).setPosition(25, 85);
		pdfdoc.addAbsolutePosition(number);
		
		pdfdoc.saveToLocal();
	}

}
