import java.util.ArrayList;

public final class Logger {

    public static void imprimirArvore(ArrayList<Diretorio> diretorios, boolean detalhar, int nivel) {
        if(diretorios != null) {
            for (Diretorio diretorio : diretorios) {
                print("|- Diretório: " + diretorio.getId(), nivel);
                if (diretorio.getArquivos(false) != null && diretorio.getArquivos(false).size() > 0) {
                    for (Arquivo arquivo : diretorio.getArquivos(false)) {
                        print("|- Arquivo: " + arquivo.getNome(), nivel + 1);
                        if (detalhar) {
                            print("|- Código: " +
                                (arquivo.getCodigo() != null ? arquivo.getCodigo() : "não identificado"), nivel + 2
                            );
                            print("|- Tipo: " +
                              (arquivo.getTipo() != null ? arquivo.getTipo() : "não identificado"), nivel + 2
                            );
                            print("|- Extensão: " + arquivo.getExtensao(), nivel + 2);
                        }
                    }
                }
                ArrayList<Diretorio> subDiretorios = diretorio.getDiretorios(false);
                if (subDiretorios != null && subDiretorios.size() > 0) {
                    imprimirArvore(subDiretorios, detalhar, ++nivel);
                }
            }
        }else {
            System.out.println();
        }
    }


    private static void print(String texto, int nivel) {
        StringBuilder prefixo = new StringBuilder();
        for (int i = 1; i < nivel; i++) {
            prefixo.append(" |");
        }
        System.out.println(" " + prefixo + " " + texto);
    }
}
