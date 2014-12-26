package br.com.fgm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.fgm.ConnectionFactory.ConnectionFactory;
import br.com.fgm.bean.ContatoBean;
import br.com.fgm.model.Contato;
import br.com.fgm.model.Database;


@Repository
public class ContatoDao {
	
	private final Connection connection;	

	@Autowired
	public ContatoDao(DataSource dataSource) {
        try {
        	System.out.println("ContatoDao");
            this.connection = new ConnectionFactory().getConnection();
       } catch (SQLException e) {
            throw new RuntimeException(e);
       }
  }
	
	public void adiciona(Contato contato) {
		try {
			String sql = "insert into contato "
					+ "(nome,email,endereco,dataNascimento)" + " values (?,?,?,?)";
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());			
			Calendar tdataNascimento = contato.getDataNascimento();
			if (tdataNascimento==null)
				{stmt.setDate(4, null);}
			else
				{stmt.setDate(4, new Date(tdataNascimento.getTimeInMillis()));}
			stmt.execute();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}

	public void altera(Contato contato) {
		String sql = "update contato set nome=?, email=?,"
				+ "endereco=?, dataNascimento=? where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			Calendar tdataNascimento = contato.getDataNascimento();
			if (tdataNascimento==null)
			{stmt.setDate(4, null);}
			else
			{stmt.setDate(4, new Date(tdataNascimento.getTimeInMillis()));}
			stmt.setLong(5, contato.getId());
			stmt.execute();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void exclui(Contato contato) {
        if (contato.getId() == null) {
            throw new IllegalStateException("Id do Contato não deve ser nulo.");
        }		
		try {
			PreparedStatement stmt = connection
					.prepareStatement("delete from contato where id=?");
			stmt.setLong(1, contato.getId());
			stmt.execute();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// este aqui é chamado pelo Spring
	public List<Contato> Lista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();	
			
			/* String mWhereNome = ContatoSearch.getNomeMask();
			if (mWhereNome != null) {
				System.out.println("mWhereNome = "+mWhereNome);
			} else {
				System.out.println("mWhereNome = NULO");
			} 
			*/
			
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from contato");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("dataNascimento"));
					contato.setDataNascimento(data);					
				} catch (RuntimeException e) {					
					contato.setDataNascimento(null);
					}
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* este método é para o datatable do primefaces */
	public static  ArrayList<ContatoBean> getContato(String mWhere) {			
		 try {
			 	System.out.println("==================WHERE - " + mWhere);
	            Connection con = Database.getConnection();
	            PreparedStatement ps = con.prepareStatement("select * from contato");
	            ArrayList<ContatoBean> al = new ArrayList<ContatoBean>();
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	                ContatoBean e = new ContatoBean();
	                e.setNome(rs.getString("nome"));
	                e.setEmail(rs.getString("email"));
	                e.setEndereco(rs.getString("endereco"));
					try {
						Calendar data2 = Calendar.getInstance();
						data2.setTime(rs.getDate("dataNascimento"));
						e.setDataNascimento(data2);					
					} catch (RuntimeException ee) {					
						e.setDataNascimento(null);
						}

	                al.add(e);
	                found = true;
	            }
	            rs.close();
	            if (found) {
	                return al;
	            } else {
	                return null; // no entires found
	            }
	        } catch (Exception ee) {
	            System.out.println("Error In getContato() -->" + ee.getMessage());
	            return (null);
	        }		
	}
	
	
	public Contato getContatoPorID(Long id) {
        if (id == null) {
            throw new IllegalStateException("Id do Contato não deve ser nulo.");
       }		
		String sql = "select * from contato where id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			Contato contato = new Contato();

			while (rs.next()) {
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				System.out.println("ContatoDao-getContatoPorID-next");
				Calendar data = Calendar.getInstance();
				try {
					data.setTime(rs.getDate("dataNascimento"));
					contato.setDataNascimento(data);
				}
				catch (Exception e) {        
					contato.setDataNascimento(null);
		        }
			}
			rs.close();
			stmt.close();
			//return null;
			return contato;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
}
