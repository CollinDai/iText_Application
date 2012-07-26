package pdfPrinter;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;

/**
 * PdfImage.java
 * 
 * @author Peike Dai
 * Date: 2012/06/07
 * Platform: MyEclipse Java 8.6， Windows7 Ultimate
 * Version: 1.0
 * 
 * module operation
 * ================
 * 提供一个模块，用来想pdf文档里添加图片
 * 
 * public interface
 * ================
 * PdfImage(String imageurl);			// create a PdfImage object from a valid URL
 * setScale(float percent);				// set the scale of the image with percentage
 * setScale(float xPercent, float yPercent);
 * 										// set the scale of the image with width and height percentage
 */

public class PdfImage extends PdfElement {
	private Image pdfimage;
	private float imagewidth;
	private float imageheight;
	
	public PdfImage(String imageurl) {
		try {
//			pdfimage = Image.getInstance(new URL(imageurl));
			pdfimage = Image.getInstance(imageurl);
			this.imageheight = pdfimage.getHeight();
			this.imagewidth = pdfimage.getWidth();
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * return false to call Document.add in PdfDocument.addAbsolutePosition
	 */
	protected boolean setPosition(PdfContentByte canvas, float pagewidth,
			float pageheight) {
		if (xPercentage == -1 || yPercentage == -1)
			return false;
		float xPos = (pagewidth - this.imagewidth) * this.xPercentage / 100;
		float yPos = (pageheight - this.imageheight)
				* (1 - this.yPercentage / 100);

		pdfimage.setAbsolutePosition(xPos, yPos);

		return false;
	}

	public PdfImage setScale(float percent) {
		if (percent <= 100f && percent >= 0) {
			pdfimage.scalePercent(percent);
			imageheight = pdfimage.getHeight() * percent / 100;
			imagewidth = pdfimage.getWidth() * percent / 100;
		}
		return this;
	}
	
	public PdfImage setScale(float xPercent, float yPercent){
		pdfimage.scalePercent(xPercent, yPercent);
		return this;
	}
	@Override
	protected Element getPdfElement() {
		return pdfimage;
	}

}
