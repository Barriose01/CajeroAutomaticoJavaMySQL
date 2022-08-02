import java.util.*;
import org.apache.commons.lang3.text.WordUtils;
public class MenuInicioCajero {
	static Scanner sc = new Scanner(System.in);
	static ConexionBD bd = new ConexionBD();
	
	private static boolean validarDatosIngresados(String rut, String dv, String clave) {
		boolean sonValidos = true;
		int parseRut,parseClave, parseDV;		
		try {
				parseRut = Integer.parseInt(rut);
				parseClave = Integer.parseInt(clave);
				if(!dv.equals("k")) {
					parseDV = Integer.parseInt(dv);
				}
			}catch(Exception e) {
				sonValidos = false;
		}
		if(sonValidos) {
			if(dv.length() != 1) {sonValidos = false;}
			
			if(clave.length() != 4) {sonValidos = false;}
			
		}
		return sonValidos;

	}
	private static void registrar() {
		while(true) {
			System.out.println("\nREGISTRO DE CUENTA");
			System.out.println("Presione (q) en cualquier momento para volver al menu anterior");
			System.out.print("Ingrese su nombre: ");
			String nombre = WordUtils.capitalize(sc.nextLine().strip());
			if(nombre.equals("Q")) {break;}
			System.out.print("Ingrese su apellido: ");
			String apellido = WordUtils.capitalize(sc.nextLine().strip());
			if(apellido.equals("Q")) {break;}
			System.out.print("Ingrese su Rut (Sin dv, sin guiones y sin punto): ");
			String rut =sc.nextLine().toLowerCase().strip();
			if(rut.equals("q")) {break;}
			System.out.print("Ingrese el DV: ");
			String dv = sc.nextLine().toLowerCase().strip();
			if(dv.equals("q")) {break;}
			System.out.print("Ingrese una clave personal de 4 digitos: ");
			String clave = sc.nextLine().toLowerCase().strip();
			if(clave.equals("q")) {break;}
			System.out.print("Vuelva a introducir la clave: ");
			String clave2 = sc.nextLine().toLowerCase().strip();
			if(clave2.equals("q")) {break;}
			if(!clave.equals(clave2)) {
				System.out.println("Las claves no coinciden");
			}else {
				boolean cuentaValida = validarDatosIngresados(rut,dv,clave);
				if(!cuentaValida) {System.out.println("\nDebe ingresar informacion valida. Revise su rut, dv o clave");}
				else {
					ArrayList<String> cuentas = bd.cuentaExiste(rut, dv);
					if(cuentas.size() > 0) {
						System.out.println("\nLo sentimos, la cuenta ingresada ya existe");
					}else {
						bd.registrarUsuario(rut, dv, clave, nombre, apellido);
						bd.registrarLog(rut, dv);
						System.out.println("La cuenta ha sido registrada con exito");
						
					}
					break;
				}
			}
		}
	}
	private static void iniciarSesion() {
		while(true) {
			System.out.println("\nINICIO DE SESION");
			System.out.println("Presione (q) en cualquier momento para volver al menu anterior");
			System.out.print("Ingrese su Rut (Sin dv, sin guiones y sin punto): ");
			String rut = sc.nextLine().toLowerCase().strip();
			if(rut.equals("q")) {break;}
			System.out.print("Ingrese el DV: ");
			String dv = sc.nextLine().toLowerCase().strip();
			if(dv.equals("q")) {break;}
			System.out.print("Ingrese su clave: ");
			String clave = sc.nextLine().toLowerCase().strip();
			if(clave.equals("q")) {break;}
			ArrayList<String> cuenta = bd.cuentaExiste(rut, dv);
			if(cuenta.size() > 0) {
				if(clave.equals(cuenta.get(5))) {
					String id = cuenta.get(0);
					MenuPrincipalCajero mc = new MenuPrincipalCajero(rut,dv,id);
					mc.menuCajero();
				}else {
					System.out.println("\nLa clave que ingreso es incorrecta");
				}
				
			}else {
				System.out.println("\nLa cuenta ingresada no esta registrada");
			}
			break;
			
		}
	}

	public static void main(String[] args) {
		
		String opcion;
		int parseOpcion, salida = 0;
		
		while(salida == 0) {
			System.out.println("\n(1): Registrar Cuenta");
			System.out.println("(2): Iniciar Sesion");
			System.out.println("(3): Salir");
			try {
				opcion = sc.nextLine();
				parseOpcion = Integer.parseInt(opcion);
			}catch(Exception e) {
				System.out.println("Ingrese una opcion valida");
				continue;
			}
			switch(parseOpcion) {
			case 1:
				registrar();
				break;
			case 2:
				iniciarSesion();
				break;
			case 3:
				salida = 1;
				break;
			default:
				System.out.println("Ingrese una opcion valida");
				break;
			}
		}

	}

}
