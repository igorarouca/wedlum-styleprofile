package com.wedlum.styleprofile.web;

import winstone.Launcher;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Launcher.main("--webroot src/main/webapp".split(" "));
    }
}
