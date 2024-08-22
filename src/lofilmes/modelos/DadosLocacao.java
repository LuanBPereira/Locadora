package lofilmes.modelos;

import java.time.LocalDate;

public record DadosLocacao(Cliente cliente, double valorPago, LocalDate data,
		Filme filme, int diasAlugado) {}