package service;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class WatcherService {

    private final Path pastaDownloads;
    private final OrganizadorService organizadorService;

    public WatcherService(OrganizadorService organizadorService) {
        this.organizadorService = organizadorService;
        this.pastaDownloads = Paths.get(System.getProperty("user.home") + "/Downloads");
    }

    public void iniciar() throws IOException, InterruptedException {

        WatchService watchService = FileSystems.getDefault().newWatchService();

        pastaDownloads.register(watchService, ENTRY_CREATE);

        System.out.println(" Monitorando Downloads...");

        while (true) {

            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {

                Path arquivo = pastaDownloads.resolve((Path) event.context());

                System.out.println("Novo arquivo: " + arquivo.getFileName());

                try {
                    Thread.sleep(1500); // espera terminar download
                    organizadorService.organizarArquivoUnico(arquivo);
                } catch (Exception e) {
                    System.err.println("Erro ao processar arquivo");
                }
            }

            key.reset();
        }
    }
}
