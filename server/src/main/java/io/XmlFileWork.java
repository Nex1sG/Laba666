package io.interfaceXml;
import java.io.IOException;

public interface XmlFileWork {

    String saveToFile(String filename) throws IOException;

    String loadFromFile(String filename) throws IOException;
}