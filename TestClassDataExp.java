/**
 * 
 */
package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 体験準確認用クラスデータ
 * @author Peike Dai
 *
 */
public class TestClassDataExp {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream fileoutput = new FileOutputStream("体験準確認用クラスデータ.pdf");
		PdfDocument pdfdoc = new PdfDocument(fileoutput, new float[]{1031.94f, 728.6f});
		// title 
		PdfTitle pdftitle = new PdfTitle("相模大野店　【　体験準確認用クラスデータ　】");
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
		String[] heads = { "クラス番号", "サブタイトル", "クラス名", "後付サブタイトル", "講師番号", 
				"HP講師名", "回数", "曜日", "体験日1", "体験日2", "体験開始時間", "体験終了時間", "体験受講料",
				"体験教材費", "体験持物", "体験備考" };
		List<String> header = new ArrayList<String>();
		for (String s : heads)
			header.add(s);
		float[] widthpct = new float[] { 0.9f, 2.9f, 4.5f, 2.4f, 1.2f, 2.7f, 1.1f, 1.3f, 1.2f, 1.7f, 1.7f, 1.7f, 1.4f, 1.7f, 4.5f, 4f};
//		System.out.print(widthpct.length);
		pdftable.addHeader(header, widthpct, 22);
		String[] properties = { "classCode", "subTitle", "className",
				"hpTeacherName", "code", "hpTeacherName", "times", "week",
				"tryDate1", "tryDate2", "tryStartTime", "tryEndTime",
				"tryClassCost", "tryMaterialCost", "tryCarry", "tryRemark" };
//		System.out.print(properties.length);
		pdftable.addContent(null, properties);
		pdftable.setWidthPercentage(100).setHeaderFontSize(7).setContentFontSize(7);
		pdfdoc.add(pdftable);
		
		
		pdfdoc.saveToLocal();


	}

}
