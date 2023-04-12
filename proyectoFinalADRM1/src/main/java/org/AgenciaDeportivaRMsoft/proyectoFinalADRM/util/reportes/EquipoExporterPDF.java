package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.util.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Equipo;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class EquipoExporterPDF {
	private List<Equipo> listaEquipos;

	public EquipoExporterPDF(List<Equipo> listaEquipos) {
		super();
		this.listaEquipos = listaEquipos;
	}

	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		celda.setBackgroundColor(Color.black);
		celda.setPadding(5);
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.white);

		celda.setPhrase(new Phrase("ID", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Nombre", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Descripcion", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Estatus", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Email", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Imagen", fuente));
		tabla.addCell(celda);
	}

	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Equipo equipo : listaEquipos) {
			tabla.addCell(String.valueOf(equipo.getId()));
			tabla.addCell(equipo.getNombre());
			tabla.addCell(equipo.getDescripcion());
			tabla.addCell(String.valueOf(equipo.getEstatus()));
			tabla.addCell(equipo.getEmail());
			tabla.addCell(equipo.getImagen());
		}
	}

	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		documento.open();
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.white);
		fuente.setStyle(18);
		fuente.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Lista de Equipos", fuente);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		documento.add(p);

		PdfPTable tabla = new PdfPTable(6);
		tabla.setWidthPercentage(100f);
		tabla.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 3.0f });
		tabla.setSpacingBefore(10);
		tabla.setWidthPercentage(110);

		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);

		documento.add(tabla);

		documento.close();
	}
}
