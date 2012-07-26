/**
 * 
 */
package pdfPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 一日体験受講券2
 * @author Peike dai
 *
 */
public class TestClassExpTicket2 {

	/**
	 * @param args
	 */
	private static void generateSeperatePage(ByteArrayOutputStream bout,
			List<Object> data) {
		PdfDocument pdfdoc = new PdfDocument(bout, false, new float[] { 728,
				257.9f }, new float[] { 18, 18, 18, 18 });
		for (int i = 0; i < 2; ++i) {
			// empty title
			PdfTitle emptytitle = new PdfTitle("　");
			pdfdoc.add(emptytitle);
			// 店控え
			PdfTextBlock invoice = new PdfTextBlock("店控え");
			invoice.setFontSize(13).setPosition(3, 10);
			pdfdoc.addAbsolutePosition(invoice);
			// title
			PdfTextBlock title = new PdfTextBlock("一 日 体 験 受 講 券");
			title.setFontSize(13).setBold().setPosition(16, 10);
			pdfdoc.addAbsolutePosition(title);
			title.setPosition(76f, 10);
			pdfdoc.addAbsolutePosition(title);
			// number
			PdfTextBlock number = new PdfTextBlock("No　　　　");
			number.setFontSize(9).setUnderlined().setPosition(41f, 10);
			pdfdoc.addAbsolutePosition(number);
			number.setPosition(58f, 10);
			pdfdoc.addAbsolutePosition(number);
			// shop keep
			PdfTextBlock shopkeep = new PdfTextBlock("当日受付にご持参下さい");
			shopkeep.setFontSize(10).setPosition(97f, 10);
			pdfdoc.addAbsolutePosition(shopkeep);
			// shop code
			PdfTextBlock shopcode = new PdfTextBlock("1039");
			shopcode.setFontSize(7).setPosition(4, 17);
			pdfdoc.addAbsolutePosition(shopcode);
			// // left table
			PdfTable table = new PdfTable(9, new float[] { 1.5f, 5.5f, 1.2f,
					2f, 2f, 1.5f, 5.5f, 1.2f, 2f });
			table.addCell("講座名", 14f, 4, false, 8)
					.addCell("おれめないげびな　池坊", 14f,false, 0, 3, 10, 5)
					.addCell("", 0f, 0, false)
					.addCell("講座名", 14f, 4, false, 8)
					.addCell("おれめないげびな　池坊", 14f, false,0, 3, 10f, 5);
			table.addCell("体験時日 ", 14f, 4, false, 8)
					.addCell("2009年 6月13日(土)13:00～15:00", 14f, false, 0, 3, 10f, 5)
					.addCell("", 0f, 0, false).addCell("体験時日 ", 14f, 4, false,
							8).addCell("2009年 6月13日(土)13:00～15:00", 14f, false,
							0, 3, 10f, 5);
			table.addCell("持ち物 ", 40, 4, false, 8)
					.addCell("蘇打綠副たへむなずびぎふてわぶびわみちは地方了", 40, false, 0, 3, 10f, 4)
					.addCell("", 0f, 0, false)
					.addCell("持ち物 ", 40, 4, false, 8)
					.addCell("蘇打綠副たへむなずびぎふてわぶびわみちは地方了", 40, false, 0, 3, 10f, 4);
			table.addCell("適　用 ", 32, false, 2, 0, 8f, 4).addCell(
					"塑料袋開房間阿斯蒂芬健康", 32, false, 2, 0, 10f, 4).addCell("体験費",
					16f, 5, false, 8f).addCell("￥1,500", 16f, 6, false, 10f)
					.addCell("　", 32f, false, 2, 0).addCell("適　用 ", 32, false,
							2, 0, 8f, 4).addCell("塑料袋開房間阿斯蒂芬健康", 32, false, 2,
							0, 10f, 4).addCell("体験費", 16f, 5, false, 8f)
					.addCell("￥1,500", 16f, 6, false, 10f).addCell("教材費", 16f,
							5, false, 8f).addCell("￥1,500", 16f, 6, false, 10f)
					.addCell("教材費", 16f, 5, false, 8f).addCell("￥1,500", 16f,
							6, false, 10f);
			table.addCell("氏　名", 30f, false, 2, 0, 10f, 7).addCell("様　ＴＥＬ",
					30f, false, 2, 3, 10f, 8).addCell("　", 30f, false, 2, 0)
					.addCell("お名前", 12, 4, false, 8f).addCell("ご住所 ", 12f,
							false, 0, 2, 8f, 5).addCell("　", 12f, false, 0, 0,
							8f, 5).addCell("様 ", 18f, false, 0, 2, 10f, 5)
					.addCell("　 ", 18f, false, 0, 2, 10f, 5);
			table.addCell("受付日　　　　年　　　月　　　日 　　　　印", 16f, false, 0, 4, 9f, 4)
					.addCell("　", 14f, false, 0, 0).addCell(
							"受付日　　　　年　　　月　　　日 　　　　印　お名前、ご住所をご記入下さい", 16f,
							false, 0, 4, 9f, 4);
			table.setWidthPercentage(100f).setMarginTop(30);
			pdfdoc.add(table);
			// notification
			PdfTextBlock notice = new PdfTextBlock("※納入後の教材費は原則としてお返しいたしません");
			notice.setFontSize(7.5f).setPosition(2.5f, 85);
			pdfdoc.addAbsolutePosition(notice);
			notice.setPosition(70f, 85);
			pdfdoc.addAbsolutePosition(notice);
			// // shop name
			PdfTextBlock shopname = new PdfTextBlock("相模大野店ゲズミタヘニザナギ");
			shopname.setFontSize(7).setPosition(24, 93);
			pdfdoc.addAbsolutePosition(shopname);
			shopname.setFontSize(12).setPosition(70, 93);
			pdfdoc.addAbsolutePosition(shopname);
			// // shop telephone
			PdfTextBlock shoptel = new PdfTextBlock("Tel.042-506-5269");
			shoptel.setFontSize(7).setPosition(40, 93);
			pdfdoc.addAbsolutePosition(shoptel);
			shoptel.setFontSize(12).setPosition(90, 93);
			pdfdoc.addAbsolutePosition(shoptel);

			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(
					new float[] { 364, 247 }, new float[] { 364, 0 }));
			// left
			// ellipse
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createRoundRectangle(
					new float[] { 20, 210, 45, 22, 5 }, false, null));
			
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{20, 178.8f}, new float[]{332, 178.8f}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{20, 166}, new float[]{332, 166}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{20, 126}, new float[]{332, 126}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{20, 94}, new float[]{224, 94}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{236, 110}, new float[]{332, 110}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{236, 94}, new float[]{332, 94}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{20, 62}, new float[]{332, 62}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{20, 46}, new float[]{332, 46}));
			// right
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createRoundRectangle(
					new float[] { 390, 208, 330, 25, 5 }, false, null));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createRoundRectangle(
					new float[] { 595, 212, 120, 17, 5 }, false, null));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{394, 178.8f}, new float[]{708, 178.8f}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{394, 166}, new float[]{708, 166}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{394, 126}, new float[]{708, 126}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createRoundRectangle(
					new float[] { 394, 62, 320, 30, 0 }, false, null));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{394, 46}, new float[]{708, 46}));
			if (i == 0)
				pdfdoc.newPage();
		}
		pdfdoc.saveToLocal();
	}
	public static void main(String[] args) throws IOException {
		FileOutputStream out = new FileOutputStream("一日体験受講券2.pdf");
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		generateSeperatePage(bout, null);
		
		PdfManipulator newpdf = new PdfManipulator(out,
				new ByteArrayInputStream(bout.toByteArray()), new float[] {
						728, 515.7f});
		newpdf.mergeTo(new int[]{2, 1});
		newpdf.saveDoc();
	}

}
