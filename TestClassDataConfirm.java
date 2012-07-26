package pdfPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ���d�_�J�å��饹�ǩ`��
 * @author Peike Dai
 *
 */
public class TestClassDataConfirm {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream fileoutput = new FileOutputStream("���d�_�J�å��饹�ǩ`��.pdf");
		PdfDocument pdfdoc = new PdfDocument(fileoutput, new float[]{1031.94f, 728.6f});
		// title 
		PdfTitle pdftitle = new PdfTitle("�V�ڵꡡ�����ȣВ��d�_�J�å��饹�ǩ`������");
		pdftitle.setFontSize(12.5f);
		pdfdoc.add(pdftitle);
		// pdf signiture
		PdfTextBlock pdfsign = new PdfTextBlock("���饹.TBL   "+(new SimpleDateFormat("yyyy�ꡡM��dd�ա� HH:mm").format(new Date())));
		pdfsign.setFontSize(7).setPosition(2, 1);
		pdfdoc.addAbsolutePosition(pdfsign);
		// pdf page number
		pdfdoc.headerOrPagenumber(false, null, null, true, 3);
		// pdf table
		PdfTable pdftable = new PdfTable(16, 1000f);
		String[] heads = { "���饹����", "���֥����ȥ�", "���饹��", "�Ḷ���֥����ȥ�", "���`����", "���",
				"HP�v����", "����", "����", "�_ʼ�r�g", "�K�˕r�g", "HP���v��", "HP�̲��M",
				"HP������", "���饹д��ե�������", "HP���d" };
		List<String> header = new ArrayList<String>();
		for (String s : heads)
			header.add(s);
		float[] widthpct = new float[] { 0.9f, 2.9f, 4.5f, 2.4f, 2.4f, 2.7f, 3.4f, 1.3f, 0.7f, 1.2f, 1.2f, 1.5f, 1.5f, 6.3f, 1.4f, 0.7f};
//		System.out.print(widthpct.length);
		pdftable.addHeader(header, widthpct, 22);
		String[] properties = { "classCode", "subTitle", "className",
				"hpTeacherName", "courseName", "position", "hpTeacherName",
				"times", "week", "startTime", "endTime", "cCost", "mCost",
				"comment", "hpImage", "hpPublishFlag" };
//		System.out.print(properties.length);
		pdftable.addContent(null, properties);
		pdftable.setWidthPercentage(100).setHeaderFontSize(7).setContentFontSize(7);
		pdfdoc.add(pdftable);
		
		
		pdfdoc.saveToLocal();

	}

}
