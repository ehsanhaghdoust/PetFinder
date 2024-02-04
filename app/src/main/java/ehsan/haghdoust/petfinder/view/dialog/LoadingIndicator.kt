package ehsan.haghdoust.petfinder.view.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ehsan.haghdoust.petfinder.R

class LoadingIndicator: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?) : Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.alert_loading_indicator, null))
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}