import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.*;

public class OrganizadorDownloads {

    private static final String DOWNLOADS =
            System.getProperty("user.home") + "\\Downloads";

    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        System.out.println(" Organizador iniciado...");

        scheduler.scheduleAtFixedRate(() -> {
            try {
                organizar();
            } catch (Exception e) {
                System.err.println("Erro ao organizar: " + e.getMessage());
            }
        }, 0, 10, TimeUnit.MINUTES);
    }

    public static void organizar() throws IOException {

        Path pastaDownloads = Paths.get(DOWNLOADS);

        if (!Files.exists(pastaDownloads)) {
            System.out.println(" Pasta Downloads não encontrada.");
            return;
        }

        Files.list(pastaDownloads).forEach(arquivo -> {
            try {
                organizarArquivo(arquivo, pastaDownloads);
            } catch (IOException e) {
                System.err.println("Erro ao mover arquivo: " + arquivo.getFileName());
            }
        });

        System.out.println("✔ Organização concluída em: " + java.time.LocalTime.now());
    }

    private static void organizarArquivo(Path arquivo, Path base) throws IOException {

        if (Files.isDirectory(arquivo)) return;

        String nome = arquivo.getFileName().toString().toLowerCase();

        String destino;

        if (nome.endsWith(".jpg") || nome.endsWith(".png") || nome.endsWith(".jpeg")) {
            destino = "Imagens";

        } else if (nome.endsWith(".pdf") || nome.endsWith(".docx") || nome.endsWith(".txt")) {
            destino = "Documentos";

        } else if (nome.endsWith(".mp4") || nome.endsWith(".mkv") || nome.endsWith(".avi")) {
            destino = "Videos";

        } else if (nome.endsWith(".zip") || nome.endsWith(".rar")) {
            destino = "Compactados";

        } else {
            destino = "Outros";
        }

        Path pastaDestino = base.resolve(destino);

        if (!Files.exists(pastaDestino)) {
            Files.createDirectory(pastaDestino);
        }

        Path novoCaminho = pastaDestino.resolve(arquivo.getFileName());

        // 🔥 Evita sobrescrever arquivos
        if (Files.exists(novoCaminho)) {
            String novoNome = System.currentTimeMillis() + "_" + arquivo.getFileName();
            novoCaminho = pastaDestino.resolve(novoNome);
        }

        Files.move(arquivo, novoCaminho, StandardCopyOption.REPLACE_EXISTING);

        // 🔥 Log
        System.out.println("📦 Movido: " + arquivo.getFileName() + " → " + destino);
    }
}