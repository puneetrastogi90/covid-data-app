package com.covid.covidapp.utils


import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Utils {


    companion object {

        fun getReadableDateString(dateString: String?): String {
            if (dateString == null)
                return ""


            val formatter = SimpleDateFormat(Constants.DATE_DEFAULT_FORMAT)
            val dateObj = formatter.parse(dateString)
            val requireDateFormat = SimpleDateFormat(Constants.READABLE_DATE_FORMAT)
            return requireDateFormat.format(dateObj)


        }


    }

}