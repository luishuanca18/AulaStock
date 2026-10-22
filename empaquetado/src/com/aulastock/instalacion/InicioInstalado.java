package com.aulastock.instalacion;

import com.aulastock.vista.FrmPrincipal;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JOptionPane;

/** Punto de entrada exclusivo de la distribucion de Windows. */
public class InicioInstalado {
    public static void main(String[] args) {
        try {
            Path configuracion = Path.of(System.getenv("LOCALAPPDATA"), "AulaStock");
            if (!Files.exists(configuracion.resolve("database.properties"))) {
                Files.createDirectories(configuracion);
                Path carpetaApp = Path.of(InicioInstalado.class.getProtectionDomain()
                        .getCodeSource().getLocation().toURI()).getParent();
                String powershell = Path.of(System.getenv("WINDIR"), "System32",
                        "WindowsPowerShell", "v1.0", "powershell.exe").toString();
                Process preparacion = new ProcessBuilder(powershell, "-NoProfile", "-STA",
                        "-ExecutionPolicy", "Bypass", "-WindowStyle", "Hidden", "-File",
                        carpetaApp.resolve("PrepararAulaStock.ps1").toString(),
                        "-ConfigDirectory", configuracion.toString())
                        .redirectErrorStream(true)
                        .redirectOutput(configuracion.resolve("preparacion.log").toFile()).start();
                int resultado = preparacion.waitFor();
                if (resultado != 0 || !Files.exists(configuracion.resolve("database.properties"))) {
                    return;
                }
            }
            FrmPrincipal.main(args);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "No se pudo iniciar AulaStock. "
                    + error.getMessage(), "AulaStock", JOptionPane.ERROR_MESSAGE);
        }
    }
}
