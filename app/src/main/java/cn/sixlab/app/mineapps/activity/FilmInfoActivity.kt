package cn.sixlab.app.mineapps.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import cn.sixlab.app.mineapps.R
import cn.sixlab.app.mineapps.util.HttpUtil
import kotlinx.android.synthetic.main.activity_film_info.*
import kotlinx.android.synthetic.main.content_film_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class FilmInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_info)

        val extra = intent.getStringExtra("filmInfo")
        if(null==extra){
            toolbar.setTitle(R.string.apps_film_add)
        }else{
            toolbar.setTitle(R.string.apps_film_modify)
        }

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val date = Date()
        val text = SimpleDateFormat("yyyy-MM-dd").format(date)
        text_produce_year.setText(text.split("-")[0])
        text_view_date.setText(text)
    }

    fun submitFilm(v: View?){
        val map:HashMap<String, Any> = HashMap()

        val filmName = text_film_name.text
        if(TextUtils.isEmpty(filmName)){
            text_film_name.error = getString(R.string.error_field_required)
            text_film_name.requestFocus()
            return
        }
        map.put("movieName", filmName.toString())

        val produceYear = text_produce_year.text
        if(!TextUtils.isEmpty(produceYear)){
            map.put("produceYear", produceYear.toString())
        }

        val director = text_director.text
        if(!TextUtils.isEmpty(director)){
            map.put("director", director.toString())
        }

        val viewDate = text_view_date.text
        if(TextUtils.isEmpty(viewDate)){
            text_view_date.error = getString(R.string.error_field_required)
            text_view_date.requestFocus()
            return
        }else{
            val sdf = SimpleDateFormat("yyyy-MM-dd")

            map.put("viewDate", sdf.parse(viewDate.toString()))
        }

        val cinema = text_cinema.text
        if(!TextUtils.isEmpty(cinema)){
            map.put("cinema", cinema.toString())
        }

        val doubanScore = text_douban_score.text
        if(!TextUtils.isEmpty(doubanScore)){
            map.put("doubanScore", doubanScore.toString().toDouble())
//            map.put("doubanKey", mUsername)
        }

        val remark = text_remark.text
        if(!TextUtils.isEmpty(remark)){
            map.put("remark", remark.toString())
        }

        val body = HttpUtil.buildData(map)

        val route = HttpUtil.buildRoute(this)
        val call = route.addFilm(body)

        call.enqueue(object : Callback<Map<Any, Any>> {
            override fun onResponse(call: Call<Map<Any, Any>>?, response: Response<Map<Any, Any>>?) {
                submitSuccess(response)
            }

            override fun onFailure(call: Call<Map<Any, Any>>?, t: Throwable?) {
                submitFail(t)
            }
        })
    }

    private fun submitFail(t: Throwable?) {
        if(t?.message != null){
            toast(t.message!!)
        }
    }

    private fun submitSuccess(response: Response<Map<Any, Any>>?) {
        val intent = Intent(this, FilmActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toast( msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}
