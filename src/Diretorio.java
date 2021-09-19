import java.util.ArrayList;
import java.util.Collections;

public class Diretorio {

    private String id;
    private final ArrayList<Arquivo> arquivos;
    private final ArrayList<Diretorio> diretorios;

    public Diretorio() {
        this.arquivos = new ArrayList<>();
        this.diretorios = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Arquivo> getArquivos(boolean recursivo) {
        ArrayList<Diretorio> diretorios = new ArrayList<>(this.diretorios);
        diretorios.add(this);
        return getArquivos(diretorios, recursivo);
    }

    private ArrayList<Arquivo> getArquivos(ArrayList<Diretorio> diretorios, boolean recursivo) {
        if(!recursivo) {
            return this.arquivos;
        }

        ArrayList<Arquivo> lista = new ArrayList<>();

        if(diretorios != null) {
            for (Diretorio diretorio : diretorios) {
                lista.addAll(diretorio.getArquivos(false));
                if(diretorio != this) {
                    ArrayList<Diretorio> subDiretorios = diretorio.getDiretorios(false);
                    if(subDiretorios != null && subDiretorios.size() > 0) {
                        lista.addAll(getArquivos(subDiretorios, true));
                    }
                }
            }
        }

        return lista;
    }

    public ArrayList<Diretorio> getDiretorios(boolean recursivo) {
        return getDiretorios(diretorios, recursivo);
    }

    private ArrayList<Diretorio> getDiretorios(ArrayList<Diretorio> diretorios, boolean recursivo) {
        if(!recursivo) {
            return this.diretorios;
        }

        ArrayList<Diretorio> lista = new ArrayList<>();

        if(diretorios != null) {
            lista.addAll(diretorios);
            for(Diretorio diretorio : diretorios) {
                ArrayList<Diretorio> subDiretorios = diretorio.getDiretorios(false);
                if(subDiretorios != null && subDiretorios.size() > 0) {
                    lista.addAll(getDiretorios(subDiretorios, true));
                }
            }
        }

        return lista;
    }

    public void imprimirArvore(boolean detalhar) {
        Logger.imprimirArvore(new ArrayList<>(Collections.singletonList(this)), detalhar, 1);
    }

    public void addArquivo(Arquivo arquivo) {
        arquivos.add(arquivo);
    }

    public void addDiretorio(Diretorio diretorio) {
        diretorios.add(diretorio);
    }

    public int getQtdeArquivos() {
        return getQtdeArquivos(false);
    }

    public int getQtdeArquivos(boolean contarDiretorios) {
        if(contarDiretorios) {
            return getArquivos(false).size();
        }

        return this.arquivos.size();
    }

    public int getQtdeDiretorios() {
        return Math.min(0, this.diretorios.size() - this.arquivos.size());
    }
}
