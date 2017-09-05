package com.onetarget.onetargetdemo2.net;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onetarget.onetargetdemo2.mvp.QuickApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils implements AppConstants {


    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    public static <T> T createApi(Class<T> clazz) {
        if (retrofit == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofit == null) {
                    retrofit = getRetrofit();
                }
            }
        }
        return retrofit.create(clazz);
    }


    public static Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Gson gson = new GsonBuilder().create();
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.baseUrl(APP_BASE_URL);
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(getOkHttpClient());
        return builder.build();
    }

    private static Interceptor getRequestInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder newRequestBuilder = request.newBuilder();
                request = newRequestBuilder.build();
                loggerRequest(request);
                Response response = chain.proceed(request);
                response = loggerResponse(request, response);
                return response;
            }
        };
    }


    private static Response loggerResponse(Request request, Response response) {
        try {
            if (DEBUG) {
                StringBuilder sb = new StringBuilder();
                String url = request.toString();
                Long start = System.currentTimeMillis();
                sb.append("---------------------request--------------------------------")
                        .append("\nrequest-->url").append("|").append(url)
                        .append("\nrequest.headers").append(":{\n").append(request.headers()).append("\n}");
                Log.i("Retrofit", sb.toString());
                sb.delete(0, sb.length());
                sb.append("---------------------response--------------------------------")
                        .append("\nresponse-->url").append("|").append(url)
                        .append("\nresponse.body").append("|\n");

                Log.i("Retrofit", sb.toString());
                final int LOG_CHUNK_SIZE = 4000;
                ResponseBody oldResponseBody = response.body();
                String oldBodyString = oldResponseBody.string();
                for (int i = 0, len = oldBodyString.length(); i < len; i += LOG_CHUNK_SIZE) {
                    int end = Math.min(len, i + LOG_CHUNK_SIZE);
                    Log.i("Retrofit", oldBodyString.substring(i, end));
                }
                sb.delete(0, sb.length());
                sb.append("\nFly Time").append("|").append(System.currentTimeMillis() - start);
                Log.i("Retrofit", sb.toString());

                ResponseBody newResponseBody = ResponseBody.create(oldResponseBody.contentType(),
                        oldBodyString);
                Response.Builder newBuilderder = response.newBuilder().body(newResponseBody);
                return newBuilderder.build();
            }
        } catch (Exception e) {
            return response;
        }
        return response;
    }

    private static void loggerRequest(Request request) {
        if (DEBUG) {
            try {
                String url = request.toString();
                CacheBufferedSkin cache = new CacheBufferedSkin();
                request.body().writeTo(cache);
                String strBody = cache.bodyStr;
                Headers headers = request.headers();
                Log.i("Retrofit", "loggerRequest--- " + " \nurl:" + url + " \nheaders:" + headers + " \nbody:" + strBody);
            } catch (Exception e) {

            }
        }
    }


    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            Context context = QuickApplication.getInstance();
            File cacheDir = new File(context.getCacheDir(), RESPONSE_CACHE);
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(getRequestInterceptor())
                    .addInterceptor(new CacheInterceptor())
                    .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                    .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                    .cache(new Cache(cacheDir, RESPONSE_CACHE_SIZE))
                    .build();
        }
        return okHttpClient;
    }


    /**
     * 拦截器压缩http请求体，许多服务器无法解析
     */
    static class GzipRequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
                return chain.proceed(originalRequest);
            }

            Request compressedRequest = originalRequest.newBuilder()
                    .header("Content-Encoding", "gzip")
                    .method(originalRequest.method(), gzip(originalRequest.body()))
                    .build();
            return chain.proceed(compressedRequest);
        }

        private RequestBody gzip(final RequestBody body) {
            return new RequestBody() {
                @Override
                public MediaType contentType() {
                    return body.contentType();
                }

                @Override
                public long contentLength() {
                    return -1; // 无法知道压缩后的数据大小
                }

                @Override
                public void writeTo(BufferedSink sink) throws IOException {
                    BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                    body.writeTo(gzipSink);
                    gzipSink.close();
                }
            };
        }
    }

}
