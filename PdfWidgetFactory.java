/**
 * 
 */
package pdfPrinter;

/**
 * @author Peike Dai
 * Platform: MyEclipse Java 8.6， Windows7 Ultimate
 * Version
 * =======
 * 1.0 - 2012/06/07
 * 	First release
 * 1.1 - 2012/07/06
 * 	add method to create a dotted line, with parameters to input start point and end point of line
 * 1.2 - 2012.07/16
 * 	Add Ellipse and Round corner Rectangle
 * 
 * Module Operation
 * ================
 * 此模块包含一个createWidget静态函数，用来创建打印在pdf中的分割线
 * 
 * public interface
 * ================
 * PdfWidgetFactory.createSeparator();	// create a default separator
 * 
 * 
 */
public class PdfWidgetFactory {

	/**
	 * 
	 * @return
	 */	
	public static PdfElement createSeparator() {
		return (new PdfWidget()).createSeparator();
		}
	public static PdfElement createLine(float[] start, float[] end) {
		return (new PdfWidget()).createLine(start, end);
	}
	public static PdfElement createDashedLine(float[] start, float[] end){
		return (new PdfWidget()).createDashedLine(start, end);
	}
	public static PdfElement createEllipse(float[] leftdown, float[] upright, boolean filled, float[] dotted) {
		return (new PdfWidget()).createEllipse(leftdown, upright, filled, dotted);
	}
	/**
	 * 
	 * @param init init[0],init[1]: x, y(lower left); init[2]: width; init[3]: height, init[4]: radius
	 * @param filled
	 * @param dotted dotted[0]: line lenght; dotted[1]: gap length;
	 * @return
	 */
	public static PdfElement createRoundRectangle	(float[] init, boolean filled, float[] dotted) {
		return (new PdfWidget()).createRoundRectangle(init, filled, dotted);
	}
}
