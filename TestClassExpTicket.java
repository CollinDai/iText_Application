/**
 * 
 */
package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * 体験受講券
 * @author Col
 *
 */
public class TestClassExpTicket {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream("体験受講券.pdf");
		PdfDocument pdfdoc = new PdfDocument(out, false, new float[]{515.7f, 728f}, new float[]{18, 18, 18, 18});
		
		// title
		PdfTitle pdftit = new PdfTitle("一日体験講習会　（音楽コース）　受講券");
		pdftit.setFontSize(14);
		pdfdoc.add(pdftit);
		
		// table 1
		PdfTable table1 = new PdfTable(7, new float[]{1.1f, 5.8f, 3, 3, 2, 6.1f, 3.3f});
		String[] heads = {"No.", "クラス名", "体験日", "時間", "体験料\n教材費", "持ち物・服装・その他", "受講料\n教材費"};
		ArrayList<String> header = new ArrayList<String>();
		for (String e : heads)
			header.add(e);
		table1.addHeader(header);
		ArrayList<Object> nullobj = new ArrayList<Object>();
		int mod;
		if (!nullobj.isEmpty()) {
			table1.addContent(null, null, false);
			mod = nullobj.size() % 12;
			for (int i=0; i<mod*7; ++i)
				table1.addCell("　", 32f, 5);
		} else 
			for (int i=0; i<84; ++i)
				table1.addCell("　", 32f, 5);
		table1.setHeaderFontSize(9).setWidthPercentage(100);
		pdfdoc.add(table1);
		////////////////////////////////
		// add contents
		// ? if it's 12 rows fixed
		///////////////////////////////
		// round rectangle
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createRoundRectangle(new float[]{19, 220, 240, 50, 15}, false, null));
		// お名前
		PdfTextBlock yourname = new PdfTextBlock("お名前　　　　　　　　　　　　　　様", -1);
		yourname.setFontSize(12).setPosition(8, 65);
		pdfdoc.addAbsolutePosition(yourname);
		// dashed separator
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{19, 245}, new float[]{259, 245}));
		//  ご住所
		PdfTextBlock address = new PdfTextBlock("ご住所");
		address.setFontSize(12).setPosition(5, 68);
		pdfdoc.addAbsolutePosition(address);
		// 受付 日付
		PdfTextBlock reception = new PdfTextBlock("受付日付　　　　　　受領印　");
		reception.setUnderlined().setFontSize(8).setPosition(65, 64);
		pdfdoc.addAbsolutePosition(reception);
		PdfTextBlock emptyline = new PdfTextBlock("　　　　　　　　　　　　　　");
		emptyline.setUnderlined().setFontSize(8).setPosition(65, 68);
		pdfdoc.addAbsolutePosition(emptyline);
		// ellipse 
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createEllipse(new float[]{385, 210}, new float[]{497, 270}, true, new float[]{1,2}));
		// 当日、この券を受付にお持ち下さい
		PdfTextBlock circle = new PdfTextBlock("当日、この券を\n受付にお持ち下さい");
		circle.setFontSize(9.5f).setPosition(100, 67);
		pdfdoc.addAbsolutePosition(circle);
		//《入会と受講のご案内》
		PdfTextBlock guide = new PdfTextBlock("《入会と受講のご案内》");
		guide.setFontSize(10).setPosition(5, 71);
		pdfdoc.addAbsolutePosition(guide);
		PdfTextBlock guidetext = new PdfTextBlock("入会金は、￥５，２５０（家族会員は半額）です。\n当日はなるべくご用意してお越し下さい。", 8f, -1);
		guidetext.setFontSize(8).setPosition(40, 74);
		pdfdoc.addAbsolutePosition(guidetext);
		//お願い
		PdfTextBlock pls = new PdfTextBlock("お願い ：");
		pls.setFontSize(10).setPosition(5, 76.5f);
		pdfdoc.addAbsolutePosition(pls);
		PdfTable plstxt = new PdfTable(1);
		plstxt.addCell("納入後の参加費、材料費は、原則としてお返しできませんので\nご了承下さい。原則としてお子様のご同伴はご遠慮下さい。", 25f, 4, false);	
		plstxt.setContentFontSize(8).setWidthPercentage(50).setPosition(26, 75f);
		pdfdoc.addAbsolutePosition(plstxt);
		//shop name
		PdfTextBlock shopname= new PdfTextBlock("相模大野ホチニヒエキセスラ");
		shopname.setPosition(95, 74);
		pdfdoc.addAbsolutePosition(shopname);
		// telephone number
		PdfTextBlock tel= new PdfTextBlock("Tel.042-462-8732");
		tel.setPosition(95, 77f);
		pdfdoc.addAbsolutePosition(tel);
		// dotted separator
		PdfTextBlock separator = new PdfTextBlock(
				"－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－"
				+ "切　り　取　り" +
				"－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
		separator.setFontSize(6).setPosition(50, 80);
		pdfdoc.addAbsolutePosition(separator);
		// 受付 日付
		PdfTextBlock recept = new PdfTextBlock("　受付 日付　　　・　　　・　　");
		recept.setFontSize(8).setUnderlined().setPosition(90, 83);
		pdfdoc.addAbsolutePosition(recept);
		// bottom table
		PdfTable table2 = new PdfTable(3, new float[]{1.3f,1,1.3f});
		table2.addCell("フリガナ", 12, 0, 8, 0, 4, 0, 0)
				.addCell("T E L", 42f, true, 2, 0)
				.addCell("（児童のみ）生年月日", 16.8f, 0, 10, 0, 4, 0, 0)
				.addCell("お名前", 30, 0, 10, 0, 3, 0, 0)
				.addCell("年　　月　　日 年齢　　オ", 25.2f, 0, 10, 6, 3, 0, 0)
				.addCell("講座名", 35f, 10, 0, 2, 0)
				.addCell("体験時日", 35f, 10, 0, 2, 0)
				.addCell("体験費・材料費の受領　　　受領日　　　係印", 15f, 0, 8, 0, 4, 0, 0)
				.addCell("￥　　　　　　　　　　／", 20f, 0, 10, 0, 3, 0, 0);
		table2.setWidthPercentage(100).setMarginTop(170);
		pdfdoc.add(table2);
		pdfdoc.addAbsolutePosition(PdfWidgetFactory.createDashedLine(new float[]{53, 92}, new float[]{180, 92}));
		pdfdoc.saveToLocal();
		
	}

}
