package lofilmes.modelos;

import java.time.LocalDate;

import lofilmes.utilidades.Formatador;

public record DadosLocacao(Long idLocacao, Cliente cliente, Filme filme, LocalDate data, double valorPago, int diasAlugado) {
	
	@Override
	public final String toString() {
		return String.format("DadosLocacoes [\nId locação: %d\n%s\n%s\nData: %s\nValor Pago: %.2f\nDias alugados: %d\n]", idLocacao,
				cliente, filme, data.format(Formatador.FORMATO_DATA_BR), valorPago, diasAlugado);
	}
}