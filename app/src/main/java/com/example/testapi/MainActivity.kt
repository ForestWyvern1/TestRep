package com.example.testapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapi.Case.userToken
import com.example.testapi.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rv_view.view.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var initials = ""

        rv_view_for_api.layoutManager = LinearLayoutManager(this)

        open_close_profile.setOnClickListener {
            if (initial.text != "X"){
                rv_view_for_api.visibility = View.GONE
                profile.visibility = View.VISIBLE
                initial.text = "X"
            }
            else{
                rv_view_for_api.visibility = View.VISIBLE
                profile.visibility = View.GONE
                initial.text = initials
            }
        }

        logout.setOnClickListener {
            App.dm.logout()
            startActivity(Intent(this, SignActivity::class.java))
        }



        val disq = App.dm.apisign
            .getProfile(userToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({m->
                initials = m.content.nickName.toCharArray()[0].toString() + m.content.nickName.toCharArray()[1].toString()
                initial.text = initials
                profile_nickname.text = m.content.nickName
                profile_login.text = m.content.email
            },{
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            })



        val disp = App.dm.api
            .getPopular()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ m ->
                val disq = App.dm.api
                .getGenre()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({n ->
                    var genre_list = mutableListOf<Genre>()
                    for (i in m.results) {
                        for (j in n.genres){
                            if (i.genre_ids.contains(j.id)){
                                genre_list.add(j)
                            }
                        }
                    }
                    progressBar_main.visibility = View.GONE
                    rv_view_for_api.adapter = RvAdapter(genre_list.distinct(), this, applicationContext)
                }, {
                    Toast.makeText(this, "ВОВА ЛОХ", Toast.LENGTH_SHORT).show() //onError
                }, {

                })
            }, {
                Toast.makeText(this, "ВОВА ЛОХ", Toast.LENGTH_SHORT).show() //onError
            })
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}