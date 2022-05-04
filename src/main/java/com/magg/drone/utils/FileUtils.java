package com.magg.drone.utils;

import com.magg.drone.exception.DeliveryFileProcessingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for File Handling.
 */
public class FileUtils
{

    public static List<String> readFile(String filePath) {

        Path path = Paths.get(filePath);

        List<String> list;
        try {
            list = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DeliveryFileProcessingException("File processing issue", e);
        }
        return list;
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path: ");
        return scanner.nextLine();
    }

    public static void createFile(List<String> list, String filePath) {

        Path path = Paths.get(filePath);

        try {
            Files.write(path, list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DeliveryFileProcessingException("File creation issue", e);
        }
    }
}
