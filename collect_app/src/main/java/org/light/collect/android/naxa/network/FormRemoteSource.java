package org.light.collect.android.naxa.network;

import org.light.collect.android.dao.InstancesDao;
import org.light.collect.android.naxa.formloader.FileUtils;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FormRemoteSource {
    private static FormRemoteSource INSTANCE;
    private InstancesDao instancesDao;


    public static FormRemoteSource getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new FormRemoteSource();
        }
        return INSTANCE;
    }

    public Observable<UploadResponse> uploadOneSubmission(String json, String photo) {
        RequestBody requestBody;
        MultipartBody.Part body = null;

        File file = FileUtils.getFileByPath(photo);

        if (FileUtils.isFileExists(file)) {
            requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            body = MultipartBody.Part.createFormData("photo", file.getName(), requestBody);
        }

        RequestBody data = RequestBody.create(MediaType.parse("text/plain"), json);

        return ServiceGenerator.getRxClient()
                .create(ApiInterface.class)
                .uploadForm(APIEndpoint.UPLOAD_FORM, body, data);


    }
}
