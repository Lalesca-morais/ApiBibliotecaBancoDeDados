package service;

import java.sql.*;
import static connection.Conexao.fazerConexao;
public class LivroService {
    private Statement statement;
    public LivroService(){
        try{
            statement = fazerConexao().createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void inserirLivro(String nome, String autor, String dataDeLancamento, String codigo) {
        String sql = "INSERT INTO livros (nome, autor, dataDeLancamento, codigo) VALUES ('" +
                nome + "', '" + autor + "', '" + dataDeLancamento + "', '" + codigo + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Livro '" + nome + "' foi adicionado com sucesso no banco!");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void consultaTodosDados(){
        String sql = "SELECT * FROM livros";
        boolean livrosEncontrados = false;
        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                livrosEncontrados = true;
                System.out.println("ID: " + resultSet.getInt("id") +
                        " | NOME: " + resultSet.getString("nome"));
            }
            if (!livrosEncontrados) {
                System.out.println("Ainda não foi cadastrado nenhum livro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultaLivroPorId(int id) {
        String sql = "SELECT * FROM livros WHERE id = " + id;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                System.out.println("=====LIVRO ENCONTRADO=====");
                System.out.println("ID: " + resultSet.getInt("id") +
                        " | Nome: " + resultSet.getString("nome") +
                        " | Autor: " + resultSet.getString("autor") +
                        " | Data de Lançamento: " + resultSet.getString("dataDeLancamento") +
                        " | Código: " + resultSet.getString("codigo"));
            } else {
                System.out.println("Livro com ID " + id + " não encontrado, tente novamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterarLivro(String nome, String autor, int dataDeLancamento, String codigo) {
        String sql = "UPDATE livros SET nome = '" + nome + "', autor = '" + autor +
                "', dataDeLancamento = '" + dataDeLancamento + "', codigo = '" + codigo + "' WHERE id = ";
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Livro com nome " + nome + "foi alterado com sucesso.");
            } else {
                System.out.println("Livro com nome " + nome + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarLivro(int id){
        String sql = "DELETE FROM livros WHERE id = " + id;

        try{
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0){
                System.out.println("Livro " + id + " foi deletado com sucesso!");

            }else{
                System.out.println("Livro " + id + " não encontrado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

