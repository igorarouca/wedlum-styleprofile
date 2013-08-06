package com.wedlum.styleprofile.domain.photo;

import java.io.File;
import java.io.IOException;

import javax.inject.Named;

import org.apache.commons.io.FileUtils;

import com.wedlum.styleprofile.util.observer.GenericSubject;
import com.wedlum.styleprofile.util.observer.Observer;

@Named(value = "photoSource")
public class PhotoSourceUsingFileSystem implements PhotoSource {

    public static File STORAGE = new File("photo-storage");

    private GenericSubject<String> delegate = new GenericSubject<String>();

    public void addPhoto(File photo) {
        String photoId = store(photo);
    	delegate.setChanged();
		delegate.notifyObservers(photoId);
	}

    @Override
    public String[] getPhotos() {
    	return null;
    }

    public void addObserver(Observer<String> observer) {
        delegate.registerObserver(observer);
        updateObserverOnPastEvents(observer);
    }

    public String getMetadata(String photoId) {
        try {
            File file = getMetadataFile(photoId);
            if (!file.exists()) return "";
            return FileUtils.readFileToString(file);
        } catch (IOException e) {
            throw new IllegalStateException("Error reading metadata for " + photoId, e);
        }
    }

    public void setMetadata(String photoId, String metadata) {
        try {
            FileUtils.writeStringToFile(getMetadataFile(photoId), metadata);
        } catch (IOException e) {
            throw new IllegalStateException("Error storing metadata for " + photoId, e);
        }

        delegate.setChanged();
        delegate.notifyObservers(photoId);
    }

    @Override
    public void delete(String id) {
        getMetadataFile(id).delete();
        new File(STORAGE, id).delete();
        delegate.notifyObserversRemove(id);
    }

    private File getMetadataFile(String id) {
        return new File(STORAGE, id + ".metadata");
    }

    private String store(File photo) {
        File storedPhoto = new File(STORAGE, photo.getName());
        try {
            FileUtils.copyFile(photo, storedPhoto);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return storedPhoto.getName();
    }

    private void updateObserverOnPastEvents(Observer<String> observer) {
        if(!STORAGE.exists()) STORAGE.mkdir();

    	for(File file: STORAGE.listFiles()){
            if (isImageFile(file))
                observer.update(file.getName());
        }

    }

	private static boolean isImageFile(File file) {
		String fileName = file.getName().toLowerCase();

		for (String validImageFileFormat : VALID_IMAGE_FILE_FORMATS)
			if (fileName.endsWith(validImageFileFormat))
				return true;

		return false;
	}

}
