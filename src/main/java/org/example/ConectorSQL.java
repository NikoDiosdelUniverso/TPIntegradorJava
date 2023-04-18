package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConectorSQL {
    private String usuario;
    private String contrasenia;
    private String url;
    private String driver;
    private String rutaconfiguracion;

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public ConectorSQL(String rutaconfiguracion) {
        this.rutaconfiguracion = rutaconfiguracion;
    }

    public void leerconfiguracion () throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(rutaconfiguracion));
        reader.readLine(); // ignora el encabezado
        String linea; while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(",");
            this.usuario = partes[0];
            this.contrasenia = partes[1];
            this.driver = partes[2];
            this.url = partes[3];
        }
        reader.close();
    }
}
