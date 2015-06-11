package utillity;
import java.io.IOException;
import java.util.Iterator;
import java.util.PrimitiveIterator.OfDouble;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dataStructure.Journal;
import dataStructure.Link;

public class Utillity {
	
	public static void main(String[] args) {
		
		openUrl("www.baidu.com");

	}

	public static void openUrl(String url)
	{
		 //判断当前系统是否支持Java AWT Desktop扩展
	    if(java.awt.Desktop.isDesktopSupported()){
	        try {
	            //创建一个URI实例
	            java.net.URI uri = java.net.URI.create(url); 
	            //获取当前系统桌面扩展
	            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
	            //判断系统桌面是否支持要执行的功能
	            if(dp.isSupported(java.awt.Desktop.Action.BROWSE)){
	                //获取系统默认浏览器打开链接
	                dp.browse(uri);    
	            }
	        } catch(java.lang.NullPointerException e){
	            //此为uri为空时抛出异常
	        } catch (java.io.IOException e) {
	            //此为无法获取系统默认浏览器
	        }             
	    }
	}
}
