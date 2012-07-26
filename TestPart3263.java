package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *�����e�������ϼ�Ӌ 
 * @author Peike Dai
 *
 */
public class TestPart3263 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream("2011�����������һ�E.pdf");
		PdfDocument pdfdoc = new PdfDocument(out, true);
		
		PdfTitle title = new PdfTitle("2011�����������һ�E");
		pdfdoc.add(title);
		
		PdfTextBlock sign = new PdfTextBlock("�����e�������ϼ�Ӌ.tbl��" + new SimpleDateFormat("yyyy��MM��dd�� HH:mm").format(new Date()));
		sign.setFontSize(10f).setPosition(10, 2);
		pdfdoc.addAbsolutePosition(sign);
		
		PdfTable table = new PdfTable(15, new float[]{1.3f, 2.5f, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2.5f});
		String[] heads = {"���\n����", "���n��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��", "1��", "2��", "3��", "��Ӌ"};
		ArrayList<String> header = new ArrayList<String>();
		for (String e : heads)
			header.add(e);
		table.addHeader(header);
		table.setWidthPercentage(100f).setHeaderFontSize(10).setContentFontSize(10f).addContent(null, null, true);
		pdfdoc.add(table);
		
		pdfdoc.headerOrPagenumber(null, null, 0, 3);
		pdfdoc.saveToLocal();
	}

}
