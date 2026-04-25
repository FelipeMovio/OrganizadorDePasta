import service.OrganizadorService;

public class Main {

    public static void main(String[] args) {

        System.out.println(" Organizador iniciado...");

        OrganizadorService service = new OrganizadorService();

        try {
            service.organizarDownloads();
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }

        System.out.println(" Finalizado.");
    }

}