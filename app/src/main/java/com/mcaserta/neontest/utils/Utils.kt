package com.mcaserta.neontest.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * Classe de utilidade para textos da aplicação
 */
class Utils {
    companion object {
        fun formatMoney(value: String, context: Context): String {

            // Remover caracteres especiais
            val re = Regex("[^0-9 ]")
            val formattedValue = re.replace(value, "")

            val df = DecimalFormat.getCurrencyInstance(Locale("pt", "BR"))
            return df.format(java.lang.Double.valueOf(formattedValue) / 100)
                .replace(df.currency.symbol, "").trim()
        }

        fun getImageByName(imgName: String, context: Context): Drawable? {
            return Drawable.createFromStream(context.assets.open("img/$imgName"), null)
        }
    }
}