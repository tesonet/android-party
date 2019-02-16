package lt.petraslabutis.testio.extensions

import android.os.Build
import android.widget.TextView
import androidx.appcompat.text.AllCapsTransformationMethod

fun TextView.allCaps(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        this.isAllCaps
    } else { //Reflection is evil :(
        transformationMethod?.javaClass?.simpleName?.equals(
            AllCapsTransformationMethod::class.java.simpleName,
            ignoreCase = true
        ) ?: false
    }
}