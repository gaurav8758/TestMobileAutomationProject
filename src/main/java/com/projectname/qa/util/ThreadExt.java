package com.projectname.qa.util;

import com.projectname.qa.base.MobileTestBase;

public class ThreadExt extends Thread {
	
	  public static void sleep(WaitTime SleepTime)
	       throws InterruptedException {
		  Long Sleep = GetSleepTime(SleepTime);
		  MobileTestBase.ScriptWaitTimeCounter = MobileTestBase.ScriptWaitTimeCounter + Sleep;	
		  sleep(Sleep, 0);
	       }
	  
	  public static void sleep(int SleepTime) {
		  MobileTestBase.ScriptWaitTimeCounter = MobileTestBase.ScriptWaitTimeCounter + SleepTime;	
			 
			  try {
				sleep(SleepTime, 0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
			  
		       }
	
	  private static Long GetSleepTime(WaitTime sleepTime) {
		// TODO Auto-generated method stub
		Long GetSleepTime = null;
		switch (MobileTestBase.GlobalplatformName.trim().toLowerCase()) {
		case "android":
			switch (sleepTime) {
				case High:
					GetSleepTime = 4000L;
					break;
				case Medium:
					GetSleepTime = 3000L;
					break;
				case Low:
					GetSleepTime = 1500L;
					break;
				case OneSecond:
					GetSleepTime = 1000L;
					break;	
				case TwoSecond:
					GetSleepTime = 2000L;
					break;	
				case ThreeSecond:
					GetSleepTime = 3000L;
					break;	
				case FourSecond:
					GetSleepTime = 4000L;
					break;
				case FiveSecond:
					GetSleepTime = 5000L;
					break;	
				case SixSecond:
					GetSleepTime = 6000L;
					break;	
				case SevenSecond:
					GetSleepTime = 7000L;
					break;	
				case EightSecond:
					GetSleepTime = 8000L;
					break;	
				case NineSecond:
					GetSleepTime = 9000L;
					break;	
				case TenSecond:
					GetSleepTime = 10000L;
					break;
				case Zero:
					GetSleepTime = 0L;
					break;
			}
			break;
		case "iphone":
			switch (sleepTime) {
				case High:
					GetSleepTime = 8000L;
					break;
				case Medium:
					GetSleepTime = 4000L;
					break;
				case Low:
					GetSleepTime = 2500L;
					break;
				case OneSecond:
					GetSleepTime = 1000L;
					break;	
				case TwoSecond:
					GetSleepTime = 2000L;
					break;	
				case ThreeSecond:
					GetSleepTime = 3000L;
					break;	
				case FourSecond:
					GetSleepTime = 4000L;
					break;
				case FiveSecond:
					GetSleepTime = 5000L;
					break;	
				case SixSecond:
					GetSleepTime = 6000L;
					break;	
				case SevenSecond:
					GetSleepTime = 7000L;
					break;	
				case EightSecond:
					GetSleepTime = 8000L;
					break;	
				case NineSecond:
					GetSleepTime = 9000L;
					break;	
				case TenSecond:
					GetSleepTime = 10000L;
					break;
				case Zero:
					GetSleepTime = 0L;
					break;
			}
			break;
		}
		return GetSleepTime;
	}
}
