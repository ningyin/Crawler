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
		 //�жϵ�ǰϵͳ�Ƿ�֧��Java AWT Desktop��չ
	    if(java.awt.Desktop.isDesktopSupported()){
	        try {
	            //����һ��URIʵ��
	            java.net.URI uri = java.net.URI.create(url); 
	            //��ȡ��ǰϵͳ������չ
	            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
	            //�ж�ϵͳ�����Ƿ�֧��Ҫִ�еĹ���
	            if(dp.isSupported(java.awt.Desktop.Action.BROWSE)){
	                //��ȡϵͳĬ�������������
	                dp.browse(uri);    
	            }
	        } catch(java.lang.NullPointerException e){
	            //��ΪuriΪ��ʱ�׳��쳣
	        } catch (java.io.IOException e) {
	            //��Ϊ�޷���ȡϵͳĬ�������
	        }             
	    }
	}
}
