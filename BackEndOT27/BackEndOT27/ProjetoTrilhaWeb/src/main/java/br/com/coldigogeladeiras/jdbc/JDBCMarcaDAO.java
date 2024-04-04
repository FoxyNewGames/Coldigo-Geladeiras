package br.com.coldigogeladeiras.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.coldigogeladeiras.jdbanterface.MarcaDAO;
import br.com.coldigogeladeiras.modelo.Marca;

import java.util.List;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class JDBCMarcaDAO implements MarcaDAO {
	private Connection conexao;

	public JDBCMarcaDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public List<Marca> buscar() {
		// Criação da instituição SQL para busca de todos as marcas
		String comando = "SELECT * FROM marcas";
		// crição de uma lista para armazenar cada marca encontrada
		List<Marca> listMarcas = new ArrayList<Marca>();
		// Criação do objeto marca com valor null(ou seja, sem instancia-lo)
		Marca marca = null;

		// abertura do try catch
		try {
			// Uso da conexao com banco de dados para prepara-lo para uma instrucao SQL
			Statement stmt = conexao.createStatement();

			// Execução da instrução criada previamente
			// e armazenamento do resultado no obleto rs
			ResultSet rs = stmt.executeQuery(comando);

			// enquanto houver uma proxima linha no resultado
			while (rs.next()) {

				// criacao de instancia da classe marca
				marca = new Marca();

				// recebimento dos 2 dados retornados do bd para cada linha encontrada
				int id = rs.getInt("id");
				String nome = rs.getString("nome");

				// setando no objeto marca os valores encontrados
				marca.setId(id);
				marca.setNome(nome);

				// adição da instancia contida no objeto MArca na lista de marcas
				listMarcas.add(marca);
			}
			// Caso alguma exceção seja gerado no try, recebe-a no objeto "ex"
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Retorna para quem chamou o metodo a lista criada
		return listMarcas;

	}

	public boolean inserir(Marca marca) {
		String comando = "INSERT INTO marcas" + "(id, nome, data)" + "VALUES(?,?,?)";

		PreparedStatement p;

		try {

			p = this.conexao.prepareStatement(comando);
			p.setInt(1, marca.getId());
			p.setString(2, marca.getNome());
			p.setString(3, marca.getData());

			p.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<JsonObject> buscarPorMarca(String nome) {

		String comando = "SELECT * FROM marcas ";

		if (!nome.equals("")) {
			comando += "WHERE nome LIKE '%" + nome + "%'";
		}

		List<JsonObject> listaMarcas = new ArrayList<JsonObject>();
		JsonObject marca = null;

		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			while (rs.next()) {

				int id = rs.getInt("id");
				String nomeMarca = rs.getString("nome");
				String data = rs.getString("data");
				String status = rs.getString("status");

				marca = new JsonObject();
				marca.addProperty("id", id);
				marca.addProperty("nomeMarca", nomeMarca);
				marca.addProperty("data", data);
				marca.addProperty("status", status);

				listaMarcas.add(marca);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaMarcas;
	}

	public boolean deletar(int id) {
		String comando = "DELETE FROM marcas WHERE id = ?";

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

	public Marca buscarPorId(int id) {
		String comando = "SELECT * FROM marcas WHERE marcas.id = ?";
		Marca marca = new Marca();
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {

				String nome = rs.getString("nome");
				String data = rs.getString("data");

				marca.setId(id);
				marca.setNome(nome);
				marca.setData(data);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marca;
	}

	public boolean alterar(Marca marca) {
		String comando = "UPDATE marcas " + "SET nome=?, data=?" + " WHERE id=?";
		PreparedStatement p;

		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, marca.getNome());
			p.setString(2, marca.getData());
			p.setInt(3, marca.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean inativar(int id) {
		String comando = "UPDATE marcas " + "SET status = CASE WHEN status = 0 THEN 1 ELSE 0 END" + " WHERE id = ?";

		PreparedStatement p;
		try {

			p = conexao.prepareStatement(comando);
			p.setInt(1, id);
			p.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
