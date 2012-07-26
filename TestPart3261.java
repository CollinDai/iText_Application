/**
 * 
 */
package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 店舗別著作権料1
 * 不確定表格行首的編號是否要
 * @author Col
 *
 */
public class TestPart3261 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream("店舗別著作権料1");
		PdfDocument pdfdoc = new PdfDocument(out);
		
		PdfTitle title = new PdfTitle("●店舗別著作権料");
		title.setFontSize(10f);
		pdfdoc.add(title);
		
		PdfTextBlock sign = new PdfTextBlock("JASRAC月報.tbl　" + new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date()));
		sign.setFontSize(10f).setPosition(10, 2);
		pdfdoc.addAbsolutePosition(sign);
		
		PdfTable table = new PdfTable(8, new float[]{1, 3, 2, 2, 1.5f, 3, 2.5f, 2.5f});
		String[] heads = {"店鋪\n番号", "店舗名", "クラス名", "当月在籍", "1/2\n人数", "受講料合計", "著作権料", "月報年月日"};
		ArrayList<String> header = new ArrayList<String>();
		for (String e : heads)
			header.add(e);
		table.addHeader(header);
		table.addContent(null, null, true);
		table.setHeaderFontSize(10).setContentFontSize(10);
		pdfdoc.add(table);
		
		pdfdoc.headerOrPagenumber(null, null, 0, 3);
		pdfdoc.saveToLocal();
	}

}
