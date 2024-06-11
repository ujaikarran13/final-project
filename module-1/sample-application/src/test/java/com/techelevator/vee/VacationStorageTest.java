package com.techelevator.vee;

import com.techelevator.util.BasicFileStorage;
import com.techelevator.util.exception.FileStorageException;
import com.techelevator.vee.model.Vacation;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class VacationStorageTest {

    @Test
    public void canStoreVacation() throws FileStorageException {
        // arrange
        final Vacation vacation = new Vacation();
        final StringStorage backingStore = new StringStorage();
        final VacationStorage storage = new VacationStorage(backingStore);
        final String filename = "target-vacation-file";

        // act
        storage.writeVacationToFile(vacation, filename);

        // assert
        final String fileContent = backingStore.content.get(filename);
        Assert.assertTrue("no content was written", fileContent.length() > 0);
        Assert.assertTrue("unexpected root element", fileContent.startsWith("<vacation>"));
    }

    @Test(expected = FileStorageException.class)
    public void whenRestoringFromEmptyFile_throw() throws FileStorageException {

        // arrange
        final StringStorage backingStore = new StringStorage();
        final VacationStorage storage = new VacationStorage(backingStore);
        String filename = "doesnotexist";
        backingStore.content.put(filename, "");

        // act
        final Vacation copy = storage.readVacationFromFile(filename);

    }

    @Test
    public void canRestoreFromStorage() throws FileStorageException {
        // arrange
        final Vacation vacation = new Vacation();
        vacation.setDestination("storage-unit-tests");
        final StringStorage backingStore = new StringStorage();
        final VacationStorage storage = new VacationStorage(backingStore);
        final String filename = "target-vacation-file";

        // act
        storage.writeVacationToFile(vacation, filename);
        final Vacation copy = storage.readVacationFromFile(filename);

        // assert
        Assert.assertNotNull("vacation was not restored", copy);
        Assert.assertEquals("restored content may be wrong", vacation.getDestination(), copy.getDestination());
    }

    static class StringStorage implements BasicFileStorage {

        public Map<String, String> content = new HashMap<>();

        @Override
        public void writeContentsToFile(String contents, String filename) throws FileStorageException {
            content.put(filename, contents);
        }

        @Override
        public String readContentsOfFile(String filename) throws FileStorageException {
            return content.get(filename);
        }
    }

}
