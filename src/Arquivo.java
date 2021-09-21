import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Arquivo {

    private String codigo;
    private String nome;
    private byte[] bytes;
    private String tipo;
    private String extensao;
    private final String caminho;

    public Arquivo(String caminho) {
        this.caminho = caminho;
    }

    public Arquivo(File file) throws IOException {
        this.nome = file.getName();
        this.caminho = file.getPath();
        this.bytes = Files.readAllBytes(Paths.get(file.getPath()));
        this.extensao = getExtensao(file);
    }

    public static Arquivo criarArquivoTipado(File file) throws IOException {
        Arquivo arquivo = new Arquivo(file.getPath());
        try {
            arquivo.setNome(file.getName());
            arquivo.setBytes(Files.readAllBytes(Paths.get(file.getPath())));
            arquivo.setExtensao(getExtensao(file));
            String tipoExt = arquivo.getNome().split("_", 2)[1];
            arquivo.setCodigo(arquivo.getNome().split("_")[0]);
            arquivo.setTipo(tipoExt.split("\\.")[0]);
        }catch (Exception ex) {
            arquivo = new Arquivo(file);
        }

        return arquivo;
    }

    public static String getExtensao(File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return name.substring(index + 1);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String[] getRootList() {
        if(Gerenciador.CAMINHO_INICIAL != null) {
            String path = this.caminho.replace(Gerenciador.CAMINHO_INICIAL, "").replace(this.nome, "");
            return Arrays.stream(path.split("/")).filter(s -> !s.isEmpty()).toArray(String[]::new);
        }

        return new String[0];
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
