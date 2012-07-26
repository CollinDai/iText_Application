/**
 * 
 */
package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 
 * @author 
 *
 */
public class TestPart3112 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream("講師料 お支払い 明細表.pdf");
		PdfDocument pdfdoc = new PdfDocument (out, new float[]{36, 36, 18, 8}, false);
		// null block
		PdfTitle nulltitle = new PdfTitle("　");
		pdfdoc.add(nulltitle);
		// tel 1
		PdfTextBlock tel1 = new PdfTextBlock("〒344-0062");
		tel1.setFontSize(9).setPosition(9, 1.5f);
		pdfdoc.addAbsolutePosition(tel1);
		// address 1
		PdfTextBlock addr1 = new PdfTextBlock("埼玉県春日部市粕壁東1-4-5-505", 0, 1);
		addr1.setFontSize(9).setPosition(10, 4);
		pdfdoc.addAbsolutePosition(addr1);
		// title
		PdfTextBlock title = new PdfTextBlock("* * * 講師料　お支払い　明細表 * * *", 0, 2);
		title.setFontSize(11).setPosition(90, 3);
		pdfdoc.addAbsolutePosition(title);
		// date
		PdfTextBlock date= new PdfTextBlock("2012-03月分");
		date.setFontSize(9).setPosition(65, 6);
		pdfdoc.addAbsolutePosition(date);
		// title 1
		PdfTextBlock title2 = new PdfTextBlock("クラス別明細表", 0, 1);
		title2.setFontSize(9).setPosition(88, 6);
		pdfdoc.addAbsolutePosition(title2);
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createRoundRectangle(new float[]{465, 773, 80, 10, 3}, false, null));
		// name
		PdfTextBlock name = new PdfTextBlock("山瀬　隆男　様", 0, 1);
		name.setFontSize(12).setPosition(15, 8);
		pdfdoc.addAbsolutePosition(name);
		// number
		PdfTextBlock num= new PdfTextBlock("No.　120", 0, 1);
		num.setFontSize(9f).setPosition(30, 10);
		pdfdoc.addAbsolutePosition(num);
		// image
		// shopinfo
		PdfTextBlock shopinfo = new PdfTextBlock("〒344-0062\n神奈川県相模原市南区相模大野3-1-7\nクソトレチ4階 TEL042-746-9212(代)", 10, -1, 2);
		shopinfo.setFontSize(7).setWidthPercentage(60).setPosition(100, 10);
		pdfdoc.addAbsolutePosition(shopinfo);
		// table
		PdfTable table = new PdfTable(17, 570f, new float[]{1.3f, 0.9f, 2.3f, 1.1f, 0.9f, 0.9f, 0.9f, 0.9f, 0.9f, 0.9f, 1.4f, 1.3f, 1.3f, 1.3f, 1.3f, 1.3f, 1.3f});
		table.addCell("店名", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("NO.", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("クラス名", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("単価", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("当月在籍", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("前月入金", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("当月入金", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("当月未入", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("支払人数", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("途中入会", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("講師料", 28f, 0f, true, 0, 0, 10f, 5, 2)
			.addCell("体験費", 28f, 0f, true, 0, 0, 10f, 5, 2)
			.addCell("その他支払", 28f, 0f, true, 0, 0, 10f, 5, 2)
			.addCell("源泉税", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("教材費", 28f, 0f, true, 0, 0, 10f, 5, 2)
			.addCell("その他支払", 28f, 0f, true, 0, 0, 10f, 5, 2)
			.addCell("消費税", 40f, 0f, true, 2, 0, 10f, 5, 2)
			.addCell("源　泉　有", 12f, 0f, true, 0, 3, 8f, 5, 2)
			.addCell("源　泉　無", 12f, 0f, true, 0, 2, 8f, 5, 2);
		int datalen = 7;
		for (int i=0; i<datalen; ++i) {
			table.addCell("大宮", 35f, 5).addCell("300", 35f, 5).addCell("木　　彫", 35f, 4)
				.addCell("1,600", 35f, 6).addCell("5", 35f, 6).addCell("0", 35f, 6)
				.addCell("5", 35f, 6).addCell("0", 35f, 6).addCell("5", 35f, 6)
				.addCell("0", 35f, 6).addCell("8,000", 35f, 6).addCell("0", 35f, 6)
				.addCell("0", 35f, 6).addCell("800", 35f, 6).addCell("0", 35f, 6)
				.addCell("0", 35f, 6).addCell("400", 35f, 6);
		}
		int fill = datalen % 15;
		if (fill != 0)
			for (int i=0; i<(fill*17); ++i)
				table.addCell("", 35f, 5);
		table.addCell("合計", 30f, 0f, true, 0, 4, 10f, 4, 2).addCell("5", 35f, 6).addCell("0", 35f, 6)
			.addCell("5", 35f, 6).addCell("0", 35f, 6).addCell("5", 35f, 6)
			.addCell("0", 35f, 6).addCell("8,000", 35f, 6).addCell("0", 35f, 6)
			.addCell("0", 35f, 6).addCell("800", 35f, 6).addCell("0", 35f, 6)
			.addCell("0", 35f, 6).addCell("400", 35f, 6);
		table.setMarginTop(100f).setContentFontSize(8f);
		pdfdoc.add(table);
		// bottom table
		PdfTable table2 = new PdfTable(8, new float[]{1.5f,2,1.5f,2,1.5f,2,1.5f,2}, 0f, 2);
		table2.addCell("講師料", 40f, 5, false).addCell("0", 40f, 6, false)
			.addCell("体験費", 40f, 5, false).addCell("0", 40f, 6, false)
			.addCell("その他\n(源泉有)", 40f, 5, false).addCell("0", 40f, 6, false)
			.addCell("源泉税", 40f, 5, false).addCell("5,760", 40f, 6, false)
			.addCell("教材費", 40f, 5, false).addCell("4,000", 40f, 6, false)
			.addCell("その他\n(源泉無)", 40f, 5, false).addCell("0", 40f, 6, false)
			.addCell("消費税", 40f, 5, false).addCell("3,060", 40f, 6, false)
			.addCell("差引\n支払額", 40f, 5, false).addCell("55,760", 40f, 6, false);
		table2.setContentFontSize(10f).setMarginTop(10);
		pdfdoc.add(table2);
		
//		PdfTextBlock bottomtxt = new PdfTextBlock("")
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createRoundRectangle(new float[]{84, 55, 320f, 72f, 16}, false, null));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{84, 91}, new float[]{404, 91}));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{138, 127}, new float[]{138 , 55}));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{193, 127}, new float[]{193, 55}));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{237, 127}, new float[]{237, 55}));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{297, 127}, new float[]{297, 55}));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{341, 127}, new float[]{341, 55}));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createRoundRectangle(new float[]{405, 91, 110f, 36f, 16}, false, null));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createRoundRectangle(new float[]{405, 55, 110f, 36f, 16}, false, null));
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{449, 127}, new float[]{449, 55}));
		pdfdoc.headerOrPagenumber(null, null, 0, 9, true);
		// bottom text
		PdfTextBlock bottomtxt = new PdfTextBlock("いつもお世話になっております。今月の講師料は上記の通りでございます。\n"
					+ "尚、ご不明な点などございましたら講座課までご連絡下さい。今後ともよろしくお願い申し上げます。", 10, -1, 2);
		bottomtxt.setFontSize(10).setWidthPercentage(90f);
		pdfdoc.add(bottomtxt);
	
		
		pdfdoc.saveToLocal();
	}

}
