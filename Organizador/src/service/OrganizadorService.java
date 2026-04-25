package service;


import util.TipoArquivoUtil;

import java.io.IOException;
import java.nio.file.*;

public class OrganizadorService {

    private final String DOWNLOADS =
            System.getProperty("user.home") + "/Downloads";

    public void organizarDownloads() throws IOException {

        Path pastaDownloads = Paths.get(DOWNLOADS);

        if (!Files.exists(pastaDownloads)) {
            System.out.println(" Pasta Downloads não encontrada.");
            return;
        }

        Files.list(pastaDownloads).forEach(arquivo -> {
            try {
                organizarArquivo(arquivo, pastaDownloads);
            } catch (IOException e) {
                System.err.println("Erro ao mover: " + arquivo.getFileName());
            }
        });

        System.out.println("✔ Organização concluída!");
    }

    private void organizarArquivo(Path arquivo, Path base) throws IOException {

        if (Files.isDirectory(arquivo)) return;

        String nome = arquivo.getFileName().toString();

        String destino = TipoArquivoUtil.definirTipo(nome);

        Path pastaDestino = base.resolve(destino);

        if (!Files.exists(pastaDestino)) {
            Files.createDirectory(pastaDestino);
        }

        Path novoCaminho = pastaDestino.resolve(arquivo.getFileName());

        if (Files.exists(novoCaminho)) {
            String novoNome = System.currentTimeMillis() + "_" + arquivo.getFileName();
            novoCaminho = pastaDestino.resolve(novoNome);
        }

        Files.move(arquivo, novoCaminho, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("📦 Movido: " + arquivo.getFileName() + " → " + destino);
    }
}
