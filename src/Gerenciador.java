import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class Gerenciador {

    public static String CAMINHO_INICIAL;
    private final ArrayList<Empreendedor> empreendedores;

    public Gerenciador(String CAMINHO_INICIAL) {
        this.CAMINHO_INICIAL = CAMINHO_INICIAL;
        this.empreendedores = new ArrayList<>();

        File folder = new File(CAMINHO_INICIAL);
        for (final File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                Empreendedor empreendedor = new Empreendedor(file.getName());
                empreendedores.add(empreendedor);
            }
        }
    }

    public ArrayList<Empreendedor> getEmpreendedores() throws IOException {
        for(Empreendedor empreendedor : this.empreendedores) {
            popularDiretorios(empreendedor);
        }
        return this.empreendedores;
    }

    private String getDiretorioEmpreendedor(Empreendedor empreendedor) {
        return Paths.get(this.CAMINHO_INICIAL, empreendedor.getId()).toString();
    }

    public void popularDiretorios(Empreendedor empreendedor) throws IOException {
        if(empreendedor.getId().matches("\\d+")) {
            popularDiretorios(empreendedor, null, Paths.get(CAMINHO_INICIAL, empreendedor.getId()).toString());
        }
    }

    private void popularDiretorios(Empreendedor empreendedor, Diretorio diretorioAtual, String caminho) throws IOException {
        File diretorio = new File(caminho);

        if(diretorio.exists() && Objects.requireNonNull(diretorio.list()).length > 0) {
            Diretorio novoDiretorio = new Diretorio();
            if(diretorioAtual == null) {
                empreendedor.setDiretorio(novoDiretorio);
            }else {
                diretorioAtual.addDiretorio(novoDiretorio);
            }

            for (File file : Objects.requireNonNull(diretorio.listFiles())) {
                novoDiretorio.setId(diretorio.getName());
                if (file.isDirectory()) {
                    popularDiretorios(empreendedor, novoDiretorio, file.getPath());
                } else if(!file.getName().endsWith(".md5")) {
                    Arquivo arquivo;

                    if(caminho.equals(getDiretorioEmpreendedor(empreendedor))) {
                        novoDiretorio.setId(empreendedor.getId());
                        arquivo = Arquivo.criarArquivoTipado(file);
                    }else {
                        arquivo = new Arquivo(file);
                    }

                    novoDiretorio.addArquivo(arquivo);
                }
            }
        }
    }
}
