package map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TMXReader {

	private static File getFile(String filepath) {
		return new File(filepath);
	}
	
	private static Document getDocument(File xmlFile) {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document doc = null;
		
		try {
			doc = docBuilder.parse(xmlFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;
	}
	
	private static Element getTileSetImageElement(Element tileSetElement) {
		return (Element) tileSetElement.getElementsByTagName("image").item(0);
	}
	
	private static List<TileSet> getTileSetFromDocument(Document doc) {
		List<TileSet> tileSet = new ArrayList<TileSet>();
		
		NodeList tileSetNodeList = doc.getElementsByTagName("tileset");
		for (int i = 0; i < tileSetNodeList.getLength(); i++) {
			Node tileSetNode = tileSetNodeList.item(i);
			Element tileSetElement = (Element) tileSetNode;
			Element tileSetImageElement = getTileSetImageElement(tileSetElement);
			
			int firstGid = Integer.parseInt(tileSetElement.getAttribute("firstgid"));
			String name = tileSetElement.getAttribute("name");
			int tileWidth = Integer.parseInt(tileSetElement.getAttribute("tilewidth"));
			int tileHeight = Integer.parseInt(tileSetElement.getAttribute("tileheight"));
			String source = tileSetImageElement.getAttribute("source");
			int imageWidth = Integer.parseInt(tileSetImageElement.getAttribute("width"));
			int imageHeight = Integer.parseInt(tileSetImageElement.getAttribute("height"));
			
			TileSet t = new TileSet(firstGid, name, tileWidth, tileHeight, source, imageWidth, imageHeight);
			tileSet.add(t);
		}
		
		return tileSet;
	}
	
	private static List<Integer> getDataFromDocument(Document doc) {
		List<Integer> dataList = new ArrayList<Integer>();
		
		NodeList layerNodeList = doc.getElementsByTagName("layer");
		for (int i = 0; i < layerNodeList.getLength(); i++) {
			Node layerNode = layerNodeList.item(i);
			Element layerElement = (Element) layerNode;
			
			Node dataNode = layerElement.getElementsByTagName("data").item(0);
			Element dataElement = (Element) dataNode;
			NodeList tileNodeList = dataElement.getElementsByTagName("tile");
			List<Integer> layerData = new ArrayList<Integer>(); 
			for (int j = 0; j < tileNodeList.getLength(); j++) {
				Node tileNode = tileNodeList.item(j);
				Element tileElement = (Element) tileNode;
				layerData.add(Integer.parseInt(tileElement.getAttribute("gid")));
			}
			
			dataList.addAll(layerData);
		}
		
		return dataList;
	}
	
	public static TileMap read(String xmlFilepath) {
		Document doc = getDocument(getFile(xmlFilepath));
		doc.getDocumentElement().normalize();
		Node mapNode = doc.getElementsByTagName("map").item(0);
		System.out.println(mapNode.getNodeName());
		
		Element mapElement = (Element) mapNode;
		int mapWidth = Integer.parseInt(mapElement.getAttribute("width"));
		int mapHeight = Integer.parseInt(mapElement.getAttribute("height"));
		int tileWidth = Integer.parseInt(mapElement.getAttribute("tilewidth"));
		int tileHeight = Integer.parseInt(mapElement.getAttribute("tileheight"));
		System.out.println(mapWidth + ", " + mapHeight + ", " + tileWidth + ", " + tileHeight);
		
		TileMap map = new TileMap(mapWidth, mapHeight, tileWidth, tileHeight);
		
		map.setTileSet(getTileSetFromDocument(doc));
		map.setData(getDataFromDocument(doc));
		
		return map;
	}
	
	public static void main(String[] args) {
		TMXReader.read("resources/example.tmx");
	}
}
