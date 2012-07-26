package pdfPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Used for 月报，two in one pdf
 * @author Peike Dai
 *
 */
public class TestMain {

	/**
	 * @param args
	 * @return 
	 * @throws FileNotFoundException 
	 */
	static void generatePdf(OutputStream out, String title, List<List<TestData2>> data){		
		PdfDocument pdfdoc = new PdfDocument(out, new float[]{841.5f, 297.5f});
		
		for (List<TestData2> listdata : data){
			// pdf signiture
			PdfTextBlock pdfsign = new PdfTextBlock("前受業計");
			pdfsign.setFontSize(10).setPosition(3, 5);
			pdfdoc.addAbsolutePosition(pdfsign);
			// current date and time
			PdfTextBlock curdatetime = new PdfTextBlock(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			curdatetime.setFontSize(10).setPosition(12, 5);
			pdfdoc.addAbsolutePosition(curdatetime);
			//title
			PdfTitle pdftitle = new PdfTitle(title);
			pdftitle.setMarginTop(45).setFontSize(18);
			pdfdoc.add(pdftitle);
			// pdf table
			PdfTable pdftable = new PdfTable(15, new float[] { 1.4f, 1.1f,
					1.9f, 1.9f, 1.9f, 1.9f, 1.9f, 1.9f, 1.9f, 1.9f, 1.9f, 1.9f,
					1.9f, 1.9f, 1.9f });
			String[] properties = { "head", "outlecture", "outmaterial",
					"outmaintain", "outsum", "normlecture", "normmaterial",
					"normmaintain", "normsum", "sum" };
			List<Object> contents =(List<Object>)(List<?>) listdata;
			pdftable.setColumnAlignment(new int[]{4, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6});
			pdftable.addContent(contents, properties);
			pdftable.setContentFontSize(8.5f).setWidthPercentage(100f).setMarginTop(5);
			pdfdoc.headerOrPagenumber(false, null, null, true, 3);
			pdfdoc.add(pdftable);
			pdfdoc.newPage();
		}
		pdfdoc.saveToLocal();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream fileout = new FileOutputStream("twopagesinoneFINAL.pdf");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		PdfDocument pdfdoc = new PdfDocument(out, new float[]{1031.94f, 728.6f});
		List<List<TestData2>> listobj = new ArrayList<List<TestData2>>(); 
		List<TestData2> obj;
		for (int i = 0; i < 3; ++i) {
			obj = new ArrayList<TestData2>();
			TestData2 td2;
			td2 = new TestData2();
			td2.setHead("通常／外");
			td2.setOutlecture("外部");
			td2.setOutmaterial("外部");
			td2.setOutmaintain("外部");
			td2.setOutsum("");
			td2.setNormlecture("通常講座");
			td2.setNormmaterial("通常講座");
			td2.setNormmaintain("通常講座");
			td2.setNormsum("");
			td2.setSum("合計");
			obj.add(td2);
			td2 = new TestData2();
			td2.setHead("項目");
			td2.setOutlecture("受講料");
			td2.setOutmaterial("教材費");
			td2.setOutmaintain("維持費");
			td2.setOutsum("");
			td2.setNormlecture("受講料");
			td2.setNormmaterial("教材費");
			td2.setNormmaintain("維持費");
			td2.setNormsum("");
			td2.setSum("");
			obj.add(td2);
			for (int j = 0; j < 13; ++j) {
				td2 = new TestData2();
				td2.setHead("３月");
				td2.setOutlecture("￥21,000");
				td2.setOutmaterial("￥0");
				td2.setOutmaintain("￥2,100");
				td2.setOutsum("￥23,100");
				td2.setNormlecture("￥2,734,108");
				td2.setNormmaterial("￥48,703");
				td2.setNormmaintain("￥123,690");
				td2.setNormsum("￥2,906,501");
				td2.setSum("￥2,929,601");
				obj.add(td2);
			}
			listobj.add(obj);
		}
		String title = "稻毛海岸店　　平成24年　3月～平成25年　2月　　会員調查報告書";
		generatePdf(out, title, listobj);
		try {
			PdfManipulator newpdf = new PdfManipulator(fileout,
					new ByteArrayInputStream(out.toByteArray()), new float[] {
							841.5f, 595 });
			newpdf.mergeTo(new int[] { 2, 1 }); // define a new 3 rows and 3
												// columns pdf
			newpdf.saveDoc();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// title
//		PdfTitle pdftitle = new PdfTitle("月報印刷　　【順序】");
//		pdfdoc.add(pdftitle);
//		// date and time
//		PdfTextBlock datettime = new PdfTextBlock("平成23年 9月分");
//		datettime.setFontSize(14).setPosition(3, 7);
//		pdfdoc.addAbsolutePosition(datettime);
//		// shop code and name
//		PdfTextBlock shop = new PdfTextBlock("73　降魔大爺");
//		shop.setFontSize(14).setPosition(18, 7);
//		pdfdoc.addAbsolutePosition(shop);
//		// current　datetime
//		PdfTextBlock curtime = new PdfTextBlock((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
//		curtime.setFontSize(14).setPosition(95, 7);
//		pdfdoc.addAbsolutePosition(curtime);
//		// table
//		PdfTable pdftable = new PdfTable(23, 990, new float[] { 1.3f, 3.5f,
//				1.1f, 2.5f, 1.1f, 1.4f, 1.33f, 1.3f, 1.3f, 1.33f, 1.4f, 1.33f, 1.4f,
//				1.33f, 1.1f, 1.8f, 1.7f, 1.8f, 1.8f, 1.8f, 0.9f, 0.9f, 1.7f });
//		String[] heads = { "クラスNo", "クラス名", "講師No", "講師名", "講座No", "単価", "前月在籍",
//				"当月入会", "当月退会", "当月在籍", "前月分入金", "当月入金", "当月未入金", "支払記",
//				"1/2入金", "講師料源泉有", "体験費源泉有", "その他源泉有", "教材費源泉無", "その他源泉無",
//				"支払率", "方法", "通／外" };
//		ArrayList<String> header = new ArrayList<String>();
//		for (String h : heads) 
//			header.add(h);
//		String[] foots = { "0", "0", "0", "0", "0", "0", "1234", "133", "33",
//				"1,424", "10", "1,552", "10", "1,232", "0", "2,32,646", "0",
//				"0", "154,755", "0", "0", "0", "0" };
//		ArrayList<String> footer = new ArrayList<String>();
//		for (String h : foots) 
//			footer.add(h);
//		int [] alignment = { 5, 4, 5, 4, 5, 6, 8, 8, 8, 8, 8, 8, 8, 8, 5, 9, 9,
//				9, 9, 9, 5, 5, 5 };
//		pdftable.addHeaderFooter(header, footer, "合計", new int[] { 0, 0, 0, 0,
//				0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0 },alignment, false);
//		String[] testdata = { "140", "水墨畫基礎", "2343", "淺野信二", "155", "4,000",
//				"10", "5", "5", "5", "10", "10", "0", "15", "0", "10,000", "0",
//				"0", "500", "0", "40", "步合", "通常講座" };
//		for (int j = 0; j <100; ++j) {
//			for (int i = 0; i < heads.length; ++i) {
//				pdftable.addCell(testdata[i], 15f, alignment[i]);
//			}
//		}
//		pdftable.setMarginTop(50).setHeaderFontSize(7).setContentFontSize(10);
//		
//		PdfElement[] repeatable = {pdftitle, datettime, shop, curtime};
//		pdfdoc.headerOrPagenumber(true, repeatable, new int[][]{{50, 5},{8, 9}, {21, 9}, {90, 9}}, true, 9);
//		pdfdoc.add(pdftable);
		///////////////////////////////////////////////////////
		
//		pdfdoc.saveToLocal();
		
	}

}
