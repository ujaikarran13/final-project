package com.techelevator.vee;

import com.techelevator.util.BasicFileStorage;
import com.techelevator.util.exception.FileStorageException;
import com.techelevator.util.xml.BasicXmlBuilder;
import com.techelevator.util.xml.BasicXmlParser;
import com.techelevator.vee.model.Vacation;

/**
 * VacationStorage is a class for saving a vacation to a file so that it be loaded back
 * in later. It depends on the Vacation object's ability to provide and populate its data
 * in basic XML, and it depends on an object that implements the BasicFileStorage interface
 * to handle the details of interacting with the file system.
 */
 
public class VacationStorage {

    private final BasicFileStorage fileStorage;

    public VacationStorage(BasicFileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public Vacation readVacationFromFile(String filename) throws FileStorageException {
        String contents = fileStorage.readContentsOfFile(filename);
        BasicXmlParser parser = new BasicXmlParser(contents);
        String innerXml = parser.getStringContent("vacation");
        if (innerXml == null || innerXml.isEmpty()) {
            throw new FileStorageException("No vacation data found in " + filename);
        } else {
            Vacation result = new Vacation();
            result.initializeFromXml(innerXml);
            return result;
        }
    }

    public void writeVacationToFile(Vacation vacation, String filename) throws FileStorageException {
        //Example: calling a static method
        String xml = BasicXmlBuilder.createSingleElement("vacation", vacation.getInnerXml());
        fileStorage.writeContentsToFile(xml, filename);
    }
    

}
