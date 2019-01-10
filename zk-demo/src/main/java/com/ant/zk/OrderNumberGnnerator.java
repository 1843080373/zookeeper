package com.ant.zk;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumberGnnerator {

	private static int count=0;
	
	public String createOrderNumber(){
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String number=(simpleDateFormat.format(new Date()))+"-"+(++count);
		return number;
	}
}
