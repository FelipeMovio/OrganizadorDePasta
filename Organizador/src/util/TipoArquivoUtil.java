package util;

public class TipoArquivoUtil {

    public static String definirTipo(String nomeArquivo) {

        String nome = nomeArquivo.toLowerCase();

        if (nome.endsWith(".jpg") || nome.endsWith(".png") || nome.endsWith(".jpeg")) {
            return "Imagens";
        }

        if (nome.endsWith(".pdf") || nome.endsWith(".docx") || nome.endsWith(".txt")) {
            return "Documentos";
        }

        if (nome.endsWith(".mp4") || nome.endsWith(".mkv") || nome.endsWith(".avi")) {
            return "Videos";
        }

        if (nome.endsWith(".zip") || nome.endsWith(".rar")) {
            return "Compactados";
        }

        return "Outros";
    }
}
