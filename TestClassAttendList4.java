package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 出席簿 2-2-1
 * @author peike dai
 *
 */
public class TestClassAttendList4 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream out = new FileOutputStream("出席簿4.pdf");
		PdfDocument pdfdoc = new PdfDocument(out,
				new float[] { 36, 36, 36, 18 }, true);
		PdfTitle nulltitle = new PdfTitle(" ");
		pdfdoc.add(nulltitle);

		PdfTextBlock pdftit = new PdfTextBlock("出 席 簿", 0, 1);
		pdftit.setFontSize(25);
		pdftit.setPosition(5, 4);
		pdfdoc.addAbsolutePosition(pdftit);
		// roll
		PdfTextBlock roll = new PdfTextBlock("ROLL");
		roll.setFontSize(11).setPosition(20, 4);
		pdfdoc.addAbsolutePosition(roll);
		// pdf signiture
		PdfTextBlock pdfsign = new PdfTextBlock("印刷");
		pdfsign.setFontSize(11).setPosition(80, 4);
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
		clsroom.setFontSize(11).setPosition(86, 9);
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
		// 月	月	月	月
		PdfTextBlock monthchar = new PdfTextBlock("月");
		monthchar.setFontSize(11);
		for (float i=0, j=38f; i<4; ++i) {
			monthchar.setPosition(j+=10.6f, 18);
			pdfdoc.addAbsolutePosition(monthchar);
		}
		// set header part repeat
		PdfElement[] _text = { pdftit, roll, pdfsign, cls, clsnum, clsname,
				dayperweek, day, clsroom, teacher, tchrnum, time, duration, monthchar, monthchar, monthchar, monthchar};
		pdfdoc.headerOrPagenumber(_text, new float[][] { { 10, 7 }, {21.5f, 7},
				{ 79.5f, 6f }, { 7, 12 }, { 12.5f, 12 }, { 19.5f, 12 },
				{ 59.5f, 12 }, { 68.5f, 12 }, { 85, 12 }, { 7, 16 },
				{ 16, 16 }, { 59.5f, 16 }, { 68.5f, 16 }, 
				{ 49.1f, 20.5f }, { 59.7f, 20.5f }, { 70.3f, 20.5f }, { 80.9f, 20.5f } },
				72f);
		// add table

		PdfTable pdftable = new PdfTable(21, new float[] { 0.9f, 1.8f, 4.3f,
				5.2f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f,
				0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 3 });
		String[] heads = { "No.", "会員番号", "氏　　名", "N　A　M　E", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "有効年月" };
		List<String> header = new ArrayList<String>();
		for (String e : heads)
			header.add(e);
		pdftable.addHeader(header, 21.25f);
		// add footer rows
		pdftable.addCell("", 21.25f, 5, false).addCell("", 21.25f, 5, false)
				.addCell("", 21.25f, 5, false).addCell("", 21.25f, 5, false);
		for (int i = 0; i < 4; ++i)
			pdftable.addCell("在籍", 21.25f, true, 2, 2, 6, 1).addCell("新",
					21.25f, 1, true, 5).addCell("退", 21.25f, 1, true, 5);
		for (int i = 0; i < 5; ++i)
			pdftable.addCell("", 21.25f, 5, false);
		for (int i = 0; i < 8; ++i)
			pdftable.addCell("", 21.25f, 5, true);
		pdftable.addCell("", 21.25f, 5, false);
		pdftable.setFooter(2);
		// add table contents
		pdftable.setColumnAlignment(new int[] { 5, 5, 5, 5, 5, 5, 1,
				1, 5, 5, 1, 1, 5, 5, 1, 1, 5, 5, 1, 1, 4 });

		int objnumber = 25;

		String[] testobject = { "1284", "劉德華" ,"Kaoru kojima", "平成23年15月"};
		for (int i = 0; i < objnumber; ++i) {
			for (int j = 0; j < 21; ++j) {
				if (j == 0)
					pdftable.addCell(Integer.toString(i + 1), 21.25f, 5);
				else if (j == 20)
					pdftable.addCell(testobject[3], 21.25f, 4);
				else if (j > 3 && j < 20)
					pdftable.addCell("", 14f, 5);
				else
					pdftable.addCell(testobject[j - 1], 21.25f, 5);
			}
		}
		int leftrows = 18 - objnumber % 18;
		for (int i = 0; i < leftrows; ++i)
			for (int j = 0; j < 21; ++j)
				pdftable.addCell("", 21.25f, 5, true);

		pdftable.setMarginTop(72f).setHeaderFontSize(9).setContentFontSize(9)
				.setWidthPercentage(100);
		pdfdoc.add(pdftable);

		pdfdoc.saveToLocal();
	}
}