package com.wedlum.styleprofile.domain.photo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.commons.io.FileUtils;

import com.wedlum.styleprofile.util.observer.GenericSubject;
import com.wedlum.styleprofile.util.observer.Observer;

@Named(value = "photoSource")
public class PhotoSourceUsingFileSystem implements PhotoSource {

    public static File STORAGE = new File("photo-storage");
    private GenericSubject<String> delegate = new GenericSubject<String>();
    private Map<String, String> metadataByPhotoId = new LinkedHashMap<String, String>();

    public void addPhoto(File photo) {
        String photoId = store(photo);
        updated(photoId);
    }

	@Override
	public List<Photo> getPhotos() {
		List<Photo> result = new ArrayList<Photo>();
		for (String photoId : metadataByPhotoId.keySet())
			result.add(getPhoto(photoId));

		return result;
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

    public void setMetadata(String photoId, ColorSwatchMetadata metadata) {
        try {
            FileUtils.writeStringToFile(getMetadataFile(photoId), metadata.toString());
        } catch (IOException e) {
            throw new IllegalStateException("Error storing metadata for " + photoId, e);
        }

        updated(photoId, metadata.toString());
    }

    @Override
    public void delete(String id) {
        getMetadataFile(id).delete();
        new File(STORAGE, id).delete();
        delegate.notifyObserversRemove(id);
        metadataByPhotoId.remove(id);
    }

    @Override
    public Photo getPhoto(String id) {
        String metadata = getMetadata(id);
        if (metadata == null)
            return new Photo(id, "");

        return new Photo(id, metadata);
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
            if (isImageFile(file)) {
                String photoId = file.getName();
				updated(photoId);
                String metadataSource = getMetadata(photoId);
                if (metadataSource.isEmpty())
                    continue;
                setMetadata(photoId, ColorSwatchMetadata.fromJson(metadataSource));
            }
        }
    }

	private static boolean isImageFile(File file) {
		String fileName = file.getName().toLowerCase();

		for (String validImageFileFormat : VALID_IMAGE_FILE_FORMATS)
			if (fileName.endsWith(validImageFileFormat))
				return true;

		return false;
	}

    private void updated(String photoId) {
        updated(photoId, null);
    }

    private void updated(String photoId,String metadata){
        delegate.setChanged();
        delegate.notifyObservers(photoId);
        if (metadata != null)
            metadataByPhotoId.put(photoId, metadata);
    }

}
