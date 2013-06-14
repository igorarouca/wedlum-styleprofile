package com.wedlum.styleprofile.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

public class UploadController extends HttpServlet {

	private static final String UPLOAD_TMP_DIR = "/tmp";
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<File> receivedFiles = receiveFiles(req);
		// TODO: Add PhotoGallery Call
		throw new IllegalStateException("Use PhotoGallery here");
	}

	private List<File> receiveFiles(HttpServletRequest req) throws FileNotFoundException, IOException {
		List<File> receivedFiles = new ArrayList<File>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(req);

			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				File file = new File(UPLOAD_TMP_DIR, item.getName());
				receivedFiles.add(file);
				FileOutputStream out = new FileOutputStream(file);
				InputStream in = item.getInputStream();

				IOUtils.copy(in, out);

				IOUtils.closeQuietly(in);
				IOUtils.closeQuietly(out);
			}

		} catch (FileUploadException fue) {
			throw new IllegalStateException(fue);
		}
		return receivedFiles;
	}


}