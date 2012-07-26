/**
 * 
 */
package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * 体験名簿 孩子
 * header part is not repeatable
 * @author peike dai
 *
 */
public class TestClassExpList2 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream("体験名簿2.pdf");
		PdfDocument pdfdoc = new PdfDocument(out);
		PdfTable headtable = new PdfTable(9, new float[]{1.7f, 1f, 2.5f, 4.3f, 1.1f, 1.6f, 1.1f, 1.4f, 1.7f});
		// row one in picture
		headtable.addCell("一日体験\n講習会名簿", 80f, 15f, 11f, 5, 0, 2, 2).addCell("101", 15, 0, 10,
				4, 4, 0, 6).addCell("講師ヘの連絡日", 80f, 15f, 7f, 2, 0, 2, 0).addCell(
				"チテトヘヲ油絵入門（日）\n平成23年　6月23日（日）　14:00～16:00", 65, 20, 14, 2, 3, 0, 6);
		// row two
		headtable.addCell("内容", 60f, 0f, 1, 2, 3).addCell("持ち物", 15f, 0,
				10f, 1, 4, 0, 3).addCell("体験料\n\n教材費", 60f, 0, 10, 4, 2, 2, 0)
				.addCell("￥4,555\n\n￥2,333", 60f, 0, 10, 6, 1, 2, 0).addCell("連絡者", 60f,
						7f, 1, 2, 0).addCell("污水處理神馬玩意兒七上八十分艱苦", 45f, 0, 0, 4, 3, 0, 3);
		// row three
		headtable.addCell("本講座", 40f, 0, 5, 3, 0).addCell("日時", 40f, 0, 10, 4,
				2, 3, 0).addCell("2・4(日)14:00～16:00", 40f, 0, 11f, 5, 1, 2, 2).addCell("受講料", 40f, 0, 10, 4, 2, 2, 0).addCell("￥234,200", 40f, 0, 8f, 5, 1, 2, 0)
				.addCell("教材費", 40f, 0, 10f, 4, 2, 2, 0).addCell("￥655,240", 40f, 0, 8f, 5, 1, 2, 0).addCell("定員", 11f, 0, 7f, 1, 4, 0, 0).addCell("名", 29f, 0, 9, 9, 3, 0, 0);
		headtable.setWidthPercentage(100);
		pdfdoc.add(headtable);
		// main table
		PdfTable pdftable = new PdfTable(10, new float[]{0.5f, 1.2f, 3.5f, 2.5f, 2.2f, 1.2f, 1.5f, 1f, 1.1f, 1.7f});
		String[] heads = {"", "受付日", "氏　　名", "お電話番号", "年　齢", "受付印", "体験料\n教材費", "日付", "受領印", "備考"};
		ArrayList<String> header = new ArrayList<String>();
		for (String e : heads)
			header.add(e);
		pdftable.addHeader(header, 20f, 8f);
		// main table content
		String[] testobj = {"／", "", "", "　才　ヵ月", "", "￥　", "／", "", ""};
		int objnumber = 21;
		for (int i=0; i<objnumber; ++i) {
			for (int j=0; j<10; ++j) {
				if (j == 0)
					pdftable.addCell(Integer.toString(i+1), 27f, 5);
				else
					pdftable.addCell(testobj[j-1], 27f, 5);
			}
		}
		pdftable.setHeaderFontSize(8f).setWidthPercentage(100f).setMarginTop(0);
		pdfdoc.add(pdftable);
		pdfdoc.saveToLocal();
	}

}
