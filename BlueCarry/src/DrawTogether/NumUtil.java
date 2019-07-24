package DrawTogether;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class NumUtil {
	ArrayList dataList=new ArrayList();//数据存储
	public byte[] int2ByteArray(int i){
		byte[] result=new byte[4];
		result[0]=(byte)((i >> 24)& 0xFF);
		result[1]=(byte)((i >> 16)& 0xFF);
		result[2]=(byte)((i >> 8)& 0xFF);
		result[3]=(byte)(i & 0xFF);
		return result;
	}
	public  int bytes2Int(byte[] bytes){
		int num=bytes[3] & 0xFF;
		num |=((bytes[2] <<8)& 0xFF00);
		num |=((bytes[1] <<16)& 0xFF0000);
		num |=((bytes[0] <<24)& 0xFF0000);
		return num;
	}
	
	public byte[] int_transformto_byte(int[] points) {//int[]转byte[]
//		byte[] bytes=new byte[16];
//		int count=0;
//		for(int point:points) {
//			byte[] bte=int2ByteArray(point);
//			for(byte b:bte) {
//				bytes[count]=b;
//				count++;
//			}
//		}
//		for(byte b:bytes) {
//			System.out.println(b);
//		}
		String message=",";
		for(int point:points) {
			message+=(","+point);
		}
		byte[] bytes=null;
		try {
			bytes = message.trim().getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			System.out.println("int 转byte编码错误："+e.getMessage());
			e.printStackTrace();
		}
		return bytes;
	}
	public int[] byte_transformto_int(byte[] data) {
//		int[] result = new int[4];
//		// 拆开为4个bytes,并转换为int
//		byte[] bytes = new byte[4];
//		for (int i = 0, num = 0, m = 0; i <=16; i++, m++) {
//			if (i % 4 == 0) {
//				result[num] = bytes2Int(bytes);
//				System.out.println("num: "+num);
//				System.out.println("bytes.length: "+bytes.length);
//				num++;
//			}
//			bytes[m % 4] = data[i];
//		}
		String message = null;
		try {
			message=new String(data,"GBK").trim();
//			System.out.println("收到的字符串为："+message);
		} catch (UnsupportedEncodingException e) {
			System.out.println("byte编码错误："+e.getMessage());
			e.printStackTrace();
		}
		String[] points_str=message.split(",");
		int[] points_int=new int[6];
		for(int i=0;i<points_int.length;i++) {
			if(points_str[i].equals("")) {
				continue;
			}
			points_int[i]= Integer.parseInt(points_str[i]);
		}
		return points_int;
	}
}
