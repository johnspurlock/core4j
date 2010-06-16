package org.core4j.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.core4j.xml.XDocument;
import org.core4j.xml.XElement;
import org.core4j.xml.XmlFormat;


public class TestXDocument {

	
	public static void main(String[] args) throws Exception {
		
		
		//print("RSS-CONNECTEDSHOW");
//		parse("xml1");
		print("RSS-THISWEEK");
//		parse("RSS-ITCONVERSATIONS");
		
	}
	
	private static void test1(){
		XDocument doc = XDocument.parse("<a foo='bar' foo2='bax'><b><c/></b><d><b>boom</b></d></a>");
		
		System.out.println(doc);
		
		System.out.println("\ndescendants()");
		for(XElement e : doc.descendants())
			System.out.println(e);
		
		System.out.println("\ndescendants(b)");
		for(XElement e : doc.descendants("b"))
			System.out.println(e);
		
		System.out.println("\ndoc.element(a)");
		System.out.println(doc.element("a"));
		
	}
	
	
	private static void parse(String name) throws IOException {
		XDocument xml1 = XDocument.loadUtf8(new FileInputStream(new File("src-test/META-INF/" + name + ".xml")));
		
		System.out.println("\n"+name);
		System.out.println(xml1);
	}
	
	private static void print(String name) throws IOException {
		XDocument xml1 = XDocument.loadUtf8(new FileInputStream(new File("src-test/META-INF/" + name + ".xml")));
		
		System.out.println("\n"+name);
		System.out.println(xml1.toString(XmlFormat.INDENTED));
	}
	
	


}
