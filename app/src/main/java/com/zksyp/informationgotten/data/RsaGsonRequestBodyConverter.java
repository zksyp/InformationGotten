package com.zksyp.informationgotten.data;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:34
 * Desc:
 */

final class RsaGsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    RsaGsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
//        if(value instanceof BaseRequest){
//            ((BaseRequest) value).sessionId= UserInfoHelper.getSessionId();
//            ((BaseRequest) value).ttid= PhoneUtil.getTTID();
//        }

        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.close();
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
//        if(value instanceof RsaReq){
//            ((RsaReq)value).mhcToken=null;
//            ((RsaReq)value).mhcParams=null;
//            adapter.write(jsonWriter, value);
//            jsonWriter.close();
//            String json = new String(buffer.readByteArray());
//            Buffer rsaBuffer = new Buffer();
//            Writer rsaWriter = new OutputStreamWriter(rsaBuffer.outputStream(), UTF_8);
//            JsonWriter rsaJsonWriter = gson.newJsonWriter(rsaWriter);
//            String aesKey = EncryptionUtil.getAesKey();
//            if(BuildConfig.DEBUG){
//                ((RsaReq)value).mhcToken=EncryptionUtil.getToken(aesKey);
//                ((RsaReq)value).mhcParams=EncryptionUtil.aesEncrypt(json,aesKey);
//                adapter.write(rsaJsonWriter, value);
//                rsaJsonWriter.close();
//                return RequestBody.create(MEDIA_TYPE, rsaBuffer.readByteString());
//            }else {
//                RsaReq req = new RsaReq();
//                req.mhcToken = EncryptionUtil.getToken(aesKey);
//                req.mhcParams = EncryptionUtil.aesEncrypt(json, aesKey);
//                TypeAdapter<RsaReq> rsaAdapter = gson.getAdapter(TypeToken.get(RsaReq.class));
//                rsaAdapter.write(rsaJsonWriter, req);
//                rsaJsonWriter.close();
//                return RequestBody.create(MEDIA_TYPE, rsaBuffer.readByteString());
//            }
//        }else {
//            ((BaseRequestN) value).sessionId= UserInfoHelper.getSessionId();
//            ((BaseRequestN) value).ttid= PhoneUtil.getTTID();
//            adapter.write(jsonWriter, value);
//            jsonWriter.close();
//            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
//        }
    }
}