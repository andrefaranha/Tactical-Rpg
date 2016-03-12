package tilemap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TilemapBuilder {

	private static final String MAP = "map";
	private static final String MAP_WIDTH = "width";
	private static final String MAP_HEIGHT = "height";
	private static final String MAP_TILEWIDTH = "tilewidth";
	private static final String MAP_TILEHEIGHT = "tileheight";

	private static final String TILESET = "tileset";
	private static final String TILESET_FIRSTGID = "firstgid";
	private static final String TILESET_NAME = "name";
	private static final String TILESET_TILEWIDTH = "tilewidth";
	private static final String TILESET_TILEHEIGHT = "tileheight";

	private static final String IMAGE = "image";
	private static final String IMAGE_SOURCE = "source";
	private static final String IMAGE_WIDTH = "width";
	private static final String IMAGE_HEIGHT = "height";

	private static final String LAYER = "layer";
	private static final String LAYER_TYPE = "type";
	private static final String LAYER_TYPE_BACKGROUND = "background";
	private static final String LAYER_TYPE_FOREGROUND = "foreground";
	private static final String LAYER_TYPE_TOP = "top";
	private static final String LAYER_DATA = "data";

	private static final String TILE = "tile";
	private static final String TILE_GID = "gid";

	private static Element getRootElement(File xmlFile) {
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

		doc.normalize();

		return (Element) doc.getElementsByTagName(MAP).item(0);
	}

	private static List<Tileset> getTileSetList(Element mapElement) {
		List<Tileset> tileSet = new ArrayList<Tileset>();

		NodeList tileSetNodeList = mapElement.getElementsByTagName(TILESET);
		for (int i = 0; i < tileSetNodeList.getLength(); i++) {
			Element tileSetElement = (Element) tileSetNodeList.item(i);
			Element tileSetImageElement = (Element) tileSetElement.getElementsByTagName(IMAGE).item(0);

			int firstGid = Integer.parseInt(tileSetElement.getAttribute(TILESET_FIRSTGID));
			String name = tileSetElement.getAttribute(TILESET_NAME);
			int tileWidth = Integer.parseInt(tileSetElement.getAttribute(TILESET_TILEWIDTH));
			int tileHeight = Integer.parseInt(tileSetElement.getAttribute(TILESET_TILEHEIGHT));
			String source = tileSetImageElement.getAttribute(IMAGE_SOURCE);
			int imageWidth = Integer.parseInt(tileSetImageElement.getAttribute(IMAGE_WIDTH));
			int imageHeight = Integer.parseInt(tileSetImageElement.getAttribute(IMAGE_HEIGHT));

			Tileset t = new Tileset(firstGid, name, tileWidth, tileHeight, source, imageWidth, imageHeight);
			tileSet.add(t);
		}

		return tileSet;
	}

	private static List<Element> getLayerWithType(Element mapElement, String layerType) {
		List<Element> layerElementList = new ArrayList<Element>();

		NodeList layerNodeList = mapElement.getElementsByTagName(LAYER);
		for (int i = 0; i < layerNodeList.getLength(); i++) {
			Element layerElement = (Element) layerNodeList.item(i);
			if (layerElement.getAttribute(LAYER_TYPE).equals(layerType))
				layerElementList.add(layerElement);
		}

		return layerElementList;
	}

	private static List<Integer> getLayerDataFromType(Element mapElement, String layerType) {
		List<Integer> layerData = new ArrayList<Integer>();

		for (Element layerElement : getLayerWithType(mapElement, layerType)) {
			Element dataElement = (Element) layerElement.getElementsByTagName(LAYER_DATA).item(0);

			NodeList tileNodeList = dataElement.getElementsByTagName(TILE);
			for (int j = 0; j < tileNodeList.getLength(); j++) {
				Element tileElement = (Element) tileNodeList.item(j);
				layerData.add(Integer.parseInt(tileElement.getAttribute(TILE_GID)));
			}
		}

		return layerData;
	}

	public static Tilemap build(String filepath) {
		Element mapElement = getRootElement(new File(filepath));

		int mapWidth = Integer.parseInt(mapElement.getAttribute(MAP_WIDTH));
		int mapHeight = Integer.parseInt(mapElement.getAttribute(MAP_HEIGHT));
		int tileWidth = Integer.parseInt(mapElement.getAttribute(MAP_TILEWIDTH));
		int tileHeight = Integer.parseInt(mapElement.getAttribute(MAP_TILEHEIGHT));

		TilesetCollection tilesetCollection = new TilesetCollection(tileWidth, tileHeight, getTileSetList(mapElement));

		return new Tilemap(mapWidth, mapHeight,
				new TilemapLayer(mapWidth, mapHeight, getLayerDataFromType(mapElement, LAYER_TYPE_BACKGROUND),
						tilesetCollection),
				new TilemapLayer(mapWidth, mapHeight, getLayerDataFromType(mapElement, LAYER_TYPE_FOREGROUND),
						tilesetCollection),
				new TilemapLayer(mapWidth, mapHeight, getLayerDataFromType(mapElement, LAYER_TYPE_TOP),
						tilesetCollection));
	}
}
