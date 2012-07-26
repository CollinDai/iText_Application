package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 出席簿 2-2-8
 * empty attendance record
 * @author peike dai
 *
 */
public class TestClassAttendList8 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream out = new FileOutputStream("出席簿8.pdf");
		PdfDocument pdfdoc = new PdfDocument(out,
				new float[] { 36, 36, 36, 18 }, true);
		PdfTitle nulltitle = new PdfTitle(" ");
		pdfdoc.add(nulltitle);

		PdfTextBlock pdftit = new PdfTextBlock("出 席 簿", 0, 1);
		pdftit.setFontSize(25);
		pdftit.setPosition(5, 4);
		pdfdoc.addAbsolutePosition(pdftit);

		// class number and name
		PdfTextBlock cls = new PdfTextBlock("クラス");
		cls.setFontSize(11).setUnderlined().setPosition(5, 9);
		pdfdoc.addAbsolutePosition(cls);
		PdfTextBlock clsnum = new PdfTextBlock("　　　　　");
		clsnum.setFontSize(11).setUnderlined().setPosition(11f, 9);
		pdfdoc.addAbsolutePosition(clsnum);
		PdfTextBlock clsname = new PdfTextBlock("　　　　　　　　　　　　　　　　　　　　");
		clsname.setFontSize(11).setUnderlined().setPosition(25, 9);
		pdfdoc.addAbsolutePosition(clsname);

		PdfTextBlock dayperweek = new PdfTextBlock("曜日");
		dayperweek.setFontSize(11).setPosition(54, 9);
		pdfdoc.addAbsolutePosition(dayperweek);
		PdfTextBlock day = new PdfTextBlock("　　　　　　　　　　　　　　　");
		day.setFontSize(11).setUnderlined().setPosition(70, 9);
		pdfdoc.addAbsolutePosition(day);

		PdfTextBlock clsroom = new PdfTextBlock("　　　教室");
		clsroom.setFontSize(11).setUnderlined().setPosition(86, 9);
		pdfdoc.addAbsolutePosition(clsroom);

		PdfTextBlock teacher = new PdfTextBlock("講師名");
		teacher.setFontSize(11).setUnderlined().setPosition(5, 13.5f);
		pdfdoc.addAbsolutePosition(teacher);
		PdfTextBlock tchrnum = new PdfTextBlock("　　　　　");
		tchrnum.setFontSize(11).setUnderlined().setPosition(11f, 13.5f);
		pdfdoc.addAbsolutePosition(tchrnum);

		PdfTextBlock tchrname = new PdfTextBlock("　　　　　　　　　　　　　　　　　　　　");
		tchrname.setFontSize(11).setUnderlined().setPosition(25, 13.5f);
		pdfdoc.addAbsolutePosition(tchrname);
		
		
		PdfTextBlock time = new PdfTextBlock("時間");
		time.setFontSize(11).setPosition(54, 13.5f);
		pdfdoc.addAbsolutePosition(time);

		PdfTextBlock duration = new PdfTextBlock("　　　　　　　～　　　　　　　");
		duration.setFontSize(11).setUnderlined().setPosition(70, 13.5f);
		pdfdoc.addAbsolutePosition(duration);
		
		// 月	月	月	月	月
		PdfTextBlock monthchar = new PdfTextBlock("月");
		monthchar.setFontSize(11);
		for (float i=0, j=27.4f; i<5; ++i) {
			monthchar.setPosition(j+=10.6f, 18);
			pdfdoc.addAbsolutePosition(monthchar);
		}
		// set header part repeat
		PdfElement[] _text = { pdftit, cls, clsnum, clsname,
				dayperweek, day, clsroom, teacher, tchrnum, tchrname, time, duration, monthchar, monthchar, monthchar, monthchar, monthchar};
		pdfdoc.headerOrPagenumber(_text, new float[][] { { 10, 7 }, { 7, 11.5f },
				{ 14, 11.5f }, { 32, 11.5f }, { 54.3f, 11.5f }, { 66.3f, 11.5f },
				{ 84, 11.5f }, { 7, 16 }, { 14, 16 }, {32, 16}, { 54.3f, 16 },
				{ 66.3f, 16 }, { 38.5f, 20.5f }, { 49, 20.5f },
				{ 59.4f, 20.5f }, { 70, 20.5f }, { 80.6f, 20.5f } }, 72f);
		// add table

		PdfTable pdftable = new PdfTable(24, new float[] { 1.3f, 2.2f, 5.3f,
				0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f,
				0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 3 });
		String[] heads = { "No.", "会員番号", "氏　　名", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "有効年月" };
		List<String> header = new ArrayList<String>();
		for (String e : heads)
			header.add(e);
		pdftable.addHeader(header, 21.25f);
		// add footer rows
		pdftable.addCell("", 21.25f, 5, false).addCell("", 21.25f, 5, false)
				.addCell("", 21.25f, 5, false);
		for (int i = 0; i < 5; ++i)
			pdftable.addCell("在籍", 21.25f, true, 2, 2, 6, 1).addCell("新",
					21.25f, 1, true, 5).addCell("退", 21.25f, 1, true, 5);
		pdftable.addCell("", 21.25f, 5, false).addCell("", 21.25f, 5, false)
				.addCell("", 21.25f, 5, false).addCell("", 21.25f, 5, false);
		for (int i = 0; i < 10; ++i)
			pdftable.addCell("", 21.25f, 5, true);
		pdftable.addCell("", 21.25f, 5, false);
		pdftable.setFooter(2);
		// add table contents
		pdftable.setColumnAlignment(new int[] { 5, 5, 5, 5, 5, 1, 1, 5, 5, 1,
				1, 5, 5, 1, 1, 5, 5, 1, 1, 5, 5, 1, 1, 4 });

		int objnumber = 25;

		String[] testobject = { "1284", "劉德華", "平成23年15月" };
		for (int i = 0; i < objnumber; ++i) {
			for (int j = 0; j < 24; ++j) {
				if (j == 0)
					pdftable.addCell(Integer.toString(i + 1), 21.25f, 5);
				else if (j == 23)
					pdftable.addCell(testobject[2], 21.25f, 4);
				else if (j > 2 && j < 23)
					pdftable.addCell("", 14f, 5);
				else
					pdftable.addCell(testobject[j - 1], 21.25f, 5);
			}
		}
		int leftrows = 18 - objnumber % 18;
		for (int i = 0; i < leftrows; ++i)
			for (int j = 0; j < 24; ++j)
				pdftable.addCell("", 21.25f, 5, true);

		pdftable.setMarginTop(72f).setHeaderFontSize(9).setContentFontSize(9)
				.setWidthPercentage(100);
		pdfdoc.add(pdftable);

		pdfdoc.saveToLocal();
	}
}