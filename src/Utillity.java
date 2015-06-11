import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Utillity {

	public static void main(String[] args) {
		
		String url="http://academic.research.microsoft.com/Detail?entitytype=8&searchtype=4&id=4376&start=1&end=100";
		
		try {
			Document document = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
