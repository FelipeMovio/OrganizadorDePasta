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

                if (event.kind() != ENTRY_CREATE) continue;

                Path arquivo = pastaDownloads.resolve((Path) event.context());

                if (Files.isDirectory(arquivo)) continue;

                System.out.println("Novo arquivo: " + arquivo.getFileName());

                try {
                    esperarArquivoCompleto(arquivo);
                    organizadorService.organizarArquivoUnico(arquivo);
                } catch (Exception e) {
                    System.err.println("Erro ao processar arquivo");
                }
            }

            key.reset();
        }
    }

    private void esperarArquivoCompleto(Path arquivo) throws InterruptedException {

        long tamanhoAnterior = -1;

        while (true) {
            try {
                long tamanhoAtual = Files.size(arquivo);

                if (tamanhoAtual == tamanhoAnterior) {
                    break;
                }

                tamanhoAnterior = tamanhoAtual;
                Thread.sleep(1000);

            } catch (Exception e) {
                break;
            }
        }
    }
}
