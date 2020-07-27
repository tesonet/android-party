package lt.havefun.tesonetfunparty.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import lt.havefun.tesonetfunparty.R
import lt.havefun.tesonetfunparty.events.LogoutEvent
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class ToolbarView(context: Context, attrs: AttributeSet) : Toolbar(context, attrs) {

    @Inject
    lateinit var eventBus: EventBus

    override fun onFinishInflate() {
        super.onFinishInflate()
        val logoutBtn = findViewById<ImageView>(R.id.logout_iv)
        logoutBtn.setOnClickListener{
            eventBus.post(LogoutEvent())
        }
    }
}