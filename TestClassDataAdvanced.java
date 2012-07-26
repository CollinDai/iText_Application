package pdfPrinter;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ǰ�ܽ����~�_�J
 * @author Peike Dai
 *
 */
public class TestClassDataAdvanced {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		PdfDocument pdfdoc = new PdfDocument();
		// title 
		PdfTitle pdftitle = new PdfTitle("��ģ��Ұ�ꡡǰ�����~�_�J");
		pdftitle.setFontSize(12.5f);
		pdfdoc.add(pdftitle);
		// pdf signiture
		PdfTextBlock pdfsign = new PdfTextBlock("���饹.TBL   "+(new SimpleDateFormat("yyyy�ꡡM��dd�� HH:mm").format(new Date())));
		pdfsign.setFontSize(7).setPosition(6.5f, 1);
		pdfdoc.addAbsolutePosition(pdfsign);
		// pdf page number
		pdfdoc.headerOrPagenumber(false, null, null, true, 3);
		// pdf table
		PdfTable pdftable = new PdfTable(10);
		String[] heads = { "���饹����", "���饹��", "���v�υg��", "���v�����~", "�ȣ����v��",
				"�̲��M�g��", "�̲��M���~", "�ȣн̲��M", "�������g��", "�\�ӾS���M�g��" };
		List<String> header = new ArrayList<String>();
		for (String s : heads)
			header.add(s);
		float[] widthpct = new float[] { 1.4f, 5.3f, 2f, 2, 2.5f, 2, 2, 2.5f, 1.4f, 1.9f };
//		System.out.print(widthpct.length);
		pdftable.addHeader(header, widthpct);
		String[] properties = { "classCode", "className",
				"cCost", "cMonthCost", "cHPCost", "mCost",
				"mMonthCost", "mHPCost", "oCost", "kCost" };
//		System.out.print(properties.length);
		pdftable.addContent(null, properties);
		pdftable.setWidthPercentage(100).setHeaderFontSize(7).setContentFontSize(7);
		pdfdoc.add(pdftable);
		
		
		pdfdoc.saveToLocal();
	}

}
