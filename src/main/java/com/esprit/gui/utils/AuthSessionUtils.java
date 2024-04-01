package com.esprit.gui.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthSessionUtils {
    public static String getCurrentUser() {
        String path = "set-id.txt";
        String line = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                return line;
            }
        } catch (IOException e) {
            System.out.println("cannot login");
        }
        return line;
    }

    public static void LogOut() {
        try {
            Files.deleteIfExists(Paths.get("set-id.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
