import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Reader {

    private String clienteRoot;

    public Reader() {
    }

    public ArrayList<Empreendedor> getEmpreendedores(String rootPath) {
        if(this.clienteRoot == null) {
            this.clienteRoot = rootPath;
        }

        ArrayList<Empreendedor> empreendedores = new ArrayList<>();
        File folder = new File(rootPath);
        for (final File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                Empreendedor empreendedor = new Empreendedor();
                empreendedor.setId(file.getName());
                empreendedores.add(empreendedor);
            }
        }

        return empreendedores;
    }

    public void populateEmpreendedor(Empreendedor empreendedor, String path) throws IOException {
        File folder = new File(path);

        Processo processo = null;

        for (final File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                populateEmpreendedor(empreendedor, file.getPath());
            } else if(!file.getName().endsWith(".md5")) {
                if((this.clienteRoot + empreendedor.getId()).equals(path)) {
                    processo = new Processo();
                    processo.setId(empreendedor.getId());
                    Arquivo arquivo = Arquivo.criarArquivoTipado(file);
                    processo.addArquivo(arquivo);
                    empreendedor.addProcesso(processo);
                }else {
                    if(processo == null) {
                        processo = new Processo();
                        processo.setId(folder.getName());
                        empreendedor.addProcesso(processo);
                    }
                    Arquivo arquivo = new Arquivo(file);
                    processo.addArquivo(arquivo);
                }
            }
        }
    }

}
