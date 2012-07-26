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
 * 受講料合計
 * @author peike dai
 *
 */
public class TestClassTuitionSum {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	private static void generateSeperatePage(ByteArrayOutputStream bout, List<Object> data) {
		PdfDocument pdfdoc = new PdfDocument(bout, false, new float[]{297.5f, 210.5f}, new float[]{18, 18, 18, 18});
		for (int i=0; i<8; ++i) {
			// class id
			PdfTextBlock classid = new PdfTextBlock("1092", 1);
			classid.setWidthPercentage(100).setMarginTop(0).setFontSize(8);
			pdfdoc.add(classid);
			// table
			PdfTable table = new PdfTable(4, new float[]{1.2f, 1.5f, 1, 1.5f});
			table.addCell("せてろりすどぼ 池坊", 35f, 12, 5, 0, 4);
			table.addCell("曜日 ・時間", 17f, 5).addCell("2.4(土)13:00～15:00", 17f, 10f, 5, 0, 3);
			table.addCell("受 講 料", 22f, 5).addCell("￥4,200", 22f, 6).addCell("×2.5ケ月", 22f, 5).addCell("￥10,500", 22f, 6);
			table.addCell("教 材 費", 22f, 5).addCell("￥0", 22f, 6).addCell("×3ケ月", 22f, 5).addCell("￥0", 22f, 6);
			table.addCell("運営維持費", 22f, 5).addCell("￥210", 22f, 6).addCell("×3ケ月", 22f, 5).addCell("￥630", 22f, 6);
			table.addCell("", 35f, 10f, 5, 0, 2).addCell("合計\n(税込)", 35f, 15f, 10f, 2, 0, 0, 0).addCell("", 35f, 6);
			table.setWidthPercentage(100f);
			pdfdoc.add(table);
			pdfdoc.newPage();
		}
		pdfdoc.saveToLocal();
	}
	public static void main(String[] args) throws IOException {
		FileOutputStream out = new FileOutputStream("受講料合計.pdf");
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		generateSeperatePage(bout, null);
		PdfManipulator newpdf = new PdfManipulator(out,
				new ByteArrayInputStream(bout.toByteArray()), new float[] {
						595, 842 });
		newpdf.mergeTo(new int[]{4, 2});
		newpdf.saveDoc();
	}

}
