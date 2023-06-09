package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.util.reportes;

import java.io.IOException;
import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Equipo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class EquipoExporterExcel {
	private  XSSFWorkbook libro;
	private XSSFSheet hoja;

	private List<Equipo> listaEquipos;

	public EquipoExporterExcel( List<Equipo> listaEquipos) {

		this.listaEquipos = listaEquipos;
		libro = new XSSFWorkbook();
		hoja= libro.createSheet("Equipos");
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
	celda.setCellValue("Descripcion");
	celda.setCellStyle(estilo);
	
	 celda = fila.createCell(3);
	celda.setCellValue("Estatus");
	celda.setCellStyle(estilo);
	
	 celda = fila.createCell(4);
	celda.setCellValue("Email");
	celda.setCellStyle(estilo);
	
	 celda = fila.createCell(5);
	celda.setCellValue("Imagen");
	celda.setCellStyle(estilo);
}
private void escribirDatosDeLaTabla() {
	int numeroFilas =1;
	CellStyle estilo = libro.createCellStyle();
	XSSFFont fuente = libro.createFont();
	fuente.setFontHeight(14);
	estilo.setFont(fuente);
	
	for (Equipo equipo : listaEquipos) {
		Row fila = hoja.createRow(numeroFilas ++); 
		
		Cell celda = fila.createCell(0);
		celda.setCellValue(equipo.getId());
		hoja.autoSizeColumn(0);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(1);
		celda.setCellValue(equipo.getNombre());
		hoja.autoSizeColumn(1);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(2);
		celda.setCellValue(equipo.getDescripcion());
		hoja.autoSizeColumn(2);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(3);
		celda.setCellValue(equipo.getEstatus());
		hoja.autoSizeColumn(3);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(4);
		celda.setCellValue(equipo.getEmail());
		hoja.autoSizeColumn(4);
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(5);
		celda.setCellValue(equipo.getImagen());
		hoja.autoSizeColumn(5);
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
