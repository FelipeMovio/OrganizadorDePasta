import service.OrganizadorService;
import service.WatcherService;

public class Main {

    public static void main(String[] args) {

        System.out.println(" Organizador iniciado...");

        try {
            OrganizadorService service = new OrganizadorService();
            WatcherService watcher = new WatcherService(service);

            watcher.iniciar();
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }

        System.out.println(" Finalizado.");
    }

}