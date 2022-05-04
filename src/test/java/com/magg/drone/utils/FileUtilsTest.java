package com.magg.drone.utils;

import com.magg.drone.exception.DeliveryFileProcessingException;
import com.magg.drone.model.Drone;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;

import static com.magg.drone.utils.FileUtils.createFile;
import static com.magg.drone.utils.FileUtils.readFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FileUtilsTest
{

    @Test
    public void readFileTest() throws Exception
    {

        String filename = "files/test.txt";

        File file = getFileFromResource(filename);

        String fullPath = file.getAbsolutePath();

        List<String> lines = readFile(fullPath);
        assertEquals(8, lines.size());

    }


    @Test
    public void writeFileTest() throws Exception
    {
        Path newFilePath = Paths.get("hello.file");

        List<String> lines = List.of("test", "1", "LO", "MAN");
        assertEquals(4, lines.size());
        createFile(lines, newFilePath.toString());

        File file = new File("hello.file");

        List<String> readLines = readFile(newFilePath.toString());
        assertEquals(4, readLines.size());

        file.delete();

    }


    @Test
    public void testInvalidFile() {
        Drone drone =  new Drone();
        DeliveryFileProcessingException ex = assertThrows(
            DeliveryFileProcessingException.class,
            () -> readFile("ts-lol"));
    }

    private File getFileFromResource(String fileName) throws URISyntaxException
    {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

}
