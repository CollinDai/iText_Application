/**
 * 
 */
package pdfPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 手配リチラシ
 * @author peike dai		
 *
 */
public class TestClassLeafLets {

	/**
	 * @param args
	 */
	private static String newString(String text) {
		char[] stringchar = text.toCharArray();
		String finalstring = "";
		if (text.length() >= 5) {
			for (char c : stringchar)
				finalstring += c + " ";
		} else {
			for (char c : stringchar)
				finalstring += c + "　";
		}
		return finalstring;
			
	}
	private static void generateSeperatePage(ByteArrayOutputStream bout,
			List<Object> data) {
		PdfDocument pdfdoc = new PdfDocument(bout, false, new float[] { 421,
				595 }, new float[] { 18, 18, 36, 0 });
		for (int i = 0; i < 2; ++i) {
			// title
			PdfTextBlock minortitle = new PdfTextBlock("自由な画材で描く", -1);
			minortitle.setFontSize(16).setWidthPercentage(90f).adaptPaper(421);
			pdfdoc.add(minortitle);
			String testtitle = newString("基礎デッと絵画");
			PdfTitle title = new PdfTitle(testtitle);
			title.setFontSize(30).setMarginTop(40).adaptPaper(421);
			pdfdoc.add(title);
			// table
			PdfTable table1 = new PdfTable(2, new float[] { 1, 4.5f });
			table1.addCell("講師　（社）創元会運営委員審查員", 20f, false, 0, 2, 11f, 4);
			table1.addCell("前田　潤", 35f, false, 0, 2, 16f, 5);
			table1.addCell("フマムカタラグてずざるつむじぐそち゛びぐちとむでざるつむじぐそち゛びぐちとむでざるつむじぐそち゛びぐちとむでざるつむじぐそち゛びぐちとむで", 90f, false, 0, 2, 12f, 4);
			table1.addCell("日　時", 28f, 4, false, 16f).addCell("１・３（土）１３：００～１５：３０", 28f, 4, false, 16f);
			table1.addCell("受講料", 21, 4, false, 14f).addCell("￥４，７５３", 21, 4, false, 14f);
			table1.addCell("教材費", 21, 4, false, 14f).addCell("￥４，７５３", 21, 4, false, 14f);
			table1.setWidthPercentage(90f).setMarginTop(40);
			pdfdoc.add(table1);
			// seperator
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{20, 465}, new float[]{400, 465}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{20, 405}, new float[]{400, 405}));
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createLine(new float[]{20, 315}, new float[]{400, 315}));
			// image
			PdfImage image = new PdfImage("paintboard.jpg");
			image.setScale(40).setPosition(50, 75);
			pdfdoc.addAbsolutePosition(image);
			// ellipse
			pdfdoc.addAbsolutePosition(PdfWidgetFactory.createRoundRectangle(new float[]{30, 60, 360, 50, 15}, false, null));
			// bottom tree
			PdfImage treeimage = new PdfImage("culturetree.jpg");
			treeimage.setScale(40).setPosition(8, 98);
			pdfdoc.addAbsolutePosition(treeimage);
			// bottom text block
			PdfTable joinandguide = new PdfTable(1);
			joinandguide.addCell("入会と受講のご案内", 16f, 5, false);
			joinandguide.setContentFontSize(11.5f).setMarginTop(120).setWidthPercentage(35);
			pdfdoc.add(joinandguide);
			PdfTextBlock texts = new PdfTextBlock("入金会は5250円（永久会員）。何科目でも受講できます。\n受講料は自動振替を利用していただきます。ご利用の銀行届け出印と通帳\nの口座番号をお持ち下さい。講座によって教材費のかかるものもあります。\n　尚、別途運営維持費２１０円がかかります。", 10f, -1);
			texts.setFontSize(10).setWidthPercentage(90f);
			pdfdoc.add(texts);
			PdfTextBlock advertise = new PdfTextBlock("●一流の講師とせげてみみ仲間", -1);
			advertise.setFontSize(6.5f).setWidthPercentage(62).setMarginTop(0);
			pdfdoc.add(advertise);
			// shop name
			PdfTextBlock shopname = new PdfTextBlock("相模大野レトチセルメネン");
			shopname.setFontSize(22).setWidthPercentage(100).adaptPaper(421);
			pdfdoc.add(shopname);
			// shop info
			PdfTextBlock shopinfo = new PdfTextBlock("女子大通レトチセルメ2F　TEL042-523-7830　http://culture.gr.jp");
			shopinfo.setFontSize(8).adaptPaper(421).setPosition(44, 97.4f);
			pdfdoc.addAbsolutePosition(shopinfo);
			if (i==0)
				pdfdoc.newPage();
		}
		pdfdoc.saveToLocal();
		
	}
	public static void main(String[] args) throws IOException {
		FileOutputStream out = new FileOutputStream("手配リチラシ.pdf");
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		generateSeperatePage(bout, null);
		
		PdfManipulator newpdf = new PdfManipulator(out,
				new ByteArrayInputStream(bout.toByteArray()), new float[] {
						842, 595});
		newpdf.mergeTo(new int[]{1, 2});
		newpdf.saveDoc();
	}
}
