package edu.neu.mad_sea.xinyizhu.TodoApp.utils;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

/**
 * The type Time picker fragment.
 */
public class TimePickerFragment extends DialogFragment {

  /**
   * On create dialog dialog.
   *
   * @param savedInstanceState the saved instance state
   * @return the dialog
   */
  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(),
        hour, minute,
        android.text.format.DateFormat.is24HourFormat(getActivity()));
  }
}
