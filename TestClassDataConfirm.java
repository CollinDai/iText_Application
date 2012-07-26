package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 掲載確認用クラスデータ
 * @author Peike Dai
 *
 */
public class TestClassDataConfirm {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream fileoutput = new FileOutputStream("掲載確認用クラスデータ.pdf");
		PdfDocument pdfdoc = new PdfDocument(fileoutput, new float[]{1031.94f, 728.6f});
		// title 
		PdfTitle pdftitle = new PdfTitle("塚口店　【　ＨＰ掲載確認用クラスデータ　】");
		pdftitle.setFontSize(12.5f);
		pdfdoc.add(pdftitle);
		// pdf signiture
		PdfTextBlock pdfsign = new PdfTextBlock("クラス.TBL   "+(new SimpleDateFormat("yyyy年　M月dd日　 HH:mm").format(new Date())));
		pdfsign.setFontSize(7).setPosition(2, 1);
		pdfdoc.addAbsolutePosition(pdfsign);
		// pdf page number
		pdfdoc.headerOrPagenumber(false, null, null, true, 3);
		// pdf table
		PdfTable pdftable = new PdfTable(16, 1000f);
		String[] heads = { "クラス番号", "サブタイトル", "クラス名", "後付サブタイトル", "コース名", "肩書",
				"HP講師名", "回数", "曜日", "開始時間", "終了時間", "HP受講料", "HP教材費",
				"HPコメント", "クラス写真フアイル名", "HP掲載" };
		List<String> header = new ArrayList<String>();
		for (String s : heads)
			header.add(s);
		float[] widthpct = new float[] { 0.9f, 2.9f, 4.5f, 2.4f, 2.4f, 2.7f, 3.4f, 1.3f, 0.7f, 1.2f, 1.2f, 1.5f, 1.5f, 6.3f, 1.4f, 0.7f};
//		System.out.print(widthpct.length);
		pdftable.addHeader(header, widthpct, 22);
		String[] properties = { "classCode", "subTitle", "className",
				"hpTeacherName", "courseName", "position", "hpTeacherName",
				"times", "week", "startTime", "endTime", "cCost", "mCost",
				"comment", "hpImage", "hpPublishFlag" };
//		System.out.print(properties.length);
		pdftable.addContent(null, properties);
		pdftable.setWidthPercentage(100).setHeaderFontSize(7).setContentFontSize(7);
		pdfdoc.add(pdftable);
		
		
		pdfdoc.saveToLocal();

	}

}
