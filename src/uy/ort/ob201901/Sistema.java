package uy.ort.ob201802;

import Estructura.ABBAfiliado;
import Estructura.Afiliado;
import Estructura.GrafoMatriz;
import Estructura.Arco;
import Estructura.GrafoMatriz.TipoVertice;
import uy.ort.ob201802.Retorno.Resultado;

public class Sistema implements ISistema {

	ABBAfiliado afiliados;
	GrafoMatriz grafo;
	
	@Override
	public Retorno inicializarSistema (int maxPuntos, Double coordX, Double coordY) {
		afiliados = new ABBAfiliado();
		grafo=new GrafoMatriz(maxPuntos);
		grafo.insertarVertice(coordX, coordY, TipoVertice.CENTRAL);
		return new Retorno(Resultado.OK);
	}
	
	@Override
	public Retorno destruirSistema() {
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarAfiliado(String cedula, String nombre, String email) {
		// ToDO: Chequea formato CI para retornar error 1
		// ToDO: Chequea formato email para retornar error 2
		Retorno r = afiliados.buscar(cedula);
		if (r.resultado==Resultado.OK)
			return new Retorno(Resultado.ERROR_3);
		afiliados.agregar(new Afiliado(cedula, nombre, email, ""));
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno buscarAfiliado(String CI) {
		// ToDO: Chequea formato CI para retornar error 1
		Retorno r = afiliados.buscar(CI);
		return r;
	}

	@Override
	public Retorno listarAfiliados() {
		return new Retorno(Resultado.OK, afiliados.inOrder(), 0);
	}

	/* 1. Si en el sistema ya hay registrados cantPuntos puntos.
 		2. Si ya existe un punto en las coordenadas coordX, coordY del sistema.
 		3. Si el afiliado no existe.
*/
	@Override
	public Retorno registrarCanalera(String chipid, String CIafiliado, Double coordX, Double coordY) {
		if (grafo.getSize()==grafo.getCount())
			return new Retorno(Resultado.ERROR_1);
		if (grafo.buscarPosicion(coordX, coordY)>=0)
			return new Retorno(Resultado.ERROR_2);
		if (afiliados.buscar(CIafiliado).resultado==Resultado.ERROR_2)
			return new Retorno(Resultado.ERROR_3);
		grafo.insertarVertice(coordX, coordY, TipoVertice.CANALERA);
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarNodo(String nodoid, Double coordX, Double coordY) {
		if (grafo.getSize()==grafo.getCount())
			return new Retorno(Resultado.ERROR_1);
		if (grafo.buscarPosicion(coordX, coordY)>=0)
			return new Retorno(Resultado.ERROR_2);
		grafo.insertarVertice(coordX, coordY, TipoVertice.NODO);
		return new Retorno(Resultado.OK);
	}

	/* 1. Si peso es menor o igual a 0.
 2. Si no existe coordi o coordf.
 3. Si ya existe un tramo registrado desde coordi a coordf.
*/
	@Override
	public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int perdidaCalidad) {
		if (perdidaCalidad<=0)
			return new Retorno(Resultado.ERROR_1);
		int pto1 = grafo.buscarPosicion(coordXi, coordYi);
		int pto2 = grafo.buscarPosicion(coordXf, coordYf);
		if (pto1<0 || pto2<0)
			return new Retorno(Resultado.ERROR_2);
		if (grafo.arcos[pto1][pto2]!=null || grafo.arcos[pto2][pto1]!=null)
			return new Retorno(Resultado.ERROR_3);
		grafo.arcos[pto1][pto2] = new Arco(perdidaCalidad);
		grafo.arcos[pto2][pto1] = new Arco(perdidaCalidad);
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno modificarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf,
			int nuevoValorPerdidaCalidad) {
		if (nuevoValorPerdidaCalidad<=0)
			return new Retorno(Resultado.ERROR_1);
		int pto1 = grafo.buscarPosicion(coordXi, coordYi);
		int pto2 = grafo.buscarPosicion(coordXf, coordYf);
		if (pto1<0 || pto2<0)
			return new Retorno(Resultado.ERROR_2);
		if (grafo.arcos[pto1][pto2]==null || grafo.arcos[pto2][pto1]==null)
			return new Retorno(Resultado.ERROR_2);
		grafo.arcos[pto1][pto2] = new Arco(nuevoValorPerdidaCalidad);
		grafo.arcos[pto2][pto1] = new Arco(nuevoValorPerdidaCalidad);
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno calidadCanalera(Double coordX, Double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno nodosCriticos() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno dibujarMapa() {
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno deshabilitarTramo(double d, double e, double f, double g) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
