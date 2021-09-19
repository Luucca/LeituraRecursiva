import java.util.ArrayList;
import java.util.Collections;

public class Empreendedor {
	private String id;
	private Diretorio diretorio;

	public Empreendedor(String id) {
		this.id = id;
		this.diretorio = new Diretorio();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void imprimirArvore(boolean detalhar) {
		System.out.println("• Empreendedor: " + this.id);
		Logger.imprimirArvore(new ArrayList<>(Collections.singletonList(this.diretorio)), detalhar, 1);
	}

	public Diretorio getDiretorioRoot() {
		return this.diretorio;
	}

	public ArrayList<Diretorio> getDiretorios(boolean recursivo) {
		return getDiretorios(new ArrayList<>(Collections.singletonList(this.diretorio)), recursivo);
	}

	private ArrayList<Diretorio> getDiretorios(ArrayList<Diretorio> diretorios, boolean recursivo) {
		if(!recursivo) {
			return diretorios;
		}

		ArrayList<Diretorio> list = new ArrayList<>();

		if(diretorios != null) {
			for(Diretorio diretorio : diretorios) {
				list.add(diretorio);
				ArrayList<Diretorio> subDiretorios = diretorio.getDiretorios(false);
				if(subDiretorios != null && subDiretorios.size() > 0) {
					list.addAll(getDiretorios(subDiretorios, true));
				}
			}
		}

		return list;
	}

	public void setDiretorio(Diretorio diretorio) {
		this.diretorio = diretorio;
	}
}
