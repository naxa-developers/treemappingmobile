package org.light.collect.android.naxa.network;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface ApiInterface {


    @Multipart
    @POST
    Observable<UploadResponse> uploadForm(@Url String url,
                                  @Part MultipartBody.Part file,
                                  @Part("data") RequestBody name
    );


}
