package com.tocersoft.base.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片处理工具类
 * 
 * @author vinartis
 * @createDate Apr 8, 2013
 */
public class ImageUtils {

	private static final Log logger = LogFactory.getLog(ImageUtils.class);

	/**
	 * 
	 * @param originalPath
	 *            原始图片路径
	 * @param destPath
	 *            新图片路径
	 * @param dpi
	 *            要设置的dpi值
	 * @param w
	 *            生成新图片宽度
	 * @param h
	 *            生成新图片高度
	 */
	public static void changeDpi(String originalPath, String destPath, int dpi,
			int w, int h) {
		try {
			File input = new File(originalPath);
			BufferedImage image = ImageIO.read(input);
			// 读取图的一些属性信息
			ImageReader imageReader = null;
			String type = getImageRealType(input);
			Iterator<ImageReader> readers = ImageIO
					.getImageReadersByFormatName(type);
			while (readers.hasNext()) {
				imageReader = readers.next();
				imageReader.setInput(new FileImageInputStream(input), true,
						false);
				IIOMetadata data = imageReader.getImageMetadata(0);
				String[] formatNames = data.getMetadataFormatNames();
				String format = formatNames[0];
				System.out.println(format);
				Node tree = data.getAsTree(format);
				NodeList list = tree.getChildNodes();
				int len = list.getLength();
				for (int x = 0; x < len; x++) {
					Node node = list.item(x);
					NamedNodeMap map = node.getAttributes();
					int mapLen = map.getLength();
					for (int i = 0; i < mapLen; i++) {
						Node child = map.item(i);
						System.out.println(child.getNodeName() + ":"
								+ child.getNodeValue());
					}
					NodeList list2 = node.getChildNodes();
					int len2 = list.getLength();
					for (int y = 0; y < len2; y++) {
						Node node2 = list2.item(x);
						if (null == node2) {
							continue;
						}
						NamedNodeMap map2 = node2.getAttributes();
						int mapLen2 = map2.getLength();
						for (int i = 0; i < mapLen2; i++) {
							Node child2 = map2.item(i);
							System.out.println(child2.getNodeName() + "-"
									+ child2.getNodeValue());
						}
					}
				}

				Image rescaled = image.getScaledInstance(w, h,
						Image.SCALE_AREA_AVERAGING);
				BufferedImage output = toBufferedImage(rescaled,
						BufferedImage.TYPE_INT_RGB);
				File outfile = new File(destPath);
				FileOutputStream fos = new FileOutputStream(outfile);
				if ("png".equals(type)) {
					setDPI(data, dpi);
					Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("png");
					while (imageWriters.hasNext()) {
						ImageWriter iw = (ImageWriter) imageWriters.next();
						IIOImage iioImage = new IIOImage(output, null, data);
						ImageOutputStream ios = ImageIO
								.createImageOutputStream(outfile);
						iw.setOutput(ios);
						iw.write(iioImage);
						ios.flush();
						ios.close();
					}
				} else if ("jpg".equals(type)) {
					JPEGImageEncoder jpegEncoder = JPEGCodec
							.createJPEGEncoder(fos);
					JPEGEncodeParam jpegEncodeParam = jpegEncoder
							.getDefaultJPEGEncodeParam(output);
					jpegEncodeParam
							.setDensityUnit(JPEGEncodeParam.DENSITY_UNIT_DOTS_INCH);
					jpegEncodeParam.setXDensity(dpi);
					jpegEncodeParam.setYDensity(dpi);
					jpegEncoder.encode(output, jpegEncodeParam);
				}
				fos.close();
			}
		} catch (FileNotFoundException e) {
			logger.error("文件未找到：" + originalPath);
			e.printStackTrace();
		} catch (ImageFormatException e) {
			logger.error("图片格式处理异常：" + originalPath);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IO异常：" + originalPath);
			e.printStackTrace();
		}
	}

	private static void setDPI(IIOMetadata metadata, int dpi)
			throws IIOInvalidTreeException {
		// for PNG, it's dots per millimeter
		double dotsPerMilli = 1.0 * dpi / 10 / 2.54;
		IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
		horiz.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
		vert.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode dim = new IIOMetadataNode("Dimension");
		dim.appendChild(horiz);
		dim.appendChild(vert);

		IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
		root.appendChild(dim);
		metadata.mergeTree("javax_imageio_1.0", root);
	}

	public static long getDpi4Png(File input) throws Exception {
		Iterator<ImageReader> readers = ImageIO
				.getImageReadersByFormatName("png");
		if (null != readers && readers.hasNext()) {
			ImageReader imageReader = readers.next();
			imageReader.setInput(new FileImageInputStream(input), true, false);
			IIOMetadata metadata = imageReader.getImageMetadata(0);
			Node tree = metadata.getAsTree("javax_imageio_png_1.0");
			NodeList list = tree.getChildNodes();
			int len = list.getLength();
			for (int x = 0; x < len; x++) {
				Node node = list.item(x);
				NamedNodeMap map = node.getAttributes();
				int mapLen = map.getLength();
				for (int i = 0; i < mapLen; i++) {
					Node child = map.item(i);
					if (child.getNodeName().equals("pixelsPerUnitXAxis")) {
						String dpiCm = child.getNodeValue();
						Float ratio = 2.54F;
						if (StringUtils.isNotBlank(dpiCm)) {
							Double dpiDouble = Double.parseDouble(dpiCm)
									* ratio / 100;
							Long dpi = Math.round(dpiDouble);
							return dpi;
						}
					}
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		String originalPath = "D:/poitest/300.png";
		 String destPath = "D:/poitest/300-1.png";
		int w = getWidth(new File(originalPath));
		int h = -1;
		changeDpi(originalPath, destPath, 20, w, h);
	}

	public static BufferedImage toBufferedImage(Image image, int type) {
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		BufferedImage result = new BufferedImage(w, h, type);
		Graphics2D g = result.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return result;
	}

	/**
	 * 根据文件流读取图片文件真实类型
	 * 
	 * @param is
	 * @return
	 */
	public static String getImageRealType(File image) {
		byte[] b = new byte[4];
		try {
			FileInputStream is = new FileInputStream(image);
			is.read(b, 0, b.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String type = bytesToHexString(b).toUpperCase();
		if (type.contains("FFD8FF")) {
			return "jpg";
		} else if (type.contains("89504E47")) {
			return "png";
		} else if (type.contains("47494638")) {
			return "gif";
		} else if (type.contains("49492A00")) {
			return "tif";
		} else if (type.contains("424D")) {
			return "bmp";
		}
		return type;
	}

	/**
	 * byte数组转换成16进制字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 缩放图片
	 * 
	 * @param oldFile
	 *            原图片
	 * @param formatName
	 *            缩放后格式
	 * @param newFile
	 *            缩放后图片
	 * @param width
	 *            指定宽度
	 * @param height
	 *            指定高度
	 * @param db
	 *            是否等比例缩放
	 * @throws IOException
	 */
	public static void resizeImage(File oldFile, String formatName,
			File newFile, int width, int height, boolean db) throws IOException {
		try {
			BufferedImage image = null;
			boolean osFile = oldFile.isFile();
			boolean exists = oldFile.exists();
			if (oldFile != null && osFile && exists) {
				image = ImageIO.read(oldFile);
			}
			double[] zoomSize = new double[2];
			double srcWidth = image.getWidth();
			double srcHeigth = image.getHeight();

			if (db) {
				// 文件宽和高都小于指定宽和高则不需要处理
				// if (srcWidth <= width && srcHeigth <= height) {
				// // 不缩放
				// zoomSize[0] = srcWidth;
				// zoomSize[1] = srcHeigth;
				// } else {
				// 等比例缩放控制
				double tempHeight = (srcHeigth / srcWidth) * width;
				if (tempHeight > height) {
					zoomSize[0] = (srcWidth / srcHeigth) * height;
					zoomSize[1] = height;
				} else {
					zoomSize[0] = width;
					zoomSize[1] = (srcHeigth / srcWidth) * width;
				}
				// }
			} else {// 不等比例
				zoomSize[0] = width;
				zoomSize[1] = height;
			}
			// ResampleOp resampleOp = new ResampleOp((int) zoomSize[0],
			// (int) zoomSize[1]);
			// BufferedImage tag = resampleOp.filter(image, null);
			// ImageIO.write(tag, formatName, newFile);
		} catch (IOException e) {
			logger.error("ImageUtils resizeImage this image IOException"
					+ e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 获得图片宽度
	 * 
	 * @param file
	 *            图片文件
	 * @return
	 */
	public static int getWidth(File file) {
		try {
			Image src = ImageIO.read(file);
			return src.getWidth(null);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获得图片高度
	 * 
	 * @param file
	 *            图片文件
	 * @return
	 */
	public static int getHeight(File file) {
		try {
			Image src = ImageIO.read(file);
			return src.getHeight(null);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 图片切割
	 * 
	 * @param intImageFile
	 *            源图像地址
	 * @param outImageFile
	 *            新图片地址
	 * @param x
	 *            目标切片起点x坐标
	 * @param y
	 *            目标切片起点y坐标
	 * @param destWidth
	 *            目标切片宽度
	 * @param destHeight
	 *            目标切片高度
	 */
	public static void cut(String intImageFile, String outImageFile, int x,
			int y, int destWidth, int destHeight) {
		try {
			Image img;
			ImageFilter cropFilter;
			// 读取源图像
			BufferedImage bi = ImageIO.read(new File(intImageFile));
			int srcWidth = bi.getWidth(); // 源图宽度
			int srcHeight = bi.getHeight(); // 源图高度
			if (srcWidth >= destWidth && srcHeight >= destHeight) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight,
						Image.SCALE_DEFAULT);
				cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
				img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(destWidth, destHeight,
						BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, null); // 绘制缩小后的图
				g.dispose();
				// 输出为文件
				ImageIO.write(tag, "JPEG", new File(outImageFile));
			} else {
				ImageIO.write(bi, "JPEG", new File(outImageFile));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
