import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

import com.mysql.jdbc.Statement;

public class ConexionBD {
	Connection cn;
	
	public ConexionBD() {
		this.cn = this.conectar();
	}
	
	public Connection conectar() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cajeroAutomatico","root",""); 
		}catch(Exception e) {
			System.out.println("Error al realizar la conexion con la base de datos");
		}
		return con;
		
	}
	public ArrayList<String> cuentaExiste(String rut, String dv) {
		ArrayList<String> cuentas = new ArrayList<String>();
		try {
			String sql = "SELECT * FROM cuentaUsuario WHERE rut = '" + rut + "' AND dv = '" + dv + "'";
			Statement statement = (Statement) this.cn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				//Son 7 columnas en la tabla
				for(int i = 1; i < 8; i++) {
					cuentas.add(rs.getString(i));
				}
			}
		}catch(Exception e) {
			System.out.println("Error al verificar la cuenta");
		}
		return cuentas;
	}
	
	
	
	public void registrarUsuario(String rut, String dv, String clave, String nombre, String apellido) {
		try {
			String sql = "INSERT INTO cuentaUsuario(rut,dv,clave,nombre,apellido) VALUES('" + rut + "', '" + dv 
					+ "', '" + clave + "', '" + nombre + "', '" + apellido + "')";
			Statement statement = (Statement) this.cn.createStatement();
			statement.execute(sql);
		}catch(Exception e) {
			System.out.println("Error al registrar el usuario");
		}
	}
	public void registrarLog(String rut, String dv) {
		try {
			ArrayList<String> cuentas = this.cuentaExiste(rut, dv);
			String id = "";
			id = cuentas.get(0);
			String sql = "INSERT INTO logCuentaUsuario(idUsuario,informacion) VALUES('" + id 
					+ "', 'SE CREO UNA NUEVA CUENTA')";
			Statement statement = (Statement) this.cn.createStatement();
			statement.execute(sql);
			System.out.println("La cuenta ha sido registrada con exito");
		}catch(Exception e) {
			System.out.println("Error al registrar la informacion de la cuenta");
		}
		
		
	}
	public void retiro(String rut, String dv, String id, Float saldo, float monto) {
		try {
			String sql = "CALL retiro('" + rut + "', '" + dv + "', '" + id + "',  '" + saldo + "', '" + monto + "')";
			Statement statement = (Statement) cn.createStatement();
			statement.execute(sql);
			System.out.println("\nRETIRO: $" + monto);
			System.out.println("SALDO: $" + saldo);
		}catch(Exception e) {
			System.out.println("Error al realizar el retiro");
		}
	}
	public void deposito(String rut, String dv, String id, float saldo, float monto) {
		try {
			String sql = "CALL deposito('" + rut + "', '" + dv + "', '" + id + "',  '" + saldo + "', '" + monto + "')";
			Statement statement = (Statement) cn.createStatement();
			statement.execute(sql);
			System.out.println("\nRETIRO: $" + monto);
			System.out.println("SALDO: $" + saldo);
		}catch(Exception e) {
			System.out.println("Error al realizar el deposito");
		}
	}
	public void transferencia(String rut, String dv, String id, float saldo, String rutTransferencia, 
			String dvTransferencia, String idTransferencia, float saldoTransferencia, float monto ) {
		
		try {
			String sql = "CALL transferencia('" + rut + "', '" + dv + "', '" + id + "','" + saldo + "', '" 
		+ rutTransferencia + "', '" + dvTransferencia + "', '" + idTransferencia + "', '" + saldoTransferencia
		+ "', '" + monto + "')";
			
			Statement statement = (Statement) cn.createStatement();
			statement.execute(sql);
			System.out.println("\nLa transferencia se ha realizado con exito");
			System.out.println("MONTO: $" + monto);
			System.out.println("SALDO: $" + saldo);
		}catch(Exception e) {
			System.out.println("Error al realizar la transferencia");
		}
	}
	public ArrayList<String> movimineots(String id) {
		ArrayList<String> movimientosCuenta = new ArrayList<String>();
		try {
			String sql = "SELECT monto, informacion, fecha FROM vistaLog WHERE id = '" + id + "'";
			Statement statement = (Statement) cn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				//Son 3 columnas
				for(int i = 1; i < 4; i++) {
					movimientosCuenta.add(rs.getString(i));
				}
			}
		}catch(Exception e) {
			System.out.println("Error al ver los movimientos");
		}
		return movimientosCuenta;
	}

}
