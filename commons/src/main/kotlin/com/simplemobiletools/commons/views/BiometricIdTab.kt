package com.simplemobiletools.commons.views

import android.content.Context
import android.util.AttributeSet
import androidx.biometric.auth.AuthPromptHost
import androidx.constraintlayout.widget.ConstraintLayout
import com.simplemobiletools.commons.extensions.showBiometricPrompt
import com.simplemobiletools.commons.extensions.updateTextColors
import com.simplemobiletools.commons.helpers.PROTECTION_FINGERPRINT
import com.simplemobiletools.commons.interfaces.HashListener
import com.simplemobiletools.commons.interfaces.SecurityTab
import kotlinx.android.synthetic.main.tab_biometric_id.view.*

class BiometricIdTab(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs), SecurityTab {
    private lateinit var hashListener: HashListener
    private lateinit var biometricPromptHost: AuthPromptHost

    override fun onFinishInflate() {
        super.onFinishInflate()
        context.updateTextColors(biometric_lock_holder)

        open_biometric_dialog.setOnClickListener {
            biometricPromptHost.activity?.showBiometricPrompt {
                hashListener.receivedHash("", PROTECTION_FINGERPRINT)
            }
        }
    }

    override fun initTab(requiredHash: String, listener: HashListener, scrollView: MyScrollView, biometricPromptHost: AuthPromptHost) {
        this.biometricPromptHost = biometricPromptHost
        hashListener = listener
    }

    override fun visibilityChanged(isVisible: Boolean) {}
}
