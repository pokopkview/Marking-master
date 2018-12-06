//package com.intelligent.marking.service;
//
//import java.io.UnsupportedEncodingException;
//
//import android.app.Service;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.os.Handler;
//import android.os.IBinder;
//import android.posapi.PosApi;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.intelligent.marking.R;
//import com.intelligent.marking.application.MarkingApplication;
//import com.intelligent.marking.common.okgo.App;
//
///**
// * 扫描服务类
// * 调用PDA扫描的服务类
// * @author wsl
// *
// */
//public class ScanService extends Service {
//
//	public static PosApi mApi = null;
//	//GPIO电源的控制
//	private static byte mGpioPower = 0x1E;// PB14
//	private static byte mGpioTrig = 0x29;// PC9
//	//串口号和波特率的设置
//	private static int mCurSerialNo = 3; // usart3
//	private static int mBaudrate = 4; // 9600
//	//SCAN按键监听
//	private ScanBroadcastReceiver scanBroadcastReceiver;
//	//F3键监听
//	private ScanBroadcastReceiver_F3 scanBroadcastReceiver_F3;
//	//扫描音播放器
//	private MediaPlayer player;
//	// 音频控制
//	private AudioManager audioManager = null;
//
//
//	@Override
//	public IBinder onBind(Intent intent) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void onCreate() {
//		// TODO Auto-generated method stub
//		//初始化
//		init();
//		//注册广播接收器
//		registerListener();
//		//播放器实例化
//		player = MediaPlayer.create(getApplicationContext(), R.raw.beep);
//		//音频初始化
//		audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
//
//		super.onCreate();
//
//	}
//
//	public static void init() {
//
//		//mApi类赋值
//		mApi = MarkingApplication.getInstance().getPosApi();
//		System.out.println("mApi:"+mApi);
//		//延迟一秒打开串口，这个为了初始化扫描头，必须延迟一秒执行，否则会出现延迟打印或者打印不出的现象，需注意
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				//打开扫描串口
//				openDevice();
//			}
//		}, 1000);
//
//	}
//
//	@Override
//	public int onStartCommand(Intent intent, int flags, int startId) {
//		// TODO Auto-generated method stub
//		return super.onStartCommand(intent, flags, startId);
//	}
//
//	/**
//	 * 扫描头扫描信息接收器
//	 */
//	BroadcastReceiver receiver_ = new BroadcastReceiver() {
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// TODO Auto-generated method stub
//			String action = intent.getAction();
//			if (action.equalsIgnoreCase(PosApi.ACTION_POS_COMM_STATUS)) {
//				int cmdFlag = intent.getIntExtra(PosApi.KEY_CMD_FLAG, -1);
//				byte[] buffer = intent
//						.getByteArrayExtra(PosApi.KEY_CMD_DATA_BUFFER);
//				switch (cmdFlag) {
//					// 传输扫描信息的串口
//					case PosApi.POS_EXPAND_SERIAL3:
//						//如果为空，返回
//						if (buffer == null)
//							return;
//						//播放扫描音，提示已经扫描到信息
//						player.start();
//						try {
//							//把扫描头传过来的byte字节转成字符串
//							String str = new String(buffer, "GBK");
//							Log.e("ScanStr", "-----:" + str.trim());
//							//准备通过广播发送扫描信息，如果是集成进自己项目，此段可忽略
//							Intent intentBroadcast = new Intent();
//							Intent intentBroadcast1 = new Intent();
//							//设置发送广播的action
//							intentBroadcast.setAction("com.qs.scancode");
//							intentBroadcast1.setAction("com.zkc.scancode");
//							//附加扫描信息至intent上
//							intentBroadcast.putExtra("code", str.trim());
//							intentBroadcast1.putExtra("code", str.trim());
//							//发送广播，在Softkeyboard或者谷歌拼音输入法中注册有接收该类广播的接收器，输入法接收到后会把信息输入到当前处于焦点的编辑框中
//							sendBroadcast(intentBroadcast);
//							sendBroadcast(intentBroadcast1);
//							//设置当前客户可再次进行扫描
//							isScan = false;
//							//拉低扫描头电压，使扫描头熄灭
//							ScanService.mApi.gpioControl(mGpioTrig, 0, 1);
//							//移除扫描头熄灭线程
//							handler.removeCallbacks(run);
//							// 根据指定的模式进行震动
//							// 第一个参数：该数组中第一个元素是等待多长的时间才启动震动，
//							// 之后将会是开启和关闭震动的持续时间，单位为毫秒
//							// 第二个参数：重复震动时在pattern中的索引，如果设置为-1则表示不重复震动
//							// vibrator.vibrate(new long[]{1000,50,50,100,50},-1);
//							//振动2秒
//							// vibrator.vibrate(2000);
//
//						} catch (UnsupportedEncodingException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						break;
//				}
//				buffer = null;
//			}
//		}
//	};
//
//	//将字符串转成GBK格式字符串
//	public String toGBK(String str) throws UnsupportedEncodingException {
//		return this.changeCharset(str, "GBK");
//	}
//
//	/**
//	 * 字符串编码转换的实现方法
//	 *
//	 * @param str
//	 *            待转换编码的字符串
//	 * @param newCharset
//	 *            目标编码
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 */
//	public String changeCharset(String str, String newCharset)
//			throws UnsupportedEncodingException {
//		if (str != null) {
//			// 用默认字符编码解码字符串。
//			byte[] bs = str.getBytes(newCharset);
//			// 用新的字符编码生成字符串
//			return new String(bs, newCharset);
//		}
//		return null;
//	}
//	//打开扫描头，注：拉低拉高一次电压，扫描头将会亮一次光线
//	public static void openScan() {
//		//拉低扫描头电压
//		ScanService.mApi.gpioControl(mGpioTrig, 0, 0);
//		try {
//			//休眠100ms
//			Thread.sleep(100);
//		} catch (Exception e) {
//		}
//		//拉高扫描头电压
//		ScanService.mApi.gpioControl(mGpioTrig, 0, 1);
//	}
//
//	//打开串口
//	private static void openDevice() {
//		// GPIO控制器初始化
//		mApi.gpioControl(mGpioPower, 0, 1);
//		//扫描串口初始化
//		mApi.extendSerialInit(mCurSerialNo, mBaudrate, 1, 1, 1, 1);
//
//	}
//
//
//	@Override
//	public void onDestroy() {
//		// TODO Auto-generated method stub
//		//关闭整个下层串口
//		mApi.closeDev();
//		super.onDestroy();
//	}
//
//	boolean isScan = false;
//	//SCAN按键的监听
//	class ScanBroadcastReceiver extends BroadcastReceiver {
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// TODO Auto-generated method stub
//			if (!isScan) {
//				//扫描头未处于扫描状态
//				//打开扫描头
//				ScanService.mApi.gpioControl(mGpioTrig, 0, 0);
//				isScan = true;
//				handler.removeCallbacks(run);
//				handler.postDelayed(run, 3000);
//			} else {
//				//扫描头处于扫描头状态，先关掉扫描头光
//				ScanService.mApi.gpioControl(mGpioTrig, 0, 1);
//				//打开扫描头
//				ScanService.mApi.gpioControl(mGpioTrig, 0, 0);
//				isScan = true;
//				handler.removeCallbacks(run);
//				handler.postDelayed(run, 3000);
//			}
//		}
//	}
//
//	//F3按键的监听
//	class ScanBroadcastReceiver_F3 extends BroadcastReceiver {
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// TODO Auto-generated method stub
//			//呼出音量调节框
////			audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
////					AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND
////							| AudioManager.FLAG_SHOW_UI);
//			Toast.makeText(context, "F3点击", Toast.LENGTH_SHORT).show();
//		}
//	}
//
//	Handler handler = new Handler();
//	Runnable run = new Runnable() {
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			//到一定时间后拉低扫描头电压，关掉扫描光
//			ScanService.mApi.gpioControl(mGpioTrig, 0, 1);
//			isScan = false;
//		}
//	};
//
//	/**
//	 * 注册广播接收器
//	 */
//	private void registerListener() {
//
//		//注册扫描信息的接收器
//		IntentFilter mFilter = new IntentFilter();
//		mFilter.addAction(PosApi.ACTION_POS_COMM_STATUS);
//		registerReceiver(receiver_, mFilter);
//		//SCAN按键按下时候广播的接收器
//		scanBroadcastReceiver = new ScanBroadcastReceiver();
//		IntentFilter intentFilter = new IntentFilter();
//		intentFilter.addAction("ismart.intent.scandown");
//		this.registerReceiver(scanBroadcastReceiver, intentFilter);
//		//F3按键按下时候广播的接收器（F3按键仅在5501机器的右边，也就是扫描键上面那个小键）
//		scanBroadcastReceiver_F3 = new ScanBroadcastReceiver_F3();
//		IntentFilter intentFilter_f3 = new IntentFilter();
//		intentFilter_f3.addAction("ismart.intent.f3down");
//		this.registerReceiver(scanBroadcastReceiver_F3, intentFilter_f3);
//
//	}
//
//
//}
