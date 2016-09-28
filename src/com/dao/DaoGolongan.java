package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.config.Koneksi;
import com.model.ModelGolongan;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DaoGolongan {
	private Connection conn = null;
	private String query = "";
	
	public DaoGolongan(){
		conn = new Koneksi().getKoneksi();
	}
	
	public List<ModelGolongan> ambilDaftarGolongan(){
		List<ModelGolongan> res = new ArrayList<ModelGolongan>();
		
		query = "SELECT idgolongan,nama_golongan,gaji FROM tb_golongan";
		try {
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				ModelGolongan gol = new ModelGolongan();
				gol.setId(rs.getInt("idgolongan"));
				gol.setNama_golongan(rs.getString("nama_golongan"));
				gol.setGaji(rs.getInt("gaji"));
				res.add(gol);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		
	}
}
