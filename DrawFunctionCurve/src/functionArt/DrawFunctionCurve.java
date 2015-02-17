package functionArt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;

public class DrawFunctionCurve extends JFrame {
	private static final double WIDTH = Toolkit.getDefaultToolkit()
			.getScreenSize().getWidth();
	private static final double HEIGHT = Toolkit.getDefaultToolkit()
			.getScreenSize().getHeight();
	private static final int INCREMENT = 20;
	private static final int SIZE = 100;
	private static final double SHAPE_BEGIN = 0.000001;
	private static final double SHAPE_END = 8 * Math.PI;
	private static final double SHAPE_CONDITION = 0.0001 * Math.PI;

	public static void main(String[] args) {
		new DrawFunctionCurve();
	}

	public DrawFunctionCurve() {
		this.setTitle("画函数曲线");
		this.setLocation(50, 50);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.white);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		Color source = g2d.getColor();

		g2d.setColor(Color.black);
		g2d.drawString("心形图像", 50, 50);

		// 画 X 轴
		g2d.drawLine(INCREMENT, (int) HEIGHT / 2, (int) WIDTH - INCREMENT,
				(int) HEIGHT / 2);
		g2d.drawLine((int) WIDTH - INCREMENT, (int) HEIGHT / 2,
				(int) WIDTH - 10, (int) HEIGHT / 2 - 5);
		g2d.drawLine((int) WIDTH - INCREMENT, (int) HEIGHT / 2,
				(int) WIDTH - 10, (int) HEIGHT / 2 + 5);

		// 画 Y 轴
		g2d.drawLine((int) WIDTH / 2, 40, (int) WIDTH / 2, (int) HEIGHT - 50);
		g2d.drawLine((int) WIDTH / 2, 40, (int) WIDTH / 2 - 10, 50);
		g2d.drawLine((int) WIDTH / 2, 40, (int) WIDTH / 2 + 10, 50);

		// 将当前画笔移动到中心
		g2d.translate((int) WIDTH / 2, (int) HEIGHT / 2);

		// 利用GeneralPath类来画曲线
		GeneralPath gp = new GeneralPath();

		// 将GeneralPath的实例gp的画笔移动到当前画面的中心，但是这个点是相对于g2d画笔的中心的
		gp.moveTo(0, 0);
		g2d.setColor(Color.red);
		// 画心形图像
		drawHeart(gp, g2d);

		// 画sin(x)/x 的图像
		// drawSinxDX(gp, g2d);

		// sin(x)的图像
		// drawSinx(gp, g2d);

		// cos(x)的图像
		// drawCosx(gp,g2d);

		// tan(x)的图像
		// drawTanx(gp, g2d);
		g2d.setColor(source);
	}

	private void drawHeart(GeneralPath gp, Graphics2D g2d) {
		// 参考函数：x^2+(y-(x^2)^(1/3))^2=1
		// 因为y带平方所以有＋—两个部分。
		// 画心型的上半部分
		for (double x = SHAPE_BEGIN; x <= SHAPE_END; x += SHAPE_CONDITION) {
			double y = Math.sqrt((1 - Math.pow(x, 2)))
					+ Math.pow(x, (double) 2 / 3);
			// double y = x;

			gp.lineTo(SIZE * x, SIZE * (-y));
		}

		g2d.draw(gp);

		// 将当前画笔以Y中为对称轴，画偶函数(关于Y轴对称)
		g2d.scale(-1, 1);
		g2d.draw(gp);
		// 画笔移到中心
		gp.moveTo(0, 0);

		// 画心形的下半部分
		for (double x = SHAPE_BEGIN; x <= SHAPE_END; x += SHAPE_CONDITION) {
			// x^2+(y-(x^2)^(1/3))^2=1
			//
			double y = -Math.sqrt((1 - Math.pow(x, 2)))
					+ Math.pow(x, (double) 2 / 3);
			// double y = i;

			gp.lineTo(SIZE * x, SIZE * (-y));
		}
		g2d.draw(gp);
		// 将当前画笔以Y中为对称轴，画偶函数(关于Y轴对称)
		g2d.scale(-1, 1);
		g2d.draw(gp);
	}

	private void drawTanx(GeneralPath gp, Graphics2D g2d) {
		for (double x = SHAPE_BEGIN; x <= SHAPE_END; x += SHAPE_CONDITION) {
			gp.lineTo(SIZE * x, SIZE * -Math.tan(x));
		}
		g2d.draw(gp);
		// 将当前画笔以原点为中心，旋转180度，画奇函数（关于原点对称）
		g2d.rotate(Math.PI);
		g2d.draw(gp);
	}

	private void drawCosx(GeneralPath gp, Graphics2D g2d) {
		for (double x = SHAPE_BEGIN; x <= SHAPE_END; x += SHAPE_CONDITION) {
			gp.lineTo(SIZE * x, SIZE * -Math.cos(x));
		}
		g2d.draw(gp);

		// 将当前画笔以Y中为对称轴，画偶函数(关于Y轴对称)
		g2d.scale(-1, 1);
		g2d.draw(gp);
	}

	private void drawSinx(GeneralPath gp, Graphics2D g2d) {
		for (double x = SHAPE_BEGIN; x <= SHAPE_END; x += SHAPE_CONDITION) {
			gp.lineTo(SIZE * x, SIZE * -Math.sin(x));
		}
		g2d.draw(gp);
		g2d.rotate(Math.PI);
		g2d.draw(gp);
	}

	private void drawSinxDX(GeneralPath gp, Graphics2D g) {
		for (double x = SHAPE_BEGIN; x <= SHAPE_END; x += SHAPE_CONDITION) {
			gp.lineTo(SIZE * x, SIZE * -Math.sin(x) / x);
		}
		g.draw(gp);
		g.scale(-1, 1);
		g.draw(gp);
	}
}