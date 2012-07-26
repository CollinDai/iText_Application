package pdfPrinter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * PdfWidget.java
 * 
 * @author Peike Dai
 * Platform: MyEclipse Java 8.6， Windows7 Ultimate
 * 
 * Version:
 * =======
 * 1.0 - 2012/06/07
 * 	First release
 * 1.1 - 2012/07/06
 * 	Add a method in PdfLine class to provide a dotted line option
 * 1.2 - 2012.07/16
 * 	Add Ellipse and Round corner Rectangle
 * 
 * module operation
 * ================
 * 提供一个pdf分割线部件；
 * 可用PdfWidgetFactory生成默认的分割线或者图片，也可以用此模块生成部件的对象，
 * 进行自定义，再添加到PDF文档中
 * 
 * Public Interface
 * ================
 * PdfSeparator(float linethick, float percentage,
			BaseColor linecolor, int align)		
							// line thickness, width percentage, line color, horizontal alignment
 * PdfSeparator();			// 1f, 100f, BLACK, CENTER					 
 * 
 */
class PdfWidget {	
	
	public PdfElement createSeparator(){
		//pdfSeparator = this.new PdfSeparator();
		return new PdfSeparator(); 
	}
	
	PdfElement createLine(float[] start, float[] end) {
		return new PdfLine(start, end);
	}
	
	PdfElement createDashedLine(float[] start, float[] end) {
		return new PdfLine(start, end, true);
	}
	
	PdfElement createEllipse(float[] leftdown, float[] upright, boolean filled, float[] dotted) {
		return new PdfEllipse(leftdown, upright, filled, dotted);
	}
	
	PdfElement createRoundRectangle(float[] init, boolean filled, float[] dotted) {
		return new PdfRoundRectangle(init, filled, dotted);
	}
	
	//public static PdfSeparator pdfSeparator;
	
	public class PdfSeparator extends PdfElement {
		private PdfPTable separator;
		
		/**
		 * 
		 * @param linethick
		 * 				line thickness
		 * @param percentage
		 * 				line width percentage
		 * @param linecolor
		 * 				line color
		 * @param align
		 * 				line alignment
		 */
		public PdfSeparator(float linethick, float percentage,
			BaseColor linecolor, int align) {
			try {
				separator = new PdfPTable(1);
				separator.setWidthPercentage(percentage);
				separator.getDefaultCell().setBorderWidthLeft(0);
				separator.getDefaultCell().setBorderWidthTop(0);
				separator.getDefaultCell().setBorderWidthRight(0);
				separator.getDefaultCell().setBorderWidthBottom(linethick);
				separator.getDefaultCell().setBorderColorBottom(linecolor);
				separator.setHorizontalAlignment(align);
				separator.setSpacingAfter(10f);
				separator.addCell("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public PdfSeparator(){
			this(1f, 100f, BaseColor.BLACK, Element.ALIGN_CENTER);
		}
		
		protected Element getPdfElement(){
			return separator;
		}		
	}
	
	/**
	 * Default line color and line thickness
	 * @author Peike Dai
	 *
	 */
	class PdfLine extends PdfElement {
		float[] linestart;
		float[] lineend;
		boolean isDotted;
		protected boolean setPosition(PdfContentByte canvas, float pagewidth,
				float pageheight) {
			if (linestart == null || lineend  == null || linestart.length != 2
					|| lineend.length != 2)
				return true;
			canvas.saveState();
			canvas.setLineWidth(0.5f);
			if (isDotted) {
				canvas.setLineWidth(0.2f);
				canvas.setLineDash(1, 2, 0);
			}
			canvas.moveTo(linestart[0], linestart[1]);
			canvas.lineTo(lineend[0], lineend[1]);
			canvas.stroke();
	        canvas.restoreState();
			return true;
		}
		
		public PdfLine(float[] start, float[] end) {
			this.linestart = start;
			this.lineend = end;
		}
		
		public PdfLine(float[] start, float[] end, boolean dotted){
			this.linestart = start;
			this.lineend = end;
			this.isDotted = dotted;
		}
		protected Element getPdfElement() {
			return null;
		}
	}
	
	class PdfEllipse extends PdfElement {
		private float[] leftdown, upright, dotted;
		private boolean isFilled;
		PdfEllipse(float[] leftdown, float[] upright){
			this(leftdown, upright, false, null);
		}
		
		PdfEllipse (float[] leftdown, float[] upright, boolean filled){
			this(leftdown, upright, filled, null);
		}
		
		PdfEllipse (float[] leftdown, float[] upright, float[] dotted){
			this(leftdown, upright, false, dotted);
		}
		
		PdfEllipse(float[] leftdown, float[] upright, boolean filled, float[] dotted) {
			this.leftdown = leftdown;
			this.upright = upright;
			this.isFilled = filled;
			this.dotted = dotted;
		}
		
		protected boolean setPosition(PdfContentByte canvas, float pagewidth,
				float pageheight) {
			if (leftdown == null || upright == null || leftdown.length != 2 || upright.length != 2)
				return true;
			canvas.saveState();
			if (dotted != null && dotted.length == 2)
				canvas.setLineDash(dotted[0], dotted[1], 0);
			canvas.ellipse(leftdown[0], leftdown[1], upright[0], upright[1]);
			if (isFilled) {
				canvas.setColorFill(new GrayColor(0.9f));
				canvas.fillStroke();
			} else 
				canvas.stroke();
			canvas.restoreState();
			return true;
		}
		
		@Override
		protected Element getPdfElement() {
			return null;
		}
	}
	
	class PdfRoundRectangle extends PdfElement {
		private float[] startpoint, dotted;
		private float width, height, radius;
		private boolean isFilled;
		
		PdfRoundRectangle(float[] init, boolean filled, float[] dotted) {
			this.startpoint = new float[]{init[0], init[1]};
			this.width = init[2];
			this.height = init[3];
			this.radius = init[4];
			this.isFilled = filled;
			this.dotted = dotted;
		}
		
		protected boolean setPosition(PdfContentByte canvas, float pagewidth,
				float pageheight) {
			if (this.startpoint == null || this.startpoint.length != 2)
				return true;
			try {
				canvas.saveState();
				if (dotted != null && dotted.length == 2)
					canvas.setLineDash(dotted[0], dotted[1], 0);
				canvas.roundRectangle(startpoint[0], startpoint[1], width, height, radius);
				if (this.isFilled) {
					canvas.setColorFill(new GrayColor(0.9f));
					canvas.fillStroke();
				} else 
					canvas.stroke();
				canvas.restoreState();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		
		@Override
		protected Element getPdfElement() {
			return null;
		}
		
	}
}


