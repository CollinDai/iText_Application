package pdfPrinter;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 前受金月額確認
 * @author Peike Dai
 *
 */
public class TestClassDataAdvanced {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		PdfDocument pdfdoc = new PdfDocument();
		// title 
		PdfTitle pdftitle = new PdfTitle("相模大野店　前受月額確認");
		pdftitle.setFontSize(12.5f);
		pdfdoc.add(pdftitle);
		// pdf signiture
		PdfTextBlock pdfsign = new PdfTextBlock("クラス.TBL   "+(new SimpleDateFormat("yyyy年　M月dd日 HH:mm").format(new Date())));
		pdfsign.setFontSize(7).setPosition(6.5f, 1);
		pdfdoc.addAbsolutePosition(pdfsign);
		// pdf page number
		pdfdoc.headerOrPagenumber(false, null, null, true, 3);
		// pdf table
		PdfTable pdftable = new PdfTable(10);
		String[] heads = { "クラス番号", "クラス名", "受講料単価", "受講料月額", "ＨＰ受講料",
				"教材費単価", "教材費月額", "ＨＰ教材費", "その他単価", "運営維持費単価" };
		List<String> header = new ArrayList<String>();
		for (String s : heads)
			header.add(s);
		float[] widthpct = new float[] { 1.4f, 5.3f, 2f, 2, 2.5f, 2, 2, 2.5f, 1.4f, 1.9f };
//		System.out.print(widthpct.length);
		pdftable.addHeader(header, widthpct);
		String[] properties = { "classCode", "className",
				"cCost", "cMonthCost", "cHPCost", "mCost",
				"mMonthCost", "mHPCost", "oCost", "kCost" };
//		System.out.print(properties.length);
		pdftable.addContent(null, properties);
		pdftable.setWidthPercentage(100).setHeaderFontSize(7).setContentFontSize(7);
		pdfdoc.add(pdftable);
		
		
		pdfdoc.saveToLocal();
	}

}
