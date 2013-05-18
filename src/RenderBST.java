import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class RenderBST<T extends Comparable<T>> {
	
	public static final int CANVAS_HEIGHT = 1000;
	public static final int CANVAS_WIDTH = 1800;
	public static final double SPACE_RATIO = .90f;
	public static final int TEXT_OFFSET_X = -15;
	public static final int TEXT_OFFSET_Y = -15;
	
	private Display display;
	private Shell shell;
	private Canvas canvas;
	
	private BinarySearchTree<T> bst;
	
	private int nodeWidth;
	private int nodeHeight;
	
	public RenderBST(final BinarySearchTree<T> bst) {
		this.bst = bst;
		
        display = new Display();
		
		shell = new Shell();
		shell.setText("Assignment 4: Binary Search Tree");
		shell.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		
		int totalNodeWidth = (int) Math.round(SPACE_RATIO * (double) CANVAS_WIDTH);
		nodeWidth = (int) Math.round(totalNodeWidth / (double) Math.pow(2, bst.getDepth() - 1));
		nodeHeight = nodeWidth;
		
		canvas = new Canvas(shell, SWT.NONE);
		canvas.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				drawNodes(e);
			}
		});
		
	    centerShell();
	}
	
	public void open() {
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		display.dispose();
	}
	
	/* Set the initial window size to SCREEN_RATIO percent of the total screen size and center it */
	private void centerShell() {
		Rectangle shellBounds = shell.getBounds();
		Rectangle displayBounds = shell.getMonitor().getBounds();
		
		shell.setBounds((displayBounds.width - shellBounds.width) / 2, (displayBounds.height - shellBounds.height) / 2, shellBounds.width, shellBounds.height);
		shell.setMinimumSize(shellBounds.width, shellBounds.height);
	}
	
	private void drawNodes(PaintEvent e) {
		e.gc.setAntialias(SWT.ON);
		
		e.gc.setForeground(new Color(display, 255, 255, 255));
		e.gc.setBackground(new Color(display, 150, 150, 150));
		e.gc.fillGradientRectangle(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT, true);
		
		e.gc.setBackground(new Color(display, 0, 0, 255));
		e.gc.setForeground(new Color(display, 255, 0, 0));
		e.gc.setLineWidth(2);
		
		drawNode(e, bst.getRoot(), 0, 1, 1);
	}
	
	private int drawNode(PaintEvent e, Node<T> current, int depth, int index, int drawIndex) {
		depth++;
		
		if (current.getLeft() != null)
			index = drawNode(e, current.getLeft(), depth, index, (drawIndex * 2) - 1);
		
		int numNodes = (int) Math.pow(2, depth - 1);
		int printWidth = (int) Math.round((double) CANVAS_WIDTH / (double) numNodes);
		int printHeight = (int) Math.round((double) CANVAS_HEIGHT / (double) bst.getDepth());
		
		if (depth > 1) {
			int parentNumNodes = (int) Math.pow(2, depth - 2);
			int parentPrintWidth = (int) Math.round((double) CANVAS_WIDTH / (double) parentNumNodes);
			
			Point pNode = new Point(0, 0);
			pNode.x = (printWidth / 2) + (printWidth * (drawIndex - 1));
			pNode.y = (printHeight / 2) + (printHeight * (depth - 1));
			
			int parentIndex = (int) Math.ceil((double) drawIndex / 2.0);
			Point pParent = new Point(0, 0);
			pParent.x = (parentPrintWidth / 2) + (parentPrintWidth * (parentIndex - 1));
			pParent.y = (printHeight / 2) + (printHeight * (depth - 2));
			
			e.gc.drawLine(pNode.x, pNode.y, pParent.x, pParent.y);
		}
		
		Rectangle rNode = new Rectangle(0, 0, 0, 0);
		rNode.x = ((printWidth - nodeWidth) / 2) + (printWidth * (drawIndex - 1));
		rNode.y = ((printHeight - nodeHeight) / 2) + (printHeight * (depth - 1));
		rNode.width = nodeWidth;
		rNode.height = nodeHeight;
		
		e.gc.setForeground(new Color(display, 0, 0, 0));
		e.gc.drawString(Integer.toString(index), rNode.x + TEXT_OFFSET_X, rNode.y + TEXT_OFFSET_Y, true);
		e.gc.setForeground(new Color(display, 255, 0, 0));
		
		index++;
		
		if (current.getRight() != null)
			index = drawNode(e, current.getRight(), depth, index, drawIndex * 2);
		
		e.gc.fillRectangle(rNode);
		
		return index;
	}
}
