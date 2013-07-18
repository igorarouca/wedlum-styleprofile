package com.wedlum.styleprofile.domain.profile;

public class Step {
    public final String name;
    public final String type;
    public final Object data;

    public Step(String name, String type, String... data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
