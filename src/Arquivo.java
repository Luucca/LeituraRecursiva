import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Arquivo {

    private String nome;
    private byte[] bytes;
    private String tipo;
    private String extensao;

    public Arquivo() {
    }

    public Arquivo(File file) throws IOException {
        this.nome = file.getName();
        this.bytes = Files.readAllBytes(Paths.get(file.getPath()));
        this.extensao = this.nome.split("\\.")[this.nome.split("\\.").length - 1];
    }

    public Arquivo(String nome, byte[] bytes, String tipo, String extensao) {
        this.nome = nome;
        this.bytes = bytes;
        this.tipo = tipo;
        this.extensao = extensao;
    }

    public static Arquivo criarArquivoTipado(File file) throws IOException {
        Arquivo arquivo = new Arquivo();
        arquivo.setNome(file.getName());
        arquivo.setBytes(Files.readAllBytes(Paths.get(file.getPath())));
        String tipoExt = arquivo.getNome().split("_", 2)[1];
        arquivo.setTipo(tipoExt.split("\\.")[0]);
        arquivo.setExtensao(tipoExt.split("\\.")[tipoExt.split("\\.").length - 1]);
        return arquivo;
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
}
