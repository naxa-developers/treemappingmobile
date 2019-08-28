package org.light.collect.treemappingmobile.http.mock;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.light.collect.treemappingmobile.http.HttpCredentialsInterface;
import org.light.collect.treemappingmobile.http.HttpGetResult;

import java.net.URI;

public class MockHttpClientConnectionError extends MockHttpClientConnection {

    @Override
    @NonNull
    public HttpGetResult get(@NonNull URI uri, @Nullable String contentType, @Nullable HttpCredentialsInterface credentials) throws Exception {
        return null;
    }
}
