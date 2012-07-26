package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 本部提出用クラスデータ
 * @author Peike Dai
 *
 */
public class TestClassDataforHead {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream fileoutput = new FileOutputStream("本部提出用クラスデータ.pdf");
		PdfDocument pdfdoc = new PdfDocument(fileoutput, new float[]{1031.94f, 728.6f});
		// title 
		PdfTitle pdftitle = new PdfTitle("塚口店　●本部提出用クラスデータ　(每月10日FAX)　※新講座・変更講座のみ絞込の事");
		pdftitle.setFontSize(12.5f);
		pdfdoc.add(pdftitle);
		// pdf signiture
		PdfTextBlock pdfsign = new PdfTextBlock("クラス.TBL   "+(new SimpleDateFormat("yyyy年　M月dd日　 HH:mm").format(new Date())));
		pdfsign.setFontSize(9).setPosition(4, 2.5f);
		pdfdoc.addAbsolutePosition(pdfsign);
		// pdf page number
		pdfdoc.headerOrPagenumber(false, null, null, true, 3);
		// pdf table
		PdfTable pdftable = new PdfTable(25, 1000f);
		String[] heads = { "日付", "クラス番号", "クラス名", "教室", "講座番号", "講師番号", "講師名",
				"回数", "曜日", "開始時間", "終了時間", "通常／外部", "支払方法", "受講料単価", "受講料区分",
				"受講料支払率", "教材費単価", "教材費区分", "教材費支払率", "運営維持費単価", "運営維持費区分",
				"その他単価", "その他区分", "その他支払率", "JASRAC"};
		List<String> header = new ArrayList<String>();
		for (String s : heads)
			header.add(s);
		float[] widthpct = new float[] { 2, 1.4f, 4.6f, 0.8f, 1.2f, 1.2f, 4.6f,
				1.3f, 0.9f, 1.2f, 1.2f, 1.4f, 1.2f, 1.4f, 0.9f, 0.9f, 1.4f,
				0.9f, 0.9f, 1.4f, 1.2f, 1.4f, 0.9f, 0.9f, 0.8f };
//		System.out.print(widthpct.length);
		pdftable.addHeader(header, widthpct, 24);
		String[] properties = { "inputDate", "classCode", "className", "name",
				"name", "code", "name", "times", "week", "startTime",
				"endTime", "normalExternal", "payCategory", "cCost",
				"cCategory", "cPayRate", "mCost", "mCategory", "mPayRate",
				"kCost", "kCategory", "oCost", "oCategory", "oPayRate",
				"jasrac" };
		System.out.print(properties.length);
		pdftable.addContent(null, properties);
		pdftable.setWidthPercentage(100).setHeaderFontSize(7).setContentFontSize(7);
		pdfdoc.add(pdftable);
		
		
		pdfdoc.saveToLocal();

	}

}
