package com.meddemo3.tools

import android.app.Activity
import android.app.Dialog
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.TextView
import com.meddemo3.R
import com.meddemo3.common.MyConst
import com.meddemo3.utils.MyLogger
import javax.inject.Inject

class MyProgress @Inject constructor() {
    private var progressBar: ProgressBar? = null
    var progressMessage: String? = null
    var textViewProgress: TextView? = null
    var progressEnd: Int = 0
    var progressCount: Int = 0

    var dialog: Dialog? = null

    var countDownTimerProgress: CountDownTimer? = null
    var myMessageNow: String? = null


    fun showProgressRound(
        activity: Activity?,
        message: String,
        progressEnd1: Int,
        progressType: Int
    ) {
        myMessageNow = message
        initAlert()
        if (MyConst.DEBUG_ALERT_LOG) {
            MyLogger.e("MyProgress - showProgressRound end1=$progressEnd1 progressType=$progressType")
        }
        progressEnd = progressEnd1
        progressCount = 0

        try {
            dialog = Dialog(activity!!) // Context, this, etc.

            // set color
            var layoutId: Int = R.layout.dialog_round_cian
            dialog?.setContentView(layoutId)
            dialog?.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog?.setCanceledOnTouchOutside(false)
            dialog?.setCancelable(false)

            val textViewProgress = dialog?.findViewById(R.id.progress_round_message) as TextView
            val progressBar = dialog?.findViewById(R.id.progress_round_bar) as ProgressBar
            this.progressBar = progressBar
            progressBar.setSecondaryProgress(100)
            progressMessage = message
            textViewProgress.setText(message)

            dialog?.show()
            if (MyConst.DEBUG_ALERT_LOG) {
                MyLogger.e("MyProgress - showProgressRound dialog=" + dialog)
                MyLogger.e("MyProgress - showProgressRound this.dialog=" + this.dialog)
            }

            if (progressEnd1 > 0) {
                startCountDownTimerProgress() // showProgressRound
            }
        } catch (e: Exception) {
            MyLogger.e("MyProgress - showProgressRound message=$message error=$e")
        }
    }

    /**
     * Countdown timer to show payment progress bar
     */
    private fun startCountDownTimerProgress() {
        MyLogger.d("MyProgress - startCountDownTimerProgress");
        countDownTimerProgress = object : CountDownTimer(60000, 300) {
            override fun onTick(millisUntilFinished: Long) {
//                MyLogger.d("MyProgress - startCountDownTimerProgress progressCount=" + progressCount + " progressEnd=" + progressEnd);
                if (dialog != null) {
                    if (progressEnd <= 0) {
                        return
                    }
                    val progressToShow: Int = progressCount * 100 / (progressEnd * 3)
                    try {
//                        MyLogger.d("MyProgress - startCountDownTimerProgress tick progressToShow=" + progressToShow);
                        progressBar?.setProgress(progressToShow)
                        progressCount += 1
                    } catch (e: Exception) {
                        MyLogger.e("UpdateDatabaseThread - setProgress error:$e")
//                        cancelAlertMarked(0, "in countDownTimerProgress")
                    }
                }
            }

            override fun onFinish() {
                //add your code here
            }
        }.start()
    }

    /**
     * Cancel dialog alert window
     */
    fun initAlert() {
        progressBar = null
        textViewProgress = null
    }


    /**
     * Cancel dialog alert window
     */
    fun cancelAlert() {
        stopTimer()
        if (MyConst.DEBUG_ALERT_LOG) {
            MyLogger.v("MyProgress - cancelAlert")
        }

        progressBar = null
        textViewProgress = null

        try {
            dialog?.cancel()
            dialog = null
        } catch (e: Exception) {
            MyLogger.e("MyProgress - cancelAlert error:$e")
        }
    }

    fun stopTimer(){
        countDownTimerProgress?.cancel()
        countDownTimerProgress = null
    }


}