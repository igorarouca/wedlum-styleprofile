package com.wedlum.styleprofile.web;

import com.wedlum.styleprofile.business.model.PhotoSource;
import winstone.Launcher;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PhotoSource.STORAGE = new File("src/main/webapp/photo-storage");
        Launcher.main("--webroot src/main/webapp".split(" "));
    }
}
