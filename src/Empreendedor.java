import java.util.ArrayList;

public class Empreendedor {
	private String id;
	private ArrayList<Processo> processos;

	public Empreendedor() {
	}

	public Empreendedor(String id, ArrayList<Processo> processos) {
		this.id = id;
		this.processos = processos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(ArrayList<Processo> processos) {
		this.processos = processos;
	}

	public void addProcesso(Processo processo) {
		if(this.processos == null) {
			this.processos = new ArrayList<>();
		}
		processos.add(processo);
	}
}
