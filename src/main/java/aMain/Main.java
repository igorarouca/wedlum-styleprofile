package aMain;

import winstone.Launcher;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Launcher.main("--webroot target/wedlum-styleprofile".split(" "));
    }
}
