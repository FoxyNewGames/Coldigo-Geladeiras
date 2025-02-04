package br.com.coldigogeladeiras.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import br.com.coldigogeladeiras.jdbanterface.ProdutoDAO;
import br.com.coldigogeladeiras.modelo.Produto;

public class JBDCProdutoDAO implements ProdutoDAO {

	private Connection conexao;

	public JBDCProdutoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public boolean inserir(Produto produto) {
		String comando = "INSERT INTO produtos" + "(id,categoria, modelo, capacidade,valor,marcas_id)"
				+ "VALUES(?,?,?,?,?,?)";
		PreparedStatement p;

		try {
//Prepara o comando para execucao no BD em q nos conectamos
			p = this.conexao.prepareStatement(comando);
//susbstitiu no comando os ? oelos valores do produto
			p.setInt(1, produto.getId());
			p.setString(2, produto.getCategoria());
			p.setString(3, produto.getModelo());
			p.setInt(4, produto.getCapacidade());
			p.setFloat(5, produto.getValor());
			p.setInt(6, produto.getMarcaId());
//executa o codigo no bd
			p.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<JsonObject> buscarPorNome(String nome) {
		// inicie criacao do comando SQL de busca
		String comando = "SELECT produtos.*, marcas.nome as marca FROM produtos "
				+ "INNER JOIN marcas on produtos.marcas_id = marcas.id ";
		// se o nome nao estiver vazio...
		if (!nome.equals("")) {
			// concatena o comando o WHERE buscando no nome do produto
			// O texto da variavel nome
			comando += "WHERE modelo LIKE '%" + nome + "%' ";
		}
		// Finaliza o comando ordenando alfabeticamente por
		// categoria, marca e depois modelo
		comando += "ORDER BY  categoria ASC, marcas.nome ASC, modelo ASC";
		System.out.println(comando);
		List<JsonObject> listaProdutos = new ArrayList<JsonObject>();
		JsonObject produto = null;

		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			while (rs.next()) {

				int id = rs.getInt("id");
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("modelo");
				int capacidade = rs.getInt("capacidade");
				float valor = rs.getFloat("valor");
				String marcaNome = rs.getString("marca");

				if (categoria.equals("1")) {
					categoria = "Geladeira";
				} else if (categoria.equals("2")) {
					categoria = "Freezer";
				}
				/* criar código no formato JSON, mas como um objeto Java */
				produto = new JsonObject();
				produto.addProperty("id", id);
				produto.addProperty("categoria", categoria);
				produto.addProperty("modelo", modelo);
				produto.addProperty("capacidade", capacidade);
				produto.addProperty("valor", valor);
				produto.addProperty("marcaNome", marcaNome);

				listaProdutos.add(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaProdutos;
	}

	public boolean deletar(int id) {
		String comando = "DELETE FROM produtos WHERE id = ?";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Produto buscarPorId(int id) {
		String comando = "SELECT * FROM produtos WHERE produtos.id = ?";
		Produto produto = new Produto();
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("modelo");
				int capacidade = rs.getInt("capacidade");
				float valor = rs.getFloat("valor");
				int marcaId = rs.getInt("marcaId");

				produto.setId(id);
				produto.setCategoria(categoria);
				produto.setMarcaId(marcaId);
				produto.setModelo(modelo);
				produto.setCapacidade(capacidade);
				produto.setValor(valor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produto;
	}

	public boolean alterar(Produto produto) {
		String comando = "UPDATE produtos " + "SET categoria=?, modelo=?, capacidade=?, valor=?, marcas_id=?"
				+ " WHERE id=?";
		PreparedStatement p;

		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, produto.getCategoria());
			p.setString(2, produto.getModelo());
			p.setInt(3, produto.getCapacidade());
			p.setFloat(4, produto.getValor());
			p.setInt(5, produto.getMarcaId());
			p.setInt(6, produto.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}