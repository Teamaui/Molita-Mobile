package com.molita.molita.model.api;

import com.molita.molita.model.data.AnakImunisasiModel;
import com.molita.molita.model.data.AnakModel;
import com.molita.molita.model.data.DataAnakModel;
import com.molita.molita.model.data.EdukasiModel;
import com.molita.molita.model.data.ImunisasiModel;
import com.molita.molita.model.data.JenisEdukasiModel;
import com.molita.molita.model.data.OrangTuaModel;
import com.molita.molita.model.data.PertumbuhanModel;
import com.molita.molita.model.data.PosyanduModel;
import com.molita.molita.model.data.ResponseData;
import com.molita.molita.model.data.ResponseListData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    // ------------------- AUTH

    // REGISTER ------------
    @FormUrlEncoded
    @POST("molita-api/register-orang-tua")
    Call<ResponseData> register(
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password
    );
    // LOGIN ------------
    @FormUrlEncoded
    @POST("molita-api/login-orang-tua")
    Call<ResponseListData<OrangTuaModel>> login(
            @Field("email") String email,
            @Field("password") String password
    );

    // EDUKASI ----------
    @GET("molita-api/get-edukasi")
    Call<ResponseListData<EdukasiModel>> getEdukasi();

    @FormUrlEncoded
    @POST("molita-api/update-like-user-edukasi")
    Call<ResponseListData> updateEdukasiPartial(
            @Field("like_user") String like_user,
            @Field("id_edukasi") String id_edukasi);


    @GET("molita-api/get-jenis-edukasi")
    Call<ResponseListData<JenisEdukasiModel>> getJenisEdukasi();

    @GET("molita-api/get-edukasi-slug")
    Call<ResponseListData<EdukasiModel>> getEdukasiBySlug(@Query("slug") String slug);

    // IMUNISASI -----------
    @FormUrlEncoded
    @POST("molita-api/get-anak-imunisasi")
    Call<ResponseListData<AnakImunisasiModel>> getAnakImunisasi(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("molita-api/get-imunisasi-by-anak")
    Call<ResponseListData<ImunisasiModel>> getAnakImunisasiById(
            @Field("id") String id
    );

    // POSYANDU -----------
    @FormUrlEncoded
    @POST("molita-api/get-anak-posyandu")
    Call<ResponseListData<PosyanduModel>> getAnakPosyandu(
            @Field("id") String id
    );

    // PROFILE -----------
    @FormUrlEncoded
    @POST("molita-api/get-orang-tua")
    Call<ResponseListData<OrangTuaModel>> getOrangTuaById(
            @Field("id") String id
    );

    // PERTUMBUHAN -------
    @GET("molita-api/get-pertumbuhan")
    Call<ResponseListData<PertumbuhanModel>> getPertumbuhan();

    @FormUrlEncoded
    @POST("molita-api/get-pertumbuhan")
    Call<ResponseListData<PertumbuhanModel>> getPertumbuhanById(
            @Field("id") String id
    );

    // GENERAL ----------
    @FormUrlEncoded
    @POST("molita-api/get-anak-orang-tua")
    Call<ResponseListData<AnakModel>> getAnakById(
            @Field("id") String id
    );

    // DASHBOARD ---------
    @FormUrlEncoded
    @POST("molita-api/get-all-data-anak-orang-tua")
    Call<ResponseListData<DataAnakModel>> getAllDataAnakById(
            @Field("id") String id
    );
}
