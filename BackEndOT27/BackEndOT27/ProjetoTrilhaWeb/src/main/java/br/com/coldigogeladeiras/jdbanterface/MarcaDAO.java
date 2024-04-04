package br.com.coldigogeladeiras.jdbanterface;

import java.util.List;

import com.google.gson.JsonObject;

import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.modelo.Produto;

//	Interface que define o contrato para implementação das operações de acesso do banco de dados
// 	Possui o metodo buscar que retorna a lista de marcas 

public interface MarcaDAO {
	public List<Marca> buscar();

	public boolean inserir(Marca marca);

	public List<JsonObject> buscarPorMarca(String nome);

	public boolean deletar(int id);

	public Marca buscarPorId(int id);

	public boolean alterar(Marca marca);

	public boolean inativar(int id);
}