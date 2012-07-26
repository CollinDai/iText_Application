package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 初回持ち物確認用
 * @author Peike Dai
 *
 */
public class TestClassDataInitConfirm {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream fileoutput = new FileOutputStream("初回持ち物確認用.pdf");
		PdfDocument pdfdoc = new PdfDocument(fileoutput, new float[]{1031.94f, 728.6f});
		// title 
		PdfTitle pdftitle = new PdfTitle("相模大野店　【　初回持ち物確認用クラスデータ　】");
		pdftitle.setFontSize(12.5f);
		pdfdoc.add(pdftitle);
		// pdf signiture
		PdfTextBlock pdfsign = new PdfTextBlock("クラス.TBL   "+(new SimpleDateFormat("yyyy年　M月dd日　 HH:mm").format(new Date())));
		pdfsign.setFontSize(7).setPosition(2, 1);
		pdfdoc.addAbsolutePosition(pdfsign);
		// pdf page number
		pdfdoc.headerOrPagenumber(false, null, null, true, 3);
		// pdf table
		PdfTable pdftable = new PdfTable(10, 1000f);
		String[] heads = { "クラス番号", "サブタイトル", "クラス名", "後付サブタイトル", "回数", "曜日",
				"開始時間", "終了時間", "初回持ち物", "初回持ち物備考" };
		List<String> header = new ArrayList<String>();
		for (String s : heads)
			header.add(s);
		float[] widthpct = new float[] { 1.7f, 4f, 5f, 3.5f, 2f, 1.1f, 1.3f, 1.3f, 5f, 5f };
//		System.out.print(widthpct.length);
		pdftable.addHeader(header, widthpct, 12f, 5);
		String[] properties = { "classCode", "subTitle", "className",
				"hpTeacherName", "courseName", "position", "hpTeacherName",
				"times", "week", "startTime", "endTime", "cCost", "mCost",
				"comment", "hpImage", "hpPublishFlag" };
//		System.out.print(properties.length);
		pdftable.addContent(null, properties);
		pdftable.setWidthPercentage(100).setHeaderFontSize(8).setContentFontSize(8);
		pdfdoc.add(pdftable);
		
		
		pdfdoc.saveToLocal();
	}

}
