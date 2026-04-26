import service.OrganizadorService;
import service.WatcherService;

public class Main {

    public static void main(String[] args) {

        System.out.println(" Organizador iniciado...");

        OrganizadorService service = new OrganizadorService();
        WatcherService watcher = new WatcherService(service);

        try {
            service.organizarDownloads();
            Thread.sleep(2000);
            watcher.iniciar();
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }

        System.out.println(" Finalizado.");
    }

}