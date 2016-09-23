package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.config.Koneksi;
import com.model.ModelPegawai;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DaoPegawai {
	private Connection conn = null;
	private String query = "";
	
	public DaoPegawai(){
		conn = new Koneksi().getKoneksi();
	}
	
	public int insertPegawai(ModelPegawai pegawai){
		int res = 0;
		query = "INSERT INTO `db_pegawai`.`tb_pegawai` (`nama`, `jenis_kelamin`, `alamat`) VALUES (?,?,?);";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setString(1, pegawai.getNama());
			ps.setString(2, pegawai.getJenkel());
			ps.setString(3, pegawai.getAlamat());
			
			ps.execute();
			ps.close();
			res = 1;
			conn.close();
		} catch (SQLException e) {
			res = 0;
			e.printStackTrace();
		}
		return res;
	}
	
	public int updatePegawai(ModelPegawai pegawai){
		int res = 0;
		query = "UPDATE `db_pegawai`.`tb_pegawai` SET `nama`=?, `jenis_kelamin`=?, `alamat`=? WHERE `idpegawai`=?;";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setString(1, pegawai.getNama());
			ps.setString(2, pegawai.getJenkel());
			ps.setString(3, pegawai.getAlamat());
			ps.setInt(4, pegawai.getIdPegawai());
			
			ps.execute();
			ps.close();
			res = 1;
			conn.close();
		} catch (SQLException e) {
			res = 0;
			e.printStackTrace();
		}
		return res;
	}
	
	public int deletePegawai(int id){
		int res = 0;
		query = "DELETE FROM `db_pegawai`.`tb_pegawai` WHERE `idpegawai`=?;";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			res = 1;
			conn.close();
		} catch (SQLException e) {
			res = 0;
			e.printStackTrace();
		}
		return res;
	}
	
	public List<ModelPegawai> ambilPegawai(){
		List<ModelPegawai> res = new ArrayList<ModelPegawai>();
		query = "SELECT idpegawai,nama,jenis_kelamin,alamat FROM tb_pegawai";
		try {
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query);			
			while(rs.next()){
				ModelPegawai temp = new ModelPegawai();
				temp.setIdPegawai(rs.getInt("idpegawai"));
				temp.setNama(rs.getString("nama"));
				temp.setJenkel(rs.getString("jenis_kelamin"));
				temp.setAlamat(rs.getString("alamat"));
				res.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public ModelPegawai ambilPegawayById(int id){
		ModelPegawai res = new ModelPegawai();
		query = "SELECT idpegawai,nama,jenis_kelamin,alamat FROM tb_pegawai WHERE idpegawai="+id;
		try {
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()){
				res.setIdPegawai(rs.getInt("idpegawai"));
				res.setNama(rs.getString("nama"));
				res.setJenkel(rs.getString("jenis_kelamin"));
				res.setAlamat(rs.getString("alamat"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
}
