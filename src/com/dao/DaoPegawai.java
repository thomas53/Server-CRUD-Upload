package com.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.config.Koneksi;
import com.model.ModelFile;
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
		query = "INSERT INTO `db_pegawai`.`tb_pegawai` (`nama`, `jenis_kelamin`, `alamat`,foto) VALUES (?,?,?,?);";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setString(1, pegawai.getNama());
			ps.setString(2, pegawai.getJenis_kelamin());
			ps.setString(3, pegawai.getAlamat());
			ps.setString(4, pegawai.getFileFoto());
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
			ps.setString(2, pegawai.getJenis_kelamin());
			ps.setString(3, pegawai.getAlamat());
			ps.setInt(4, pegawai.getIdpegawai());
			
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
		query = "SELECT idpegawai,nama,jenis_kelamin,alamat,foto FROM tb_pegawai";
		try {
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query);			
			while(rs.next()){
				ModelPegawai temp = new ModelPegawai();
				temp.setIdpegawai(rs.getInt("idpegawai"));
				temp.setNama(rs.getString("nama"));
				temp.setJenis_kelamin(rs.getString("jenis_kelamin"));
				temp.setAlamat(rs.getString("alamat"));
				temp.setFileFoto(rs.getString("foto"));
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
				res.setIdpegawai(rs.getInt("idpegawai"));
				res.setNama(rs.getString("nama"));
				res.setJenis_kelamin(rs.getString("jenis_kelamin"));
				res.setAlamat(rs.getString("alamat"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public void saveFile(String fileName, byte[] content) throws IOException{
		File file = new File(fileName);
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
        writer.write(content);
        writer.flush();
        writer.close();
	}
}
