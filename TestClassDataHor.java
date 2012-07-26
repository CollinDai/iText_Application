/**
 * 
 */
package pdfPrinter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Used for 1-2
 * @author Peike Dai
 *
 */
public class TestClassDataHor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean rotation = true;	// substitute by data
		PdfDocument pdfdoc = new PdfDocument(rotation);
		// title 
		PdfTitle pdftitle = new PdfTitle("���饹�ǩ`��");
		pdftitle.setFontSize(12.5f);
		pdfdoc.add(pdftitle);
		// pdf signiture
		PdfTextBlock pdfsign = new PdfTextBlock("���饹.TBL   "+(new SimpleDateFormat("yyyy�ꡡM��dd�ա� HH:mm").format(new Date())));
		pdfsign.setFontSize(9).setPosition(4, 2.5f);
		pdfdoc.addAbsolutePosition(pdfsign);
		// pdf page number
		pdfdoc.headerOrPagenumber(false, null, null, true, 3);
		// pdf table
		PdfTable pdftable = new PdfTable();
		String[] heads = {"���饹����", "���饹��", "�v������", "���", "HP�v����", "�v������", "����", "����", "�_ʼ�r�g", "�K�˕r�g", "����", "HP���v��", "HP�̲��M", "��Ʒд��ե�������"};
		List<String> header = new ArrayList<String>();
		for (String s : heads)
			header.add(s);
		pdftable.addHeader(header, new float[]{2.25f, 6, 2f, 6, 5, 2, 2, 1.1f, 2, 2, 1.1f, 2.5f, 2.5f, 2.5f});
		String[] properties = {"classCode", "className", "name", "position", "hpTeacherName", "code", "week", "startTime", "endTime", "cCost", "cHPCost", "mHPCost", "hpImage"};
		pdftable.addContent(null, properties);
		pdftable.setWidthPercentage(100).setHeaderFontSize(8);
		pdfdoc.add(pdftable);
		
		
		pdfdoc.saveToLocal();

	}

}
