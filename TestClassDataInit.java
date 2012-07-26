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
 * 初回持ち物
 * @author Col
 *
 */
public class TestClassDataInit {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
	private static void generateSeperatePage(ByteArrayOutputStream bout, List<Object> data) {
		PdfDocument pdfdoc = new PdfDocument(bout, false, new float[]{297.5f, 421}, new float[]{18, 18, 45, 18});
		for (int i = 0; i < 4; ++i) {
			// pdf title
			PdfTextBlock pdftitle = new PdfTextBlock("●初回持ち物");
			pdftitle.setPosition(8f, 5.5f);
			pdfdoc.addAbsolutePosition(pdftitle);
			// pdf number
			PdfTextBlock pdfnum = new PdfTextBlock("125");
			pdfnum.setFontSize(8).setPosition(90, 5.5f);
			pdfdoc.addAbsolutePosition(pdfnum);
			// main table
			PdfTable pdftable = new PdfTable(2, new float[] { 1.2f, 9.5f });
			pdftable.addCell("講座名", 45f, 8f, 5, 2, 0).addCell("俳画", 0, 22.5f, 4, 0, 0, 5)
					.addCell("第1(木)10:30～12:00", 0, 17.5f, 3, 0, 0, 5);
			pdftable
					.addCell(
							"硯、\n文鎮、\n下敷(フエルトの樣なもの)、\n水の吸の取の布巾、\n筆洗の(の樣什麼讀不懂)、顏彩、\n墨、\n筆（大・中）。",
							180, 20f, 0, 1, 4, 0, 2).addCell("以上の持神馬嘰裏呱啦講師用意の麼麼", 0,
							50, 3, 0, 2, 5);
			pdftable.addCell("嘰裏呱啦稀里嘩啦劈裏啪啦轟！", 0, 20, 4, 0, 2, 5).addCell(
					"相模大野店神馬的\nTel.042-746-9611", 0, 40, 3, 0, 2, 6);
			pdftable.setWidthPercentage(100);
			pdfdoc.add(pdftable);
			pdfdoc.newPage();
		}
		pdfdoc.saveToLocal();
	}
	
	public static void main(String[] args) throws IOException {
		FileOutputStream out = new FileOutputStream("初回持ち物.pdf");
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		generateSeperatePage(bout, null);
		
		PdfManipulator newpdf = new PdfManipulator(out,
				new ByteArrayInputStream(bout.toByteArray()), new float[] {
						595, 842 });
		newpdf.mergeTo(new int[]{2, 2});
		newpdf.saveDoc();
	}
}
