package lofilmes.servicos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lofilmes.modelos.Cliente;
import lofilmes.modelos.DadosLocacao;
import lofilmes.modelos.Filme;

public class HistoricoLocacoes {

	private List<DadosLocacao> historico = new ArrayList<>();
	private GerenciadorClientes gerenciadorClientes; // Adiciona a referência ao GerenciadorClientes

	public HistoricoLocacoes(GerenciadorClientes gerenciadorClientes) {
		this.gerenciadorClientes = gerenciadorClientes;
	}

	public void salvar(Cliente cliente, double valorPago, LocalDate data, Filme filme, int diasAlugado) {
		historico.add(new DadosLocacao(cliente, valorPago, data, filme, diasAlugado));
	}

	public List<DadosLocacao> getHistorico() {
		return historico;
	}

	public List<DadosLocacao> getFilmesLocadosNosUltimos7Dias() {
		LocalDate dataAtual = LocalDate.now();
		List<DadosLocacao> alugadosRecentes = new ArrayList<>();

		for (DadosLocacao dadosL : historico) {
			LocalDate dataCompra = dadosL.data();
			if (!dataCompra.isBefore(dataAtual.minusDays(7))) {
				alugadosRecentes.add(dadosL);
			}
		}

		return alugadosRecentes;
	}

	public List<String> getFilmesMaisLocados() {
		Map<String, Integer> contagemFilmes = new HashMap<>();
		List<String> filmesMaisLocados = new ArrayList<>();
		int contagemMax = 0;

		for (DadosLocacao dadosL : historico) {
			String titulo = dadosL.filme().getTitulo();
			int contagemAtual = contagemFilmes.getOrDefault(titulo, 0) + 1;
			contagemFilmes.put(titulo, contagemAtual);

			if (contagemAtual > contagemMax) {
				contagemMax = contagemAtual;
			}
		}

		for (Map.Entry<String, Integer> entry : contagemFilmes.entrySet()) {
			if (entry.getValue() == contagemMax) {
				filmesMaisLocados.add(entry.getKey());
			}
		}

		return filmesMaisLocados;
	}

	public double getValorTotalLocacoesUltimoMes() {
		LocalDate dataAtual = LocalDate.now();
		double total = 0.0;

		for (DadosLocacao dadosL : historico) {
			LocalDate dataLocacao = dadosL.data();
			if (dataLocacao.getMonthValue() == dataAtual.getMonthValue() &&
					dataLocacao.getYear() == dataAtual.getYear()) {
				total += dadosL.valorPago();
			}
		}
		return total;
	}

	public List<String> getClienteQueMaisLocou() {
		Map<Long, Integer> contagemLocacaoCliente = new HashMap<>();
		List<String> clienteQueMaisLocou = new ArrayList<>();
		int contagemMax = 0;

		for (DadosLocacao dadosL : historico) {
			Long cpfCliente = dadosL.cliente().getCPF();
			int contagemAtual = contagemLocacaoCliente.getOrDefault(cpfCliente, 0) + 1;
			contagemLocacaoCliente.put(cpfCliente, contagemAtual);

			if (contagemAtual > contagemMax) {
				contagemMax = contagemAtual;
			}
		}

		for (Long cpf : contagemLocacaoCliente.keySet()) {
			if (contagemLocacaoCliente.get(cpf) == contagemMax) {
				Cliente cliente = gerenciadorClientes.getClientePorCpf(cpf);
				if (cliente != null) {
					String nomeCliente = cliente.getNome() + " " + cliente.getSobrenome();
					if (!clienteQueMaisLocou.contains(nomeCliente)) {
						clienteQueMaisLocou.add(nomeCliente);
					}
				}
			}
		}

		return clienteQueMaisLocou;
	}
}
