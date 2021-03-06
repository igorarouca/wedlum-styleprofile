package com.wedlum.styleprofile;

import java.io.File;
import java.io.IOException;

import winstone.Launcher;

import com.wedlum.styleprofile.domain.photo.PhotoSourceUsingFileSystem;

/** Runs Winstone (Servlet Container) to enable manual tests and validations (it's lighter than Tomcat) */
public class Main {

	public static void main(String[] args) throws IOException {
        PhotoSourceUsingFileSystem.STORAGE = new File("src/main/webapp/photo-storage");
        String webroot = (args.length >0)?args[0]:"src/main/webapp";
        Launcher.main(("--webroot " + webroot).split(" "));
    }

}
