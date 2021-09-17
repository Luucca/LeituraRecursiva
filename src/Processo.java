import java.io.File;
import java.util.ArrayList;

public class Processo {

    private String id;
    private ArrayList<Arquivo> arquivos;
    private ArrayList<Processo> processos;

    public Processo() {
    }

    public Processo(String id, ArrayList<Arquivo> arquivos) {
        this.id = id;
        this.arquivos = arquivos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(ArrayList<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public void addArquivo(Arquivo arquivo) {
        if(arquivos == null) {
            arquivos = new ArrayList<>();
        }
        arquivos.add(arquivo);
    }

    public ArrayList<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(ArrayList<Processo> processos) {
        this.processos = processos;
    }
}
