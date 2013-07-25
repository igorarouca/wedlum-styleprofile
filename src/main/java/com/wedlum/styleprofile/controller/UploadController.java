package com.wedlum.styleprofile.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedlum.styleprofile.domain.photo.PhotoSource;

@Controller
@RequestMapping(value = "upload")
public class UploadController {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_TMP_DIR = "/tmp";

    @Inject private PhotoSource source;

	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request) throws FileNotFoundException, IOException {
		List<File> receivedFiles = receiveFiles(request);
        for(File photo : receivedFiles)
            source.addPhoto(photo);
        return "Success";
	}

	private List<File> receiveFiles(HttpServletRequest req) throws FileNotFoundException, IOException {
		List<File> receivedFiles = new ArrayList<File>();
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		File repository = new File(System.getProperty("java.io.tmpdir"));
		factory.setRepository(repository);
		
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(req);

			// Process the uploaded items
            for (FileItem item : items) {
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