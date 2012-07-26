/**
 * 
 */
package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 受講生名簿 2-2-6
 * @author Peike Dai
 *
 */
public class TestClassAttendList6 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream out = new FileOutputStream("受講生名簿.pdf");
		PdfDocument pdfdoc = new PdfDocument(out,
				new float[] { 36, 36, 36, 18 }, true);
		PdfTitle nulltitle = new PdfTitle(" ");
		pdfdoc.add(nulltitle);

		PdfTextBlock pdftit = new PdfTextBlock("受 講 生 名 簿", 0, 1);
		pdftit.setFontSize(25);
		pdftit.setPosition(50, 4);
		pdfdoc.addAbsolutePosition(pdftit);

		// pdf signiture
		PdfTextBlock pdfsign = new PdfTextBlock("印刷");
		pdfsign.setFontSize(12).setPosition(80, 4);
		pdfdoc.addAbsolutePosition(pdfsign);

		// class number and name
		PdfTextBlock cls = new PdfTextBlock("クラス");
		cls.setFontSize(11).setUnderlined().setPosition(5, 9);
		pdfdoc.addAbsolutePosition(cls);
		PdfTextBlock clsnum = new PdfTextBlock("101");
		clsnum.setFontSize(11).setUnderlined().setPosition(11.5f, 9);
		pdfdoc.addAbsolutePosition(clsnum);
		PdfTextBlock clsname = new PdfTextBlock("油畫繪畫入門");
		clsname.setFontSize(11).setUnderlined().setPosition(17, 9);
		pdfdoc.addAbsolutePosition(clsname);

		PdfTextBlock dayperweek = new PdfTextBlock("曜日");
		dayperweek.setFontSize(11).setUnderlined().setPosition(59, 9);
		pdfdoc.addAbsolutePosition(dayperweek);
		PdfTextBlock day = new PdfTextBlock("２・４　（日）");
		day.setFontSize(11).setPosition(70, 9);
		pdfdoc.addAbsolutePosition(day);

		PdfTextBlock clsroom = new PdfTextBlock("Ａ　教室");
		clsroom.setFontSize(11).setPosition(80, 13.5f);
		pdfdoc.addAbsolutePosition(clsroom);

		PdfTextBlock teacher = new PdfTextBlock("講師名");
		teacher.setFontSize(11).setUnderlined().setPosition(5, 13.5f);
		pdfdoc.addAbsolutePosition(teacher);
		PdfTextBlock tchrnum = new PdfTextBlock("14242　浅野　信二");
		tchrnum.setFontSize(11).setPosition(11f, 13.5f);
		pdfdoc.addAbsolutePosition(tchrnum);

		PdfTextBlock time = new PdfTextBlock("時間");
		time.setUnderlined().setFontSize(11).setPosition(59, 13.5f);
		pdfdoc.addAbsolutePosition(time);

		PdfTextBlock duration = new PdfTextBlock("14:00～16:00");
		duration.setFontSize(11).setPosition(69, 13.5f);
		pdfdoc.addAbsolutePosition(duration);
		
		// set header part repeat
		PdfElement[] _text = { pdftit, pdfsign, cls, clsnum, clsname,
				dayperweek, day, clsroom, teacher, tchrnum, time, duration };
		pdfdoc.headerOrPagenumber(_text, new float[][] { { 50, 7 },
				{ 79.5f, 6f }, { 7.25f, 11.75f  }, { 12.5f, 11.75f   }, { 19.85f, 11.75f   },
				{ 59.5f, 11.75f  }, { 68.5f, 11.75f  }, { 80, 16 }, { 7, 16 },
				{ 16, 16 }, { 59.5f, 16 }, { 68.5f, 16 } }, 72f);
		// add table

		PdfTable pdftable = new PdfTable(9, new float[] { 0.8f, 1.6f, 5,
				9, 2.4f, 2.4f, 3.5f, 2, 2f });
		String[] heads = { "No.", "会員番号", "氏　　名", "住	　所", "電話番号", "携帯番号", "メール",
				"有効年月", "在籍状態" };
		List<String> header = new ArrayList<String>();
		for (String e : heads)
			header.add(e);
		pdftable.addHeader(header, 21.25f);
		// add table contents
		int[] align = new int[] { 5, 5, 5, 4, 5, 5, 5, 5, 5 };

		int objnumber = 25;

		String[] testobject = { "1284", "劉德華", "661-0952 兵庫尼奇市東元田町　9-5-13-501", "06-6498-6832", "", "", "平成24年 2月", "在籍" };
		for (int i = 0; i < objnumber; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (j == 0)
					pdftable.addCell(Integer.toString(i+1), 21.25f, align[j]);
				else
					pdftable.addCell(testobject[j-1], 21.25f, align[j]);
			}
		}
		int leftrows = 20 - (objnumber % 20);
		for (int i = 0; i < leftrows; ++i)
			for (int j = 0; j < 9; ++j)
				pdftable.addCell("", 21.25f, 5, true);

		pdftable.setMarginTop(72f).setHeaderFontSize(9).setContentFontSize(9)
				.setWidthPercentage(100);
		pdfdoc.add(pdftable);

		pdfdoc.saveToLocal();
	}
}
 