package org.core4j.xml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.core4j.Enumerable;
import org.core4j.Enumerables;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XDocument extends XContainer {

	private XElement documentElement;
	
	
	public XDocument(XElement documentElement){
		this.documentElement = documentElement;
	}
	
	private XDocument(Document document){
		
		this.documentElement = parse(document.getDocumentElement());
	}
	
	@Override
	protected XElement getXElement() {
		return documentElement;
	}
	
	public XElement getRoot(){
		return documentElement;
	}
	
	@Override
	public XmlNodeType getNodeType() {
		return XmlNodeType.DOCUMENT;
	}
	
	@Override
	public Enumerable<XNode> nodes() {
		return Enumerable.create(documentElement).cast(XNode.class);
	}
	

	public static XDocument parse(String text){
		return load(new StringReader(text));
	}
	
	
	public static XDocument loadUtf8(InputStream inputStream) {
		try {
			return load(new InputStreamReader(inputStream,"UTF8"));
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public static XDocument load(Reader reader) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(reader));
            return new XDocument(document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	
	
}
