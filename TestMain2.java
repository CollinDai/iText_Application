package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author peike dai
 * used for  會員調查報告表 （11）
 *
 */
public class TestMain2 {
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream out = new FileOutputStream("会员调查报告书.pdf");
		PdfDocument pdfdoc = new PdfDocument(out, null);
		
		//title
		PdfTitle pdftitle = new PdfTitle("会員調査報告書");
		pdftitle.setMarginTop(10);
		pdfdoc.add(pdftitle);
		// report number
		PdfTextBlock reportnum = new PdfTextBlock("No.73");
		reportnum.setFontSize(10).setPosition(90, 3);
		pdfdoc.addAbsolutePosition(reportnum);
		// date period
		PdfTextBlock period = new PdfTextBlock("平成23年5月1日 ～ 平成23年5月31日");
		period.setFontSize(12).setPosition(10, 6);
		pdfdoc.addAbsolutePosition(period);
		// shop name
		PdfTextBlock shopname = new PdfTextBlock("相模大野店");
		shopname.setFontSize(17).setPosition(92, 6);
		pdfdoc.addAbsolutePosition(shopname);
		// table 1
		PdfTable table1 = new PdfTable(2, new float[]{0.65f, 0.35f});
		table1.addCell("購読新聞", 1, 16.75f).addCell("人数", 1, 16.75f).addCell("朝日新聞", 16.75f,
				4).addCell("18", 16.75f, 6).addCell("朝日新聞", 16.75f, 4).addCell("18",
				16.75f, 6).addCell("朝日新聞", 16.75f, 4).addCell("18", 16.75f, 6).addCell(
				"朝日新聞	", 16.75f, 4).addCell("18", 16.75f, 6).addCell("朝日新聞", 16.75f, 4)
				.addCell("18", 16.75f, 6).addCell("朝日新聞", 16.75f, 4).addCell("18", 16.75f,
						6).addCell("合　　計", 1, 16.75f).addCell("81", 16.75f, 6)
				.setWidthPercentage(25.7f).setContentFontSize(11.5f).setPosition(9, 9);
		pdfdoc.addAbsolutePosition(table1);
		// table 2
		PdfTable table2 = new PdfTable(2, new float[]{7, 3});
		table2.addCell("入会動機媒体", 1, 16.75f).addCell("人数", 1, 16.75f).addCell("店頭で気が付いて", 16.75f,
				4).addCell("18", 16.75f, 6).addCell("店頭で気が付いて", 16.75f, 4).addCell("5",
				16.75f, 6).addCell("店頭で気が付いて", 16.75f, 4).addCell("5", 16.75f, 6).addCell(
				"店頭で気が付いて	", 16.75f, 4).addCell("5", 16.75f, 6).addCell("店頭で気が付いて", 16.75f, 4)
				.addCell("5", 16.75f, 6).addCell("店頭で気が付いて", 16.75f, 4).addCell("5", 16.75f,
						6).addCell("合　　計", 1, 16.75f).addCell("40", 16.75f, 6)
				.setWidthPercentage(30.5f).setContentFontSize(11.5f).setPosition(50, 9);
		pdfdoc.addAbsolutePosition(table2);
		// table 3
		PdfTable table3 = new PdfTable(2, new float[]{0.65f, 0.35f});
		table3.addCell("来店手段", 1, 16.75f).addCell("人数", 1, 16.75f).addCell("ヘリコプター", 16.75f,
				4).addCell("1", 16.75f, 6).addCell("ヘリコプター", 16.75f, 4).addCell("1",
				16.75f, 6).addCell("ヘリコプター", 16.75f, 4).addCell("1", 16.75f, 6).addCell(
				"ヘリコプター	", 16.75f, 4).addCell("1", 16.75f, 6).addCell("ヘリコプター", 16.75f, 4)
				.addCell("1", 16.75f, 6).addCell("ヘリコプター", 16.75f, 4).addCell("1", 16.75f,
						6).addCell("合　　計", 1, 16.75f).addCell("55", 16.75f, 6)
				.setWidthPercentage(25.7f).setContentFontSize(11.5f).setPosition(91, 9);
		pdfdoc.addAbsolutePosition(table3);
		// table4 title, age distribution
		PdfTextBlock agedist = new PdfTextBlock("◎ 男女年齡別");
		agedist.setFontSize(13).setPosition(6.5f, 25.5f);
		pdfdoc.addAbsolutePosition(agedist);
		// table 4
		PdfTable table4 = new PdfTable(11, new float[] { 0.6f, 1.1f, 1.8f,
				1.8f, 1.8f, 1.8f, 1.8f, 1.8f, 1.8f, 1.8f, 1.7f });
		table4.addCell("年齡", 1, 16.75f, 0, 2, 5).addCell("0～12", 1, 16.75f)
				.addCell("13～19", 1, 16.75f).addCell("20～29", 1, 16.75f)
				.addCell("30～39", 1, 16.75f).addCell("40～49", 1, 16.75f)
				.addCell("50～59", 1, 16.75f).addCell("60～69", 1, 16.75f)
				.addCell("70以上", 1, 16.75f).addCell("合計", 1, 16.75f);
		table4.addCell("男", 1, 0, 2, 0, 5).addCell("入会", 16.75f, 5, 4)
				.addCell("5", 16.75f, 6, 4).addCell("5", 16.75f, 6, 4)
				.addCell("5",16.75f, 6, 4).addCell("5", 16.75f, 6, 4)
				.addCell("5", 16.75f, 6, 4).addCell("5", 16.75f, 6, 4)
				.addCell("5", 16.75f, 6, 4).addCell("5", 16.75f, 6, 4)
				.addCell("5", 16.75f, 6, 4).addCell("在籍", 16.75f, 5, 3)
				.addCell("125", 16.75f, 6, 3).addCell("125", 16.75f, 6, 3)
				.addCell("125", 16.75f, 6, 3).addCell("125", 16.75f, 6, 3)
				.addCell("125", 16.75f, 6, 3).addCell("125", 16.75f, 6, 3)
				.addCell("125", 16.75f, 6, 3).addCell("125", 16.75f, 6, 3)
				.addCell("125", 16.75f, 6, 3);
		table4.addCell("女", 1, 0, 2, 0, 5).addCell("入会", 16.75f, 5, 4)
				.addCell("5", 16.75f, 6, 4).addCell("5", 16.75f, 6, 4)
				.addCell("5", 16.75f, 6, 4).addCell("5", 16.75f, 6, 4)
				.addCell("5", 16.75f, 6, 4).addCell("5", 16.75f, 6, 4)
				.addCell("5", 16.75f, 6, 4).addCell("5", 16.75f, 6, 4)
				.addCell("5", 16.75f, 6, 4).addCell("在籍", 16.75f, 5, 3)
				.addCell("125", 16.75f, 6, 3).addCell("125", 16.75f, 6, 3)
				.addCell("125", 16.75f, 6, 3).addCell("125", 16.75f, 6, 3)
				.addCell("125", 16.75f, 6, 3).addCell("125", 16.75f, 6, 3)
				.addCell("125", 16.75f, 6, 3).addCell("125", 16.75f, 6, 3)
				.addCell("125", 16.75f, 6, 3)
				.setWidthPercentage(98.5f).setContentFontSize(10f).setMarginTop(186);
		pdfdoc.add(table4);
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{57, 575.5f}, new float[]{557, 575.5f}));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{57, 542.5f}, new float[]{557, 542.5f}));
		// subtitle for table 5
		PdfTextBlock addrinfo = new PdfTextBlock("◎ 住居地域別入会者数・在籍者数　　　　　　　　　　　　　　　　　　　入会・在籍");
		addrinfo.setFontSize(13).setPosition(42f, 38.5f);
		pdfdoc.addAbsolutePosition(addrinfo);
		// table 5
		PdfTable table5 = new PdfTable(9, 2);
		table5.setColumnPercentage(new float[]{5, 1, 1.3f, 5, 1, 1.3f, 5, 1, 1.3f});
		for (int i=0; i<30; ++i) {
			if (i == 29) {
				table5.addCell("合　計", 1, 16.75f).addCell("0", 0f, 5, 2).addCell(Integer.toString(i), 0f, 5, 1);
				break;
			}
			table5.addCell("大沼", 24f, 4).addCell("0", 0f, 5, 2).addCell(Integer.toString(i), 0f, 5, 1);
		}
		table5.setWidthPercentage(98.5f).setContentFontSize(10f).setMarginTop(24);
		pdfdoc.add(table5);
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{184f, 502}, new  float[]{184f, 262}));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{356f, 502}, new  float[]{356, 262}));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{526, 502}, new  float[]{526, 262}));
		// actual month
		PdfTextBlock actual = new PdfTextBlock("◎ 当月実績");
		actual.setFontSize(13).setPosition(6.4f, 70.5f);
		pdfdoc.addAbsolutePosition(actual);
		// classroom registered
		PdfTextBlock classroom = new PdfTextBlock("◎ 教室別在籍者数");
		classroom.setFontSize(13).setPosition(58, 70.5f);
		pdfdoc.addAbsolutePosition(classroom);
		// table 6
		PdfTable table6 = new PdfTable(2, new float[]{73.5f, 26.5f});
		table6.addCell("会員番号", 1, 16.75f).addCell("入会者数", 1, 0f).addCell(
				"No. 3859 ～ No. 3917", 18f, 5).addCell("59名", 0f, 6)
				.setAlignment(-1).setWidthPercentage(32).setPosition(9.8f, 71.5f);
		pdfdoc.addAbsolutePosition(table6);
		// table 7
		PdfTable table7 = new PdfTable(1, new float[]{1});
		table7.addCell("講師人数", 1, 16.75f).addCell("154名", 18f, 5).setWidthPercentage(8).setPosition(43, 71.5f);
		pdfdoc.addAbsolutePosition(table7);
		// table 8
		PdfTable table8 = new PdfTable(9, new float[]{1,1,1,1,1,1,1,1,1});
		for (char c='A'; c<'J'; ++c) 
			table8.addCell(String.valueOf(c), 1, 16.75f);
		for (int i=111; i<120; ++i) 
			table8.addCell(String.valueOf(i), 18f, 5);
		table8.setWidthPercentage(45).setPosition(88, 71.5f);
		pdfdoc.addAbsolutePosition(table8);
		// last subtitle
		PdfTextBlock last = new PdfTextBlock("◎ コース別入会者数・在籍者数（男／女）・クラス数");
		last.setFontSize(13).setPosition(12, 77.4f);
		pdfdoc.addAbsolutePosition(last);
		// table 9
		PdfTable table9 = new PdfTable(10, new float[]{32, 14, 13, 13, 17, 32, 14, 13, 13, 17});
		for (int i=0; i<2; ++i) 
			table9.addCell("コース", 1, 16.75f, 0, 0, 5).addCell("入会者", 1, 0, 0, 0, 5).addCell("在籍者", 1, 0, 0, 2, 5).addCell("クラス数", 1, 0, 0, 0, 5);
		table9.setWidthPercentage(98.5f).setHeaderFontSize(10).setMarginTop(78);
		for (int i=0; i<10; ++i)
			table9.addCell("書道・美術", 17f, 4).addCell("10", 0f, 5).addCell("9", 0f, 6, 2).addCell("99", 0f, 6, 1).addCell("24", 0f, 5);
		for (int i=0; i<5; ++i)
			table9.addCell("", 17f, false, 2, 0);
		table9.addCell("合　計", 1, 0, 2, 0, 5).addCell("53", 0, 0, 2, 0, 5).addCell("121", 17f, 6, 2).addCell("1186", 17f, 6, 1).addCell("252", 0, 0, 2, 0, 5).addCell("1403名", 0, 17f, 0, 2, 6);
		pdfdoc.add(table9);
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{210, 167}, new  float[]{210, 82.5f}));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{468, 167}, new  float[]{468, 67f}));
		pdfdoc.saveToLocal();

	}	
}
