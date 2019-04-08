package uy.ort.ob201802;


// Interfaz del sistema
// No modificar esta clase!!!!!!!!!
public interface ISistema {

	Retorno inicializarSistema (int maxPuntos, Double coordX, Double coordY);
	
	Retorno destruirSistema();
	
	Retorno registrarAfiliado(String cedula, String nombre, String email);
	
	Retorno buscarAfiliado(String CI);
	
	Retorno listarAfiliados();
	
	Retorno registrarCanalera(String chipid, String CIafiliado, 
			Double coordX, Double coordY);
	
	Retorno registrarNodo(String nodoid, Double coordX, Double coordY);
	
	Retorno registrarTramo(Double coordXi, Double coordYi, Double
			coordXf, Double coordYf, int perdidaCalidad);
	
	Retorno modificarTramo(Double coordXi, Double coordYi, Double
			coordXf, Double coordYf, int nuevoValorPerdidaCalidad);
	
	Retorno calidadCanalera(Double coordX, Double coordY);
	
	Retorno nodosCriticos();
	
	Retorno dibujarMapa();

	Retorno deshabilitarTramo(double d, double e, double f, double g);
}
