package com.pratik.happyscore.mainFragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.pratik.happyscore.R
import java.util.*

class UserCard : AppCompatActivity() {

    lateinit var nameview: TextView
    lateinit var uid:String
    lateinit var qrcodeview:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_card)
        hook()

        nameview.text = intent.getStringExtra("name").toString()
        uid = intent.getStringExtra("uid").toString()
        qrcodeview.setImageBitmap(generateQrCode("www.happyscore.com/$uid"))

    }

    private fun hook() {

        nameview = findViewById(R.id.namePreview1)
        qrcodeview = findViewById(R.id.Userqrcode)

    }

    fun sharelink(view: View) {
        val emailIntent = Intent(Intent.ACTION_SEND)

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, ""+intent.getStringExtra("name").toString())
        val ss = Html.fromHtml("Find my User Details through this link, https://www.happyscore.com/$uid")
        emailIntent.putExtra(Intent.EXTRA_TEXT,ss.toString() )
        emailIntent.type = "text/plain";
        startActivity(Intent.createChooser(emailIntent, "Send to friend"))


    }
    @Throws(WriterException::class)
    fun generateQrCode(value: String): Bitmap {
        val hintMap = Hashtable<EncodeHintType, ErrorCorrectionLevel>()
        hintMap[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H

        val qrCodeWriter = QRCodeWriter()
        val size = 512
        val bitMatrix = qrCodeWriter.encode(value, BarcodeFormat.QR_CODE, size, size)
        val width = bitMatrix.width
        val bmp = Bitmap.createBitmap(width, width, Bitmap.Config.RGB_565)
        for (x in 0 until width)
            for (y in 0 until width)
                bmp.setPixel(y, x, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)

        return bmp
    }
}