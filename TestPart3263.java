package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *店铺月別著作権料集計 
 * @author Peike Dai
 *
 */
public class TestPart3263 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream("2011年度著作権料一覧.pdf");
		PdfDocument pdfdoc = new PdfDocument(out, true);
		
		PdfTitle title = new PdfTitle("2011年度著作権料一覧");
		pdfdoc.add(title);
		
		PdfTextBlock sign = new PdfTextBlock("店铺月別著作権料集計.tbl　" + new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date()));
		sign.setFontSize(10f).setPosition(10, 2);
		pdfdoc.addAbsolutePosition(sign);
		
		PdfTable table = new PdfTable(15, new float[]{1.3f, 2.5f, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2.5f});
		String[] heads = {"店鋪\n番号", "店舗名", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月", "1月", "2月", "3月", "合計"};
		ArrayList<String> header = new ArrayList<String>();
		for (String e : heads)
			header.add(e);
		table.addHeader(header);
		table.setWidthPercentage(100f).setHeaderFontSize(10).setContentFontSize(10f).addContent(null, null, true);
		pdfdoc.add(table);
		
		pdfdoc.headerOrPagenumber(null, null, 0, 3);
		pdfdoc.saveToLocal();
	}

}
