package ui;

public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new DashBoard("Sistema de Gestión");
        });
    }
}
// This class serves as the entry point for the application, initializing the dashboard window.