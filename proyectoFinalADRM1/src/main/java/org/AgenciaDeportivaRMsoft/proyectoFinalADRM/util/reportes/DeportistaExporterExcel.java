package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.util.reportes;

import java.io.IOException;
import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Deportista;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class DeportistaExporterExcel {
	private  XSSFWorkbook libro;
	private XSSFSheet hoja;

	private List<Deportista> listaDeportistas;

	public DeportistaExporterExcel( List<Deportista> listaDeportistas) {

		this.listaDeportistas = listaDeportistas;
		libro = new XSSFWorkbook();
		hoja= libro.createSheet("Deportistas");
	}
	
private void escribirCabeceraDeTabla() {
	Row fila = hoja.createRow(0);
	CellStyle estilo = libro.createCellStyle();
	XSSFFont fuente = libro.createFont();
	fuente.setBold(true);
	fuente.setFontHeight(16);
	estilo.setFont(fuente);
	
	Cell celda = fila.createCell(0);
	celda.setCellValue("ID");
	celda.setCellStyle(estilo);
	
	 celda = fila.createCell(1);
	celda.setCellValue("Nombre");
	celda.setCellStyle(estilo);
	
	 celda = fila.createCell(2);
	celda.setCellValue("Categoria");
	celda.setCellStyle(estilo);
	
	 celda = fila.createCell(3);
	celda.setCellValue("Posicion");
	celda.setCellStyle(estilo);
	
//	 celda = fila.createCell(4);
//	celda.setCellValue("FechaNacimiento");
//	celda.setCellStyle(estilo);
	
	 celda = fila.createCell(5);
	celda.setCellValue("Peso");
	celda.setCellStyle(estilo);
	
	celda = fila.createCell(6);
	celda.setCellValue("Estatura");
	celda.setCellStyle(estilo);
	
	celda = fila.createCell(7);
	celda.setCellValue("Telefono");
	celda.setCellStyle(estilo);
	
	celda = fila.createCell(8);
	celda.setCellValue("Email");
	celda.setCellStyle(estilo);
	
	celda = fila.createCell(9);
	celda.setCellValue("Descripcion");
	celda.setCellStyle(estilo);
	
	celda = fila.createCell(10);
	celda.setCellValue("Imagen");
	celda.setCellStyle(estilo);
}
private void escribirDatosDeLaTabla() {
	int numeroFilas =1;
	CellStyle estilo = libro.createCellStyle();
	XSSFFont fuente = libro.createFont();
	fuente.setFontHeight(14);
	estilo.setFont(fuente);
	
	for (Deportista deportista : listaDeportistas) {
		Row fila = hoja.createRow(numeroFilas ++); 
		
		Cell celda = fila.createCell(0);
		celda.setCellValue(deportista.getId());
		hoja.autoSizeColumn(0);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(1);
		celda.setCellValue(deportista.getNombre());
		hoja.autoSizeColumn(1);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(2);
		celda.setCellValue(deportista.getCategoria());
		hoja.autoSizeColumn(2);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(3);
		celda.setCellValue(deportista.getPosicion());
		hoja.autoSizeColumn(3);
		celda.setCellStyle(estilo);
		
//		celda = fila.createCell(4);
//		celda.setCellValue(deportista.getFechaNacimiento());
//		hoja.autoSizeColumn(4);
//		celda.setCellStyle(estilo);
		
		celda = fila.createCell(4);
		celda.setCellValue(deportista.getPeso());
		hoja.autoSizeColumn(4);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(5);
		celda.setCellValue(deportista.getEstatura());
		hoja.autoSizeColumn(5);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(6);
		celda.setCellValue(deportista.getTelefono());
		hoja.autoSizeColumn(6);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(7);
		celda.setCellValue(deportista.getEmail());
		hoja.autoSizeColumn(7);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(8);
		celda.setCellValue(deportista.getDescripcion());
		hoja.autoSizeColumn(8);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(9);
		celda.setCellValue(deportista.getImagen());
		hoja.autoSizeColumn(9);
		celda.setCellStyle(estilo);
	}
	
	
}
public void exportar(HttpServletResponse response) throws IOException {
	escribirCabeceraDeTabla();
	escribirDatosDeLaTabla();
	
	ServletOutputStream outPutStream = response.getOutputStream();
	
	libro.write(outPutStream);
	libro.close();

}

}
