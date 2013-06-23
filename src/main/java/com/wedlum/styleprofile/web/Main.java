package com.wedlum.styleprofile.web;

import com.wedlum.styleprofile.business.model.PhotoSourceUsingFileSystem;
import winstone.Launcher;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PhotoSourceUsingFileSystem.STORAGE = new File("src/main/webapp/photo-storage");
        String webroot = (args.length >0)?args[0]:"src/main/webapp";
        Launcher.main(("--webroot " + webroot).split(" "));
    }
}
