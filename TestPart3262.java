package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * ���n�e��������2
 * ���_��������׵ľ�̖�Ƿ�Ҫ
 * ���_���Ƿ��п�Ӌ�����СӋ
 * @author Peike
 *
 */
public class TestPart3262 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream("���n�e��������2.pdf");
		PdfDocument pdfdoc = new PdfDocument(out);
		
		PdfTitle title = new PdfTitle("����n�e��������");
		title.setFontSize(10f);
		pdfdoc.add(title);
		
		PdfTextBlock sign = new PdfTextBlock("JASRAC��.tbl��" + new SimpleDateFormat("yyyy��MM��dd�� HH:mm").format(new Date()));
		sign.setFontSize(10f).setPosition(10, 2);
		pdfdoc.addAbsolutePosition(sign);
		
		PdfTable table = new PdfTable(7, new float[]{1, 3, 2, 2, 3, 2.5f, 2.5f});
		String[] heads = {"���\n����", "���n��", "���饹��", "�����ڼ�", "���v�Ϻ�Ӌ", "��������", "��������"};
		ArrayList<String> header = new ArrayList<String>();
		for (String e : heads)
			header.add(e);
		table.addHeader(header);
		table.addContent(null, null, true);
		table.setHeaderFontSize(10).setContentFontSize(10);
		pdfdoc.add(table);
		
		pdfdoc.headerOrPagenumber(null, null, 0, 3);
		pdfdoc.saveToLocal();
	}

}
