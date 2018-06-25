package com.weatherapp.ui.homeScreen

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.a2a.android.hbtf.data.network.model.LocationObject
import com.a2a.android.hbtf.ui.base.BaseActivity
import com.a2a.android.hbtf.ui.login.HomeScreenPresenter
import com.kucherenko.RxValidationTextInputLayout
import com.weatherapp.R
import com.weatherapp.data.model.LocationKey
import com.weatherapp.data.model.TempObject
import com.weatherapp.ui.LocationSelect
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.widget.TextView


class HomeScreen : BaseActivity(), HomeScreenMvpView, View.OnClickListener {


    lateinit var mPresenter: HomeScreenPresenter<HomeScreenMvpView>
    var regionObject: LocationObject? = null
    var countryObject: LocationObject? = null
    var areaObject: LocationObject? = null
    var locationKey: LocationKey? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUp()

    }


    override fun setUp() {
        mPresenter = HomeScreenPresenter()
        mPresenter!!.onAttach(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.txtSelectRegion -> mPresenter.getRegion()
            R.id.txtSelectCountry -> {
                if (regionObject == null)
                    onError(R.string.alert_select_region)
                else
                    mPresenter.getCountry(regionObject!!.ID)
            }

            R.id.txtSelectArea -> {
                if (countryObject == null)
                    onError(R.string.alert_select_country)
                else
                    mPresenter.getArea(countryObject!!.ID)
            }
            R.id.btnFindTemp -> {
                if (areaObject == null)
                    onError(R.string.alert_select_area)
                else if (locationKey == null)
                    onError(R.string.no_temp_found)
                else
                    mPresenter.getTemp(locationKey!!.Key)
            }
        }
    }

    override fun showRegionList(locationList: ArrayList<LocationObject>) {
        var intent = Intent(this, LocationSelect::class.java)
        intent.putParcelableArrayListExtra("list", locationList)
        intent.putExtra("type", LocationSelect.TypeObject.Region.name)
        startActivityForResult(intent, 0)

    }

    override fun showCountry(locationList: ArrayList<LocationObject>) {
        var intent = Intent(this, LocationSelect::class.java)
        intent.putParcelableArrayListExtra("list", locationList)
        intent.putExtra("type", LocationSelect.TypeObject.Country.name)
        startActivityForResult(intent, LocationSelect.TypeObject.Country.ordinal)
    }

    override fun showArea(locationList: ArrayList<LocationObject>) {
        var intent = Intent(this, LocationSelect::class.java)
        intent.putParcelableArrayListExtra("list", locationList)
        intent.putExtra("type", LocationSelect.TypeObject.Area.name)
        startActivityForResult(intent, LocationSelect.TypeObject.Area.ordinal)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {

                LocationSelect.TypeObject.Region.ordinal -> {
                    regionObject = data!!.getParcelableExtra("object")
                    txtSelectRegion.setText(regionObject!!.EnglishName)
                }
                LocationSelect.TypeObject.Country.ordinal -> {
                    countryObject = data!!.getParcelableExtra("object")
                    txtSelectCountry.setText(countryObject!!.EnglishName)

                }
                LocationSelect.TypeObject.Area.ordinal -> {
                    areaObject = data!!.getParcelableExtra("object")
                    txtSelectArea.setText(areaObject!!.EnglishName)
                    mPresenter.getLocationKey(areaObject!!.EnglishName, countryObject!!.ID)

                }
            }
        }
    }

    override fun successGetLocationKey(locationKey: LocationKey) {
        this.locationKey = locationKey
    }

    override fun noTempFound() {
        onError(getString(R.string.no_temp_found))
    }

    override fun showTemp(tempObject: TempObject) {
        var tempValue = tempObject.Temperature.Value.toString() + " " + tempObject.Temperature.Unit
        val snackbar = Snackbar.make(findViewById(android.R.id.content),
                tempValue, Snackbar.LENGTH_LONG)
        snackbar.setAction(R.string.hourly_forcast, View.OnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(tempObject.MobileLink))
            startActivity(browserIntent)
        })
        val sbView = snackbar.view
        val textView = sbView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))

        snackbar.show()
    }


}
