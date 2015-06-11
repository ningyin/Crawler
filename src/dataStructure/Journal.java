package dataStructure;

import java.io.Serializable;

public class Journal implements Serializable  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// <a id="ctl00_MainContent_ObjectList_ctl00_name" onmousedown="try{return si(7,'ctl00_MainContent_ObjectList','1');}catch(ex){;}" href="Journal/16952/business-process-management-journal">Business Process Management Journal</a>
	public Link title;
	
	//<a id="ctl00_MainContent_ObjectList_ctl00_hlPublication" href="Detail?entitytype=4&amp;searchtype=2&amp;id=16952&amp;orderby=2">Publications: 581</a>
	public Link totalPublications;
	
	public int citation;
	
	public int startYaer;
	
	public int endYaer;
	
	// <a id="ctl00_MainContent_ObjectList_ctl02_RelationEvidence_hypEvidence" href="/PublicationList?srcType=8&amp;desType=4&amp;srcID=4376&amp;desID=52">14 publication(s) in this journal</a>
	public Link relatedPublications;
	
	// <a href="http://academic.research.microsoft.com/RankList?entitytype=4&amp;topDomainID=7&amp;subDomainID=13&amp;last=0&amp;start=1&amp;end=100">Business Administration &amp; Economics</a>
	public Link field;
	
	// 
	public String HomePage;

	@Override
	public String toString() {
		return "Journal [title=" + title + ", totalPublications="
				+ totalPublications + ", citation=" + citation + ", startYaer="
				+ startYaer + ", endYaer=" + endYaer + ", relatedPublications="
				+ relatedPublications + ", field=" + field + ", HomePage="
				+ HomePage + "]";
	}
	
	
}
