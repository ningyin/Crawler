package dataStructure;

import java.io.Serializable;

import org.jsoup.nodes.Element;

public class Link implements Serializable{
	
	public String text;
	public String href;
	
	public Link(Element linkEle, String hrefPrefix) {
		super();
		if (linkEle!=null)
		{
			this.text = linkEle.text();
			this.href = hrefPrefix+linkEle.attr("href");
		}
//		System.out.println(this);
	}
	
	public Link(Element linkEle) {
		super();
		if (linkEle!=null)
		{
			this.text = linkEle.text();
			this.href = linkEle.attr("href");
		}
//		System.out.println(this);
	}
	
	public Link(String text, String href) {
		super();
		this.text = text;
		this.href = href;
	}

	@Override
	public String toString() {
		return "Link [text=" + text + ", href=" + href + "]";
	}
	
}
