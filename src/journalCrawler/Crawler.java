package journalCrawler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utillity.Utillity;
import dataStructure.Journal;
import dataStructure.Link;

public class Crawler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String home = "http://academic.research.microsoft.com/Detail?entitytype=8&searchtype=4&id=4376";
		String filename="AcademicKeywordsBusiness Process ManagementJournals (178).bin";
		
		try {
//			Vector<Journal> journals=getJournals(home);
//			saveJournalsToFiles(filename, journals);
			Vector<Journal> journals = loadJournalsToFiles(filename);
			for(Journal j:journals)
			{
				System.out.println(j);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Vector<Journal> loadJournalsToFiles(final String filename) throws IOException, ClassNotFoundException{
	        FileInputStream fis = new FileInputStream(filename);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        Vector<Journal> journals = (Vector<Journal>) ois.readObject();
	        ois.close();
	        fis.close();
	    return journals;
	}

	@SuppressWarnings("unused")
	private static void saveJournalsToFiles(final String filename,
			final Vector<Journal> journals) throws IOException {
	        // Open a file to write to, named SavedCases.sav.
	        FileOutputStream saveFile = new FileOutputStream(filename);
	        ObjectOutputStream save = new ObjectOutputStream(saveFile);
	        save.writeObject(journals);
	        save.close(); 
	}

	@SuppressWarnings("unused")
	private static Vector<Journal> getJournals(String home) throws IOException {
		int start=1;
		int step=50;
		int end=start+step-1;
		Vector<Journal> journals = new Vector<Journal>();
		
		int count=0;
		do {
			String url=String.format(home+"&start=%d&end=%d", start,end);
			count=addJournalFromUrl(url, journals);
			start=end+1;
			end=start+step-1;
		} while (count>0);
		
		return journals;
	}

	private static int addJournalFromUrl(String url, Vector<Journal> journals)
			throws IOException {
		int count=0;
		Elements items = Jsoup.connect(url).get()
				.getElementsByClass("journal-item");
		// System.out.println(document.toString());
		for (Element e : items) {
			// basic information
			Journal j = parseJournal(e);
			boolean success = false;
			do {
				// Utillity.openUrl(j.title.href);
				// Thread.sleep(3000);
				try {
					// 有可能请求过快而弹出验证框，需要人为操作
					Elements jCard = Jsoup.connect(j.title.href).get()
							.getElementsByClass("conference-card");
					Elements linkEles = jCard.first().getElementsByTag("a");
					j.field = new Link(linkEles.get(0));
					j.HomePage = new Link(linkEles.get(1)).href;
					success = true;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					Utillity.openUrl(((org.jsoup.HttpStatusException) e1)
							.getUrl());
					System.out.print("to continue:");
					byte[] b = new byte[1024];
					try {
						System.in.read(b);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					// String choice = new String(b);
					// choice = choice.substring(0, choice.indexOf('\n'));
				}
				journals.add(j);
				System.out.println(j);
				count++;
			} while (!success);
			// if (j.HomePage=="")
			// {
			// System.out.println(j);
			// Utillity.openUrl(j.title.href);
			// // System.out.println(j.field);
			// // System.out.println(j.HomePage);
			// }
		}
		return count;
	}

	private static Journal parseJournal(Element e) {
		Journal j = new Journal();
		String siteUrlPrefix = "http://academic.research.microsoft.com/";
		// get 3 links
		Elements linkEles = e.getElementsByTag("a");

		j.title = new Link(linkEles.get(0), siteUrlPrefix);
		j.totalPublications = new Link(linkEles.get(1), siteUrlPrefix);
		j.relatedPublications = new Link(linkEles.get(2), siteUrlPrefix);

		Elements spanEles = e.getElementsByTag("span");
		String text, targetNum;
		text = spanEles.get(1).text();
		targetNum = text.replaceAll("Citation Count: ", "");
		if (text.length() > targetNum.length()) {
			j.citation = Integer.valueOf(targetNum.replaceAll(",", ""));
		} else {
			System.out.println("Warning: Citation Count Not Found in "
					+ j.title);
		}

		text = spanEles.get(3).text();
		targetNum = text.replaceAll("Year Range: ", "");
		if (text.length() > targetNum.length()) {
			String[] targetNums = targetNum.split("-");
			j.startYaer = Integer.valueOf(targetNums[0]);
			j.endYaer = Integer.valueOf(targetNums[1]);
		} else {
			System.out.println("Warning: Year Range Count Not Found in "
					+ j.title);
		}
		// System.out.println(j);
		return j;
	}

}
