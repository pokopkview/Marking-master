// IMyAidlInterface.aidl
package com.intelligent.marking;

// Declare any non-default types here with import statements
import android.graphics.Bitmap;

interface IMyAidlInterface
{

    /**
    * 打开设备扫描头
    */
    void openScan();

	/**
	* 打印文字，文字宽度满一行自动换行排版，不满一整行不打印除非强制换行
	* @param size: 打印字体大小，size为1时候为正常字体，size为2时候为双倍字体
    * @param left：左边距，打印图片与纸张左边的距离
	* @param text:	要打印的文字字符串
	* @param align:	文字对齐方式
	*/
	void printText(int size,int align,String text);

	/**
	* 打印图片
    * @param align:	对齐方式  0 左对齐 ；1 居中对齐 ；2 右对齐
	* @param bitmap: 	图片bitmap对象(最大宽度384像素，超过无法打印并且报异常)
	*/
	void printBitmap(int align,in Bitmap bitmap);

	/**
	* 打印一维条码
    * @param @param align:	对齐方式  0 左对齐 ；1 居中对齐 ；2 右对齐
    * @param width：一维条码的宽度 58纸张最大宽度为384,80最大宽度570
    * @param height：一维条码的高度
    * @param data: 	一维条码对象(不能是中文，若为中文则会报错)
	*/
	void printBarCode(int align, int width, int height, String data);

	/**
	* 打印二维码
	* @param align:	对齐方式  0 左对齐 ；1 居中对齐 ；2 右对齐
    * @param width：二维码的宽度 58纸张最大宽度为58
    * @param data: 	二维码对象
	*
	*/
	void printQRCode(int align, int height,String data);

   /**
	* 发送命令
    * @param data: 	指令byte数组
	*
	*/
	void sendCMD(in byte[] list);


}
