import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public void OrganizarArquivo(Path arquivo, Path base) throws IOException {

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

    if (Files.exists(novoCaminho)) {
        String novoNome = System.currentTimeMillis() + "_" + arquivo.getFileName();
        novoCaminho = pastaDestino.resolve(novoNome);
    }

    Files.move(arquivo, novoCaminho, StandardCopyOption.REPLACE_EXISTING);

    System.out.println(" Movido: " + arquivo.getFileName() + " → " + destino);
}
