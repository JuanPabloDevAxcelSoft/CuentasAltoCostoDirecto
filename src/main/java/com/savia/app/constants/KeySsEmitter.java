package com.savia.app.constants;

public enum KeySsEmitter {

    KEY_INIT("INIT_SUBCRIPTION_NOTIFICATION"),
    KEY_ULTIMAS_NOTICIAS("NUEVOS_MENSAJES_USUARIOS");

    private String name;

    private KeySsEmitter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name.toString();
    }

}
