package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.util.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Deportista;

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

public class DeportistaExporterPDF {
	private List<Deportista> listaDeportistas;

	public DeportistaExporterPDF(List<Deportista> listaDeportistas) {
		super();
		this.listaDeportistas = listaDeportistas;
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
		celda.setPhrase(new Phrase("fechaNacimiento", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("estatura", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("email", fuente));
		tabla.addCell(celda);

	}

	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Deportista deportista : listaDeportistas) {
			tabla.addCell(String.valueOf(deportista.getId()));
			tabla.addCell(deportista.getNombre());
			tabla.addCell(String.valueOf(deportista.getFechaNacimiento()));
			tabla.addCell(String.valueOf(deportista.getEstatura()));
			tabla.addCell(deportista.getEmail());

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

		Paragraph p = new Paragraph("Lista de Deportistas", fuente);
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