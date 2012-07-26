/**
 * 
 */
package pdfPrinter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * used for 1-1
 * @author Col
 *
 */
public class TestClassData {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		boolean rotation = false;	// substitute by data
		PdfDocument pdfdoc = new PdfDocument(rotation);
		// title 
		PdfTitle pdftitle = new PdfTitle("クラスデータ");
		pdftitle.setFontSize(12.5f);
		pdfdoc.add(pdftitle);
		// pdf signiture
		PdfTextBlock pdfsign = new PdfTextBlock("クラス.TBL   "+(new SimpleDateFormat("yyyy年　M月dd日　 HH:mm").format(new Date())));
		pdfsign.setFontSize(9).setPosition(4, 2.5f);
		pdfdoc.addAbsolutePosition(pdfsign);
		// pdf table
		PdfTable pdftable = new PdfTable();
		String[] heads = {"クラス番号", "クラス名", "回数", "曜日", "開始時間", "終了時間", "講師番号", "講師名", "JASRAC"};
		List<String> header = new ArrayList<String>();
		for (String s : heads)
			header.add(s);
		pdftable.addHeader(header, new float[]{2.5f, 8, 2, 1.5f, 2, 2, 2, 7, 1.5f});
		String[] properties = {"classCode", "className", "times", "week", "startTime", "endTime", "teacherCode", "teacherName", "jasrac"};
		pdftable.addContent(null, properties);
		pdftable.setWidthPercentage(100).setHeaderFontSize(8);
		pdfdoc.add(pdftable);
		
		// pdf page number
		pdfdoc.headerOrPagenumber(false, null, null, true, 3);
		pdfdoc.saveToLocal();
	}

}
