package org.samples.docxconverters.jodconverter.html;

import java.io.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

public class FormattingTestsToHTML {

	public static void main(String[] args) {

		// 1) Start LibreOffice in headless mode.
		OfficeManager officeManager = null;
		try {
			officeManager = new DefaultOfficeManagerConfiguration()
					.setOfficeHome(new File(Params.officeHome))
					.buildOfficeManager();
			officeManager.start();

			// 2) Create JODConverter converter
			OfficeDocumentConverter converter = new OfficeDocumentConverter(
					officeManager);

			// 3) Create HTML
			createHTML(converter);
			createHTML(converter);

		} finally {
			// 4) Stop LibreOffice in headless mode.
			if (officeManager != null) {
				officeManager.stop();
			}
		}
	}

	private static void createHTML(OfficeDocumentConverter converter) {
		try {
			long start = System.currentTimeMillis();
			converter.convert(new File("docx/FormattingTests.docx"), new File(
					"html/FormattingTests.html"));
			System.err.println("Generate html/FormattingTests.html with "
					+ (System.currentTimeMillis() - start) + "ms");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
