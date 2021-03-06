/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 *
 * License information see the LICENSE file in the project's root directory.
 *
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.app.mineapps.http

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface MineService {

    //    @GET("api/movie/search/{keyword}")
    //    SixlabMovies searchMovie(@Path("keyword") String keyword);
    //    SixlabMovies searchMovie(@Query("keyword") String keyword);
    //    Call<ResponseBody> get();

    //    @POST("api/movie/search")
    //    Call<ResponseBody> queryMovie(@Query("keyword")String keyword);

    // 登录
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("login")
    fun login(@Body body: RequestBody): Call<Map<Any, Any>>

    // 刷新登录
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("auth/login/refresh")
    fun refresh(): Call<Map<Any, Any>>

    // 添加电影
    @POST("auth/movie/film")
    fun addFilm(@Body body: RequestBody): Call<Map<Any, Any>>

    // 获取电影
    @GET("auth/movie/film/{id}")
    fun fetchFilm(@Path("id") id: Int): Call<Map<Any, Any>>

    // 修改电影
    @PUT("auth/movie/film/{id}")
    fun modifyFilm(@Body body: RequestBody,@Path("id") id: Int): Call<Map<Any, Any>>

    // 最近的电影
    @GET("auth/movie/film/recent/{num}")
    fun recentFilms(@Path("num") num: Int): Call<Map<Any, Any>>

    // 搜索电影
    @GET("auth/movie/film")
    fun searchFilms(@Query("keyword") keyword: String): Call<Map<Any, Any>>

    // 搜索未索引电影
    @GET("auth/movie/film/db")
    fun searchDb(): Call<Map<Any, Any>>

    // 搜索剧集
    @GET("auth/movie/show")
    fun searchShows(@Query("keyword") keyword: String,@Query("showStatus") showStatus: String): Call<Map<Any, Any>>

    // 修改剧集-season
    @PUT("auth/movie/show/{id}/season/{season}")
    fun updateSeason(@Path("id") id: Int,@Path("season") season: Int): Call<Map<Any, Any>>

    // 修改剧集-episode
    @PUT("auth/movie/show/{id}/episode/{episode}")
    fun updateEpisode(@Path("id") id: Int,@Path("episode") episode: Int): Call<Map<Any, Any>>

    // 修改剧集-status
    @PUT("auth/movie/show/{id}/viewStatus/{status}")
    fun updateStatus(@Path("id") id: Int,@Path("status") status: String): Call<Map<Any, Any>>

    // 添加剧集
    @POST("auth/movie/show")
    fun addShow(@Body body: RequestBody): Call<Map<Any, Any>>

    // 查询指定日期的任务
    @GET("auth/assignment/{year}/{month}/{day}")
    fun assignment(@Path("year") year: String,@Path("month") month: String,@Path("day") day: String): Call<Map<Any, Any>>

    // 更新指定任务状态
    @PUT("auth/assignment/finish/{assignmentId}/{status}")
    fun finish(@Path("assignmentId") assignmentId: Int,@Path("status") status: Boolean): Call<Map<Any, Any>>


    // 查询指定日期的任务
    @POST("auth/point/today")
    fun todayPoint(): Call<Map<Any, Any>>

    // 完成
    @POST("auth/point/exchange")
    fun exchange(@Query("point") point: Int,@Query("name") name: String): Call<Map<Any, Any>>

    // +
    @POST("auth/point/task/add")
    fun taskAdd(@Body body: RequestBody): Call<Map<Any, Any>>

    // 改
    @POST("auth/point/task/modify")
    fun taskModify(@Body body: RequestBody): Call<Map<Any, Any>>

    // 完成
    @POST("auth/point/task/finish")
    fun taskFinish(@Query("taskId") taskId: Int): Call<Map<Any, Any>>

}