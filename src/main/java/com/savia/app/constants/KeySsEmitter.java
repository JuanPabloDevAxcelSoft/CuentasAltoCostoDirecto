package com.savia.app.constants;

public enum KeySsEmitter {

    KEY_INIT("INIT_SUBCRIPTION_NOTIFICATION"),
    KEY_ULTIMAS_NOTICIAS("NUEVOS_MENSAJES_USUARIOS"),

    KEY_INIT_GERERAR("INIT_GENERAR_EXCEL"),
    KEY_PROCESS_GERERAR("PROCESS_GENERAR_EXCEL");
    

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
