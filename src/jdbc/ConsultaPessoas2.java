package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsultaPessoas2 {
	public static void main(String[] args) throws SQLException {
		Scanner entrada = new Scanner (System.in);
		
		Connection conexao = FabricaConexao.getConexao();
		String sql = "SELECT * FROM pessoas where nome like ?";
		
		System.out.print("Infomr o valor para a pesquisa");
		String valor = entrada.nextLine();
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1,"%" + valor + "%");
		ResultSet resultado = stmt.executeQuery(sql);
		
		List<Pessoa>pessoas = new ArrayList<>();
		
		while(resultado.next()) {
			int codigo = resultado.getInt("codigo");
			String nome = resultado.getString("nome");
			pessoas.add(new Pessoa(codigo, nome));
			
		}
		for(Pessoa p: pessoas) {
			System.out.println(p.getCodigo()+ "==> " + p.getNome());
			}
		stmt.close();
		conexao.close();
		entrada.close();
		}

}
